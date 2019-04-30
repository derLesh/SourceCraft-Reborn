package minecraft;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

public class RegionFile
{
  private static final int VERSION_GZIP = 1;
  private static final int VERSION_DEFLATE = 2;
  private static final int SECTOR_BYTES = 4096;
  private static final int SECTOR_INTS = 1024;
  static final int CHUNK_HEADER_SIZE = 5;
  private static final byte[] emptySector = new byte['?'];
  private final File fileName;
  private RandomAccessFile file;
  private final int[] offsets;
  private final int[] chunkTimestamps;
  private ArrayList<Boolean> sectorFree;
  private int sizeDelta;
  private long lastModified = 0L;

  public RegionFile(File path)
  {
    this.offsets = new int['?'];
    this.chunkTimestamps = new int['?'];

    this.fileName = path;
    debugln("REGION LOAD " + this.fileName);

    this.sizeDelta = 0;
    try
    {
      if (path.exists()) {
        this.lastModified = path.lastModified();
      }
      this.file = new RandomAccessFile(path, "rw");
      if (this.file.length() < 4096L)
      {
        for (int i = 0; i < 1024; i++) {
          this.file.writeInt(0);
        }
        for (int i = 0; i < 1024; i++) {
          this.file.writeInt(0);
        }
        this.sizeDelta += 8192;
      }
      if ((this.file.length() & 0xFFF) != 0L) {
        for (int i = 0; i < (this.file.length() & 0xFFF); i++) {
          this.file.write(0);
        }
      }
      int nSectors = (int)this.file.length() / 4096;
      this.sectorFree = new ArrayList(nSectors);
      for (int i = 0; i < nSectors; i++) {
        this.sectorFree.add(Boolean.valueOf(true));
      }
      this.sectorFree.set(0, Boolean.valueOf(false));
      this.sectorFree.set(1, Boolean.valueOf(false));

      this.file.seek(0L);
      for (int i = 0; i < 1024; i++)
      {
        int offset = this.file.readInt();
        this.offsets[i] = offset;
        if ((offset != 0) && ((offset >> 8) + (offset & 0xFF) <= this.sectorFree.size())) {
          for (int sectorNum = 0; sectorNum < (offset & 0xFF); sectorNum++) {
            this.sectorFree.set((offset >> 8) + sectorNum, Boolean.valueOf(false));
          }
        }
      }
      for (int i = 0; i < 1024; i++)
      {
        int lastModValue = this.file.readInt();
        this.chunkTimestamps[i] = lastModValue;
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public long lastModified()
  {
    return this.lastModified;
  }

  public synchronized int getSizeDelta()
  {
    int ret = this.sizeDelta;
    this.sizeDelta = 0;
    return ret;
  }

  private void debug(String in) {}

  private void debugln(String in)
  {
    debug(in + "\n");
  }

  private void debug(String mode, int x, int z, String in)
  {
    debug("REGION " + mode + " " + this.fileName.getName() + "[" + x + "," + z + "] = " + in);
  }

  private void debug(String mode, int x, int z, int count, String in)
  {
    debug("REGION " + mode + " " + this.fileName.getName() + "[" + x + "," + z + "] " + count + "B = " + in);
  }

  private void debugln(String mode, int x, int z, String in)
  {
    debug(mode, x, z, in + "\n");
  }

  public synchronized DataInputStream getChunkDataInputStream(int x, int z)
  {
    if (outOfBounds(x, z))
    {
      debugln("READ", x, z, "out of bounds");
      return null;
    }
    try
    {
      int offset = getOffset(x, z);
      if (offset == 0) {
        return null;
      }
      int sectorNumber = offset >> 8;
      int numSectors = offset & 0xFF;
      if (sectorNumber + numSectors > this.sectorFree.size())
      {
        debugln("READ", x, z, "invalid sector");
        return null;
      }
      this.file.seek(sectorNumber * 4096);
      int length = this.file.readInt();
      if (length > 4096 * numSectors)
      {
        debugln("READ", x, z, "invalid length: " + length + " > 4096 * " + numSectors);
        return null;
      }
      byte version = this.file.readByte();
      if (version == 1)
      {
        byte[] data = new byte[length - 1];
        this.file.read(data);
        return new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(data)));
      }
      if (version == 2)
      {
        byte[] data = new byte[length - 1];
        this.file.read(data);
        return new DataInputStream(new InflaterInputStream(new ByteArrayInputStream(data)));
      }
      debugln("READ", x, z, "unknown version " + version);
      return null;
    }
    catch (IOException e)
    {
      debugln("READ", x, z, "exception");
    }
    return null;
  }

  public DataOutputStream getChunkDataOutputStream(int x, int z)
  {
    if (outOfBounds(x, z)) {
      return null;
    }
    return new DataOutputStream(new DeflaterOutputStream(new ChunkBuffer(x, z)));
  }

  class ChunkBuffer
          extends ByteArrayOutputStream
  {
    private int x;
    private int z;

    public ChunkBuffer(int x, int z)
    {
      super();
      this.x = x;
      this.z = z;
    }

    public void close()
    {
      RegionFile.this.write(this.x, this.z, this.buf, this.count);
    }
  }

  protected synchronized void write(int x, int z, byte[] data, int length)
  {
    try
    {
      int offset = getOffset(x, z);
      int sectorNumber = offset >> 8;
      int sectorsAllocated = offset & 0xFF;
      int sectorsNeeded = (length + 5) / 4096 + 1;
      if (sectorsNeeded >= 256) {
        return;
      }
      if ((sectorNumber != 0) && (sectorsAllocated == sectorsNeeded))
      {
        debug("SAVE", x, z, length, "rewrite");
        write(sectorNumber, data, length);
      }
      else
      {
        for (int i = 0; i < sectorsAllocated; i++) {
          this.sectorFree.set(sectorNumber + i, Boolean.valueOf(true));
        }
        int runStart = this.sectorFree.indexOf(Boolean.valueOf(true));
        int runLength = 0;
        if (runStart != -1) {
          for (int i = runStart; i < this.sectorFree.size(); i++)
          {
            if (runLength != 0)
            {
              if (((Boolean)this.sectorFree.get(i)).booleanValue()) {
                runLength++;
              } else {
                runLength = 0;
              }
            }
            else if (((Boolean)this.sectorFree.get(i)).booleanValue())
            {
              runStart = i;
              runLength = 1;
            }
            if (runLength >= sectorsNeeded) {
              break;
            }
          }
        }
        if (runLength >= sectorsNeeded)
        {
          debug("SAVE", x, z, length, "reuse");
          sectorNumber = runStart;
          setOffset(x, z, sectorNumber << 8 | sectorsNeeded);
          for (int i = 0; i < sectorsNeeded; i++) {
            this.sectorFree.set(sectorNumber + i, Boolean.valueOf(false));
          }
          write(sectorNumber, data, length);
        }
        else
        {
          debug("SAVE", x, z, length, "grow");
          this.file.seek(this.file.length());
          sectorNumber = this.sectorFree.size();
          for (int i = 0; i < sectorsNeeded; i++)
          {
            this.file.write(emptySector);
            this.sectorFree.add(Boolean.valueOf(false));
          }
          this.sizeDelta += 4096 * sectorsNeeded;

          write(sectorNumber, data, length);
          setOffset(x, z, sectorNumber << 8 | sectorsNeeded);
        }
      }
      setTimestamp(x, z, (int)(System.currentTimeMillis() / 1000L));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void write(int sectorNumber, byte[] data, int length)
          throws IOException
  {
    debugln(" " + sectorNumber);
    this.file.seek(sectorNumber * 4096);
    this.file.writeInt(length + 1);
    this.file.writeByte(2);
    this.file.write(data, 0, length);
  }

  private boolean outOfBounds(int x, int z)
  {
    return (x < 0) || (x >= 32) || (z < 0) || (z >= 32);
  }

  private int getOffset(int x, int z)
  {
    return this.offsets[(x + z * 32)];
  }

  public boolean hasChunk(int x, int z)
  {
    return getOffset(x, z) != 0;
  }

  private void setOffset(int x, int z, int offset)
          throws IOException
  {
    this.offsets[(x + z * 32)] = offset;
    this.file.seek((x + z * 32) * 4);
    this.file.writeInt(offset);
  }

  private void setTimestamp(int x, int z, int value)
          throws IOException
  {
    this.chunkTimestamps[(x + z * 32)] = value;
    this.file.seek(4096 + (x + z * 32) * 4);
    this.file.writeInt(value);
  }

  public void close()
          throws IOException
  {
    this.file.close();
  }
}

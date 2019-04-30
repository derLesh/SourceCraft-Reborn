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
  private static final byte[] emptySector = new byte['က'];
  
  private final File fileName;
  private RandomAccessFile file;
  private final int[] offsets;
  private final int[] chunkTimestamps;
  private ArrayList<Boolean> sectorFree;
  private int sizeDelta;
  private long lastModified = 0L;
  
  public RegionFile(File path) {
    offsets = new int['Ѐ'];
    chunkTimestamps = new int['Ѐ'];
    
    fileName = path;
    debugln("REGION LOAD " + fileName);
    
    sizeDelta = 0;
    try
    {
      if (path.exists()) {
        lastModified = path.lastModified();
      }
      
      file = new RandomAccessFile(path, "rw");
      
      if (file.length() < 4096L)
      {
        for (int i = 0; i < 1024; i++) {
          file.writeInt(0);
        }
        
        for (int i = 0; i < 1024; i++) {
          file.writeInt(0);
        }
        
        sizeDelta += 8192;
      }
      
      if ((file.length() & 0xFFF) != 0L)
      {
        for (int i = 0; i < (file.length() & 0xFFF); i++) {
          file.write(0);
        }
      }
      

      int nSectors = (int)file.length() / 4096;
      sectorFree = new ArrayList(nSectors);
      
      for (int i = 0; i < nSectors; i++) {
        sectorFree.add(Boolean.valueOf(true));
      }
      
      sectorFree.set(0, Boolean.valueOf(false));
      sectorFree.set(1, Boolean.valueOf(false));
      
      file.seek(0L);
      for (int i = 0; i < 1024; i++) {
        int offset = file.readInt();
        offsets[i] = offset;
        if ((offset != 0) && ((offset >> 8) + (offset & 0xFF) <= sectorFree.size())) {
          for (int sectorNum = 0; sectorNum < (offset & 0xFF); sectorNum++) {
            sectorFree.set((offset >> 8) + sectorNum, Boolean.valueOf(false));
          }
        }
      }
      for (int i = 0; i < 1024; i++) {
        int lastModValue = file.readInt();
        chunkTimestamps[i] = lastModValue;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public long lastModified()
  {
    return lastModified;
  }
  
  public synchronized int getSizeDelta()
  {
    int ret = sizeDelta;
    sizeDelta = 0;
    return ret;
  }
  

  private void debug(String in) {}
  

  private void debugln(String in)
  {
    debug(in + "\n");
  }
  
  private void debug(String mode, int x, int z, String in) {
    debug("REGION " + mode + " " + fileName.getName() + "[" + x + "," + z + "] = " + in);
  }
  
  private void debug(String mode, int x, int z, int count, String in) {
    debug("REGION " + mode + " " + fileName.getName() + "[" + x + "," + z + "] " + count + "B = " + in);
  }
  
  private void debugln(String mode, int x, int z, String in) {
    debug(mode, x, z, in + "\n");
  }
  



  public synchronized DataInputStream getChunkDataInputStream(int x, int z)
  {
    if (outOfBounds(x, z)) {
      debugln("READ", x, z, "out of bounds");
      return null;
    }
    try
    {
      int offset = getOffset(x, z);
      if (offset == 0)
      {
        return null;
      }
      
      int sectorNumber = offset >> 8;
      int numSectors = offset & 0xFF;
      
      if (sectorNumber + numSectors > sectorFree.size()) {
        debugln("READ", x, z, "invalid sector");
        return null;
      }
      
      file.seek(sectorNumber * 4096);
      int length = file.readInt();
      
      if (length > 4096 * numSectors) {
        debugln("READ", x, z, "invalid length: " + length + " > 4096 * " + numSectors);
        return null;
      }
      
      byte version = file.readByte();
      if (version == 1) {
        byte[] data = new byte[length - 1];
        file.read(data);
        return new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(data)));
      }
      
      if (version == 2) {
        byte[] data = new byte[length - 1];
        file.read(data);
        return new DataInputStream(new InflaterInputStream(new ByteArrayInputStream(data)));
      }
      


      debugln("READ", x, z, "unknown version " + version);
      return null;
    } catch (IOException e) {
      debugln("READ", x, z, "exception"); }
    return null;
  }
  
  public DataOutputStream getChunkDataOutputStream(int x, int z)
  {
    if (outOfBounds(x, z)) { return null;
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
    
    public void close() {
      write(x, z, buf, count);
    }
  }
  
  protected synchronized void write(int x, int z, byte[] data, int length)
  {
    try {
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
          sectorFree.set(sectorNumber + i, Boolean.valueOf(true));
        }
        

        int runStart = sectorFree.indexOf(Boolean.valueOf(true));
        int runLength = 0;
        if (runStart != -1) {
          for (int i = runStart; i < sectorFree.size(); i++) {
            if (runLength != 0) {
              if (((Boolean)sectorFree.get(i)).booleanValue()) runLength++; else
                runLength = 0;
            } else if (((Boolean)sectorFree.get(i)).booleanValue()) {
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
            sectorFree.set(sectorNumber + i, Boolean.valueOf(false));
          }
          write(sectorNumber, data, length);

        }
        else
        {

          debug("SAVE", x, z, length, "grow");
          file.seek(file.length());
          sectorNumber = sectorFree.size();
          for (int i = 0; i < sectorsNeeded; i++) {
            file.write(emptySector);
            sectorFree.add(Boolean.valueOf(false));
          }
          sizeDelta += 4096 * sectorsNeeded;
          
          write(sectorNumber, data, length);
          setOffset(x, z, sectorNumber << 8 | sectorsNeeded);
        }
      }
      setTimestamp(x, z, (int)(System.currentTimeMillis() / 1000L));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private void write(int sectorNumber, byte[] data, int length) throws IOException
  {
    debugln(" " + sectorNumber);
    file.seek(sectorNumber * 4096);
    file.writeInt(length + 1);
    file.writeByte(2);
    file.write(data, 0, length);
  }
  
  private boolean outOfBounds(int x, int z)
  {
    return (x < 0) || (x >= 32) || (z < 0) || (z >= 32);
  }
  
  private int getOffset(int x, int z) {
    return offsets[(x + z * 32)];
  }
  
  public boolean hasChunk(int x, int z) {
    return getOffset(x, z) != 0;
  }
  
  private void setOffset(int x, int z, int offset) throws IOException {
    offsets[(x + z * 32)] = offset;
    file.seek((x + z * 32) * 4);
    file.writeInt(offset);
  }
  
  private void setTimestamp(int x, int z, int value) throws IOException {
    chunkTimestamps[(x + z * 32)] = value;
    file.seek(4096 + (x + z * 32) * 4);
    file.writeInt(value);
  }
  
  public void close() throws IOException {
    file.close();
  }
}

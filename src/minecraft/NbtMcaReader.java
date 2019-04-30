package minecraft;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import minecraft.map.DefaultMinecraftMap;

public class NbtMcaReader
        extends NbtReader
{
  DefaultMinecraftMap map;
  int aOffset;
  int bOffset;

  public NbtMcaReader(DataInputStream newStream, DefaultMinecraftMap newMap, int aOffsetNew, int bOffsetNew)
  {
    super(newStream);
    this.map = newMap;
    this.aOffset = aOffsetNew;
    this.bOffset = bOffsetNew;
  }

  public void readChunkData()
  {
    try
    {
      if (this.s.readUnsignedByte() == 10)
      {
        String title = readTitle();
        if ((this.s.readUnsignedByte() == 10) &&
                (readTitle().equals("Level")))
        {
          int type = this.s.readUnsignedByte();
          while (type != 0)
          {
            title = readTitle();
            if (type == 10) {
              skipCompound();
            }
            if (type == 9) {
              if (title.equals("Sections")) {
                readSections();
              } else {
                skipList();
              }
            }
            if (type == 8) {
              skipString();
            }
            if (type == 7)
            {
              int length = nbtLength();
              readForLength(length);
            }
            if (type == 6) {
              for (int i = 0; i < 8; i++) {
                this.s.readByte();
              }
            }
            if (type == 5) {
              for (int i = 0; i < 4; i++) {
                this.s.readByte();
              }
            }
            if (type == 4) {
              for (int i = 0; i < 8; i++) {
                this.s.readByte();
              }
            }
            if (type == 3) {
              for (int i = 0; i < 4; i++) {
                this.s.readByte();
              }
            }
            if (type == 2)
            {
              this.s.readUnsignedByte();
              this.s.readUnsignedByte();
            }
            if (type == 1) {
              this.s.readByte();
            }
            if (type == 11) {
              skipIntArray();
            }
            type = this.s.readUnsignedByte();
          }
        }
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File missing");
    }
    catch (IOException e)
    {
      System.out.println("readChunkData-Exception:");
      System.out.println(e.toString());
    }
  }

  public void readSections()
  {
    try
    {
      int tagId = this.s.readByte();
      if (tagId == 10)
      {
        int listLength = nbtLength();
        for (int i = 0; i < listLength; i++)
        {
          McaSection section = readSection();
          this.map.addMcaSection(section);
        }
      }
      else
      {
        System.out.println("File corrupted: Sections-Tag is corrupted");
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File missing");
    }
    catch (IOException e)
    {
      System.out.println("skipList " + e.toString());
    }
  }

  public McaSection readSection()
  {
    McaSection section = new McaSection(this.aOffset, this.bOffset);
    int type = readByte();
    while (type != 0)
    {
      String title = readTitle();
      if (type == 1)
      {
        int height = readByte();

        section.setHeight(height);
      }
      else if (type == 7)
      {
        int length = nbtLength();
        if (title.equals("Blocks")) {
          section.readBlocks(this.s);
        } else if (title.equals("Data")) {
          section.readData(this.s);
        } else if (title.equals("SkyLight")) {
          readForLength(length);
        } else if (title.equals("BlockLight")) {
          readForLength(length);
        } else {
          readForLength(length);
        }
      }
      else
      {
        System.out.println("File corrupted: Sections-Tag is corrupted (2)");
      }
      type = readByte();
    }
    return section;
  }
}

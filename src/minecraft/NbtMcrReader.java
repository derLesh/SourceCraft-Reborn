package minecraft;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import minecraft.map.DefaultMinecraftMap;

public class NbtMcrReader
  extends NbtReader
{
  DefaultMinecraftMap map;
  int aOffset;
  int bOffset;
  
  public NbtMcrReader(DataInputStream newStream, DefaultMinecraftMap newMap, int aOffsetNew, int bOffsetNew)
  {
    super(newStream);
    map = newMap;
    aOffset = aOffsetNew;
    bOffset = bOffsetNew;
  }
  
  public void readChunkData()
  {
    try {
      if (s.readUnsignedByte() == 10) {
        String title = readTitle();
        if (s.readUnsignedByte() == 10)
        {
          if (readTitle().equals("Level"))
          {
            int type = s.readUnsignedByte();
            while (type != 0)
            {
              title = readTitle();
              
              if (type == 10) {
                skipCompound();
              }
              if (type == 9) {
                skipList();
              }
              if (type == 8) {
                skipString();
              }
              if (type == 7) {
                int length = nbtLength();
                if (title.equals("Blocks")) {
                  map.readBlocks(s, aOffset, bOffset);
                }
                if (title.equals("Data")) {
                  map.readData(s, aOffset, bOffset);
                }
                if (title.equals("SkyLight")) {
                  readForLength(length);
                }
                if (title.equals("BlockLight")) {
                  readForLength(length);
                }
                if (title.equals("HeightMap")) {
                  readForLength(length);
                }
              }
              if (type == 6) {
                for (int i = 0; i < 8; i++) {
                  s.readByte();
                }
              }
              if (type == 5) {
                for (int i = 0; i < 4; i++) {
                  s.readByte();
                }
              }
              if (type == 4) {
                for (int i = 0; i < 8; i++) {
                  s.readByte();
                }
              }
              if (type == 3) {
                for (int i = 0; i < 4; i++) {
                  s.readByte();
                }
              }
              if (type == 1)
              {
                s.readByte();
              }
              if (type == 11) {
                skipIntArray();
              }
              type = s.readUnsignedByte();
            }
          }
        }
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File missing");
    }
    catch (IOException e) {
      System.out.println("readChunkData-Exception:");
      System.out.println(e.toString());
    }
  }
}

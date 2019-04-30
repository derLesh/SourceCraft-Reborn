package minecraft;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class McaSection
{
  private int aOffset;
  private int bOffset;
  private int height;
  private int[][][] block;
  private byte[][][] data;
  
  public McaSection(int aOffset, int bOffset)
  {
    this.aOffset = aOffset;
    this.bOffset = bOffset;
  }
  
  public int getAOffset() {
    return aOffset;
  }
  
  public int getBOffset() {
    return bOffset;
  }
  
  public int[][][] getBlocks() {
    return block;
  }
  
  public byte[][][] getData() {
    return data;
  }
  
  public int getHeight() {
    return height;
  }
  
  public void readBlocks(DataInputStream s) {
    block = new int[16][16][16];
    for (int y = 0; y < 16; y++) {
      for (int z = 0; z < 16; z++) {
        for (int x = 0; x < 16; x++) {
          try {
            block[x][y][z] = s.readUnsignedByte();
          }
          catch (IOException ex) {
            Logger.getLogger(McaSection.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
    }
  }
  

  public void readData(DataInputStream s)
  {
    data = new byte[16][16][16];
    for (int y = 0; y < 16; y++) {
      for (int z = 0; z < 16; z++) {
        for (int x = 0; x < 16; x += 2) {
          try {
            int input = s.readUnsignedByte();
            int first = input >> 4;
            int second = input % 16;
            data[x][y][z] = ((byte)second);
            data[(x + 1)][y][z] = ((byte)first);
          }
          catch (IOException ex) {
            Logger.getLogger(McaSection.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
    }
  }
  
  public void setHeight(int height) {
    this.height = height;
  }
}

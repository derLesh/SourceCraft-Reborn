package minecraft;

import java.io.PrintStream;
import java.util.Stack;











public class Chunk
{
  private final byte[][][] material;
  final byte[][][] data;
  private static final int chunkSizeX = 16;
  private static final int chunkSizeZ = 16;
  private static final int chunkSizeY = 126;
  byte[] map;
  Stack<Byte> s = new Stack();
  
  public Chunk(byte[][][] newMaterial, byte[][][] newData) {
    if ((!sizesFitChunk(newMaterial)) || (!sizesFitChunk(newData))) {
      System.err.println("WARNING: given byte[][][] don't have chunk-size!");
    }
    material = newMaterial;
    data = newData;
  }
  
  public byte[] getData()
  {
    s = new Stack();
    getDataRun();
    byte[] result = new byte[s.size()];
    for (int i = s.size() - 1; i >= 0; i--) {
      result[i] = ((Byte)s.pop()).byteValue();
    }
    return result;
  }
  
  private void getDataRun() {
    add(10);
  }
  

  private void writeTitle(String text)
  {
    add(0);
    add(text.length());
    byte[] textBytes = text.getBytes();
    for (int i = 0; i < textBytes.length; i++) {
      add(textBytes[i]);
    }
  }
  
  private void add(byte add) {
    s.push(Byte.valueOf(add));
  }
  
  private void add(int add) {
    s.push(Byte.valueOf((byte)add));
  }
  
  public static boolean sizesFitChunk(byte[][][] blockData) {
    if ((blockData.length == 16) && 
      (blockData[0].length == 126) && 
      (blockData[0][0].length == 16)) {
      return true;
    }
    

    return false;
  }
}

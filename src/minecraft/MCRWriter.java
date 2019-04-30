package minecraft;


public class MCRWriter
{
  private RegionFile rg;
  
  private Chunk[] chunk;
  private int[] localPosX;
  private int[] localPosZ;
  
  public MCRWriter(RegionFile newRg, Chunk[] newChunk, int[] newLocalPosX, int[] newLocalPosZ)
  {
    rg = newRg;
    chunk = newChunk;
    localPosX = newLocalPosX;
    localPosZ = newLocalPosZ;
  }
  
  public void write() {
    for (int i = 0; i <= maxValidIndex(); i++) {
      byte[] data = chunk[i].getData();
      rg.write(localPosX[i], localPosZ[i], data, data.length);
    }
  }
  
  private int maxValidIndex()
  {
    int l1 = chunk.length;
    int l2 = localPosX.length;
    int l3 = localPosZ.length;
    if ((l1 <= l2) && (l1 <= l3)) {
      return l1;
    }
    if ((l2 <= l1) && (l2 <= l3)) {
      return l2;
    }
    
    return l3;
  }
}

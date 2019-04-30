package minecraft;

public class MCRWriter
{
  private RegionFile rg;
  private Chunk[] chunk;
  private int[] localPosX;
  private int[] localPosZ;

  public MCRWriter(RegionFile newRg, Chunk[] newChunk, int[] newLocalPosX, int[] newLocalPosZ)
  {
    this.rg = newRg;
    this.chunk = newChunk;
    this.localPosX = newLocalPosX;
    this.localPosZ = newLocalPosZ;
  }

  public void write()
  {
    for (int i = 0; i <= maxValidIndex(); i++)
    {
      byte[] data = this.chunk[i].getData();
      this.rg.write(this.localPosX[i], this.localPosZ[i], data, data.length);
    }
  }

  private int maxValidIndex()
  {
    int l1 = this.chunk.length;
    int l2 = this.localPosX.length;
    int l3 = this.localPosZ.length;
    if ((l1 <= l2) && (l1 <= l3)) {
      return l1;
    }
    if ((l2 <= l1) && (l2 <= l3)) {
      return l2;
    }
    return l3;
  }
}

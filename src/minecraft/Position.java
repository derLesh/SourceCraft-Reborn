package minecraft;



public class Position
{
  private int x;
  

  private int y;
  
  private int z;
  

  public Position(int x, int y, int z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public int getZ() {
    return z;
  }
  
  public boolean equals(Object object)
  {
    if ((object instanceof Position)) {
      Position other = (Position)object;
      return (x == x) && (y == y) && (z == z);
    }
    return false;
  }
  

  public String toString()
  {
    return x + "," + y + "," + z;
  }
}

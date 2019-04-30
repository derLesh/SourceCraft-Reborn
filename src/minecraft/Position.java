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

  public int getX()
  {
    return this.x;
  }

  public int getY()
  {
    return this.y;
  }

  public int getZ()
  {
    return this.z;
  }

  public boolean equals(Object object)
  {
    if ((object instanceof Position))
    {
      Position other = (Position)object;
      return (this.x == other.x) && (this.y == other.y) && (this.z == other.z);
    }
    return false;
  }

  public String toString()
  {
    return this.x + "," + this.y + "," + this.z;
  }
}
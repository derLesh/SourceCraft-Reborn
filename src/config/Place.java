package config;

public class Place
{
  private String name;
  private String world;
  private int x;
  private int z;
  private int numX;
  private int numZ;
  private int yStart;
  private int yEnd;

  public Place(String name)
  {
    this.name = name;
  }

  public String getWorld()
  {
    return this.world;
  }

  public void setWorld(String world)
  {
    this.world = world;
  }

  public boolean setName(Config config, String newName)
  {
    if (config.getPlace(newName) == null)
    {
      this.name = newName;
      return true;
    }
    return false;
  }

  public String getName()
  {
    return this.name;
  }

  public int getNumX()
  {
    return this.numX * 16;
  }

  public int getNumZ()
  {
    return this.numZ * 16;
  }

  public int getX()
  {
    return this.x;
  }

  public int getZ()
  {
    return this.z;
  }

  public int getYEnd()
  {
    return this.yEnd;
  }

  public int getYStart()
  {
    return this.yStart;
  }

  public void setYEnd(int yEnd)
  {
    this.yEnd = yEnd;
  }

  public void setYStart(int yStart)
  {
    this.yStart = yStart;
  }

  public void setNumX(int numX)
  {
    this.numX = (numX / 16);
  }

  public void setNumZ(int numZ)
  {
    this.numZ = (numZ / 16);
  }

  public void setX(int x)
  {
    this.x = x;
  }

  public void setZ(int z)
  {
    this.z = z;
  }

  public boolean adjustValues()
  {
    boolean unadjusted = true;
    if (this.yStart > this.yEnd)
    {
      this.yStart = this.yEnd;
      this.yEnd = 126;
      unadjusted = false;
    }
    if (this.yStart < 1)
    {
      this.yStart = 1;
      unadjusted = false;
    }
    if (this.yEnd > 254)
    {
      this.yEnd = 254;
      unadjusted = false;
    }
    if (this.numX < 0) {
      this.numX = 1;
    }
    if (this.numZ < 0) {
      this.numZ = 1;
    }
    return unadjusted;
  }
}

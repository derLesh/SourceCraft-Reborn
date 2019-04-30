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
  
  public String getWorld() {
    return world;
  }
  
  public void setWorld(String world) {
    this.world = world;
  }
  
  public boolean setName(Config config, String newName) {
    if (config.getPlace(newName) == null) {
      name = newName;
      return true;
    }
    return false;
  }
  
  public String getName() {
    return name;
  }
  
  public int getNumX() {
    return numX * 16;
  }
  
  public int getNumZ() {
    return numZ * 16;
  }
  
  public int getX() {
    return x;
  }
  
  public int getZ() {
    return z;
  }
  
  public int getYEnd() {
    return yEnd;
  }
  
  public int getYStart() {
    return yStart;
  }
  
  public void setYEnd(int yEnd) {
    this.yEnd = yEnd;
  }
  
  public void setYStart(int yStart) {
    this.yStart = yStart;
  }
  
  public void setNumX(int numX) {
    this.numX = (numX / 16);
  }
  
  public void setNumZ(int numZ) {
    this.numZ = (numZ / 16);
  }
  
  public void setX(int x) {
    this.x = x;
  }
  
  public void setZ(int z) {
    this.z = z;
  }
  
  public boolean adjustValues() {
    boolean unadjusted = true;
    if (yStart > yEnd) {
      yStart = yEnd;
      yEnd = 126;
      unadjusted = false;
    }
    if (yStart < 1) {
      yStart = 1;
      unadjusted = false;
    }
    if (yEnd > 254) {
      yEnd = 254;
      unadjusted = false;
    }
    if (numX < 0) {
      numX = 1;
    }
    if (numZ < 0) {
      numZ = 1;
    }
    return unadjusted;
  }
}

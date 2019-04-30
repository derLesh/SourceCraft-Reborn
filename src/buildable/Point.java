package buildable;

public class Point implements Comparable {
  public static final Point DEFAULT = new Point();
  
  public double x;
  public double y;
  public double z;
  
  public double getX()
  {
    return x;
  }
  
  public double getY() {
    return y;
  }
  
  public double getZ() {
    return z;
  }
  
  public int getPosX() {
    return (int)x;
  }
  
  public int getPosY() {
    return (int)y;
  }
  
  public int getPosZ() {
    return (int)z;
  }
  
  public Point() {
    x = 0.0D;
    y = 0.0D;
    z = 0.0D;
  }
  
  public Point(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public Point(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public Point set(Point p) {
    x = p.getX();
    y = p.getY();
    z = p.getZ();
    return this;
  }
  
  public Point set(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
    return this;
  }
  






  public Point getOffsetCopy(double x, double y, double z)
  {
    return new Point(this.x + x, this.y + y, this.z + z);
  }
  
  public Point getOffsetCopy(int x, int y, int z) {
    return new Point(this.x + x, this.y + y, this.z + z);
  }
  
  public String getString()
  {
    String aus = new Double(getX()).toString() + " ";
    aus = aus + new Double(getY()).toString() + " ";
    aus = aus + new Double(getZ()).toString();
    return aus;
  }
  
  public String getPrint() {
    return x + " " + y + " " + z;
  }
  
  public Point scale(int factor)
  {
    x *= factor;
    y *= factor;
    z *= factor;
    return this;
  }
  
  public Point scaleCopy(int factor) {
    return new Point(x * factor, y * factor, z * factor);
  }
  
  public void moveX(double distance) {
    x += distance;
  }
  
  public void moveY(double distance) {
    y += distance;
  }
  
  public void moveZ(double distance) {
    z += distance;
  }
  
  public void move(int x, int y, int z) {
    this.x += x;
    this.y += y;
    this.z += z;
  }
  
  public Point moveInHammer(double xDistance, double yDistance, double zDistance) {
    moveX(xDistance);
    moveY(yDistance);
    moveZ(zDistance);
    return this;
  }
  
  public int getRoomSizeTo(Point target) {
    return (int)((x - x) * (y - y) * (z - z));
  }
  
  public Point copy() {
    return new Point(x, y, z);
  }
  
  public int compareTo(Object object)
  {
    if ((object instanceof Point)) {
      Point other = (Point)object;
      if ((x == x) && (y == y) && (z == z)) {
        return 0;
      }
      
      if (x < x) {
        return -1;
      }
      
      if (x == x) {
        if (y < y) {
          return -1;
        }
        
        if ((y == y) && (z < z)) {
          return -1;
        }
      }
      


      return 1;
    }
    return Integer.MAX_VALUE;
  }
  
  public boolean smaller(Point other) {
    return (x < x) && (y < y) && (z < z);
  }
  
  public boolean equals(Object object)
  {
    if ((object instanceof Point)) {
      Point other = (Point)object;
      return (x == x) && (y == y) && (z == z);
    }
    return false;
  }
}

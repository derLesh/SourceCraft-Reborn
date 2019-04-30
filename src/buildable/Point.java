package buildable;

public class Point implements Comparable
{
  public static final Point DEFAULT = new Point();
  public double x;
  public double y;
  public double z;

  public double getX()
  {
    return this.x;
  }

  public double getY()
  {
    return this.y;
  }

  public double getZ()
  {
    return this.z;
  }

  public int getPosX()
  {
    return (int)this.x;
  }

  public int getPosY()
  {
    return (int)this.y;
  }

  public int getPosZ()
  {
    return (int)this.z;
  }

  public Point()
  {
    this.x = 0.0D;
    this.y = 0.0D;
    this.z = 0.0D;
  }

  public Point(double x, double y, double z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Point(int x, int y, int z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Point set(Point p)
  {
    this.x = p.getX();
    this.y = p.getY();
    this.z = p.getZ();
    return this;
  }

  public Point set(int x, int y, int z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
    return this;
  }

  public Point getOffsetCopy(double x, double y, double z)
  {
    return new Point(this.x + x, this.y + y, this.z + z);
  }

  public Point getOffsetCopy(int x, int y, int z)
  {
    return new Point(this.x + x, this.y + y, this.z + z);
  }

  public String getString()
  {
    String aus = new Double(getX()).toString() + " ";
    aus = aus + new Double(getY()).toString() + " ";
    aus = aus + new Double(getZ()).toString();
    return aus;
  }

  public String getPrint()
  {
    return this.x + " " + this.y + " " + this.z;
  }

  public Point scale(int factor)
  {
    this.x *= factor;
    this.y *= factor;
    this.z *= factor;
    return this;
  }

  public Point scaleCopy(int factor)
  {
    return new Point(this.x * factor, this.y * factor, this.z * factor);
  }

  public void moveX(double distance)
  {
    this.x += distance;
  }

  public void moveY(double distance)
  {
    this.y += distance;
  }

  public void moveZ(double distance)
  {
    this.z += distance;
  }

  public void move(int x, int y, int z)
  {
    this.x += x;
    this.y += y;
    this.z += z;
  }

  public Point moveInHammer(double xDistance, double yDistance, double zDistance)
  {
    moveX(xDistance);
    moveY(yDistance);
    moveZ(zDistance);
    return this;
  }

  public int getRoomSizeTo(Point target)
  {
    return (int)((target.x - this.x) * (target.y - this.y) * (target.z - this.z));
  }

  public Point copy()
  {
    return new Point(this.x, this.y, this.z);
  }

  public int compareTo(Object object)
  {
    if ((object instanceof Point))
    {
      Point other = (Point)object;
      if ((this.x == other.x) && (this.y == other.y) && (this.z == other.z)) {
        return 0;
      }
      if (this.x < other.x) {
        return -1;
      }
      if (this.x == other.x)
      {
        if (this.y < other.y) {
          return -1;
        }
        if ((this.y == other.y) && (this.z < other.z)) {
          return -1;
        }
      }
      return 1;
    }
    return Integer.MAX_VALUE;
  }

  public boolean smaller(Point other)
  {
    return (this.x < other.x) && (this.y < other.y) && (this.z < other.z);
  }

  public boolean equals(Object object)
  {
    if ((object instanceof Point))
    {
      Point other = (Point)object;
      return (this.x == other.x) && (this.y == other.y) && (this.z == other.z);
    }
    return false;
  }
}

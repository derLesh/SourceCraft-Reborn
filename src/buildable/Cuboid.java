package buildable;

import basic.Console;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;

public class Cuboid
        extends EightPoint
{
  private static int lightmapscale = 16;
  private double textureScaleX = 1.0D;
  private double textureScaleY = 1.0D;
  private double textureScaleZ = 1.0D;

  public Cuboid(Cuboid other)
  {
    this.skin = other.skin;
    this.a = other.a;
    this.b = other.b;
    this.c = other.c;
    this.d = other.d;
    this.e = other.e;
    this.f = other.f;
    this.g = other.g;
    this.h = other.h;
    this.textureScaleX = other.textureScaleX;
    this.textureScaleY = other.textureScaleY;
    this.textureScaleZ = other.textureScaleZ;
  }

  public Cuboid(Point[] p, Skin skin)
  {
    this.skin = skin;
    if (p.length < 8)
    {
      if (p.length < 1) {
        Console.devWarning("Less than 8 Points given.");
      } else {
        Console.devWarning("Less than 8 Points given. Create a cuboid with the first given point.");
      }
    }
    else
    {
      this.a = p[0];
      this.b = p[1];
      this.c = p[2];
      this.d = p[3];
      this.e = p[4];
      this.f = p[5];
      this.g = p[6];
      this.h = p[7];
    }
  }

  public Cuboid(Point aPoint, Point gPoint, Skin skin)
  {
    this(aPoint, gPoint);
    setSkin(skin);
  }

  public Cuboid(Point aPoint, Point gPoint)
  {
    if (!aPoint.smaller(gPoint))
    {
      Console.warning("Attampted to create invalid Cuboid.");
      System.out.println(" from " + aPoint.getString() + " to " + gPoint.getString());
      gPoint = aPoint.getOffsetCopy(1, 1, 1);
      this.skin = new Skin("Attempted to creat invalid Cuboid", 0.25D);
    }
    double xOffset = gPoint.x - aPoint.x;
    double yOffset = gPoint.y - aPoint.y;
    double zOffset = gPoint.z - aPoint.z;
    this.a = aPoint;
    this.e = this.a.getOffsetCopy(0.0D, yOffset, 0.0D);
    this.d = this.a.getOffsetCopy(xOffset, 0.0D, 0.0D);
    this.h = this.a.getOffsetCopy(xOffset, yOffset, 0.0D);

    this.b = this.a.getOffsetCopy(0.0D, 0.0D, zOffset);
    this.f = this.e.getOffsetCopy(0.0D, 0.0D, zOffset);
    this.c = this.d.getOffsetCopy(0.0D, 0.0D, zOffset);
    this.g = this.h.getOffsetCopy(0.0D, 0.0D, zOffset);

    assert (this.g.equals(gPoint));
  }

  public Cuboid(Point fPoint, int xLength, int yLength, int zLength, int scale)
  {
    if ((xLength <= 0) && (yLength <= 0) && (zLength <= 0))
    {
      Console.warning("Attampted to create invalid Cuboid.");
      xLength = -xLength + 1;yLength = -yLength + 1;zLength = -zLength + 1;
      this.skin = new Skin("Attempted to creat invalid Cuboid", 0.25D);
    }
    this.f = fPoint;
    this.g = new Point(this.f.x + xLength, this.f.y, this.f.z);
    this.b = new Point(this.f.x, this.f.y - yLength, this.f.z);
    this.c = new Point(this.f.x + xLength, this.f.y - yLength, this.f.z);
    this.e = new Point(this.f.x, this.f.y, this.f.z - zLength);
    this.h = new Point(this.g.x, this.g.y, this.g.z - zLength);
    this.a = new Point(this.b.x, this.b.y, this.b.z - zLength);
    this.d = new Point(this.c.x, this.c.y, this.c.z - zLength);
    scale(scale);
  }

  public Cuboid(Point fPoint, int xLength, int yLength, int zLength, int scale, Skin newSkin)
  {
    if ((xLength <= 0) && (yLength <= 0) && (zLength <= 0))
    {
      Console.warning("Attampted to create invalid Cuboid!");
      xLength = -xLength + 1;yLength = -yLength + 1;zLength = -zLength + 1;
      this.skin = new Skin("Attempted to creat invalid Cuboid", 0.25D);
    }
    this.f = fPoint;
    this.g = new Point(this.f.x + xLength, this.f.y, this.f.z);
    this.b = new Point(this.f.x, this.f.y - yLength, this.f.z);
    this.c = new Point(this.f.x + xLength, this.f.y - yLength, this.f.z);
    this.e = new Point(this.f.x, this.f.y, this.f.z - zLength);
    this.h = new Point(this.g.x, this.g.y, this.g.z - zLength);
    this.a = new Point(this.b.x, this.b.y, this.b.z - zLength);
    this.d = new Point(this.c.x, this.c.y, this.c.z - zLength);
    scale(scale);
    this.skin = newSkin;
  }

  public void setTextureScale(double[] textureScale)
  {
    this.textureScaleX = textureScale[0];
    this.textureScaleY = textureScale[1];
    this.textureScaleZ = textureScale[2];
  }

  public void cut(Orientation cut)
  {
    double xLength = this.g.x - this.a.x;
    double yLength = this.g.y - this.a.y;
    if (cut != null) {
      switch (cut)
      {
        case NORTH:
          this.b.y += xLength;
          this.c.y += xLength;
          break;
        case SOUTH:
          this.f.y -= xLength;
          this.g.y -= xLength;
          break;
        case EAST:
          this.f.x += yLength;
          this.b.x += yLength;
          break;
        case WEST:
          this.c.x -= yLength;
          this.g.x -= yLength;
          break;
      }
    }
  }

  public void writeVmf(Counter counter, Writer w)throws IOException
  {
    if (this.a != null)
    {
      w.write("\tsolid\n\t{\n\t\t\"id\" \"" + counter.getBrushId() + "\"\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.b.getString() + ") (" + this.f.getString() + ") (" + this.g.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialTop + "\"\n                         \"uaxis\" \"[1 0 0 0] " + this.skin.scale * this.textureScaleX + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + this.skin.scale * this.textureScaleY + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.e.getString() + ") (" + this.a.getString() + ") (" + this.d.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialBottom + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale * this.textureScaleX + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + this.skin.scale * this.textureScaleY + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.a.getString() + ") (" + this.e.getString() + ") (" + this.f.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialLeft + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + this.skin.scale * this.textureScaleX + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale * this.textureScaleZ + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.h.getString() + ") (" + this.d.getString() + ") (" + this.c.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialRight + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + this.skin.scale * this.textureScaleX + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale * this.textureScaleZ + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.e.getString() + ") (" + this.h.getString() + ") (" + this.g.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialBack + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale * this.textureScaleY + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale * this.textureScaleZ + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.d.getString() + ") (" + this.a.getString() + ") (" + this.b.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialFront + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale * this.textureScaleY + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale * this.textureScaleZ + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n");
      w.write("\t\teditor\n\t\t{\n\t\t\t\"color\" \"0 215 172\"\n\t\t\t\"visgroupshown\" \"1\"\n\t\t\t\"visgroupautoshown\" \"1\"\n\t\t}\n\t}\n");
    }
    else
    {
      System.out.println("ERROR");
    }
  }
}

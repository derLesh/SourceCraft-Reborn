package buildable;

import basic.Console;
import java.io.Writer;

public class Cuboid extends EightPoint
{
  private static int lightmapscale = 16;
  private double textureScaleX = 1.0D;
  private double textureScaleY = 1.0D;
  private double textureScaleZ = 1.0D;
  
  public Cuboid(Cuboid other) {
    skin = skin;
    a = a;
    b = b;
    c = c;
    d = d;
    e = e;
    f = f;
    g = g;
    h = h;
    textureScaleX = textureScaleX;
    textureScaleY = textureScaleY;
    textureScaleZ = textureScaleZ;
  }
  
  public Cuboid(Point[] p, Skin skin) {
    this.skin = skin;
    if (p.length < 8) {
      if (p.length < 1) {
        Console.devWarning("Less than 8 Points given.");
      }
      else
      {
        Console.devWarning("Less than 8 Points given. Create a cuboid with the first given point.");
      }
    }
    else
    {
      a = p[0];
      b = p[1];
      c = p[2];
      d = p[3];
      e = p[4];
      f = p[5];
      g = p[6];
      h = p[7];
    }
  }
  
  public Cuboid(Point aPoint, Point gPoint, Skin skin) {
    this(aPoint, gPoint);
    setSkin(skin);
  }
  
  public Cuboid(Point aPoint, Point gPoint) {
    if (!aPoint.smaller(gPoint)) {
      Console.warning("Attampted to create invalid Cuboid.");
      System.out.println(" from " + aPoint.getString() + " to " + gPoint.getString());
      gPoint = aPoint.getOffsetCopy(1, 1, 1);
      skin = new Skin("Attempted to creat invalid Cuboid", 0.25D);
    }
    double xOffset = x - x;
    double yOffset = y - y;
    double zOffset = z - z;
    a = aPoint;
    e = a.getOffsetCopy(0.0D, yOffset, 0.0D);
    d = a.getOffsetCopy(xOffset, 0.0D, 0.0D);
    h = a.getOffsetCopy(xOffset, yOffset, 0.0D);
    
    b = a.getOffsetCopy(0.0D, 0.0D, zOffset);
    f = e.getOffsetCopy(0.0D, 0.0D, zOffset);
    c = d.getOffsetCopy(0.0D, 0.0D, zOffset);
    g = h.getOffsetCopy(0.0D, 0.0D, zOffset);
    
    assert (g.equals(gPoint));
  }
  
  public Cuboid(Point fPoint, int xLength, int yLength, int zLength, int scale) {
    if ((xLength <= 0) && (yLength <= 0) && (zLength <= 0)) {
      Console.warning("Attampted to create invalid Cuboid.");
      xLength = -xLength + 1;yLength = -yLength + 1;zLength = -zLength + 1;
      skin = new Skin("Attempted to creat invalid Cuboid", 0.25D);
    }
    f = fPoint;
    g = new Point(f.x + xLength, f.y, f.z);
    b = new Point(f.x, f.y - yLength, f.z);
    c = new Point(f.x + xLength, f.y - yLength, f.z);
    e = new Point(f.x, f.y, f.z - zLength);
    h = new Point(g.x, g.y, g.z - zLength);
    a = new Point(b.x, b.y, b.z - zLength);
    d = new Point(c.x, c.y, c.z - zLength);
    scale(scale);
  }
  
  public Cuboid(Point fPoint, int xLength, int yLength, int zLength, int scale, Skin newSkin) {
    if ((xLength <= 0) && (yLength <= 0) && (zLength <= 0)) {
      Console.warning("Attampted to create invalid Cuboid!");
      xLength = -xLength + 1;yLength = -yLength + 1;zLength = -zLength + 1;
      skin = new Skin("Attempted to creat invalid Cuboid", 0.25D);
    }
    f = fPoint;
    g = new Point(f.x + xLength, f.y, f.z);
    b = new Point(f.x, f.y - yLength, f.z);
    c = new Point(f.x + xLength, f.y - yLength, f.z);
    e = new Point(f.x, f.y, f.z - zLength);
    h = new Point(g.x, g.y, g.z - zLength);
    a = new Point(b.x, b.y, b.z - zLength);
    d = new Point(c.x, c.y, c.z - zLength);
    scale(scale);
    skin = newSkin;
  }
  
  public void setTextureScale(double[] textureScale) {
    textureScaleX = textureScale[0];
    textureScaleY = textureScale[1];
    textureScaleZ = textureScale[2];
  }
  
  public void cut(Orientation cut) {
    double xLength = g.x - a.x;
    double yLength = g.y - a.y;
    
    if (cut != null) {
      switch (1.$SwitchMap$buildable$Orientation[cut.ordinal()]) {
      case 1: 
        b.y += xLength;
        c.y += xLength;
        break;
      case 2: 
        f.y -= xLength;
        g.y -= xLength;
        break;
      case 3: 
        f.x += yLength;
        b.x += yLength;
        break;
      case 4: 
        c.x -= yLength;
        g.x -= yLength;
        break;
      }
      
    }
  }
  
  public void writeVmf(Counter counter, Writer w)
    throws java.io.IOException
  {
    if (a != null) {
      w.write("\tsolid\n\t{\n\t\t\"id\" \"" + counter.getBrushId() + "\"\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + b.getString() + ") (" + f.getString() + ") (" + g.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialTop + "\"\n                         \"uaxis\" \"[1 0 0 0] " + skin.scale * textureScaleX + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + skin.scale * textureScaleY + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + e.getString() + ") (" + a.getString() + ") (" + d.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialBottom + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale * textureScaleX + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + skin.scale * textureScaleY + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + a.getString() + ") (" + e.getString() + ") (" + f.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialLeft + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + skin.scale * textureScaleX + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale * textureScaleZ + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + h.getString() + ") (" + d.getString() + ") (" + c.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialRight + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + skin.scale * textureScaleX + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale * textureScaleZ + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + e.getString() + ") (" + h.getString() + ") (" + g.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialBack + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale * textureScaleY + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale * textureScaleZ + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + d.getString() + ") (" + a.getString() + ") (" + b.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialFront + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale * textureScaleY + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale * textureScaleZ + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n");
      w.write("\t\teditor\n\t\t{\n\t\t\t\"color\" \"0 215 172\"\n\t\t\t\"visgroupshown\" \"1\"\n\t\t\t\"visgroupautoshown\" \"1\"\n\t\t}\n\t}\n");
    }
    else {
      System.out.println("ERROR");
    }
  }
}

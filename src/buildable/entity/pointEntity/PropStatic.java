package buildable.entity.pointEntity;

import buildable.Counter;
import buildable.Point;
import java.io.IOException;
import java.io.Writer;

public class PropStatic
        extends PointEntity
{
  private String model;
  private int angleX = 0;
  private int angleY = 0;
  private int angleZ = 0;

  public PropStatic(String model)
  {
    this(model, 0, 0, 0);
  }

  public PropStatic(String model, int angleX, int angleY, int angleZ)
  {
    this(Point.DEFAULT, model, angleX, angleY, angleZ);
  }

  public PropStatic(Point origin, String model)
  {
    this(origin, model, 0, 0, 0);
  }

  public PropStatic(Point origin, String model, int angleX, int angleY, int angleZ)
  {
    super(origin);
    this.model = model;
    this.angleX = angleX;
    this.angleY = angleY;
    this.angleZ = angleZ;
  }

  public void setAngleX(int angleX)
  {
    this.angleX = angleX;
  }

  public void setAngleY(int angleY)
  {
    this.angleY = angleY;
  }

  public void setAngleZ(int angleZ)
  {
    this.angleZ = angleZ;
  }

  public void setModel(String model)
  {
    this.model = model;
  }

  public PropStatic create(Point origin)
  {
    return new PropStatic(origin, this.model, this.angleX, this.angleY, this.angleZ);
  }

  public void writeVmf(Counter cc, Writer w)
          throws IOException
  {
    w.write("entity\n");
    w.write("{\n");
    w.write("\t  \"id\" \"" + cc.getBrushId() + "\"\n");
    w.write("\t  \"classname\" \"prop_static\"\n");
    w.write("\t  \"angles\" \"" + this.angleX + " " + this.angleY + " " + this.angleZ + "\"\n");
    w.write("\t  \"disableselfshadowing\" \"0\"\n");
    w.write("\t  \"disableshadows\" \"0\"\n");
    w.write("\t  \"disablevertexlighting\" \"0\"\n");
    w.write("\t  \"fademaxdist\" \"0\"\n");
    w.write("\t  \"fademindist\" \"-1\"\n");
    w.write("\t  \"fadescale\" \"1\"\n");
    w.write("  \t  \"ignorenormals\" \"0\"\n");
    w.write("\t  \"maxdxlevel\" \"0\"\n");
    w.write("\t  \"mindxlevel\" \"0\"\n");
    w.write("\t  \"model\" \"" + this.model + "\"\n");
    w.write("\t  \"screenspacefade\" \"0\"\n");
    w.write("        \"skin\" \"0\"\n");
    w.write("\t  \"solid\" \"0\"\n");
    w.write("\t  \"origin\" \"" + this.p.getString() + "\"\n");
    w.write("\t  editor\n");
    w.write("\t  {");
    w.write("\t\t  \"color\" \"255 255 0\"\n");
    w.write("\t\t  \"visgroupshown\" \"1\"\n");
    w.write("\t\t  \"visgroupautoshown\" \"1\"\n");
    w.write("\t\t  \"logicalpos\" \"[0 0]\"\n");
    w.write("\t  }");
    w.write("}");
  }
}

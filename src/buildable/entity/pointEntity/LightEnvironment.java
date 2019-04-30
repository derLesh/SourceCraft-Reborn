package buildable.entity.pointEntity;

import buildable.Color;
import buildable.Counter;
import buildable.Point;
import java.io.IOException;
import java.io.Writer;

public class LightEnvironment
        extends PointEntity
{
  private Color light;
  private Color ambient;

  public LightEnvironment(Color light, Color ambient)
  {
    this(Point.DEFAULT, light, ambient);
  }

  public LightEnvironment(Point origin, Color light, Color ambient)
  {
    super(origin);
    assert (light != null);
    assert (ambient != null);
    this.light = light.copy();
    this.ambient = ambient.copy();
    assert (this.light != null);
    assert (this.ambient != null);
  }

  public LightEnvironment create(Point origin)
  {
    assert (this.light != null);
    assert (this.ambient != null);
    return new LightEnvironment(origin, this.light.copy(), this.ambient.copy());
  }

  public void writeVmf(Counter counter, Writer w)
          throws IOException
  {
    w.write("entity\n");
    w.write("{\n");
    w.write("\t  \"id\" \"" + counter.getBrushId() + "\"\n");
    w.write("\t  \"classname\" \"light_environment\"\n");
    w.write("\t  \"_ambient\" \"" + this.ambient.red + " " + this.ambient.green + " " + this.ambient.blue + " " + this.ambient.alpha + "\"\n");
    w.write("\t  \"_ambientHDR\" \"-1 -1 -1 1\"\n");
    w.write("\t  \"_AmbientScaleHDR\" \"1\"\n");
    w.write("\t  \"_light\" \"" + this.light.red + " " + this.light.green + " " + this.light.blue + " " + this.light.alpha + "\"\n");
    w.write("\t  \"_lightHDR\" \"-1 -1 -1 1\"\n");
    w.write("\t  \"_lightscaleHDR\" \"1\"\n");
    w.write("\t  \"angles\" \"-70 356 0\"\n");
    w.write("\t  \"pitch\" \"-70\"\n");
    w.write("\t  \"SunSpreadAngle\" \"0\"\n");
    w.write("\t  \"origin\" \"" + this.p.getString() + "\"\n");
    w.write("\t  editor\n");
    w.write("\t  {\n");
    w.write("\t\t  \"color\" \"220 30 220\"\n");
    w.write("\t\t  \"visgroupshown\" \"1\"\n");
    w.write("\t\t  \"visgroupautoshown\" \"1\"\n");
    w.write("\t\t  \"logicalpos\" \"[0 0]\"\n");
    w.write("\t  }\n");
    w.write("}\n");
  }
}

package buildable.entity.pointEntity;

import buildable.Color;
import buildable.Counter;
import buildable.Point;
import java.io.IOException;
import java.io.Writer;

public class Light
  extends PointEntity
{
  private Color color;
  private int distance50;
  private int distance100;
  
  public Light(Color color, int distance50, int distance100)
  {
    this(Point.DEFAULT, color, distance50, distance100);
  }
  
  public Light(Point origin, Color color, int distance50, int distance100) {
    super(origin);
    this.color = color;
    this.distance50 = distance50;
    this.distance100 = distance100;
  }
  
  public Light create(Point origin)
  {
    return new Light(origin, color, distance50, distance100);
  }
  
  public void writeVmf(Counter cc, Writer w) throws IOException
  {
    w.write("entity");
    w.write("\n{");
    w.write("\n\t  \"id\" \"" + cc.getBrushId() + "\"");
    w.write("\n\t  \"classname\" \"light\"");
    w.write("\n\t  \"_constant_attn\" \"0\"");
    w.write("\n\t  \"_distance\" \"0\"");
    w.write("\n\t  \"_fifty_percent_distance\" \"" + distance50 + "\"");
    w.write("\n\t  \"_hardfalloff\" \"0\"");
    w.write("\n\t  \"_light\" \"" + color.getRed() + " " + color.getGreen() + " " + color.getBlue() + " " + color.getAlpha() + "\"");
    w.write("\n\t  \"_lightHDR\" \"-1 -1 -1 1\"");
    w.write("\n\t  \"_lightscaleHDR\" \"1\"");
    w.write("\n\t  \"_linear_attn\" \"0\"");
    w.write("\n\t  \"_quadratic_attn\" \"1\"");
    w.write("\n\t  \"_zero_percent_distance\" \"" + distance100 + "\"");
    w.write("\n\t  \"spawnflags\" \"0\"");
    w.write("\n\t  \"style\" \"0\"");
    w.write("\t  \"origin\" \"" + p.getString() + "\"\n");
    w.write("\n\t  editor");
    w.write("\n\t  {");
    w.write("\n\t\t  \"color\" \"220 30 220\"");
    w.write("\n\t\t  \"visgroupshown\" \"1\"");
    w.write("\n\t\t  \"visgroupautoshown\" \"1\"");
    w.write("\n\t\t  \"logicalpos\" \"[0 0]\"");
    w.write("\n\t  }");
    w.write("\n}");
  }
}

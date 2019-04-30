package buildable.entity.pointEntity;

import buildable.Color;
import buildable.Counter;
import buildable.Point;
import java.io.IOException;
import java.io.Writer;





public class ShadowControl
  extends PointEntity
{
  private Color shadow;
  
  public ShadowControl(Color shadow)
  {
    this(Point.DEFAULT, shadow);
  }
  
  public ShadowControl(Point origin, Color shadowNew) {
    super(origin);
    shadow = new Color(shadowNew);
  }
  
  public ShadowControl create(Point origin)
  {
    return new ShadowControl(origin, shadow);
  }
  
  public String getVmfString(Counter cc) {
    return "entity\n{\n\t  \"id\" \"" + cc.getBrushId() + "\"\n" + "\t  \"classname\" \"shadow_control\"\n" + "\t  \"angles\" \"-70 356 0\"\n" + "\t  \"color\" \"" + shadow.red + " " + shadow.green + " " + shadow.blue + "\"\n" + "\t  \"disableallshadows\" \"0\"\n" + "\t  \"distance\" \"75\"\n" + "\t  \"origin\" \"" + p.getString() + "\"\n" + "\t  editor\n" + "\t  {\n" + "\t\t  \"color\" \"220 30 220\"\n" + "\t\t  \"visgroupshown\" \"1\"\n" + "\t\t  \"visgroupautoshown\" \"1\"\n" + "\t\t  \"logicalpos\" \"[0 0]\"\n" + "\t  }\n" + "}\n";
  }
  















  public void writeVmf(Counter counter, Writer w)
    throws IOException
  {
    w.write("entity\n");
    w.write("{\n");
    w.write("\t  \"id\" \"" + counter.getBrushId() + "\"\n");
    w.write("\t  \"classname\" \"shadow_control\"\n");
    w.write("\t  \"angles\" \"-70 356 0\"\n");
    w.write("\t  \"color\" \"" + shadow.red + " " + shadow.green + " " + shadow.blue + "\"\n");
    w.write("\t  \"disableallshadows\" \"0\"\n");
    w.write("\t  \"distance\" \"75\"\n");
    w.write("\t  \"origin\" \"" + p.getString() + "\"\n");
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

package buildable.entity.pointEntity;

import buildable.Counter;
import buildable.Point;
import java.io.IOException;
import java.io.Writer;

public class CustomPointEntity
        extends PointEntity
{
  private String name;

  public CustomPointEntity(String name)
  {
    this(Point.DEFAULT, name);
  }

  public CustomPointEntity(Point origin, String name)
  {
    super(origin);
    this.name = name;
  }

  public CustomPointEntity create(Point origin)
  {
    return new CustomPointEntity(origin, this.name);
  }

  public String getVmfString(Counter cc)
  {
    int fireattack = 4;
    int firetype = 0;
    return "entity{\n\"id\" \"" + cc.getBrushId() + "\"\n" + "\"classname\" \"" + this.name + "\"\n" + "\"origin\" \"" + this.p.getString() + "\"\n" + "editor\n" + "{\n" + "\"color\" \"0 180 0\"\n" + "\"visgroupshown\" \"1\"\n" + "\"visgroupautoshown\" \"1\"\n" + "\"logicalpos\" \"[0 4500]\"\n" + "}\n" + "}\n";
  }

  public void writeVmf(Counter cc, Writer w)
          throws IOException
  {
    int fireattack = 4;
    int firetype = 0;
    w.write("entity");
    w.write("{\n");
    w.write("\"id\" \"" + cc.getBrushId() + "\"\n");
    w.write("\"classname\" \"" + this.name + "\"\n");
    w.write("\"origin\" \"" + this.p.getString() + "\"\n");
    w.write("editor\n");
    w.write("{\n");
    w.write("\t\"color\" \"0 180 0\"\n");
    w.write("\t\"visgroupshown\" \"1\"\n");
    w.write("\t\"visgroupautoshown\" \"1\"\n");
    w.write("\t\"logicalpos\" \"[0 4500]\"\n");
    w.write("}\n");
    w.write("}\n");
  }
}

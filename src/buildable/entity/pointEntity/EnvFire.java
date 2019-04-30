package buildable.entity.pointEntity;

import buildable.Counter;
import buildable.Point;
import java.io.IOException;
import java.io.Writer;






public class EnvFire
  extends PointEntity
{
  private int fireSize;
  
  public EnvFire(int fireSize)
  {
    this(Point.DEFAULT, fireSize);
  }
  
  public EnvFire(Point origin, int fireSize) {
    super(origin);
    this.fireSize = fireSize;
  }
  
  public EnvFire create(Point origin) {
    return new EnvFire(origin, fireSize);
  }
  
  public String getVmfString(Counter cc) {
    int fireattack = 4;
    int firetype = 0;
    return "entity{\n\"id\" \"" + cc.getBrushId() + "\"\n" + "\"classname\" \"env_fire\"\n" + "\"damagescale\" \"1.0\"\n" + "\"fireattack\" \"" + fireattack + "\"\n" + "\"firesize\" \"" + fireSize + "\"\n" + "\"firetype\" \"" + firetype + "\"\n" + "\"health\" \"60\"\n" + "\"ignitionpoint\" \"32\"\n" + "\"spawnflags\" \"5\"\n" + "\"StartDisabled\" \"0\"\n" + "\"origin\" \"" + p.getString() + "\"\n" + "editor\n" + "{\n" + "\"color\" \"0 180 0\"\n" + "\"visgroupshown\" \"1\"\n" + "\"visgroupautoshown\" \"1\"\n" + "\"logicalpos\" \"[0 4500]\"\n" + "}\n" + "}\n";
  }
  



















  public void writeVmf(Counter cc, Writer w)
    throws IOException
  {
    int fireattack = 4;
    int firetype = 0;
    w.write("entity");
    w.write("{\n");
    w.write("\"id\" \"" + cc.getBrushId() + "\"\n");
    w.write("\"classname\" \"env_fire\"\n");
    w.write("\"damagescale\" \"1.0\"\n");
    w.write("\"fireattack\" \"" + fireattack + "\"\n");
    w.write("\"firesize\" \"" + fireSize + "\"\n");
    w.write("\"firetype\" \"" + firetype + "\"\n");
    w.write("\"health\" \"60\"\n");
    w.write("\"ignitionpoint\" \"32\"\n");
    w.write("\"spawnflags\" \"5\"\n");
    w.write("\"StartDisabled\" \"0\"\n");
    w.write("\"origin\" \"" + p.getString() + "\"\n");
    w.write("editor\n");
    w.write("{\n");
    w.write("\"color\" \"0 180 0\"\n");
    w.write("\"visgroupshown\" \"1\"\n");
    w.write("\"visgroupautoshown\" \"1\"\n");
    w.write("\"logicalpos\" \"[0 4500]\"\n");
    w.write("}\n");
    w.write("}\n");
  }
}

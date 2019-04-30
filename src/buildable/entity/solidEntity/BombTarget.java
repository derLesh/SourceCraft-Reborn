package buildable.entity.solidEntity;

import buildable.Counter;
import buildable.Cuboid;
import java.io.IOException;
import java.io.Writer;

public class BombTarget
        extends SolidEntity
{
  public BombTarget(String name, Cuboid s)
  {
    super(s);
  }

  public void writeVmf(Counter counter, Writer w)
          throws IOException
  {
    w.write("entity\n{\n\t\"id\" \"");
    w.write("" + counter.getBrushId());
    w.write("\"\n\t\"classname\" \"func_bomb_target\"\n\t\"heistbomb\" \"0\"\n");

    writeSolids(counter, w);
    w.write("}\n");
  }
}

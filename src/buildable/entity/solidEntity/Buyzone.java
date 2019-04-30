package buildable.entity.solidEntity;

import buildable.Counter;
import buildable.SkinManager;
import buildable.Solid;
import java.io.IOException;
import java.io.Writer;

public class Buyzone
        extends SolidEntity
{
  int teamnumber;

  public Buyzone(Solid s, boolean police)
  {
    super(s);
    s.setSkin(SkinManager.TRIGGER);
    if (police) {
      this.teamnumber = 3;
    } else {
      this.teamnumber = 2;
    }
  }

  public void writeVmf(Counter counter, Writer w)
          throws IOException
  {
    w.write("entity\n{\n\t\"id\" \"");
    w.write("" + counter.getBrushId());
    w.write("\"\n\t\"classname\" \"func_buyzone\"\n\t\"TeamNum\" \"");
    w.write("" + this.teamnumber);
    w.write("\"\n");

    writeSolids(counter, w);
    w.write("\teditor\n\t{\n\t\t\"color\" \"220 30 220\"\n\t\t\"visgroupshown\" \"1\"\n\t\t\"visgroupautoshown\" \"1\"\n\t\t\"logicalpos\" \"[0 1000]\"\n\t}\n}\n");
  }
}

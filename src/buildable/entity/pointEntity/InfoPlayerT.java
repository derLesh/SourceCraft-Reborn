package buildable.entity.pointEntity;

import buildable.Counter;
import buildable.Point;
import java.io.IOException;
import java.io.Writer;

public class InfoPlayerT
        extends PointEntityRotateable
{
  public InfoPlayerT(int rotation)
  {
    super(Point.DEFAULT, 0);
  }

  public InfoPlayerT(Point origin, int rotation)
  {
    super(origin, rotation);
  }

  public InfoPlayerT create(Point origin)
  {
    return new InfoPlayerT(origin, getRotation());
  }

  public String getVmfString(Counter cc)
  {
    String vmf = "\nentity\n{\n\t\"id\" \"" + cc.getBrushId() + "\"" + "\n\t\"classname\" \"info_player_terrorist\"" + "\n\t\"angles\" \"0 " + getRotation() + " 0\"" + "\n\t\"origin\" \"" + this.p.getString() + "\"" + "\n\teditor\n\t{" + "\n\t\t\"color\" \"220 30 220\"" + "\n\t\t\"visgroupshown\" \"1\"" + "\n\t\t\"visgroupautoshown\" \"1\"" + "\n\t\t\"logicalpos\" \"[0 500]\"" + "\n\t}" + "\n}" + "\n";

    return vmf;
  }

  public void writeVmf(Counter cc, Writer w)
          throws IOException
  {
    w.write("\nentity\n{");
    w.write("\n\t\"id\" \"" + cc.getBrushId() + "\"");
    w.write("\n\t\"classname\" \"info_player_terrorist\"");
    w.write("\n\t\"angles\" \"0 " + getRotation() + " 0\"");
    w.write("\n\t\"origin\" \"" + this.p.getString() + "\"");
    w.write("\n\teditor\n\t{");
    w.write("\n\t\t\"color\" \"220 30 220\"");
    w.write("\n\t\t\"visgroupshown\" \"1\"");
    w.write("\n\t\t\"visgroupautoshown\" \"1\"");
    w.write("\n\t\t\"logicalpos\" \"[0 500]\"");
    w.write("\n\t}");
    w.write("\n}");
    w.write("\n");
  }
}

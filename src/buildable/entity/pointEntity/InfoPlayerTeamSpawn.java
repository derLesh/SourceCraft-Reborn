package buildable.entity.pointEntity;

import buildable.Counter;
import buildable.Point;
import java.io.IOException;
import java.io.Writer;

public class InfoPlayerTeamSpawn
        extends PointEntityRotateable
{
  public static final int ANY = 0;
  public static final int RED = 2;
  public static final int BLUE = 3;
  private int teamNumber;

  public InfoPlayerTeamSpawn(int rotation, int teamNumber)
  {
    super(Point.DEFAULT, rotation);
    this.teamNumber = teamNumber;
  }

  public InfoPlayerTeamSpawn(Point origin, int rotation, int teamNumber)
  {
    super(origin, rotation);
    this.teamNumber = teamNumber;
  }

  public InfoPlayerTeamSpawn create(Point origin)
  {
    return new InfoPlayerTeamSpawn(origin, getRotation(), this.teamNumber);
  }

  public String getVmfString(Counter cc)
  {
    String vmf = "entity\n{\n\t\"id\" \"" + cc.getBrushId() + "\"\n" + "\t\"classname\" \"info_player_teamspawn\"\n" + "\t\"angles\" \"0 " + getRotation() + " 0\"\n" + "\t\"StartDisabled\" \"0\"\n" + "\t\"TeamNum\" \"" + this.teamNumber + "\"\n" + "\t\"origin\" \"" + this.p.getString() + "\"\n" + "\teditor\n" + "\t{\n" + "\t\t\"color\" \"220 30 220\"\n" + "\t\t\"visgroupshown\" \"1\"\n" + "\t\t\"visgroupautoshown\" \"1\"\n" + "\t\t\"logicalpos\" \"[0 0]\"\n" + "\t}\n" + "}\n";

    return vmf;
  }

  public void writeVmf(Counter cc, Writer w)
          throws IOException
  {
    w.write("entity\n");
    w.write("{\n");
    w.write("\t\"id\" \"" + cc.getBrushId() + "\"\n");
    w.write("\t\"classname\" \"info_player_teamspawn\"\n");
    w.write("\t\"angles\" \"0 " + getRotation() + " 0\"\n");
    w.write("\t\"StartDisabled\" \"0\"\n");
    w.write("\t\"TeamNum\" \"" + this.teamNumber + "\"\n");
    w.write("\t\"origin\" \"" + this.p.getString() + "\"\n");
    w.write("\teditor\n");
    w.write("\t{\n");
    w.write("\t\t\"color\" \"220 30 220\"\n");
    w.write("\t\t\"visgroupshown\" \"1\"\n");
    w.write("\t\t\"visgroupautoshown\" \"1\"\n");
    w.write("\t\t\"logicalpos\" \"[0 0]\"\n");
    w.write("\t}\n");
    w.write("}\n");
  }
}

package buildable.entity.solidEntity;

import buildable.Counter;
import buildable.Solid;
import java.io.Writer;

public class Buyzone extends SolidEntity
{
  int teamnumber;
  
  public Buyzone(Solid s, boolean police)
  {
    super(s);
    s.setSkin(buildable.SkinManager.TRIGGER);
    if (police) {
      teamnumber = 3;
    }
    else {
      teamnumber = 2;
    }
  }
  
  public void writeVmf(Counter counter, Writer w) throws java.io.IOException
  {
    w.write("entity\n{\n\t\"id\" \"");
    w.write("" + counter.getBrushId());
    w.write("\"\n\t\"classname\" \"func_buyzone\"\n\t\"TeamNum\" \"");
    w.write("" + teamnumber);
    w.write("\"\n");
    
    writeSolids(counter, w);
    w.write("\teditor\n\t{\n\t\t\"color\" \"220 30 220\"\n\t\t\"visgroupshown\" \"1\"\n\t\t\"visgroupautoshown\" \"1\"\n\t\t\"logicalpos\" \"[0 1000]\"\n\t}\n}\n");
  }
}

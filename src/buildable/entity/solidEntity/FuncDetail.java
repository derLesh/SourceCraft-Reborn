package buildable.entity.solidEntity;

import buildable.Counter;
import buildable.Solid;
import java.io.IOException;
import java.io.Writer;







public class FuncDetail
  extends SolidEntity
{
  public FuncDetail(Solid solid)
  {
    super(solid);
  }
  
  public FuncDetail(Solid[] solids) {
    super(solids);
  }
  
  public void writeVmf(Counter counter, Writer w) throws IOException
  {
    w.write("entity\n");
    w.write("{\n");
    w.write("\t\"id\" \"" + counter.getBrushId() + "\"\n");
    w.write("\t\"classname\" \"func_detail\"\n");
    
    writeSolids(counter, w);
    w.write("\teditor\n");
    w.write("\t{\n");
    w.write("\t\t\"color\" \"0 180 0\"\n");
    w.write("\t\t\"visgroupshown\" \"1\"\n");
    w.write("\t\t\"visgroupautoshown\" \"1\"\n");
    w.write("\t\t\"logicalpos\" \"[0 500]\"\n");
    w.write("\t}\n");
    w.write("}\n");
  }
}

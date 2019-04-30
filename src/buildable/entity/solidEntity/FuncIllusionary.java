package buildable.entity.solidEntity;

import buildable.Counter;
import buildable.Solid;
import java.io.IOException;
import java.io.Writer;







public class FuncIllusionary
  extends SolidEntity
{
  public FuncIllusionary(Solid c)
  {
    super(c);
  }
  
  public void writeVmf(Counter counter, Writer w) throws IOException
  {
    w.write("entity\n");
    w.write("{\n");
    w.write("\t\"id\" \"" + counter.getBrushId() + "\"\n");
    w.write("\t\"classname\" \"func_illusionary\"\n");
    w.write("\t\"disablereceiveshadows\" \"0\"\n");
    w.write("\t\"disableshadows\" \"0\"\n");
    w.write("\t\"origin\" \"0 0 0\"\n");
    w.write("\t\"renderamt\" \"255\"\n");
    w.write("\t\"rendercolor\" \"255 255 255\"\n");
    w.write("\t\"renderfx\" \"0\"\n");
    w.write("\t\"rendermode\" \"0\"\n");
    
    writeSolids(counter, w);
    w.write("\teditor\n");
    w.write("\t{\n");
    w.write("\t\t\"color\" \"299 70 202\"\n");
    w.write("\t\t\"visgroupshown\" \"1\"\n");
    w.write("\t\t\"visgroupautoshown\" \"1\"\n");
    w.write("\t\t\"logicalpos\" \"[0 500]\"\n");
    w.write("\t}\n");
    w.write("}\n");
  }
}

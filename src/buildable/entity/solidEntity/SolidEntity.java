package buildable.entity.solidEntity;

import buildable.Counter;
import buildable.Solid;
import java.io.Writer;
import minecraft.VmfWriter;

public abstract class SolidEntity implements VmfWriter
{
  protected Solid[] solids;
  
  public SolidEntity(Solid solid)
  {
    solids = new Solid[1];
    solids[0] = solid;
  }
  
  public SolidEntity(Solid[] solids) {
    this.solids = solids;
  }
  
  public void writeSolids(Counter counter, Writer writer) throws java.io.IOException {
    for (Solid solid : solids) {
      solid.writeVmf(counter, writer);
    }
  }
}

package buildable.entity.solidEntity;

import buildable.Counter;
import buildable.Solid;
import java.io.IOException;
import java.io.Writer;
import minecraft.VmfWriter;

public abstract class SolidEntity
        implements VmfWriter
{
  protected Solid[] solids;

  public SolidEntity(Solid solid)
  {
    this.solids = new Solid[1];
    this.solids[0] = solid;
  }

  public SolidEntity(Solid[] solids)
  {
    this.solids = solids;
  }

  public void writeSolids(Counter counter, Writer writer)
          throws IOException
  {
    for (Solid solid : this.solids) {
      solid.writeVmf(counter, writer);
    }
  }
}

package minecraft;

import buildable.Counter;
import java.io.IOException;
import java.io.Writer;

public abstract interface VmfWriter
{
  public abstract void writeVmf(Counter paramCounter, Writer paramWriter)
    throws IOException;
}

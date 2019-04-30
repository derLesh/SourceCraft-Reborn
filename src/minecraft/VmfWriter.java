package minecraft;

import buildable.Counter;

import java.io.IOException;
import java.io.Writer;

public interface VmfWriter
{
  void writeVmf(Counter paramCounter, Writer paramWriter)throws IOException;
}
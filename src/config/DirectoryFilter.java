package config;

import java.io.File;
import java.io.FilenameFilter;






public class DirectoryFilter
  implements FilenameFilter
{
  public DirectoryFilter() {}
  
  public boolean accept(File dir, String name)
  {
    return new File(dir, name).isDirectory();
  }
}

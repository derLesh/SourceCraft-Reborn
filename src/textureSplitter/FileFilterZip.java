package textureSplitter;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FileFilterZip extends FileFilter
{
  public boolean accept(File f)
  {
    return (f.getName().toLowerCase().endsWith(".zip")) || (f.isDirectory());
  }
  
  public String getDescription() {
    return "ZIP Files";
  }
}

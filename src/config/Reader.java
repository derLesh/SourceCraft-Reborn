package config;

import buildable.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;




public abstract class Reader
{
  protected FileInputStream s;
  
  public Reader(String fileString)
    throws FileNotFoundException
  {
    File file = new File(fileString);
    s = new FileInputStream(fileString);
  }
  
  protected String nextData() {
    StringBuffer argument = new StringBuffer("");
    int read = 0;
    try {
      while ((read = s.read()) != -1)
      {
        if (read == 62) {
          return argument.toString();
        }
        if ((read != 13) && (read != 10) && (read != 9))
        {




          argument.append((char)read);
        }
      }
    } catch (IOException ex) {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
    return argument.toString();
  }
  
  protected String nextArgument() {
    StringBuffer argument = new StringBuffer("");
    int read = 0;
    try {
      while ((read = s.read()) != -1)
      {
        if (read == 60) {
          return argument.toString();
        }
        if ((read != 13) && (read != 10) && (read != 9) && (read != 32))
        {





          argument.append((char)read);
        }
      }
    } catch (IOException ex) {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
    return argument.toString();
  }
  
  protected Color nextArgumentColor()
  {
    String argument = nextData();
    String[] part = argument.split(",");
    if (part.length == 3) {
      return new Color(new Integer(part[0]).intValue(), new Integer(part[1]).intValue(), new Integer(part[2]).intValue());
    }
    if (part.length == 4) {
      return new Color(new Integer(part[0]).intValue(), new Integer(part[1]).intValue(), new Integer(part[2]).intValue(), new Integer(part[3]).intValue());
    }
    
    return null;
  }
}

package config;

import buildable.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Writer
{
  private FileWriter w;
  private int depth;

  public Writer(String file)
  {
    try
    {
      this.w = new FileWriter(new File(file));
    }
    catch (IOException ex)
    {
      Logger.getLogger(ConfigWriter.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  protected void writeFinalArgument(String name, int argument, boolean follower)
  {
    if (follower) {
      write("" + name + '<' + argument + '>' + ',' + "\n");
    } else {
      write("" + name + '<' + argument + '>' + "\n");
    }
  }

  protected void writeFinalArgument(String name, String argument, boolean follower)
  {
    if (follower) {
      write("" + name + '<' + argument + '>' + ',' + "\n");
    } else {
      write("" + name + '<' + argument + '>' + "\n");
    }
  }

  protected void writeFinalArgument(String name, Color color, boolean follower)
  {
    if (color != null)
    {
      String argument = color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "," + color.getAlpha();
      writeFinalArgument(name, argument, follower);
    }
  }

  protected void openSection()
  {
    this.depth += 1;
  }

  protected void closeSection()
  {
    this.depth -= 1;
  }

  protected void write(String string)
  {
    try
    {
      for (int i = this.depth; i > 0; i--) {
        this.w.write("    ");
      }
      this.w.write(string);
    }
    catch (IOException ex)
    {
      Logger.getLogger(ConfigWriter.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  protected void end()
  {
    try
    {
      this.w.close();
    }
    catch (IOException ex)
    {
      Logger.getLogger(ConfigWriter.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  protected void initiateSection(String name)
  {
    write(name + '<' + "\n");
    openSection();
  }

  protected void finishSection(boolean successor)
  {
    closeSection();
    if (successor) {
      write(">,\n");
    } else {
      write(">\n");
    }
  }
}

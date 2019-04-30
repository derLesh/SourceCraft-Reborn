package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;




public class TextureOptionsReader
  extends Reader
{
  private TextureOptions textureOptions;
  
  public TextureOptionsReader(String optionsfile, TextureOptions textureOptions)
    throws FileNotFoundException
  {
    super(optionsfile);
    this.textureOptions = textureOptions;
  }
  
  public void readTextureOptions() {
    try {
      do {
        String argument = nextArgument();
        if (argument.equals("version")) {
          textureOptions.setVersion(nextData());
        }
        else if (argument.equals("scale")) {
          textureOptions.setScale(nextData());
        }
        else if (argument.equals("textureVersion")) {
          textureOptions.setTextureVersion(nextData());
        }
      } while (s.read() == 44);
    } catch (IOException ex) {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}

package config;

import basic.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;





public class TextureOptions
{
  private static final int defaultScale = 128;
  private FileInputStream s;
  private String version;
  private String scale;
  private String textureVersion;
  
  public TextureOptions(String file)
  {
    try
    {
      TextureOptionsReader reader = new TextureOptionsReader(file, this);
      reader.readTextureOptions();
    }
    catch (FileNotFoundException ex) {
      Console.info("Cannot find Texture-Options.");
    }
  }
  
  public int getTextureVersion()
  {
    try {
      return new Integer(textureVersion).intValue();
    }
    catch (NumberFormatException e) {}
    return 0;
  }
  
  public int getScale()
  {
    try {
      return new Integer(scale).intValue();
    }
    catch (NumberFormatException e) {
      Console.warning("Texture-Options does not specify a scale. Using default scale."); }
    return 128;
  }
  
  public boolean isNewerThan(TextureOptions other)
  {
    return getTextureVersion() > other.getTextureVersion();
  }
  
  public void setScale(String scale) {
    this.scale = scale;
  }
  
  public void setTextureVersion(String textureVersion) {
    this.textureVersion = textureVersion;
  }
  
  public void setVersion(String version) {
    this.version = version;
  }
}

package config;

import java.io.File;

public class SourceGame
{
  private String name;
  private String nameLong;
  private String nameShort;
  private String gamePath;
  private String defaultConvertOption;

  public SourceGame(String aName)
  {
    this.name = aName;
  }

  public String getName()
  {
    return this.name;
  }

  public void setGamePath(File filePath)
  {
    this.gamePath = filePath.getParent();
  }

  public void setGamePath(String newPath)
  {
    this.gamePath = newPath;
  }

  public File getGamePath(Config config)
  {
    String path = getGamePathString(config);
    return new File(path);
  }

  public String getGamePathString(Config config)
  {
    if (this.gamePath != null)
    {
      File f = new File(this.gamePath);
      if (f.exists()) {
        return this.gamePath;
      }
      return config.getSteamPathString() + "\\steamapps\\" + config.getSteamUserName() + "\\sourcesdk_content\\" + this.nameShort + "\\mapsrc\\";
    }
    return null;
  }

  public void setLongName(String nameLong)
  {
    this.nameLong = nameLong;
  }

  public String getLongName()
  {
    return this.nameLong;
  }

  public void setShortName(String nameShort)
  {
    this.nameShort = nameShort;
  }

  public String getShortName()
  {
    return this.nameShort;
  }

  public void setDefaultConvertOption(String name)
  {
    this.defaultConvertOption = name;
  }

  public String getDefaultConvertOption()
  {
    return this.defaultConvertOption;
  }

  public File getMatriealPath(Config config, TexturePack materialFolder)
  {
    return new File(config.getSteamPath(), "\\steamapps\\" + config.getSteamUserName() + "\\" + getLongName() + "\\" + getShortName() + "\\materials\\" + materialFolder.getName());
  }
}

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
    name = aName;
  }
  
  public String getName() {
    return name;
  }
  
  public void setGamePath(File filePath) {
    gamePath = filePath.getParent();
  }
  
  public void setGamePath(String newPath) {
    gamePath = newPath;
  }
  
  public File getGamePath(Config config) {
    String path = getGamePathString(config);
    return new File(path);
  }
  
  public String getGamePathString(Config config) {
    if (gamePath != null) {
      File f = new File(gamePath);
      if (f.exists()) {
        return gamePath;
      }
      
      return config.getSteamPathString() + "\\steamapps\\" + config.getSteamUserName() + "\\sourcesdk_content\\" + nameShort + "\\mapsrc\\";
    }
    





    return null;
  }
  
  public void setLongName(String nameLong) {
    this.nameLong = nameLong;
  }
  
  public String getLongName() { return nameLong; }
  
  public void setShortName(String nameShort)
  {
    this.nameShort = nameShort;
  }
  
  public String getShortName() { return nameShort; }
  
  public void setDefaultConvertOption(String name)
  {
    defaultConvertOption = name;
  }
  
  public String getDefaultConvertOption() { return defaultConvertOption; }
  
  public File getMatriealPath(Config config, TexturePack materialFolder)
  {
    return new File(config.getSteamPath(), "\\steamapps\\" + config.getSteamUserName() + "\\" + getLongName() + "\\" + getShortName() + "\\materials\\" + materialFolder.getName());
  }
}

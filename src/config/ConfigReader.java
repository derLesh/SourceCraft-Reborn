package config;

import buildable.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigReader
        extends Reader
{
  public static final String fileName = "config.txt";
  private final Config config;

  public ConfigReader(Config config)
          throws FileNotFoundException
  {
    super("config.txt");
    this.config = config;
    readConfig();
  }

  private void readConfig()
  {
    assert (this.config != null);
    try
    {
      do
      {
        String argument = nextArgument();
        if (argument.equals("version"))
        {
          String version = nextData();
          this.config.setVersion(version);
        }
        else if (argument.equals("mcrToVmf"))
        {
          readMcrToVmf();
        }
        else if (argument.equals("window"))
        {
          readWindow();
        }
        else if (argument.equals("paths"))
        {
          readPaths();
        }
      } while (this.s.read() == 44);
    }
    catch (IOException ex)
    {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void readMcrToVmf()
  {
    try
    {
      do
      {
        String argument = nextArgument();
        if (argument.equals("variables")) {
          readVariables();
        }
        if (argument.equals("games")) {
          readGames();
        }
        if (argument.equals("texturePacks")) {
          readTexturePacks();
        }
        if (argument.equals("convertOptions")) {
          readConvertOptions();
        }
      } while (this.s.read() == 44);
    }
    catch (IOException ex)
    {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void readVariables()
  {
    try
    {
      do
      {
        String varName = nextArgument();
        if (varName.equals("game"))
        {
          String gameName = nextData();
          this.config.setGame(gameName);
        }
        else if (varName.equals("texturePack"))
        {
          String packName = nextData();
          this.config.setPack(packName);
        }
        else if (varName.equals("place"))
        {
          String place = nextData();
          this.config.setPlace(place);
        }
        else if (varName.equals("convertOption"))
        {
          this.config.setConvertOption(nextData());
        }
      } while (this.s.read() == 44);
      while (this.s.read() != 62) {}
    }
    catch (IOException ex)
    {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void readTexturePacks()
  {
    try
    {
      TexturePack pack;
      do
      {
        String packName = nextArgument();

        String size = nextData();

        pack = new TexturePack(packName, new Integer(size).intValue());
      } while (this.s.read() == 44);
      while (this.s.read() != 62) {}
    }
    catch (IOException ex)
    {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void readGames()
  {
    int read = 0;
    try
    {
      do
      {
        String gameName = nextArgument();

        readGame(gameName);
      } while (this.s.read() == 44);
      while (this.s.read() != 62) {}
    }
    catch (IOException ex)
    {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void readGame(String gameName)
  {
    SourceGame game = new SourceGame(gameName);
    try
    {
      do
      {
        String argument = nextArgument();
        if (argument.equals("longName"))
        {
          String longName = nextData();
          game.setLongName(longName);
        }
        else if (argument.equals("shortName"))
        {
          String shortName = nextData();
          game.setShortName(shortName);
        }
        else if (argument.equals("gamePath"))
        {
          String gamePath = nextData();
          game.setGamePath(gamePath);
        }
        else if (argument.equals("defaultConvertOption"))
        {
          String defaultConvertOption = nextData();
          game.setDefaultConvertOption(defaultConvertOption);
        }
      } while (this.s.read() == 44);
      while (this.s.read() != 62) {}
    }
    catch (IOException ex)
    {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
    this.config.addGame(game);
  }

  private void readConvertOptions()
  {
    int read = 0;
    try
    {
      do
      {
        String name = nextArgument();

        readConvertOption(name);
      } while (this.s.read() == 44);
      while (this.s.read() != 62) {}
    }
    catch (IOException ex)
    {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void readConvertOption(String name)
  {
    ConvertOption options = new ConvertOption(name);
    try
    {
      do
      {
        String argument = nextArgument();
        if (argument.equals("scale"))
        {
          String scale = nextData();
          options.setScale(new Integer(scale).intValue());
        }
        else if (argument.equals("skyTexture"))
        {
          String skyTexture = nextData();
          options.setSkyTexture(skyTexture);
        }
        else if (argument.equals("sunBrightness"))
        {
          Color sunLight = nextArgumentColor();
          options.setSunLight(sunLight);
        }
        else if (argument.equals("sunAmbient"))
        {
          Color sunAmbient = nextArgumentColor();
          options.setSunAmbient(sunAmbient);
        }
        else if (argument.equals("sunShadow"))
        {
          Color sunShadow = nextArgumentColor();
          options.setSunShadow(sunShadow);
        }
        else if (argument.equals("addable"))
        {
          String addable = nextData();
          options.addAddable(addable);
        }
      } while (this.s.read() == 44);
      while (this.s.read() != 62) {}
    }
    catch (IOException ex)
    {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
    this.config.addConvertOption(options);
  }

  private void readWindow()
  {
    try
    {
      do
      {
        String varName = nextArgument();
        if (varName.equals("xPos"))
        {
          String xPos = nextData();
          this.config.setWindowPosX(xPos);
        }
        if (varName.equals("yPos"))
        {
          String yPos = nextData();
          this.config.setWindowPosY(yPos);
        }
      } while (this.s.read() == 44);
      while (this.s.read() != 62) {}
    }
    catch (IOException ex)
    {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void readPaths()
  {
    try
    {
      do
      {
        String argument = nextArgument();
        if (argument.equals("minecraft")) {
          this.config.setMinecraftPath(nextData());
        } else if (argument.equals("steam")) {
          this.config.setSteamPath(nextData());
        } else if (argument.equals("steamUserName")) {
          this.config.setSteamUserName(nextData());
        }
      } while (this.s.read() == 44);
      while (this.s.read() != 62) {}
    }
    catch (IOException ex)
    {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}

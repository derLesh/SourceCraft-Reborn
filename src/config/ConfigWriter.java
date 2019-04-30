package config;

public class ConfigWriter
        extends Writer
{
  private Config config;

  public ConfigWriter(Config config)
  {
    super("config.txt");
    this.config = config;
  }

  public void writeConfig()
  {
    writeFinalArgument("version", this.config.getVersion(), true);

    initiateSection("window");
    writeWindow();
    finishSection(true);
    initiateSection("paths");
    writePaths();
    finishSection(true);
    initiateSection("mcrToVmf");

    wrtieConvertOptions(true);
    writeGames(true);
    writeVariables(false);

    finishSection(false);
    end();
  }

  private void writePaths()
  {
    writeFinalArgument("minecraft", this.config.getMinecraftPathString(), true);
    writeFinalArgument("steam", this.config.getSteamPathString(), true);
    writeFinalArgument("steamUserName", this.config.getSteamUserName(), false);
  }

  private void writeWindow()
  {
    writeFinalArgument("xPos", this.config.getWindowPosX(), true);
    writeFinalArgument("yPos", this.config.getWindowPosY(), false);
  }

  private void writeVariables(boolean successor)
  {
    initiateSection("variables");

    writeFinalArgument("place", this.config.getPlace(), true);
    writeFinalArgument("game", this.config.getGame(), true);
    writeFinalArgument("convertOption", this.config.getConvertOption(), true);
    writeFinalArgument("texturePack", this.config.getPack(), false);

    finishSection(successor);
  }

  private void writeGames(boolean successor)
  {
    initiateSection("games");

    SourceGame[] game = this.config.getGames();
    int length = game.length;
    if (length > 0)
    {
      for (int i = 0; i < length - 1; i++) {
        writeGame(game[i], true);
      }
      writeGame(game[(length - 1)], false);
    }
    finishSection(successor);
  }

  private void writeGame(SourceGame game, boolean successor)
  {
    initiateSection(game.getName());

    writeFinalArgument("longName", game.getLongName(), true);
    writeFinalArgument("shortName", game.getShortName(), true);
    writeFinalArgument("gamePath", game.getGamePathString(this.config), true);
    writeFinalArgument("defaultConvertOption", game.getDefaultConvertOption(), false);

    finishSection(successor);
  }

  private void wrtieConvertOptions(boolean successor)
  {
    initiateSection("convertOptions");

    ConvertOption[] options = this.config.getConvertOptions();
    int length = options.length;
    if (length > 0)
    {
      for (int i = 0; i < length - 1; i++) {
        wrtieConvertOption(options[i], true);
      }
      wrtieConvertOption(options[(length - 1)], false);
    }
    finishSection(successor);
  }

  private void wrtieConvertOption(ConvertOption options, boolean successor)
  {
    initiateSection(options.getName());

    writeFinalArgument("scale", options.getScale(), true);
    writeFinalArgument("skyTexture", options.getSkyTexture(), true);
    writeFinalArgument("sunBrightness", options.getSunLight(), true);
    writeFinalArgument("sunAmbient", options.getSunAmbient(), true);
    writeFinalArgument("sunShadow", options.getSunShadow(), true);

    String[] addable = options.getAddablesAsStrings();
    int length = addable.length;
    for (int i = 0; i < length - 1; i++) {
      writeFinalArgument("addable", addable[i], true);
    }
    writeFinalArgument("addable", addable[(length - 1)], false);

    finishSection(successor);
  }
}

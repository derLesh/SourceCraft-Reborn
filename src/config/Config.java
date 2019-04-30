package config;

import basic.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;






public class Config
{
  private String version;
  private Queue<SourceGame> games;
  private Queue<ConvertOption> options;
  private ArrayDeque<Place> places;
  private String[] texturePacks;
  private int windowPosX = 0;
  private int windowPosY = 0;
  private String minecraftPath;
  private String steamPath;
  private String steamUserName;
  private String place;
  private String game;
  private String convertOption;
  private String pack;
  
  public Config()
  {
    games = new LinkedList();
    places = new ArrayDeque();
    texturePacks = detectTexturePacks();
    options = new LinkedList();
    try {
      ConfigReader configReader = new ConfigReader(this);
      Console.info("Loaded config.txt.");
    }
    catch (FileNotFoundException ex) {
      Console.error("Cannot find config.txt. Make sure you extracted all files.");
    }
    try {
      PlacesReader placesReader = new PlacesReader(this);
      Console.info("Loaded places.txt.");
    }
    catch (FileNotFoundException ex) {
      Console.info("There is no places.txt yet.");
    }
  }
  
  public String getSteamUserName() {
    return steamUserName;
  }
  
  public void setSteamUserName(String steamUserName) {
    this.steamUserName = steamUserName;
  }
  
  private String[] detectTexturePacks() {
    File file = new File("textures\\");
    File[] files = file.listFiles(new DirectoryFilter());
    if (files == null) {
      Console.error("no textures to install found");
    }
    else {
      int length = files.length;
      String[] detectedTexturePacks = new String[length];
      int i = 0;
      for (File f : files) {
        detectedTexturePacks[i] = f.getName();
        i++;
      }
      return detectedTexturePacks;
    }
    return null;
  }
  
  public void addConvertOption(ConvertOption option) {
    options.offer(option);
  }
  
  public ConvertOption[] getConvertOptions() {
    Object[] optionsArray = options.toArray();
    int length = optionsArray.length;
    ConvertOption[] result = new ConvertOption[length];
    
    for (int i = 0; i < length; i++) {
      result[i] = ((ConvertOption)optionsArray[i]);
    }
    return result;
  }
  
  public SourceGame getGame(String searchedName) {
    SourceGame result = null;
    Object[] gameArray = games.toArray();
    
    int length = gameArray.length;
    for (int i = 0; i < length; i++) {
      SourceGame test = (SourceGame)gameArray[i];
      if (test.getName().equals(searchedName)) {
        result = test;
      }
    }
    return result;
  }
  
  public String[] getConvertOptionNames() {
    Object[] optionArray = options.toArray();
    int length = optionArray.length;
    String[] result = new String[length];
    
    for (int i = 0; i < length; i++) {
      result[i] = ((ConvertOption)optionArray[i]).getName();
    }
    return result;
  }
  
  public SourceGame[] getGames() {
    Object[] gameArray = games.toArray();
    int length = gameArray.length;
    SourceGame[] result = new SourceGame[length];
    
    for (int i = 0; i < length; i++) {
      result[i] = ((SourceGame)gameArray[i]);
    }
    return result;
  }
  
  public String[] getGameNames() {
    Object[] gameArray = games.toArray();
    int length = gameArray.length;
    String[] result = new String[length];
    
    for (int i = 0; i < length; i++) {
      result[i] = ((SourceGame)gameArray[i]).getName();
    }
    return result;
  }
  
  public Place[] getPlaces() {
    return (Place[])places.toArray(new Place[0]);
  }
  
  public String[] getPlaceNamesWithDefault(String defaultName) {
    Place[] placesArray = new Place[places.size()];
    placesArray = (Place[])places.toArray(placesArray);
    String[] result = new String[places.size() + 1];
    result[0] = defaultName;
    for (int i = 1; i < places.size() + 1; i++) {
      result[i] = placesArray[(i - 1)].getName();
    }
    return result;
  }
  
  public String[] getPlaceNames() {
    Place[] placesArray = new Place[places.size()];
    placesArray = (Place[])places.toArray(placesArray);
    String[] result = new String[places.size()];
    for (int i = 0; i < places.size(); i++) {
      result[i] = placesArray[i].getName();
    }
    return result;
  }
  
  public Place getPlace(String searchedName) {
    Object[] placesArray = places.toArray();
    for (Object object : placesArray) {
      Place posPlace = (Place)object;
      if (posPlace.getName().equals(searchedName)) {
        return posPlace;
      }
    }
    return null;
  }
  
  public String[] getTexturePacks() {
    return texturePacks;
  }
  
  public TexturePack getTexturePack(String name) {
    File file = new File("textures\\" + name);
    if (!file.exists()) {
      return new TexturePack(name);
    }
    
    file = new File(file, "option");
    if (!file.exists())
    {
      return new TexturePack(name);
    }
    
    TextureOptions to = new TextureOptions(file.toString());
    return new TexturePack(name, to.getScale());
  }
  

  public void setGame(String game)
  {
    this.game = game;
  }
  
  public String getVersion() {
    return version;
  }
  
  public void setVersion(String version) {
    this.version = version;
  }
  
  public void addGame(SourceGame game) {
    games.offer(game);
  }
  
  public void addPlace(Place place) {
    places.push(place);
  }
  
  public void deletePlace(Place place) {
    places.remove(place);
  }
  
  public void setPack(String pack) {
    this.pack = pack;
  }
  
  public String getGame() {
    return game;
  }
  
  public String getPack() {
    return pack;
  }
  
  public String variablesToString() {
    return "Using texturePack " + pack + ".";
  }
  
  public ConvertOption getDefaultConvertOption(String game) {
    return getDefaultConvertOption(getGame(game));
  }
  
  public ConvertOption getDefaultConvertOption(SourceGame game) {
    return getConvertOption(game.getDefaultConvertOption());
  }
  
  public ConvertOption getConvertOption(String searchedName) {
    Object[] optionsArray = options.toArray();
    int length = optionsArray.length;
    
    for (int i = 0; i < length; i++) {
      if (((ConvertOption)optionsArray[i]).getName().equals(searchedName)) {
        return (ConvertOption)optionsArray[i];
      }
    }
    System.out.println("Cannot find a convertOption named " + searchedName);
    return null;
  }
  
  public void setWindowPosX(String windowPosX) {
    if (!windowPosX.equals("")) {
      this.windowPosX = new Integer(windowPosX).intValue();
    }
  }
  
  public void setWindowPosY(String windowPosY)
  {
    if (!windowPosY.equals("")) {
      this.windowPosY = new Integer(windowPosY).intValue();
    }
  }
  
  public int getWindowPosX() {
    return windowPosX;
  }
  
  public int getWindowPosY() {
    return windowPosY;
  }
  
  public void setMinecraftPath(String path) {
    minecraftPath = path;
  }
  
  public void setSteamPath(String path) {
    steamPath = path;
  }
  
  public String getMinecraftPathString() {
    return minecraftPath;
  }
  
  public File getMinecraftPath() {
    return new File(minecraftPath);
  }
  
  public File getSteamPath() {
    return new File(steamPath);
  }
  
  public String getSteamPathString() {
    return steamPath;
  }
  
  public boolean verifySteamDirectory() {
    return verifySteamDirectory(steamPath);
  }
  
  public static boolean verifySteamDirectory(String path) {
    File steam = new File(path);
    if ((steam.exists()) && (steam.isDirectory())) {
      File appdata = new File(path + "\\steamapps");
      if ((appdata.exists()) && (appdata.isDirectory())) {
        return true;
      }
    }
    return false;
  }
  
  public boolean verifyMinecraftDirectory() {
    return verifyMinecraftDirectory(minecraftPath);
  }
  
  public static boolean verifyMinecraftDirectory(String path) {
    File saves = new File(path + "\\saves");
    if ((saves.exists()) && (saves.isDirectory())) {
      return true;
    }
    return false;
  }
  
  public String[] getPossibleWorlds() {
    File file = new File(getMinecraftPath() + "\\saves");
    return file.list();
  }
  
  public File getWorldFile(Place place) {
    return new File(getMinecraftPath() + "\\saves\\" + place.getWorld());
  }
  
  public String[] getSteamUsers() {
    assert (verifySteamDirectory());
    File steamapps = new File(steamPath + "\\steamapps");
    return steamapps.list(new DirectoryFilter());
  }
  
  public String getMostLikelySteamUser() {
    String[] users = getSteamUsers();
    String result = users[0];
    for (String user : users) {
      if ((!user.equals("common")) && (!user.equals("downloading")) && (!user.equals("temp")) && (!user.equals("sourcemods")))
      {


        result = user;
      }
    }
    return result;
  }
  
  public boolean steamUserNameExists() {
    if ((steamUserName == null) || (steamUserName.equals(""))) {
      return false;
    }
    
    File f = new File(steamPath + "\\steamapps\\" + steamUserName);
    
    return f.exists();
  }
  
  public void setConvertOption(String convertOption)
  {
    this.convertOption = convertOption;
  }
  
  public String getConvertOption() {
    return convertOption;
  }
  
  public void setPlace(String place) {
    this.place = place;
  }
  
  public String getPlace() {
    return place;
  }
  
  public boolean placesEmpty() {
    return places.isEmpty();
  }
}

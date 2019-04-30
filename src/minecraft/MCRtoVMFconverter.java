package minecraft;

import basic.Console;
import config.Config;
import config.ConfigWriter;
import config.ConvertOption;
import config.Place;
import config.PlacesWriter;
import config.SourceGame;
import config.TextureOptions;
import config.TexturePack;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import minecraft.map.DefaultMinecraftMap;


public class MCRtoVMFconverter
  extends JFrame
{
  private final String title = "Sourcecraft - MCR to VMF converter";
  private final String version = "2.12";
  private final String author = "GartenMaps aka. Garten";
  private final String license = "Attribution-NonCommercial-NoDerivs 3.0 Unported (CC BY-NC-ND 3.0)";
  private final String licenseInfo = "http://creativecommons.org/licenses/by-nc-nd/3.0/legalcode";
  
  private Config config;
  
  private static String placeName = "";
  private static final String ANVIL_ENDING = "mca";
  private static final String REGION_ENDING = "mcr";
  
  public MCRtoVMFconverter() {
    config = new Config();
  }
  
  public void writeConfig() {
    ConfigWriter writer = new ConfigWriter(config);
    writer.writeConfig();
    if (!config.placesEmpty()) {
      PlacesWriter placesWriter = new PlacesWriter(config);
      placesWriter.writePlaces();
    }
  }
  

  public void run2()
  {
    System.out.println(config.variablesToString());
    SourceGame[] games = config.getGames();
    System.out.print("Type ");
    for (SourceGame game : games) {
      System.out.print("\"" + game.getName() + "\", ");
    }
    System.out.println("\"about\", \"help\" or \"quit\" ");
    String order = Console.readString();
    while (((order.equals("quit")) || (order.equals("q")) ? 1 : 0) == 0)
    {
      SourceGame game = config.getGame(order);
      if (game == null)
      {

        if (order.equals("about")) {
          System.out.println("Sourcecraft - MCR to VMF converter 2.12");
          System.out.println("author: GartenMaps aka. Garten");
          System.out.println("license Attribution-NonCommercial-NoDerivs 3.0 Unported (CC BY-NC-ND 3.0)");
          System.out.println("for legal code licence see: http://creativecommons.org/licenses/by-nc-nd/3.0/legalcode");
        }
        else {
          System.out.println(" TYPE     TO\ntf2      convert a minecraft map for tf2\ncss      convert a minecraft map for css\nstart    convert a minecraft map\nabout    show version, author and license\nquit     close program");
        }
      }
      



      order = Console.readString();
    }
    System.exit(0);
  }
  
  public File convertMap(Place place, SourceGame game, ConvertOption option, File file)
  {
    game.setGamePath(file);
    writeConfig();
    System.out.println("Done");
    return file;
  }
  
  public void convertMapOld(SourceGame game)
  {
    DefaultMinecraftMap map = preOpenDialog(game);
    
    writeConfig();
    System.out.println("Done");
  }
  
  public boolean newerVersion(String compareVersion)
  {
    System.out.println("compare 2.12 to " + compareVersion);
    String[] parts = "2.12".split("\\.");
    if (parts.length != 2) {
      return false;
    }
    int upper = new Integer(parts[0]).intValue();
    int lower = new Integer(parts[1]).intValue();
    
    parts = compareVersion.split("\\.");
    if (parts.length != 2) {
      return true;
    }
    int upperCompare = new Integer(parts[0]).intValue();
    int lowerCompare = new Integer(parts[1]).intValue();
    
    if (upper > upperCompare) {
      return true;
    }
    
    if ((upper == upperCompare) && (lower > lowerCompare)) {
      return true;
    }
    
    return false;
  }
  
  public void checkIfTexturesExist(SourceGame game, TexturePack pack) {
    File materiaPath = game.getMatriealPath(config, pack);
    if ((materiaPath == null) || (!materiaPath.getParentFile().exists())) {
      Console.warning("Cannot find material path. Use a path like \"...\\Steam\\steamapps\\<user-name>\\sourcesdk_content\\<game-name>\\mapsrc\\Minecraft\".");
    }
    else {
      File optionsGame = new File(materiaPath.toString() + "\\options.txt");
      File optionsProgram = new File("textures\\" + pack.getName() + "\\options.txt");
      if (optionsGame.exists()) {
        TextureOptions toGame = new TextureOptions(optionsGame.toString());
        if (optionsProgram.exists()) {
          TextureOptions toProgram = new TextureOptions(optionsProgram.toString());
          if (toProgram.isNewerThan(toGame)) {
            Console.info("Textures are NOT up to date.");
            moveFolder(materiaPath);
          }
          else {
            Console.info("Textures are up to date.");
          }
        }
        else {
          Console.info("Textures are up to date (there is no \"config.txt\" for the textures to be installed).");
        }
      }
      else {
        Console.info("Textures are NOT up to date (there is no \"config.txt\" for the installed textures).");
        moveFolder(materiaPath);
      }
    }
  }
  
  public void moveFolder(File materiaPath)
  {
    Object[] options = { "Yes", "No" };
    int n = JOptionPane.showOptionDialog(null, "Do you want to update \"" + config.getPack() + "\"-textures?\nFiles in \"" + materiaPath + "\\\" may be overwritten.", "There are new " + config.getPack() + "-textures.", 1, -1, null, options, options[1]);
    






    if (n == 0) {
      File srcFolder = new File("textures\\" + config.getPack());
      TextureFolderMover.moveFolder(materiaPath, srcFolder);
    }
  }
  
  public DefaultMinecraftMap preOpenDialog(SourceGame game)
  {
    Place[] allPlaces = config.getPlaces();
    System.out.print("Found Places: ");
    for (Place place : allPlaces) {
      System.out.print("\"" + place.getName() + "\" ");
    }
    System.out.println("\nenter a name");
    String area = Console.readString();
    Place place = config.getPlace(area);
    if (place == null) {
      System.out.println("creating a new place");
      place = dialogPlaceMissing(area);
      config.addPlace(place);
    }
    else {
      System.out.println("place found");
    }
    placeName = place.getName();
    return getMinecraftMap(place, game, config.getDefaultConvertOption(game), new TexturePack());
  }
  
  private Place dialogPlaceMissing(String placeName)
  {
    Place place = new Place(placeName);
    
    int xCoor = 0;
    int zCoor = 0;
    int xNum = 5;
    int zNum = 5;
    int yStart = 55;
    int yEnd = 100;
    
    System.out.println("enter X-coordinate");
    xCoor = Console.readInt();
    System.out.println("enter Z-coordinate");
    zCoor = Console.readInt();
    
    System.out.println("You can skip the following options with enter.");
    System.out.println("enter #chunks in X (default=" + xNum + ")");
    xNum = Console.readIntOrDefault(xNum);
    System.out.println("enter #chunks in Z (default=" + zNum + ")");
    zNum = Console.readIntOrDefault(zNum);
    
    System.out.println("enter Y start coordinate (height) (default=55)");
    yStart = Console.readIntOrDefault(55);
    System.out.println("enter Y end coordinate (height) (default=" + yEnd + ")");
    yEnd = Console.readIntOrDefault(yEnd);
    if (yStart > yEnd) {
      yStart = yEnd;yEnd = 126;
    }
    if (yStart < 1) yStart = 1;
    if (yEnd > 254) { yEnd = 254;
    }
    File fileFolder = getFileFolder();
    place.setWorld(getNameFromFileFoder(fileFolder));
    
    place.setX(xCoor);
    place.setZ(zCoor);
    place.setNumX(xNum);
    place.setNumZ(zNum);
    place.setYStart(yStart);
    place.setYEnd(yEnd);
    
    return place;
  }
  
  public DefaultMinecraftMap getMinecraftMapOld(Place place, SourceGame game, ConvertOption option, TexturePack texturePack)
  {
    File fileFolder = getFileFolderFromPlace(place);
    System.out.println("mca: " + countFilesInFolderWithEnding(fileFolder, "mca"));
    System.out.println("mcr: " + countFilesInFolderWithEnding(fileFolder, "mcr"));
    try {
      File mcaFile = new File(fileFolder, "r.0.0.mca");
      if (!mcaFile.exists()) {
        File mcrFile = new File(fileFolder, "r.0.0.mcr");
        if (!mcrFile.exists()) {
          System.out.println("Correcting file path.");
          fileFolder = new File(fileFolder, "region");
        }
      }
      mcaFile = new File(fileFolder, "r.0.0.mca");
      System.out.println("read " + mcaFile.toString());
      if (mcaFile.exists() == true) {
        System.out.println("Reading mca files.");
        return DefaultMinecraftMap.openAtPoint(fileFolder, place, texturePack, game, option, "mca");
      }
      
      System.out.println("Reading mcr files.");
      return DefaultMinecraftMap.openAtPoint(fileFolder, place, texturePack, game, option, "mcr");
    }
    catch (HeadlessException e)
    {
      System.out.println(e.getMessage());
    }
    catch (ExceptionInInitializerError e) {
      System.out.println(e.getCause());
    }
    return null;
  }
  


  public DefaultMinecraftMap getMinecraftMap(Place place, SourceGame game, ConvertOption option, TexturePack texturePack)
  {
    File fileFolder = getFileFolderFromPlace(place);
    if ((countFilesInFolderWithEnding(fileFolder, "mca") == 0) && (countFilesInFolderWithEnding(fileFolder, "mcr") == 0))
    {

      fileFolder = new File(fileFolder, "region");
    }
    


    if (countFilesInFolderWithEnding(fileFolder, "mca") > 0)
    {
      return DefaultMinecraftMap.openAtPoint(fileFolder, place, texturePack, game, option, "mca");
    }
    if (countFilesInFolderWithEnding(fileFolder, "mcr") > 0)
    {
      return DefaultMinecraftMap.openAtPoint(fileFolder, place, texturePack, game, option, "mcr");
    }
    Console.error("Did not find any mcr/mca-files.");
    return null;
  }
  
  private int countFilesInFolderWithEnding(File file, String ending) {
    FilenameFilter ff = new EndingFilenameFilter(ending);
    return file.listFiles(ff).length;
  }
  
  private class EndingFilenameFilter implements FilenameFilter {
    private String ending;
    
    public EndingFilenameFilter(String ending) { this.ending = ending; }
    
    public boolean accept(File dir, String name)
    {
      String[] parts = name.split("\\.");
      if (parts.length > 0) {
        return parts[(parts.length - 1)].equals(ending);
      }
      
      return false;
    }
  }
  



  private void editScale(ConvertOption option)
  {
    int scale = option.getScale();
    System.out.println("enter scale (default=" + scale + ")");
    scale = Console.readIntOrDefault(option.getScale());
    if (scale <= 0) scale = option.getScale();
    option.setScale(scale);
  }
  
  private File getFileFolder() {
    System.out.println("Choose region-folder");
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Choose region folder");
    File minecraftSavesDirectory = new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\.minecraft\\saves");
    fileChooser.setCurrentDirectory(minecraftSavesDirectory);
    fileChooser.setFileSelectionMode(1);
    try {
      int state = fileChooser.showOpenDialog(null);
      if (state == 0) {
        return fileChooser.getSelectedFile();
      }
    }
    catch (HeadlessException e)
    {
      System.out.println(e.getMessage());
    }
    return null;
  }
  

  public File getFileFolderFromPlace(Place place)
  {
    File result = config.getWorldFile(place);
    if (!result.exists()) {
      Console.warning("Cannot find : " + result.getName());
      return getFileFolder();
    }
    return result;
  }
  
  public String getNameFromFileFoder(File file) {
    String name = file.getName();
    if (name.equals("region")) {
      name = file.getParentFile().getName();
      if (name.equals("saves")) {
        name = "region";
      }
    }
    return name;
  }
  
  public Config getConfig() {
    return config;
  }
}

package minecraft.map;

import addable.AddableManager;
import addable.MaterialFilter;
import basic.Console;
import buildable.Cuboid;
import buildable.Point;
import buildable.Skin;
import buildable.SkinManager;
import buildable.entity.pointEntity.LightEnvironment;
import buildable.entity.pointEntity.PointEntity;
import buildable.entity.pointEntity.ShadowControl;
import config.ConvertOption;
import config.Place;
import config.SourceGame;
import config.TexturePack;
import cuboidFinder.SubBlockMapCuboidFinder;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import minecraft.McaSection;
import minecraft.NbtMcaReader;
import minecraft.NbtMcrReader;
import minecraft.NbtReader;
import minecraft.OrientationStairs;
import minecraft.RegionFile;
import minecraft.SubBlockPosition;

public class DefaultMinecraftMap
  extends MinecraftMapDelegater
{
  static final int maximumHight = 256;
  int chunkSizeX;
  int chunkSizeZ;
  int verticalStart;
  int verticalEnd;
  String folder;
  private int arraySizeX;
  private int arraySizeY;
  private int arraySizeZ;
  int[][][] materialField;
  int[][][] materiatFieldRerun;
  boolean[][][] isNextToAir;
  byte[][][] data;
  private Map<Point, Integer> subBlocks;
  private Queue<Point> subBlockPositions;
  private boolean[][][] isAirBlock;
  private AddableManager addableManager;
  private int rerun = 0;
  private final ConvertOption convertOption;
  private int scale;
  private int optionScale;
  private Point fieldStart;
  private Point fieldEnd;
  private static final int maxChunkInFileX = 32;
  private static final int maxChunkInFileZ = 32;
  private static final int blocksInChunkX = 16;
  private static final int blocksInChunkZ = 16;
  
  public DefaultMinecraftMap(int sizeXNew, int sizeZNew, int verticalStartNew, int verticalEndNew, TexturePack texturePack, SourceGame game, ConvertOption convertOption) {
    super(new DefaultSourceMap(texturePack, convertOption.getScale()));
    this.convertOption = convertOption;
    target.setSkyTexture(this.convertOption.getSkyTexture());
    folder = texturePack.getTextureFolder();
    assert (convertOption != null);
    scale = convertOption.getScale();
    
    optionScale = convertOption.getScale();
    
    addableManager = new AddableManager(this, game, convertOption.getAddablesAsStrings());
    
    chunkSizeX = sizeXNew;
    chunkSizeZ = sizeZNew;
    verticalStart = verticalStartNew;
    verticalEnd = verticalEndNew;
    
    arraySizeX = (2 + chunkSizeX * 16);
    arraySizeY = (3 + verticalEnd - verticalStart);
    arraySizeZ = (2 + chunkSizeZ * 16);
    
    isNextToAir = new boolean[arraySizeX][arraySizeY][arraySizeZ];
    isAirBlock = new boolean[arraySizeX][arraySizeY][arraySizeZ];
    
    materialField = new int[arraySizeX][arraySizeY][arraySizeZ];
    materiatFieldRerun = new int[arraySizeX][arraySizeY][arraySizeZ];
    
    for (int x = 0; x < arraySizeX; x++) {
      for (int y = 0; y < arraySizeY; y++) {
        for (int z = 0; z < arraySizeZ; z++) {
          materialField[x][y][z] = 0;
          materiatFieldRerun[x][y][z] = 0;
        }
      }
    }
    data = new byte[arraySizeX][arraySizeY][arraySizeZ];
    
    subBlocks = new TreeMap();
    subBlockPositions = new ArrayDeque();
  }
  
  public int getVerticalEnd() {
    return verticalEnd;
  }
  
  public void removeData()
  {
    target.getSkinManager().removeData(this);
  }
  
  public void setMaterialAndIfNeeded(int[][][] materialNew, boolean[][][] isNextToAirNew) {
    materialField = materialNew;
    isNextToAir = isNextToAirNew;
  }


  public void markBorder()
  {
    for (int x = 0; x < this.arraySizeX; x++) {
      for (int z = 0; z < this.arraySizeZ; z++)
      {
        this.isNextToAir[x][0][z] = 1;
        this.isNextToAir[x][(this.arraySizeY - 1)][z] = 1;
      }
    }
    for (int y = 1; y < this.arraySizeY - 2; y++)
    {
      for (int x = 0; x < this.arraySizeX; x++)
      {
        this.isNextToAir[x][y][0] = 1;
        this.isNextToAir[x][y][(this.arraySizeZ - 1)] = 1;
      }
      for (int z = 1; z < this.arraySizeZ - 1; z++)
      {
        this.isNextToAir[0][y][z] = 1;
        this.isNextToAir[(this.arraySizeX - 1)][y][z] = 1;
      }
    }
  }
  
  public void addObjects(SourceMap target)
  {
    for (int x = 1; x < arraySizeX - 1; x++) {
      for (int y = 1; y < arraySizeY - 1; y++) {
        for (int z = 1; z < arraySizeZ - 1; z++) {
          if (isAirBlockInitiate(x, y, z)) {
            isAirBlock[x][y][z] = 1;
            markNeighbors(x, y, z);
          }
          else {
            isAirBlock[x][y][z] = 0;
          }
        }
      }
    }
    markBorder();
    int i = 0;
    do {
      Console.info("run");
      rerun -= 1;
      for (int y = 1; y < arraySizeY - 1; y++) {
        for (int x = 1; x < arraySizeX - 1; x++) {
          for (int z = 1; z < arraySizeZ - 1; z++) {
            if (isNextToAir[x][y][z] == 1) {
              addableManager.add(x, y, z);
            }
          }
        }
      }
      if (rerun > 0) {
        Console.info("run");
        int[][][] temp = materiatFieldRerun;
        materiatFieldRerun = materialField;
        materialField = temp;
        rerun -= 1;
        for (int y = 1; y < arraySizeY - 1; y++) {
          for (int x = 1; x < arraySizeX - 1; x++) {
            for (int z = 1; z < arraySizeZ - 1; z++) {
              if (isNextToAir[x][y][z] == 1) {
                addableManager.add(x, y, z);
              }
            }
          }
        }
        temp = materiatFieldRerun;
        materiatFieldRerun = materialField;
        materialField = temp;
        rerun -= 1;
      }
    } while (rerun > 0);
    addSubBlocksMap();
    addSkyShell();
  }
  

  public void markNeighbors(int x, int y, int z)
  {
    isNextToAir[x][y][z] = 1;
    isNextToAir[(x + 1)][y][z] = 1;
    isNextToAir[(x - 1)][y][z] = 1;
    isNextToAir[x][(y + 1)][z] = 1;
    isNextToAir[x][(y - 1)][z] = 1;
    isNextToAir[x][y][(z + 1)] = 1;
    isNextToAir[x][y][(z - 1)] = 1;
  }
  

  public boolean isAirBlock(int x, int y, int z)
  {
    return isAirBlock[x][y][z];
  }
  
  public boolean isAirBlockInitiate(int x, int y, int z)
  {
    int material = materialField[x][y][z];
    return addableManager.isAirMaterial(material);
  }
  

  public void markAsConverted(Point p)
  {
    markAsConverted(p.getPosX(), p.getPosY(), p.getPosZ());
  }
  
  private void markAsConverted(int x, int y, int z) {
    materialField[x][y][z] = (-materialField[x][y][z]);
  }
  
  public void markAsConverted(Point start, Point end)
  {
    for (int xMark = start.getPosX(); xMark <= end.getPosX(); xMark++) {
      for (int yMark = start.getPosY(); yMark <= end.getPosY(); yMark++) {
        for (int zMark = start.getPosZ(); zMark <= end.getPosZ(); zMark++) {
          markAsConverted(xMark, yMark, zMark);
        }
      }
    }
  }
  








  public static DefaultMinecraftMap openAtPoint(File fileFolder, Place place, TexturePack texturePack, SourceGame game, ConvertOption option, String mapFormat)
  {
    int xCoordinate = place.getX();
    int zCoordinate = place.getZ();
    int xNum = place.getNumX() / 16;
    int zNum = place.getNumZ() / 16;
    int yStart = place.getYStart();
    int yEnd = place.getYEnd();
    
    int chunkStartX = xCoordinate / 16 - xNum / 2;
    int chunkStartZ = zCoordinate / 16 - zNum / 2;
    return open(fileFolder, chunkStartX, chunkStartZ, xNum, zNum, yStart, yEnd, texturePack, game, option, mapFormat);
  }
  
  private static DefaultMinecraftMap open(File fileFolder, int chunkStartX, int chunkStartZ, int chunkNumX, int chunkNumZ, int newZStart, int newZEnd, TexturePack texturePack, SourceGame game, ConvertOption option, String mapFormat)
  {
    int zStart = newZStart;
    int zEnd = newZEnd;
    DefaultMinecraftMap map = new DefaultMinecraftMap(chunkNumX, chunkNumZ, zStart, zEnd, texturePack, game, option);
    
    for (int x = 0; x < arraySizeX; x++) {
      for (int y = 0; y < arraySizeY; y++) {
        for (int z = 0; z < arraySizeZ; z++) {
          materialField[x][y][z] = 0;
          
          isNextToAir[x][y][z] = 1;
        }
      }
    }
    

    for (int a = 0; a < chunkSizeX; a++) {
      for (int b = 0; b < chunkSizeZ; b++) {
        int globalChunkPosX = chunkStartX + a;
        int globalChunkPosZ = chunkStartZ + b;
        int localChunkPosX = getLocalChunkPosX(a + chunkStartX);
        int localChunkPosZ = getLocalChunkPosZ(b + chunkStartZ);
        File file = getFileOfChunk(fileFolder, globalChunkPosX, globalChunkPosZ, mapFormat);
        Console.info("reading local chunk (" + localChunkPosX + "," + localChunkPosZ + ") from " + file.toString());
        RegionFile regionfile = new RegionFile(file);
        if (!file.exists()) {
          System.out.println(mapFormat + "-file does not exist: " + file.toString());
        }
        else if (regionfile.getChunkDataInputStream(localChunkPosX, localChunkPosZ) == null) {
          System.out.println("cannot find chunk in " + file.toString());
        }
        else
        {
          int xPos = 1 + 16 * a;
          int zPos = 1 + 16 * b;
          NbtReader reader; NbtReader reader; if (mapFormat.equals("mca")) {
            reader = new NbtMcaReader(regionfile.getChunkDataInputStream(localChunkPosX, localChunkPosZ), map, xPos, zPos);
          }
          else {
            reader = new NbtMcrReader(regionfile.getChunkDataInputStream(localChunkPosX, localChunkPosZ), map, xPos, zPos);
          }
          
          reader.readChunkData();
        }
      }
    }
    
    map.removeData();
    return map;
  }
  
  private static int getLocalChunkPosX(int globalChunk) {
    while (globalChunk < 0) {
      globalChunk += 32;
    }
    return globalChunk % 32;
  }
  
  private static int getLocalChunkPosZ(int globalChunk) { while (globalChunk < 0) {
      globalChunk += 32;
    }
    return globalChunk % 32;
  }
  
  private static File getFileOfChunk(File fileFolder, int aCoor, int bCoor, String mapFormat)
  {
    int fileX = aCoor / 32;
    if (aCoor < 0) {
      fileX -= 1;
    }
    int fileZ = bCoor / 32;
    if (bCoor < 0) {
      fileZ -= 1;
    }
    return new File(fileFolder, "r." + fileX + "." + fileZ + "." + mapFormat);
  }
  

  public void readData(DataInputStream s, int xPos, int zPos)
  {
    try
    {
      for (int x = xPos; x < xPos + 16; x++) {
        for (int z = zPos; z < zPos + 16; z++) {
          int y = 0;
          for (; y < verticalStart - 1; y += 2) {
            s.readUnsignedByte();
          }
          for (; y < verticalEnd; y += 2) {
            int input = s.readUnsignedByte();
            int first = input >> 4;
            int second = input % 16;
            data[x][(y + 1 - verticalStart)][z] = ((byte)second);
            data[x][(y + 2 - verticalStart)][z] = ((byte)first); }
          int input;
          for (; y < 128; y += 2) {
            input = s.readUnsignedByte();
          }
        }
      }
    }
    catch (FileNotFoundException e) {
      System.out.println("File missing");
    }
    catch (IOException e) {
      System.out.println(e.toString());
    }
  }
  
  public void readBlocks(DataInputStream s, int xPos, int zPos)
  {
    try
    {
      for (int x = xPos; x < xPos + 16; x++) {
        for (int z = zPos; z < zPos + 16; z++) {
          for (int y = 0; 
              y < verticalStart; y++) {
            s.readUnsignedByte();
          }
          for (; y < verticalEnd; y++) {
            int input = s.readUnsignedByte();
            materialField[x][(y + 1 - verticalStart)][z] = input;
            isNextToAir[x][(y + 1 - verticalStart)][z] = 0; }
          int input;
          for (; y < 128; y++) {
            input = s.readUnsignedByte();
          }
        }
      }
    }
    catch (FileNotFoundException e) {
      System.out.println("File missing");
    }
    catch (IOException e) {
      System.out.println(e.toString());
    }
  }
  
  public void addMcaSection(McaSection section) {
    int xPos = section.getAOffset();
    int zPos = section.getBOffset();
    int yOffset = section.getHeight() * 16;
    if ((yOffset <= verticalEnd) && (yOffset + 15 >= verticalStart)) {
      for (int y = 0; (y < 16) && (y < verticalEnd); y++)
      {
        if ((yOffset + y >= verticalStart) && (yOffset + y < verticalEnd)) {
          for (int z = 0; z < 16; z++) {
            for (int x = 0; x < 16; x++) {
              int tempMaterial = section.getBlocks()[x][y][z];
              byte tempData = section.getData()[x][y][z];
              
              materialField[(xPos + x)][(yOffset + y + 1 - verticalStart)][(zPos + z)] = tempMaterial;
              isNextToAir[(xPos + x)][(yOffset + y + 1 - verticalStart)][(zPos + z)] = 0;
              
              data[(xPos + x)][(yOffset + y + 1 - verticalStart)][(zPos + z)] = tempData;
            }
          }
        }
      }
    }
  }
  

  public int getArraySizeX()
  {
    return arraySizeX;
  }
  
  public int getArraySizeZ() {
    return arraySizeZ;
  }
  
  public int getArraySizeY() {
    return arraySizeY;
  }
  
  public int getMaterial(Point p)
  {
    return getMaterial(p.getPosX(), p.getPosY(), p.getPosZ());
  }
  

  public int getMaterial(int x, int y, int z)
  {
    return materialField[x][y][z];
  }
  
  public void addMaterialForRerun(Point p, int material)
  {
    addMaterialForRerun(p.getPosX(), p.getPosY(), p.getPosZ(), material);
  }
  
  public void addMaterialForRerun(int x, int y, int z, int material) {
    materiatFieldRerun[x][y][z] = material;
  }
  
  public boolean hasMaterial(Point p, int material)
  {
    return hasMaterial(p.getPosX(), p.getPosY(), p.getPosZ(), material);
  }
  
  public boolean hasMaterial(int x, int y, int z, int material)
  {
    return materialField[x][y][z] == material;
  }
  
  public boolean hasMaterial(Point p, int[] materials)
  {
    return hasMaterial(p.getPosX(), p.getPosY(), p.getPosZ(), materials);
  }
  
  public boolean hasMaterial(int x, int y, int z, int[] materials)
  {
    for (int material : materials) {
      if (hasMaterial(x, y, z, material)) {
        return true;
      }
    }
    return false;
  }
  

  public boolean hasOrHadMaterial(Point p, int material)
  {
    return hasOrHadMaterial(p.getPosX(), p.getPosY(), p.getPosZ(), material);
  }
  
  private boolean hasOrHadMaterial(int x, int y, int z, int material) {
    return (materialField[x][y][z] == material) || (materialField[x][y][z] == -material);
  }
  

  public boolean hasOrHadMaterial(Point p, int[] materials)
  {
    for (int material : materials) {
      if (hasOrHadMaterial(p, material)) {
        return true;
      }
    }
    return false;
  }
  


  public boolean hasOrHadMaterial(int x, int y, int z, int[] materials)
  {
    for (int material : materials) {
      if (hasOrHadMaterial(x, y, z, material)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean hasOrHadMaterial(Point p, MaterialFilter filter)
  {
    return filter.filter(getMaterial(p));
  }
  
  public void printMaterial(Point p)
  {
    printMaterial(p.getPosX(), p.getPosY(), p.getPosZ());
  }
  
  public void printMaterial(int x, int y, int z)
  {
    System.out.println("at " + x + " " + y + " " + z + " found " + materialField[x][y][z]);
  }
  
  public void setMaterial(int x, int y, int z, int material) {
    materialField[x][y][z] = material;
  }
  
  public int getData(int x, int y, int z)
  {
    return data[x][y][z];
  }
  
  public boolean isNextToAir(int x, int y, int z)
  {
    return isNextToAir[x][y][z];
  }
  
  public int getScale()
  {
    return scale;
  }
  
  public int getDetailScale() {
    return scale;
  }
  
  public void addSubBlock(Point p, SubBlockPosition pos, int materialInt)
  {
    int x = p.getPosX();
    int y = p.getPosY();
    int z = p.getPosZ();
    addSubBlock(x, y, z, pos, materialInt);
  }
  
  public void addSubBlock(int x, int y, int z, SubBlockPosition pos, int materialInt)
  {
    Integer material = new Integer(materialInt);
    x = x * 2 + 1;y = y * 2 + 1;z = z * 2 + 1;
    Point point;
    switch (1.$SwitchMap$minecraft$SubBlockPosition[pos.ordinal()]) {
    case 1: 
      point = new Point(x, y - 1, z);
      break;
    case 2: 
      point = new Point(x, y - 1, z - 1);
      break;
    case 3: 
      point = new Point(x - 1, y - 1, z);
      break;
    case 4: 
      point = new Point(x - 1, y - 1, z - 1);
      break;
    case 5: 
      point = new Point(x, y, z);
      break;
    case 6: 
      point = new Point(x, y, z - 1);
      break;
    case 7: 
      point = new Point(x - 1, y, z);
      break;
    case 8: 
      point = new Point(x - 1, y, z - 1);
      break;
    default: 
      point = new Point(1, 1, 1);
      Console.warning("default cse of subBlocks occured");
    }
    
    subBlocks.put(point, material);
    subBlockPositions.add(point);
  }
  


  public void save(File file)
  {
    addObjects(target);
    target.save(file);
  }
  

  public void addSkyShell()
  {
    int verticalHeight = verticalEnd - verticalStart + 3;
    
    Point aPoint = new Point(0, -arraySizeZ, 0);
    Point hollow = new Point(1, -arraySizeZ + 1, 1);
    Point gPoint = new Point(arraySizeX, 0, verticalHeight + 2);
    aPoint.scale(scale);
    hollow.scale(scale);
    gPoint.scale(scale);
    createHollow(aPoint, hollow, gPoint, SkinManager.SKYBOX);
    
    aPoint = new Point(-scale * 4, 0, (verticalHeight + 2) * scale);
    hollow = new Point(-scale * 3, scale * 1, (verticalHeight + 2 + 1) * scale);
    gPoint = new Point(0, scale * 4, (verticalHeight + 2 + 4) * scale);
    createHollow(aPoint, hollow, gPoint, SkinManager.SKYBOX);
    

    Point p = new Point(-scale * 2, scale * 2, (verticalHeight + 4) * scale);
    target.addPointEntity(p, new LightEnvironment(p, convertOption.getSunLight(), convertOption.getSunAmbient()));
    p.move(scale / 2, 0, 0);
    target.addPointEntity(p, new ShadowControl(p, convertOption.getSunShadow()));
  }
  
  private void createHollow(Point aPoint, Point hollow, Point gPoint, Skin skin) {
    int hollowX = hollow.getPosX() - aPoint.getPosX();
    int hollowY = hollow.getPosY() - aPoint.getPosY();
    int hollowZ = hollow.getPosZ() - aPoint.getPosZ();
    
    target.addSolid(new Cuboid(aPoint, new Point(gPoint.getPosX(), gPoint.getPosY(), aPoint.getPosZ() + hollowZ), skin));
    
    target.addSolid(new Cuboid(new Point(aPoint.getPosX(), aPoint.getPosY(), gPoint.getPosZ() - hollowZ), gPoint, skin));
    

    target.addSolid(new Cuboid(aPoint.getOffsetCopy(0, 0, hollowZ), new Point(aPoint.getPosX() + hollowX, gPoint.getPosY(), gPoint.getPosZ() - hollowZ), skin));
    target.addSolid(new Cuboid(new Point(gPoint.getPosX() - hollowX, aPoint.getPosY(), aPoint.getPosZ() + hollowZ), gPoint.getOffsetCopy(0, 0, -hollowZ), skin));
    

    target.addSolid(new Cuboid(aPoint.getOffsetCopy(hollowX, 0, hollowZ), new Point(gPoint.getPosX() - hollowX, aPoint.getPosY() + hollowY, gPoint.getPosZ() - hollowZ), skin));
    
    target.addSolid(new Cuboid(new Point(aPoint.getPosX() + hollowX, gPoint.getPosY() - hollowY, aPoint.getPosZ() + hollowZ), gPoint.getOffsetCopy(-hollowX, 0, -hollowZ), skin));
  }
  
  private void addSubBlocksMap()
  {
    target.setScale(target.getScale() / 2);
    optionScale /= 2;
    SubBlockMapCuboidFinder finder = new SubBlockMapCuboidFinder(this);
    while (!subBlockPositions.isEmpty()) {
      Point start = (Point)subBlockPositions.remove();
      Integer materialInteger = (Integer)subBlocks.get(start);
      if (materialInteger != null) {
        int material = materialInteger.intValue();
        Point end = finder.getBestXYZ(start, material);
        

        addSolid(createCuboid(start, end, material));
        MarkAsConvertedSubMaterial(start, end);
      }
    }
    optionScale *= 2;
    target.setScale(target.getScale() * 2);
  }
  
  public boolean hasSubMaterial(int x, int y, int z, int material)
  {
    Integer presentMaterial = (Integer)subBlocks.get(new Point(x, y, z));
    if (presentMaterial != null) {
      return presentMaterial.intValue() == material;
    }
    return false;
  }
  
  private void MarkAsConvertedSubMaterial(Point start, Point end) {
    for (int xMark = start.getPosX(); xMark <= end.getPosX(); xMark++) {
      for (int yMark = start.getPosY(); yMark <= end.getPosY(); yMark++) {
        for (int zMark = start.getPosZ(); zMark <= end.getPosZ(); zMark++) {
          subBlocks.remove(new Point(xMark, yMark, zMark));
        }
      }
    }
  }
  


  public void setPointToGrid(Point p)
  {
    setPointToGrid(p.getPosX(), p.getPosY(), p.getPosZ());
  }
  



  public void setPointToGrid(int x, int y, int z)
  {
    target.setPointToGrid(getPointToGrid(x, y, z));
  }
  



  public void movePointInGridDimension(double x, double y, double z)
  {
    target.movePointInGridDimension(x * optionScale, -z * optionScale, y * optionScale);
  }
  
  public Point getPointToGrid(Point p)
  {
    return getPointToGrid(p.getPosX(), p.getPosY(), p.getPosZ());
  }
  
  public Point getPointToGrid(int x, int y, int z)
  {
    return new Point(x * optionScale, -z * optionScale, y * optionScale);
  }
  
  public Point getMovedPointInGridDimension(Point p, double x, double y, double z)
  {
    return p.moveInHammer(x * optionScale, -z * optionScale, y * optionScale);
  }
  
  public void movePointExactly(int x, int y, int z)
  {
    target.movePointExactly(x, -z, y);
  }
  



  public void addPointEntitys(Point p, Point end, int space, PointEntity type)
  {
    Point newP = getPointToGrid(p);
    Point newEnd = getPointToGrid(end.getOffsetCopy(1, 1, 1));
    double temp = y;
    y = y;
    y = temp;
    target.addPointEntitys(newP, newEnd, space, type);
  }
  
  public Point getCenter()
  {
    return new Point(arraySizeX / 2, 129, arraySizeZ / 2);
  }
  
  public void addClipStairs(Point p, OrientationStairs orientationStairs)
  {
    addCustomPointEntity(p, orientationStairs.toString());
  }
  
  public void addCustomPointEntity(Point p, String name)
  {
    target.addCustomPointEntity(getMovedPointInGridDimension(getPointToGrid(p), 0.5D, 0.5D, 0.5D), name);
  }
  
  public void setMaterial(Point p, int material)
  {
    materialField[p.getPosX()][p.getPosY()][p.getPosZ()] = material;
  }
  
  public void enableRerun(int amount)
  {
    if (amount > rerun) {
      rerun = amount;
    }
  }
  
  public Cuboid createCuboid(Point start, Point end, int parts, Point offset, Point negativeOffset, int material)
  {
    double grid = optionScale / parts;
    Point startNew = new Point(x * optionScale + x * grid, (-z - 1.0D) * optionScale + z * grid, y * optionScale + y * grid);
    

    Point endNew = new Point((x + 1.0D) * optionScale - x * grid, -z * optionScale - z * grid, (y + 1.0D) * optionScale - y * grid);
    

    return target.createCuboid(startNew, endNew, material);
  }
  
  public Cuboid createCuboid(Point start, Point end)
  {
    return createCuboid(start, end, 1);
  }
  
  public Cuboid createCuboid(Point start, Point end, int material)
  {
    convertField(start, end);
    fieldStart.scale(optionScale);
    fieldEnd.scale(optionScale);
    return target.createCuboid(fieldStart, fieldEnd, material);
  }
  
  private void convertField(Point start, Point end) {
    fieldStart = new Point(x, -z - 1.0D, y);
    fieldEnd = new Point(x + 1.0D, -z, y + 1.0D);
  }
}

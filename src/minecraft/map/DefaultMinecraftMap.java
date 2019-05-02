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

  public DefaultMinecraftMap(int sizeXNew, int sizeZNew, int verticalStartNew, int verticalEndNew, TexturePack texturePack, SourceGame game, ConvertOption convertOption)
  {
    super(new DefaultSourceMap(texturePack, convertOption.getScale()));
    this.convertOption = convertOption;
    this.target.setSkyTexture(this.convertOption.getSkyTexture());
    this.folder = texturePack.getTextureFolder();
    assert (convertOption != null);
    this.scale = convertOption.getScale();

    this.optionScale = convertOption.getScale();

    this.addableManager = new AddableManager(this, game, convertOption.getAddablesAsStrings());

    this.chunkSizeX = sizeXNew;
    this.chunkSizeZ = sizeZNew;
    this.verticalStart = verticalStartNew;
    this.verticalEnd = verticalEndNew;

    this.arraySizeX = (2 + this.chunkSizeX * 16);
    this.arraySizeY = (3 + this.verticalEnd - this.verticalStart);
    this.arraySizeZ = (2 + this.chunkSizeZ * 16);

    this.isNextToAir = new boolean[this.arraySizeX][this.arraySizeY][this.arraySizeZ];
    this.isAirBlock = new boolean[this.arraySizeX][this.arraySizeY][this.arraySizeZ];

    this.materialField = new int[this.arraySizeX][this.arraySizeY][this.arraySizeZ];
    this.materiatFieldRerun = new int[this.arraySizeX][this.arraySizeY][this.arraySizeZ];
    for (int x = 0; x < this.arraySizeX; x++) {
      for (int y = 0; y < this.arraySizeY; y++) {
        for (int z = 0; z < this.arraySizeZ; z++)
        {
          this.materialField[x][y][z] = 0;
          this.materiatFieldRerun[x][y][z] = 0;
        }
      }
    }
    this.data = new byte[this.arraySizeX][this.arraySizeY][this.arraySizeZ];

    this.subBlocks = new TreeMap();
    this.subBlockPositions = new ArrayDeque();
  }

  public int getVerticalEnd()
  {
    return this.verticalEnd;
  }

  public void removeData()
  {
    this.target.getSkinManager().removeData(this);
  }

  public void setMaterialAndIfNeeded(int[][][] materialNew, boolean[][][] isNextToAirNew)
  {
    this.materialField = materialNew;
    this.isNextToAir = isNextToAirNew;
  }

  public void markBorder()
  {
    for (int x = 0; x < this.arraySizeX; x++) {
      for (int z = 0; z < this.arraySizeZ; z++)
      {
        this.isNextToAir[x][0][z] = true;
        this.isNextToAir[x][(this.arraySizeY - 1)][z] = true;
      }
    }
    for (int y = 1; y < this.arraySizeY - 2; y++)
    {
      for (int x = 0; x < this.arraySizeX; x++)
      {
        this.isNextToAir[x][y][0] = true;
        this.isNextToAir[x][y][(this.arraySizeZ - 1)] = true;
      }
      for (int z = 1; z < this.arraySizeZ - 1; z++)
      {
        this.isNextToAir[0][y][z] = true;
        this.isNextToAir[(this.arraySizeX - 1)][y][z] = true;
      }
    }
  }

  public void addObjects(SourceMap target)
  {
    for (int x = 1; x < this.arraySizeX - 1; x++) {
      for (int y = 1; y < this.arraySizeY - 1; y++) {
        for (int z = 1; z < this.arraySizeZ - 1; z++) {
          if (isAirBlockInitiate(x, y, z))
          {
            this.isAirBlock[x][y][z] = true;
            markNeighbors(x, y, z);
          }
          else
          {
            this.isAirBlock[x][y][z] = false;
          }
        }
      }
    }
    markBorder();
    int i = 0;
    do
    {
      Console.info("run");
      this.rerun -= 1;
      for (int y = 1; y < this.arraySizeY - 1; y++) {
        for (int x = 1; x < this.arraySizeX - 1; x++) {
          for (int z = 1; z < this.arraySizeZ - 1; z++) {
            if (this.isNextToAir[x][y][z]) {
              this.addableManager.add(x, y, z);
            }
          }
        }
      }
      if (this.rerun > 0)
      {
        Console.info("run");
        int[][][] temp = this.materiatFieldRerun;
        this.materiatFieldRerun = this.materialField;
        this.materialField = temp;
        this.rerun -= 1;
        for (int y = 1; y < this.arraySizeY - 1; y++) {
          for (int x = 1; x < this.arraySizeX - 1; x++) {
            for (int z = 1; z < this.arraySizeZ - 1; z++) {
              if (this.isNextToAir[x][y][z]) {
                this.addableManager.add(x, y, z);
              }
            }
          }
        }
        temp = this.materiatFieldRerun;
        this.materiatFieldRerun = this.materialField;
        this.materialField = temp;
        this.rerun -= 1;
      }
    } while (this.rerun > 0);
    addSubBlocksMap();
    addSkyShell();
  }

  public void markNeighbors(int x, int y, int z)
  {
    this.isNextToAir[x][y][z] = true;
    this.isNextToAir[(x + 1)][y][z] = true;
    this.isNextToAir[(x - 1)][y][z] = true;
    this.isNextToAir[x][(y + 1)][z] = true;
    this.isNextToAir[x][(y - 1)][z] = true;
    this.isNextToAir[x][y][(z + 1)] = true;
    this.isNextToAir[x][y][(z - 1)] = true;
  }

  public boolean isAirBlock(int x, int y, int z)
  {
    return this.isAirBlock[x][y][z];
  }

  public boolean isAirBlockInitiate(int x, int y, int z)
  {
    int material = this.materialField[x][y][z];
    return this.addableManager.isAirMaterial(material);
  }

  public void markAsConverted(Point p)
  {
    markAsConverted(p.getPosX(), p.getPosY(), p.getPosZ());
  }

  private void markAsConverted(int x, int y, int z)
  {
    this.materialField[x][y][z] = (-this.materialField[x][y][z]);
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
    for (int x = 0; x < map.arraySizeX; x++) {
      for (int y = 0; y < map.arraySizeY; y++) {
        for (int z = 0; z < map.arraySizeZ; z++)
        {
          map.materialField[x][y][z] = 0;

          map.isNextToAir[x][y][z] = true;
        }
      }
    }
    for (int a = 0; a < map.chunkSizeX; a++) {
      for (int b = 0; b < map.chunkSizeZ; b++)
      {
        int globalChunkPosX = chunkStartX + a;
        int globalChunkPosZ = chunkStartZ + b;
        int localChunkPosX = getLocalChunkPosX(a + chunkStartX);
        int localChunkPosZ = getLocalChunkPosZ(b + chunkStartZ);
        File file = getFileOfChunk(fileFolder, globalChunkPosX, globalChunkPosZ, mapFormat);
        Console.info("reading local chunk (" + localChunkPosX + "," + localChunkPosZ + ") from " + file.toString());
        RegionFile regionfile = new RegionFile(file);
        if (!file.exists())
        {
          System.out.println(mapFormat + "-file does not exist: " + file.toString());
        }
        else if (regionfile.getChunkDataInputStream(localChunkPosX, localChunkPosZ) == null)
        {
          System.out.println("cannot find chunk in " + file.toString());
        }
        else
        {
          int xPos = 1 + 16 * a;
          int zPos = 1 + 16 * b;
          NbtReader reader;
          if (mapFormat.equals("mca")) {
            reader = new NbtMcaReader(regionfile.getChunkDataInputStream(localChunkPosX, localChunkPosZ), map, xPos, zPos);
          } else {
            reader = new NbtMcrReader(regionfile.getChunkDataInputStream(localChunkPosX, localChunkPosZ), map, xPos, zPos);
          }
          reader.readChunkData();
        }
      }
    }
    map.removeData();
    return map;
  }

  private static int getLocalChunkPosX(int globalChunk)
  {
    while (globalChunk < 0) {
      globalChunk += 32;
    }
    return globalChunk % 32;
  }

  private static int getLocalChunkPosZ(int globalChunk)
  {
    while (globalChunk < 0) {
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
        for (int z = zPos; z < zPos + 16; z++)
        {
          int y = 0;
          for (; y < this.verticalStart - 1; y += 2) {
            s.readUnsignedByte();
          }
          for (; y < this.verticalEnd; y += 2)
          {
            int input = s.readUnsignedByte();
            int first = input >> 4;
            int second = input % 16;
            this.data[x][(y + 1 - this.verticalStart)][z] = ((byte)second);
            this.data[x][(y + 2 - this.verticalStart)][z] = ((byte)first);
          }
          int input;
          for (; y < 128; y += 2) {
            input = s.readUnsignedByte();
          }
        }
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File missing");
    }
    catch (IOException e)
    {
      System.out.println(e.toString());
    }
  }

  public void readBlocks(DataInputStream s, int xPos, int zPos)
  {
    try
    {
      for (int x = xPos; x < xPos + 16; x++) {
        for (int z = zPos; z < zPos + 16; z++)
        {
          for (int y = 0; y < this.verticalStart; y++) {
            s.readUnsignedByte();
          }
          for (int y = 0; y < this.verticalEnd; y++)
          {
            int input = s.readUnsignedByte();
            this.materialField[x][(y + 1 - this.verticalStart)][z] = input;
            this.isNextToAir[x][(y + 1 - this.verticalStart)][z] = false;
          }
          int input;
          for (int y = 0; y < 128; y++) {
            input = s.readUnsignedByte();
          }
        }
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File missing");
    }
    catch (IOException e)
    {
      System.out.println(e.toString());
    }
  }

  public void addMcaSection(McaSection section)
  {
    int xPos = section.getAOffset();
    int zPos = section.getBOffset();
    int yOffset = section.getHeight() * 16;
    if ((yOffset <= this.verticalEnd) && (yOffset + 15 >= this.verticalStart)) {
      for (int y = 0; (y < 16) && (y < this.verticalEnd); y++) {
        if ((yOffset + y >= this.verticalStart) && (yOffset + y < this.verticalEnd)) {
          for (int z = 0; z < 16; z++) {
            for (int x = 0; x < 16; x++)
            {
              int tempMaterial = section.getBlocks()[x][y][z];
              byte tempData = section.getData()[x][y][z];

              this.materialField[(xPos + x)][(yOffset + y + 1 - this.verticalStart)][(zPos + z)] = tempMaterial;
              this.isNextToAir[(xPos + x)][(yOffset + y + 1 - this.verticalStart)][(zPos + z)] = false;

              this.data[(xPos + x)][(yOffset + y + 1 - this.verticalStart)][(zPos + z)] = tempData;
            }
          }
        }
      }
    }
  }

  public int getArraySizeX()
  {
    return this.arraySizeX;
  }

  public int getArraySizeZ()
  {
    return this.arraySizeZ;
  }

  public int getArraySizeY()
  {
    return this.arraySizeY;
  }

  public int getMaterial(Point p)
  {
    return getMaterial(p.getPosX(), p.getPosY(), p.getPosZ());
  }

  public int getMaterial(int x, int y, int z)
  {
    return this.materialField[x][y][z];
  }

  public void addMaterialForRerun(Point p, int material)
  {
    addMaterialForRerun(p.getPosX(), p.getPosY(), p.getPosZ(), material);
  }

  public void addMaterialForRerun(int x, int y, int z, int material)
  {
    this.materiatFieldRerun[x][y][z] = material;
  }

  public boolean hasMaterial(Point p, int material)
  {
    return hasMaterial(p.getPosX(), p.getPosY(), p.getPosZ(), material);
  }

  public boolean hasMaterial(int x, int y, int z, int material)
  {
    return this.materialField[x][y][z] == material;
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

  private boolean hasOrHadMaterial(int x, int y, int z, int material)
  {
    return (this.materialField[x][y][z] == material) || (this.materialField[x][y][z] == -material);
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
    System.out.println("at " + x + " " + y + " " + z + " found " + this.materialField[x][y][z]);
  }

  public void setMaterial(int x, int y, int z, int material)
  {
    this.materialField[x][y][z] = material;
  }

  public int getData(int x, int y, int z)
  {
    return this.data[x][y][z];
  }

  public boolean isNextToAir(int x, int y, int z)
  {
    return this.isNextToAir[x][y][z];
  }

  public int getScale()
  {
    return this.scale;
  }

  public int getDetailScale()
  {
    return this.scale;
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
    switch (pos)
    {
      case BOTTOM_EAST_SOUTH:
        point = new Point(x, y - 1, z);
        break;
      case BOTTOM_EAST_NORTH:
        point = new Point(x, y - 1, z - 1);
        break;
      case BOTTOM_WEST_SOUTH:
        point = new Point(x - 1, y - 1, z);
        break;
      case BOTTOM_WEST_NORTH:
        point = new Point(x - 1, y - 1, z - 1);
        break;
      case TOP_EAST_SOUTH:
        point = new Point(x, y, z);
        break;
      case TOP_EAST_NORTH:
        point = new Point(x, y, z - 1);
        break;
      case TOP_WEST_SOUTH:
        point = new Point(x - 1, y, z);
        break;
      case TOP_WEST_NORTH:
        point = new Point(x - 1, y, z - 1);
        break;
      default:
        point = new Point(1, 1, 1);
        Console.warning("default cse of subBlocks occured");
    }
    this.subBlocks.put(point, material);
    this.subBlockPositions.add(point);
  }

  public void save(File file)
  {
    addObjects(this.target);
    this.target.save(file);
  }

  public void addSkyShell()
  {
    int verticalHeight = this.verticalEnd - this.verticalStart + 3;

    Point aPoint = new Point(0, -this.arraySizeZ, 0);
    Point hollow = new Point(1, -this.arraySizeZ + 1, 1);
    Point gPoint = new Point(this.arraySizeX, 0, verticalHeight + 2);
    aPoint.scale(this.scale);
    hollow.scale(this.scale);
    gPoint.scale(this.scale);
    createHollow(aPoint, hollow, gPoint, SkinManager.SKYBOX);

    aPoint = new Point(-this.scale * 4, 0, (verticalHeight + 2) * this.scale);
    hollow = new Point(-this.scale * 3, this.scale * 1, (verticalHeight + 2 + 1) * this.scale);
    gPoint = new Point(0, this.scale * 4, (verticalHeight + 2 + 4) * this.scale);
    createHollow(aPoint, hollow, gPoint, SkinManager.SKYBOX);

    Point p = new Point(-this.scale * 2, this.scale * 2, (verticalHeight + 4) * this.scale);
    this.target.addPointEntity(p, new LightEnvironment(p, this.convertOption.getSunLight(), this.convertOption.getSunAmbient()));
    p.move(this.scale / 2, 0, 0);
    this.target.addPointEntity(p, new ShadowControl(p, this.convertOption.getSunShadow()));
  }

  private void createHollow(Point aPoint, Point hollow, Point gPoint, Skin skin)
  {
    int hollowX = hollow.getPosX() - aPoint.getPosX();
    int hollowY = hollow.getPosY() - aPoint.getPosY();
    int hollowZ = hollow.getPosZ() - aPoint.getPosZ();

    this.target.addSolid(new Cuboid(aPoint, new Point(gPoint.getPosX(), gPoint.getPosY(), aPoint.getPosZ() + hollowZ), skin));

    this.target.addSolid(new Cuboid(new Point(aPoint.getPosX(), aPoint.getPosY(), gPoint.getPosZ() - hollowZ), gPoint, skin));

    this.target.addSolid(new Cuboid(aPoint.getOffsetCopy(0, 0, hollowZ), new Point(aPoint.getPosX() + hollowX, gPoint.getPosY(), gPoint.getPosZ() - hollowZ), skin));
    this.target.addSolid(new Cuboid(new Point(gPoint.getPosX() - hollowX, aPoint.getPosY(), aPoint.getPosZ() + hollowZ), gPoint.getOffsetCopy(0, 0, -hollowZ), skin));

    this.target.addSolid(new Cuboid(aPoint.getOffsetCopy(hollowX, 0, hollowZ), new Point(gPoint.getPosX() - hollowX, aPoint.getPosY() + hollowY, gPoint.getPosZ() - hollowZ), skin));

    this.target.addSolid(new Cuboid(new Point(aPoint.getPosX() + hollowX, gPoint.getPosY() - hollowY, aPoint.getPosZ() + hollowZ), gPoint.getOffsetCopy(-hollowX, 0, -hollowZ), skin));
  }

  private void addSubBlocksMap()
  {
    this.target.setScale(this.target.getScale() / 2);
    this.optionScale /= 2;
    SubBlockMapCuboidFinder finder = new SubBlockMapCuboidFinder(this);
    while (!this.subBlockPositions.isEmpty())
    {
      Point start = (Point)this.subBlockPositions.remove();
      Integer materialInteger = (Integer)this.subBlocks.get(start);
      if (materialInteger != null)
      {
        int material = materialInteger.intValue();
        Point end = finder.getBestXYZ(start, material);

        addSolid(createCuboid(start, end, material));
        MarkAsConvertedSubMaterial(start, end);
      }
    }
    this.optionScale *= 2;
    this.target.setScale(this.target.getScale() * 2);
  }

  public boolean hasSubMaterial(int x, int y, int z, int material)
  {
    Integer presentMaterial = (Integer)this.subBlocks.get(new Point(x, y, z));
    if (presentMaterial != null) {
      return presentMaterial.intValue() == material;
    }
    return false;
  }

  private void MarkAsConvertedSubMaterial(Point start, Point end)
  {
    for (int xMark = start.getPosX(); xMark <= end.getPosX(); xMark++) {
      for (int yMark = start.getPosY(); yMark <= end.getPosY(); yMark++) {
        for (int zMark = start.getPosZ(); zMark <= end.getPosZ(); zMark++) {
          this.subBlocks.remove(new Point(xMark, yMark, zMark));
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
    this.target.setPointToGrid(getPointToGrid(x, y, z));
  }

  public void movePointInGridDimension(double x, double y, double z)
  {
    this.target.movePointInGridDimension(x * this.optionScale, -z * this.optionScale, y * this.optionScale);
  }

  public Point getPointToGrid(Point p)
  {
    return getPointToGrid(p.getPosX(), p.getPosY(), p.getPosZ());
  }

  public Point getPointToGrid(int x, int y, int z)
  {
    return new Point(x * this.optionScale, -z * this.optionScale, y * this.optionScale);
  }

  public Point getMovedPointInGridDimension(Point p, double x, double y, double z)
  {
    return p.moveInHammer(x * this.optionScale, -z * this.optionScale, y * this.optionScale);
  }

  public void movePointExactly(int x, int y, int z)
  {
    this.target.movePointExactly(x, -z, y);
  }

  public void addPointEntitys(Point p, Point end, int space, PointEntity type)
  {
    Point newP = getPointToGrid(p);
    Point newEnd = getPointToGrid(end.getOffsetCopy(1, 1, 1));
    double temp = newP.y;
    newP.y = newEnd.y;
    newEnd.y = temp;
    this.target.addPointEntitys(newP, newEnd, space, type);
  }

  public Point getCenter()
  {
    return new Point(this.arraySizeX / 2, 129, this.arraySizeZ / 2);
  }

  public void addClipStairs(Point p, OrientationStairs orientationStairs)
  {
    addCustomPointEntity(p, orientationStairs.toString());
  }

  public void addCustomPointEntity(Point p, String name)
  {
    this.target.addCustomPointEntity(getMovedPointInGridDimension(getPointToGrid(p), 0.5D, 0.5D, 0.5D), name);
  }

  public void setMaterial(Point p, int material)
  {
    this.materialField[p.getPosX()][p.getPosY()][p.getPosZ()] = material;
  }

  public void enableRerun(int amount)
  {
    if (amount > this.rerun) {
      this.rerun = amount;
    }
  }

  public Cuboid createCuboid(Point start, Point end, int parts, Point offset, Point negativeOffset, int material)
  {
    double grid = this.optionScale / parts;
    Point startNew = new Point(start.x * this.optionScale + offset.x * grid, (-end.z - 1.0D) * this.optionScale + negativeOffset.z * grid, start.y * this.optionScale + offset.y * grid);

    Point endNew = new Point((end.x + 1.0D) * this.optionScale - negativeOffset.x * grid, -start.z * this.optionScale - offset.z * grid, (end.y + 1.0D) * this.optionScale - negativeOffset.y * grid);

    return this.target.createCuboid(startNew, endNew, material);
  }

  public Cuboid createCuboid(Point start, Point end)
  {
    return createCuboid(start, end, 1);
  }

  public Cuboid createCuboid(Point start, Point end, int material)
  {
    convertField(start, end);
    this.fieldStart.scale(this.optionScale);
    this.fieldEnd.scale(this.optionScale);
    return this.target.createCuboid(this.fieldStart, this.fieldEnd, material);
  }

  private void convertField(Point start, Point end)
  {
    this.fieldStart = new Point(start.x, -end.z - 1.0D, start.y);
    this.fieldEnd = new Point(end.x + 1.0D, -start.z, end.y + 1.0D);
  }
}

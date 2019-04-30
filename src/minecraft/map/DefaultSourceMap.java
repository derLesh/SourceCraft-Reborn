package minecraft.map;

import basic.Console;
import buildable.Counter;
import buildable.Cuboid;
import buildable.Free8Point;
import buildable.Orientation;
import buildable.Point;
import buildable.Ramp;
import buildable.Skin;
import buildable.SkinManager;
import buildable.Solid;
import buildable.entity.pointEntity.CustomPointEntity;
import buildable.entity.pointEntity.PointEntity;
import buildable.entity.solidEntity.Buyzone;
import buildable.entity.solidEntity.FuncDetail;
import buildable.entity.solidEntity.FuncIllusionary;
import buildable.entity.solidEntity.SolidEntity;
import config.TexturePack;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Stack;

public class DefaultSourceMap
        implements ExtendedSourceMap
{
  private String skyTexture = "sky_day02_09";
  private int hammerGridSize;
  private Stack<Solid> cs;
  private Stack<PointEntity> pes;
  private Stack<SolidEntity> ses;
  private int scale;
  SkinManager skinManager;
  Point localPoint;
  private static final int cameraX = 2225;
  private static final int cameraY = -1370;
  private static final int cameraZ = 2675;
  private static final int cameraLookX = 1893;
  private static final int cameraLookY = -1513;
  private static final int cameraLookZ = 2615;

  public DefaultSourceMap(TexturePack texturePack, int optionScale)
  {
    this.localPoint = new Point();
    this.cs = new Stack();
    this.pes = new Stack();
    this.ses = new Stack();

    String textureFolder = texturePack.getTextureFolder();
    int textureSize = texturePack.getTextureSize();
    this.skinManager = new SkinManager(textureFolder, textureSize, optionScale);
    this.scale = optionScale;
    this.hammerGridSize = optionScale;
  }

  public void setSkyTexture(String skyTexture)
  {
    this.skyTexture = skyTexture;
  }

  public void addSolid(Solid solid)
  {
    this.cs.push(solid);
  }

  public void addDetail(Solid solid)
  {
    this.ses.push(new FuncDetail(solid));
  }

  public void addDetail(Solid[] solids)
  {
    this.ses.push(new FuncDetail(solids));
  }

  public void addIllusionary(Solid shape)
  {
    this.ses.push(new FuncIllusionary(shape));
  }

  public Cuboid createCuboid(Point start, Point end)
  {
    return createCuboid(start, end, 1);
  }

  public Cuboid createCuboid(Point start, Point end, int material)
  {
    Skin skin = this.skinManager.getSkin(material);
    return new Cuboid(start, end, skin);
  }

  public Ramp createRamp(Cuboid cuboid, Orientation orientation)
  {
    return new Ramp(cuboid, orientation);
  }

  public Ramp createRampCuttet(Cuboid cuboid, Orientation orientation, Orientation cut1, Orientation cut2)
  {
    Ramp result = new Ramp(cuboid, orientation);
    result.cut(cut1);
    result.cut(cut2);
    return result;
  }

  public Free8Point createFree8Point(Point start, Point end, int parts, Point[] offset, boolean align, int material)
  {
    double grid = this.scale / parts;
    Point[] p = new Point[8];
    for (int i = 0; i < 8; i++) {
      p[i] = new Point(start.x * this.scale + offset[i].x * grid, -(start.z * this.scale + offset[i].z * grid), (start.y + end.y - start.y) * this.scale + offset[i].y * grid);
    }
    Skin skin = this.skinManager.getSkin(material);
    Free8Point shape = new Free8Point(p, skin, align);
    return shape;
  }

  public void addBuyZone(Solid shape, boolean isCT)
  {
    this.ses.add(new Buyzone(shape, isCT));
  }

  public void addPointEntity(PointEntity type)
  {
    this.pes.push(type.create(this.localPoint));
  }

  public void addPointEntity(Point origin, PointEntity type)
  {
    this.pes.push(type.create(origin));
  }

  public void addPointEntitys(Point p, Point end, int space, PointEntity type)
  {
    p.moveInHammer(space / 2, space / 2, space / 2);
    while (p.getPosX() <= end.getPosX())
    {
      int distance = 0;
      while (p.getPosY() <= end.getPosY())
      {
        addPointEntity(p, type);
        p.moveInHammer(0.0D, space, 0.0D);
        distance++;
      }
      p.moveInHammer(space, -distance * space, 0.0D);
    }
  }

  public void save(File file)
  {
    try
    {
      Console.info("writing");
      FileWriter w = new FileWriter(file);
      w.write("versioninfo\n{\n\t\"editorversion\" \"400\"\n\t\"editorbuild\" \"5004\"\n\t\"mapversion\" \"1\"\n\t\"formatversion\" \"100\"\n\t\"prefab\" \"0\"\n}\nvisgroups\n{\n}\nviewsettings\n{\n\t\"bSnapToGrid\" \"1\"\n\t\"bShowGrid\" \"1\"\n\t\"bShowLogicalGrid\" \"0\"\n\t\"nGridSpacing\" \"" + this.hammerGridSize + "\"\n" + "\t\"bShow3DGrid\" \"0\"\n" + "}\n" + "world\n{\n" + "\t\"id\" \"1\"\n\t\"mapversion\" \"1\"\n\t\"classname\" \"worldspawn\"\n\t\"detailmaterial\" \"detail/detailsprites\"\n\t\"detailvbsp\" \"detail.vbsp\"\n\t\"maxpropscreenwidth\" \"-1\"\n" + "\t\"skyname\" \"" + this.skyTexture + "\"\n");

      int brushCount = 0;
      Counter counter = new Counter();
      while (!this.cs.empty())
      {
        brushCount++;
        ((Solid)this.cs.pop()).writeVmf(counter, w);
      }
      Console.info(brushCount + " brushes added.");
      w.write("\n}");
      while (!this.ses.empty()) {
        ((SolidEntity)this.ses.pop()).writeVmf(counter, w);
      }
      while (!this.pes.empty()) {
        ((PointEntity)this.pes.pop()).writeVmf(counter, w);
      }
      w.write("\ncameras\n{\n\t\"activecamera\" \"0\"\n camera\n\t{\n\t\t\"position\" \"[2225 -1370 2675]\"\n\t\t\"look\" \"[1893 -1513 2615]\"\n\t}\n}\ncordon\n{\n\t\"mins\" \"(-1024 -1024 -1024)\"\n\t\"maxs\" \"(1024 1024 1024)\"\n\t\"active\" \"0\"\n}\n");

      w.close();
    }
    catch (IOException e)
    {
      System.err.println(e.toString());
    }
  }

  public void setPointToGrid(Point p)
  {
    setPointToGrid(p.getPosX(), p.getPosY(), p.getPosZ());
  }

  public void setPointToGrid(int x, int y, int z)
  {
    this.localPoint.set(x, y, z);
  }

  public void movePointInGridDimension(double x, double y, double z)
  {
    this.localPoint.moveInHammer((int)x, (int)y, (int)z);
  }

  public void movePointExactly(int x, int y, int z)
  {
    this.localPoint.moveInHammer(x, y, z);
  }

  public SkinManager getSkinManager()
  {
    return this.skinManager;
  }

  public void setScale(int scale)
  {
    this.scale = scale;
  }

  public int getScale()
  {
    return this.scale;
  }

  public Point getLocalPoint()
  {
    return this.localPoint;
  }

  public void addCustomPointEntity(Point p, String name)
  {
    this.pes.push(new CustomPointEntity(p, name));
  }
}

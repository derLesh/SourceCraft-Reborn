package cuboidFinder;

import buildable.Point;
import minecraft.map.DefaultMinecraftMap;

public abstract class AbstractCuboidFinder implements CuboidFinder
{
  protected final DefaultMinecraftMap map;

  public AbstractCuboidFinder(DefaultMinecraftMap map)
  {
    this.map = map;
  }

  public Point getBestXYZ(Point p, int material)
  {
    return getBestXYZ(p.getPosX(), p.getPosY(), p.getPosZ(), material);
  }

  public Point getBestXY(Point p, int material)
  {
    return getBestXY(p.getPosX(), p.getPosY(), p.getPosZ(), material);
  }

  public Point getBestXZ(Point p, int material)
  {
    return getBestXZ(p.getPosX(), p.getPosY(), p.getPosZ(), material);
  }

  public Point getBestYZ(Point p, int material)
  {
    return getBestYZ(p.getPosX(), p.getPosY(), p.getPosZ(), material);
  }

  public Point getBestXZ(Point p, int[] materials)
  {
    return getBestXZ(p.getPosX(), p.getPosY(), p.getPosZ(), materials);
  }

  public Point getBestX(Point p, int material)
  {
    return getBestX(p.getPosX(), p.getPosY(), p.getPosZ(), material);
  }

  public Point getBestX(Point p, int[] materials)
  {
    return getBestX(p.getPosX(), p.getPosY(), p.getPosZ(), materials);
  }

  public Point getBestY(Point p, int material)
  {
    return getBestY(p.getPosX(), p.getPosY(), p.getPosZ(), material);
  }

  public Point getBestZ(Point p, int material)
  {
    return getBestZ(p.getPosX(), p.getPosY(), p.getPosZ(), material);
  }

  public Point getBestZ(Point p, int[] materials)
  {
    return getBestZ(p.getPosX(), p.getPosY(), p.getPosZ(), materials);
  }

  public Point getBestXYZ(int x, int y, int z, int material)
  {
    int xRun = x;
    int yRun = y;
    int zRun = z;
    int xBest = x;
    int yBest = y;
    int zBest = z;
    int sizeNew = 1;
    int sizeBest = 1;

    xRun = x;
    while (addXValid(x, y, z, xRun, yRun, zRun, material))
    {
      sizeNew = (xRun - x + 1) * (yRun - y + 1) * (zRun - z + 1);
      if (sizeNew > sizeBest)
      {
        sizeBest = sizeNew;xBest = xRun;yBest = yRun;zBest = zRun;
      }
      yRun = y;
      while (addYValid(x, y, z, xRun, yRun, zRun, material))
      {
        sizeNew = (xRun - x + 1) * (yRun - y + 1) * (zRun - z + 1);
        if (sizeNew > sizeBest)
        {
          sizeBest = sizeNew;xBest = xRun;yBest = yRun;zBest = zRun;
        }
        zRun = z + 1;
        while (addZValid(x, y, z, xRun, yRun, zRun, material))
        {
          sizeNew = (xRun - x + 1) * (yRun - y + 1) * (zRun - z + 1);
          if (sizeNew > sizeBest)
          {
            sizeBest = sizeNew;xBest = xRun;yBest = yRun;zBest = zRun;
          }
          zRun++;
        }
        zRun = z;
        yRun++;
      }
      yRun = y;
      xRun++;
    }
    return new Point(xBest, yBest, zBest);
  }

  public Point getBestXZ(int x, int y, int z, int material)
  {
    int xRun = x;
    int zRun = z;
    int xBest = x;
    int zBest = z;
    int sizeNew = 1;
    int sizeBest = 1;

    xRun = x;
    while (addXValid(x, y, z, xRun, y, zRun, material))
    {
      sizeNew = (xRun - x + 1) * 1 * (zRun - z + 1);
      if (sizeNew > sizeBest)
      {
        sizeBest = sizeNew;xBest = xRun;zBest = zRun;
      }
      zRun = z + 1;
      while (addZValid(x, y, z, xRun, y, zRun, material))
      {
        sizeNew = (xRun - x + 1) * 1 * (zRun - z + 1);
        if (sizeNew > sizeBest)
        {
          sizeBest = sizeNew;xBest = xRun;zBest = zRun;
        }
        zRun++;
      }
      zRun = z;
      xRun++;
    }
    return new Point(xBest, y, zBest);
  }

  public Point getBestXZ(int x, int y, int z, int[] material)
  {
    int xRun = x;
    int zRun = z;
    int xBest = x;
    int zBest = z;
    int sizeNew = 1;
    int sizeBest = 1;

    xRun = x;
    while (addXValid(x, y, z, xRun, y, zRun, material))
    {
      sizeNew = (xRun - x + 1) * 1 * (zRun - z + 1);
      if (sizeNew > sizeBest)
      {
        sizeBest = sizeNew;xBest = xRun;zBest = zRun;
      }
      zRun = z + 1;
      while (addZValid(x, y, z, xRun, y, zRun, material))
      {
        sizeNew = (xRun - x + 1) * 1 * (zRun - z + 1);
        if (sizeNew > sizeBest)
        {
          sizeBest = sizeNew;xBest = xRun;zBest = zRun;
        }
        zRun++;
      }
      zRun = z;
      xRun++;
    }
    return new Point(xBest, y, zBest);
  }

  public Point getBestXY(int x, int y, int z, int material)
  {
    int xRun = x;
    int yRun = y;
    int xBest = x;
    int yBest = y;
    int sizeNew = 1;
    int sizeBest = 1;

    xRun = x;
    while (addXValid(x, y, z, xRun, yRun, z, material))
    {
      sizeNew = (xRun - x + 1) * 1 * (yRun - y + 1);
      if (sizeNew > sizeBest)
      {
        sizeBest = sizeNew;xBest = xRun;yBest = yRun;
      }
      yRun = y + 1;
      while (addYValid(x, y, z, xRun, yRun, z, material))
      {
        sizeNew = (xRun - x + 1) * 1 * (yRun - y + 1);
        if (sizeNew > sizeBest)
        {
          sizeBest = sizeNew;xBest = xRun;yBest = yRun;
        }
        yRun++;
      }
      yRun = y;
      xRun++;
    }
    return new Point(xBest, yBest, z);
  }

  public Point getBestYZ(int x, int y, int z, int material)
  {
    int zRun = z;
    int yRun = y;
    int zBest = z;
    int yBest = y;
    int sizeNew = 1;
    int sizeBest = 1;

    zRun = z;
    while (addYValid(x, y, z, x, yRun, zRun, material))
    {
      sizeNew = (zRun - z + 1) * 1 * (yRun - y + 1);
      if (sizeNew > sizeBest)
      {
        sizeBest = sizeNew;zBest = zRun;yBest = yRun;
      }
      yRun = y + 1;
      while (addZValid(x, y, z, x, yRun, zRun, material))
      {
        sizeNew = (zRun - z + 1) * 1 * (yRun - y + 1);
        if (sizeNew > sizeBest)
        {
          sizeBest = sizeNew;zBest = zRun;yBest = yRun;
        }
        yRun++;
      }
      yRun = y;
      zRun++;
    }
    return new Point(x, yBest, zBest);
  }

  public Point getBestX(int x, int y, int z, int material)
  {
    int xRun = x;
    while (addXValid(x, y, z, xRun, y, z, material)) {
      xRun++;
    }
    int sizeBest = xRun - x;
    return new Point(xRun - 1, y, z);
  }

  public Point getBestX(int x, int y, int z, int[] materials)
  {
    int xRun = x;
    while (addXValid(x, y, z, xRun, y, z, materials)) {
      xRun++;
    }
    return new Point(xRun - 1, y, z);
  }

  public Point getBestY(int x, int y, int z, int material)
  {
    int yRun = y;
    while (addYValid(x, y, z, x, yRun, z, material)) {
      yRun++;
    }
    int sizeBest = yRun - y;
    return new Point(x, yRun - 1, z);
  }

  public Point getBestZ(int x, int y, int z, int material)
  {
    int zRun = z;
    while (addZValid(x, y, z, x, y, zRun, material)) {
      zRun++;
    }
    int sizeBest = zRun - z;
    assert (zRun - 1 >= z);
    return new Point(x, y, zRun - 1);
  }

  public Point getBestZ(int x, int y, int z, int[] materials)
  {
    int zRun = z;
    while (addZValid(x, y, z, x, y, zRun, materials)) {
      zRun++;
    }
    int sizeBest = zRun - z;
    assert (zRun - 1 >= z);
    return new Point(x, y, zRun - 1);
  }

  private boolean addZValid(int x, int y, int z, int xRun, int yRun, int zRun, int material)
  {
    for (int xTest = x; xTest <= xRun; xTest++) {
      for (int yTest = y; yTest <= yRun; yTest++) {
        if (blockNotValid(xTest, yTest, zRun, material)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean addZValid(int x, int y, int z, int xRun, int yRun, int zRun, int[] material)
  {
    for (int xTest = x; xTest <= xRun; xTest++) {
      for (int yTest = y; yTest <= yRun; yTest++) {
        if (blockNotValid(xTest, yTest, zRun, material)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean addYValid(int x, int y, int z, int xRun, int yRun, int zRun, int material)
  {
    for (int xTest = x; xTest <= xRun; xTest++) {
      for (int zTest = z; zTest <= zRun; zTest++) {
        if (blockNotValid(xTest, yRun, zTest, material)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean addXValid(int x, int y, int z, int xRun, int yRun, int zRun, int material)
  {
    for (int yTest = y; yTest <= yRun; yTest++) {
      for (int zTest = z; zTest <= zRun; zTest++) {
        if (blockNotValid(xRun, yTest, zTest, material)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean addXValid(int x, int y, int z, int xRun, int yRun, int zRun, int[] material)
  {
    for (int yTest = y; yTest <= yRun; yTest++) {
      for (int zTest = z; zTest <= zRun; zTest++) {
        if (blockNotValid(xRun, yTest, zTest, material)) {
          return false;
        }
      }
    }
    return true;
  }

  protected abstract boolean blockNotValid(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  protected abstract boolean blockNotValid(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt);
}

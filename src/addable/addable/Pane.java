package addable.addable;

import addable.Addable;
import buildable.Point;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;

public class Pane
        extends Addable
{
  public Pane()
  {
    int[] temp = { 102 };
    this.materialUsedFor = temp;
  }

  public String getName()
  {
    return "pane";
  }

  public void add(Point p, int material)
  {
    Point bestXY = this.cf.getBestXY(p, material);
    Point bestYZ = this.cf.getBestYZ(p, material);
    int sizeXY = p.getRoomSizeTo(bestXY);
    int sizeYZ = p.getRoomSizeTo(bestYZ);
    int parts = 16;
    int yOffset = 0;
    int yStop = 0;
    int xOffset = 0;
    int zOffset = 0;
    int xStop = 0;
    int zStop = 0;
    Point end;
    if (sizeXY > sizeYZ)
    {
      end = bestXY;
      zOffset = 7;
      zStop = 7;
    }
    else if (sizeXY < sizeYZ)
    {
      end = bestYZ;
      xOffset = 7;
      xStop = 7;
    }
    else
    {
      int x = p.getPosX();
      int y = p.getPosY();
      int z = p.getPosZ();
      int sumX = countAirY(x + 1, y, z, bestXY.getPosY()) + countAirY(x - 1, y, z, bestXY.getPosY());
      int sumZ = countAirY(x, y, z + 1, bestYZ.getPosY()) + countAirY(x, y, z - 1, bestYZ.getPosY());
      if (sumX <= sumZ)
      {
        end = bestXY;
        zOffset = 7;
        zStop = 7;
      }
      else
      {
        end = bestYZ;
        xOffset = 7;
        xStop = 7;
      }
    }
    Point offset = new Point(xOffset, yOffset, zOffset);
    Point negativeOffset = new Point(xStop, yStop, zStop);
    this.map.addDetail(this.map.createCuboid(p, end, parts, offset, negativeOffset, material));
    this.map.markAsConverted(p, end);
  }

  private int countAirY(int x, int yStart, int z, int yEnd)
  {
    int sum = 0;
    for (int yRun = yStart; yRun <= yEnd; yRun++) {
      if (this.map.isAirBlock(x, yRun, z)) {
        sum++;
      }
    }
    return sum;
  }
}

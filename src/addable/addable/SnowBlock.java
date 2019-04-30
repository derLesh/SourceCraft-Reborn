package addable.addable;

import addable.Addable;
import buildable.Point;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;






public class SnowBlock
  extends Addable
{
  public SnowBlock()
  {
    int[] temp = { 78 };
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "snowBlock";
  }
  
  public void add(Point p, int material)
  {
    Point end = cf.getBestXZ(p, material);
    int parts = 8;
    Point offset = new Point(0, 0, 0);
    Point negativeOffset = new Point(0, 7, 0);
    map.addDetail(map.createCuboid(p, end, parts, offset, negativeOffset, material));
    map.markAsConverted(p, end);
  }
}

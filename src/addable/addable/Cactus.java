package addable.addable;

import addable.Addable;
import buildable.Point;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;





public class Cactus
  extends Addable
{
  public Cactus()
  {
    int[] temp = { 81 };
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "cactus";
  }
  
  public void add(Point p, int material)
  {
    Point end = cf.getBestY(p, material);
    int parts = 8;
    Point offset = new Point(1, 0, 1);
    Point negativeOffset = new Point(1, 1, 1);
    map.addDetail(map.createCuboid(p, end, parts, offset, negativeOffset, material));
    map.markAsConverted(p, end);
  }
}

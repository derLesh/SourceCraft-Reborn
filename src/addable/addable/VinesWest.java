package addable.addable;

import addable.Addable;
import buildable.Point;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;




public class VinesWest
  extends Addable
{
  public VinesWest()
  {
    int[] temp = { 348 };
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "vinesWest";
  }
  
  public void add(Point p, int material)
  {
    Point end = cf.getBestY(p, material);
    int parts = 8;
    Point offset = new Point(0, 0, 0);
    Point negativeOffset = new Point(7, 0, 0);
    map.addIllusionary(map.createCuboid(p, end, parts, offset, negativeOffset, material));
    map.markAsConverted(p, end);
  }
}

package addable.addable;

import addable.Addable;
import buildable.Point;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;




public class VinesEast
  extends Addable
{
  public VinesEast()
  {
    int[] temp = { 354 };
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "vinesEast";
  }
  
  public void add(Point p, int material)
  {
    Point end = cf.getBestY(p, material);
    int parts = 8;
    Point offset = new Point(7, 0, 0);
    Point negativeOffset = new Point(0, 0, 0);
    map.addIllusionary(map.createCuboid(p, end, parts, offset, negativeOffset, material));
    map.markAsConverted(p, end);
  }
}

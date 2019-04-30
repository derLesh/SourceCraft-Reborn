package addable.addable;

import addable.Addable;
import buildable.Point;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;




public class VinesNorth
  extends Addable
{
  public VinesNorth()
  {
    int[] temp = { 350 };
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "vinesNorth";
  }
  
  public void add(Point p, int material)
  {
    Point end = cf.getBestY(p, material);
    int parts = 8;
    Point offset = new Point(0, 0, 0);
    Point negativeOffset = new Point(0, 0, 7);
    map.addIllusionary(map.createCuboid(p, end, parts, offset, negativeOffset, material));
    map.markAsConverted(p, end);
  }
}

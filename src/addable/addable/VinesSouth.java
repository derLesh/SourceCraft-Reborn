package addable.addable;

import addable.Addable;
import buildable.Point;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;




public class VinesSouth
  extends Addable
{
  public VinesSouth()
  {
    int[] temp = { 106, 347 };
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "vinesSouth";
  }
  
  public void add(Point p, int material)
  {
    Point end = cf.getBestY(p, material);
    int parts = 8;
    Point offset = new Point(0, 0, 7);
    Point negativeOffset = new Point(0, 0, 0);
    map.addIllusionary(map.createCuboid(p, end, parts, offset, negativeOffset, material));
    map.markAsConverted(p, end);
  }
}

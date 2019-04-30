package addable.addable;

import addable.Addable;
import buildable.Point;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;






public class Liquid
  extends Addable
{
  public Liquid()
  {
    int[] temp = { 8, 9, 10, 11 };
    materialUsedFor = temp;
  }
  
  public String getName()
  {
    return "liquid";
  }
  
  public void add(Point p, int material)
  {
    Point end = cf.getBestXYZ(p, material);
    map.addSolid(map.createCuboid(p, end, material));
    map.markAsConverted(p, end);
  }
}

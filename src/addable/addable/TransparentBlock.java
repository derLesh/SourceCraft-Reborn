package addable.addable;

import addable.Addable;
import buildable.Point;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;






public class TransparentBlock
  extends Addable
{
  public TransparentBlock()
  {
    int[] temp = { 18, 20, 79, 366 };
    



    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "transparentBlock";
  }
  
  public void add(Point p, int material)
  {
    Point end = cf.getBestXYZ(p, material);
    map.addSolid(map.createCuboid(p, end, material));
    map.markAsConverted(p, end);
  }
}

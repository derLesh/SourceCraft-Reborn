package addable.addable;

import addable.Addable;
import buildable.Point;
import minecraft.SubBlockPosition;
import minecraft.map.MinecraftMap;

public class Slab extends Addable
{
  public Slab()
  {
    int[] temp = { 44, 296, 297, 298, 299, 300, 301, 302, 126, 400, 401, 402 };
    











    materialUsedFor = temp;
  }
  
  public String getName()
  {
    return "slab";
  }
  
  public void add(Point p, int material)
  {
    int x = p.getPosX();
    int y = p.getPosY();
    int z = p.getPosZ();
    map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_SOUTH, material);
    map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_NORTH, material);
    map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_SOUTH, material);
    map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_NORTH, material);
    map.markAsConverted(p);
  }
}

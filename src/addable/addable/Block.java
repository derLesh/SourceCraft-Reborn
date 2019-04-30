package addable.addable;

import addable.Addable;
import buildable.Orientation;
import buildable.Point;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;

public class Block
  extends Addable
{
  private static int[] materialUsedForStatic = { 1, 2, 3, 4, 5, 7, 12, 13, 14, 15, 16, 17, 257, 258, 295, 260, 19, 20, 21, 22, 24, 25, 29, 33, 34, 35, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 275, 41, 42, 45, 46, 47, 48, 49, 56, 57, 58, 60, 276, 73, 74, 79, 80, 82, 84, 86, 87, 88, 89, 91, 97, 98, 277, 278, 99, 100, 103, 110, 112, 60, 329, 330, 331, 61, 332, 333, 334, 54, 335, 336, 337, 23, 338, 339, 340, 43, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 346, 259, 260, 365, 362, 363, 364, 121, 122, 365, 123, 124, 133, 129, 367 };

  public Block()
  {
    super.setMaterialUsedFor(materialUsedForStatic);
  }
  
  public static int[] getMaterialUsedForStatic() {
    return materialUsedForStatic;
  }
  
  public String getName()
  {
    return "block";
  }
  
  public boolean isAirBlock()
  {
    return false;
  }
  
  public boolean hasWall(Orientation orientation)
  {
    return true;
  }
  
  public void add(Point p, int material)
  {
    Point end = cf.getBestXYZ(p, material);
    map.addSolid(map.createCuboid(p, end, material));
    map.markAsConverted(p, end);
  }
}

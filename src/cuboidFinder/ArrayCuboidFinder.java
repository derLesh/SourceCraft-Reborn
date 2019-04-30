package cuboidFinder;

import minecraft.map.DefaultMinecraftMap;

public class ArrayCuboidFinder extends AbstractCuboidFinder
{
  public ArrayCuboidFinder(DefaultMinecraftMap map)
  {
    super(map);
  }

  protected boolean blockNotValid(int xTest, int yTest, int zTest, int material)
  {
    return (!this.map.hasMaterial(xTest, yTest, zTest, material)) || (!this.map.isNextToAir(xTest, yTest, zTest));
  }

  protected boolean blockNotValid(int xTest, int yTest, int zTest, int[] materials)
  {
    for (int material : materials) {
      if (this.map.hasMaterial(xTest, yTest, zTest, material)) {
        return false;
      }
    }
    return true;
  }
}

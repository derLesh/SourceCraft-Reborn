package cuboidFinder;

import minecraft.map.DefaultMinecraftMap;

public class SubBlockMapCuboidFinder
        extends AbstractCuboidFinder
{
  public SubBlockMapCuboidFinder(DefaultMinecraftMap map)
  {
    super(map);
  }

  protected boolean blockNotValid(int xTest, int yTest, int zTest, int material)
  {
    return !this.map.hasSubMaterial(xTest, yTest, zTest, material);
  }

  protected boolean blockNotValid(int xTest, int yTest, int z, int[] material)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}

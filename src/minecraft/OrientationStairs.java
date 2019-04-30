package minecraft;

import buildable.Orientation;









public enum OrientationStairs
{
  SMALL_EAST_SOUTH(null, 445), 
  SMALL_EAST_NORTH(null, 446), 
  SMALL_WEST_SOUTH(null, 447), 
  SMALL_WEST_NORTH(null, 448), 
  BIG_EAST_SOUTH(null, 449), 
  BIG_EAST_NORTH(null, 450), 
  BIG_WEST_SOUTH(null, 451), 
  BIG_WEST_NORTH(null, 452), 
  EAST(Orientation.EAST, 453), 
  WEST(Orientation.WEST, 454), 
  NORTH(Orientation.NORTH, 455), 
  SOUTH(Orientation.SOUTH, 456);
  
  private final Orientation orientation;
  private final int material;
  
  private OrientationStairs(Orientation orientation, int material) {
    this.orientation = orientation;
    this.material = material;
  }
  
  public Orientation getOrientation() {
    return orientation;
  }
  
  public int getMaterial() {
    return material;
  }
}

package minecraft.map;

import addable.MaterialFilter;
import buildable.Cuboid;
import buildable.Point;
import minecraft.OrientationStairs;
import minecraft.SubBlockPosition;

public abstract interface MinecraftMap
        extends SourceMap
{
  public abstract Cuboid createCuboid(Point paramPoint1, Point paramPoint2, int paramInt1, Point paramPoint3, Point paramPoint4, int paramInt2);

  public abstract int getData(int paramInt1, int paramInt2, int paramInt3);

  public abstract int getMaterial(Point paramPoint);

  public abstract int getMaterial(int paramInt1, int paramInt2, int paramInt3);

  public abstract boolean hasMaterial(Point paramPoint, int paramInt);

  public abstract boolean hasMaterial(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  public abstract boolean hasMaterial(Point paramPoint, int[] paramArrayOfInt);

  public abstract boolean hasMaterial(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt);

  public abstract boolean isAirBlock(int paramInt1, int paramInt2, int paramInt3);

  public abstract boolean isAirBlockInitiate(int paramInt1, int paramInt2, int paramInt3);

  public abstract boolean isNextToAir(int paramInt1, int paramInt2, int paramInt3);

  public abstract void markAsConverted(Point paramPoint);

  public abstract void markAsConverted(Point paramPoint1, Point paramPoint2);

  public abstract void printMaterial(Point paramPoint);

  public abstract void printMaterial(int paramInt1, int paramInt2, int paramInt3);

  public abstract void removeData();

  public abstract boolean hasOrHadMaterial(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt);

  public abstract boolean hasOrHadMaterial(Point paramPoint, int paramInt);

  public abstract boolean hasOrHadMaterial(Point paramPoint, int[] paramArrayOfInt);

  public abstract boolean hasOrHadMaterial(Point paramPoint, MaterialFilter paramMaterialFilter);

  public abstract void addSubBlock(int paramInt1, int paramInt2, int paramInt3, SubBlockPosition paramSubBlockPosition, int paramInt4);

  public abstract void addSubBlock(Point paramPoint, SubBlockPosition paramSubBlockPosition, int paramInt);

  public abstract int getScale();

  public abstract Point getPointToGrid(int paramInt1, int paramInt2, int paramInt3);

  public abstract Point getPointToGrid(Point paramPoint);

  public abstract Point getMovedPointInGridDimension(Point paramPoint, double paramDouble1, double paramDouble2, double paramDouble3);

  public abstract Point getCenter();

  public abstract void addClipStairs(Point paramPoint, OrientationStairs paramOrientationStairs);

  public abstract void setMaterial(Point paramPoint, int paramInt);

  public abstract void enableRerun(int paramInt);

  public abstract void addMaterialForRerun(Point paramPoint, int paramInt);
}

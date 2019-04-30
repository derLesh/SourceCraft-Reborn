package addable;

import buildable.Orientation;
import buildable.Point;

public abstract interface AddableAccess
{
  public abstract void addCuboid(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7);
  
  public abstract void addCuboid(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14);
  
  public abstract void addCuboidDetail(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14);
  
  public abstract void addCuboidDetailScaled(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, double[] paramArrayOfDouble, int paramInt14);
  
  public abstract void addCuboidIllusionary(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14);
  
  public abstract void addFree8Point(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Point[] paramArrayOfPoint, boolean paramBoolean, int paramInt8);
  
  public abstract void addFree8PointScaled(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Point[] paramArrayOfPoint, double[] paramArrayOfDouble, int paramInt8);
  
  public abstract void addRamp(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, Orientation paramOrientation);
  
  public abstract void addRamp(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, Orientation paramOrientation);
  
  public abstract void addPropStatic(String paramString);
  
  public abstract void addPropStatic(String paramString, int paramInt1, int paramInt2, int paramInt3);
  
  public abstract void addLight(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
  
  public abstract void addEnvFire(int paramInt);
  
  public abstract void addInfoParticleSystem();
  
  public abstract void setPoint(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract void movePointInBlockDimension(double paramDouble1, double paramDouble2, double paramDouble3);
  
  public abstract void markAsConverted(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7);
  
  public abstract void markAsConverted(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt);
  
  public abstract boolean hasMaterial(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract boolean hasMaterialIn(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt);
  
  public abstract void printMaterial(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract int getData(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract boolean isAirBlock(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract int getScale();
  
  public abstract boolean hasOrHadMaterial(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}

package cuboidFinder;

import buildable.Point;

public abstract interface CuboidFinder
{
  public abstract Point getBestXYZ(Point paramPoint, int paramInt);
  
  public abstract Point getBestXZ(Point paramPoint, int paramInt);
  
  public abstract Point getBestXZ(Point paramPoint, int[] paramArrayOfInt);
  
  public abstract Point getBestXY(Point paramPoint, int paramInt);
  
  public abstract Point getBestYZ(Point paramPoint, int paramInt);
  
  public abstract Point getBestX(Point paramPoint, int paramInt);
  
  public abstract Point getBestX(Point paramPoint, int[] paramArrayOfInt);
  
  public abstract Point getBestY(Point paramPoint, int paramInt);
  
  public abstract Point getBestZ(Point paramPoint, int paramInt);
  
  public abstract Point getBestZ(Point paramPoint, int[] paramArrayOfInt);
  
  public abstract Point getBestXYZ(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract Point getBestXZ(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract Point getBestXZ(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt);
  
  public abstract Point getBestXY(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract Point getBestYZ(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract Point getBestX(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract Point getBestX(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt);
  
  public abstract Point getBestY(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract Point getBestZ(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract Point getBestZ(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt);
}

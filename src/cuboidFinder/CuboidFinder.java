package cuboidFinder;

import buildable.Point;

public interface CuboidFinder
{
  Point getBestXYZ(Point paramPoint, int paramInt);

  Point getBestXZ(Point paramPoint, int paramInt);

  Point getBestXZ(Point paramPoint, int[] paramArrayOfInt);

  Point getBestXY(Point paramPoint, int paramInt);

  Point getBestYZ(Point paramPoint, int paramInt);

  Point getBestX(Point paramPoint, int paramInt);

  Point getBestX(Point paramPoint, int[] paramArrayOfInt);

  Point getBestY(Point paramPoint, int paramInt);

  Point getBestZ(Point paramPoint, int paramInt);

  Point getBestZ(Point paramPoint, int[] paramArrayOfInt);

  Point getBestXYZ(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  Point getBestXZ(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  Point getBestXZ(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt);

  Point getBestXY(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  Point getBestYZ(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  Point getBestX(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  Point getBestX(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt);

  Point getBestY(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  Point getBestZ(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  Point getBestZ(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt);
}

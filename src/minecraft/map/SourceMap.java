package minecraft.map;

import buildable.Cuboid;
import buildable.Orientation;
import buildable.Point;
import buildable.Ramp;
import buildable.Solid;
import buildable.entity.pointEntity.PointEntity;
import java.io.File;

public abstract interface SourceMap
{
  public abstract void setSkyTexture(String paramString);

  public abstract void addSolid(Solid paramSolid);

  public abstract void addDetail(Solid paramSolid);

  public abstract void addDetail(Solid[] paramArrayOfSolid);

  public abstract void addIllusionary(Solid paramSolid);

  public abstract void addBuyZone(Solid paramSolid, boolean paramBoolean);

  public abstract void addPointEntity(PointEntity paramPointEntity);

  public abstract Cuboid createCuboid(Point paramPoint1, Point paramPoint2);

  public abstract Cuboid createCuboid(Point paramPoint1, Point paramPoint2, int paramInt);

  public abstract Cuboid createFree8Point(Point paramPoint1, Point paramPoint2, int paramInt1, Point[] paramArrayOfPoint, boolean paramBoolean, int paramInt2);

  public abstract Ramp createRamp(Cuboid paramCuboid, Orientation paramOrientation);

  public abstract Ramp createRampCuttet(Cuboid paramCuboid, Orientation paramOrientation1, Orientation paramOrientation2, Orientation paramOrientation3);

  public abstract void addPointEntity(Point paramPoint, PointEntity paramPointEntity);

  public abstract void addPointEntitys(Point paramPoint1, Point paramPoint2, int paramInt, PointEntity paramPointEntity);

  public abstract void save(File paramFile);

  public abstract void setPointToGrid(Point paramPoint);

  public abstract void setPointToGrid(int paramInt1, int paramInt2, int paramInt3);

  public abstract void movePointInGridDimension(double paramDouble1, double paramDouble2, double paramDouble3);

  public abstract void movePointExactly(int paramInt1, int paramInt2, int paramInt3);

  public abstract void addCustomPointEntity(Point paramPoint, String paramString);
}

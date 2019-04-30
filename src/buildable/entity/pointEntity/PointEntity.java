package buildable.entity.pointEntity;

import buildable.Point;
import minecraft.VmfWriter;

public abstract class PointEntity
        implements VmfWriter
{
  protected Point p;

  public PointEntity(Point origin)
  {
    this.p = new Point(origin.x, origin.y, origin.z);
  }

  public abstract PointEntity create(Point paramPoint);
}

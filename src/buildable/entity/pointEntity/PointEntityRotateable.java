package buildable.entity.pointEntity;

import buildable.Point;

public abstract class PointEntityRotateable
        extends PointEntity
{
  private int rotation;

  public PointEntityRotateable(Point origin, int rotation)
  {
    super(origin);
    this.rotation = rotation;
  }

  protected int getRotation()
  {
    return this.rotation;
  }
}

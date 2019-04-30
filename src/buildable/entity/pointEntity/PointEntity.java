package buildable.entity.pointEntity;

import buildable.Point;
import minecraft.VmfWriter;

public abstract class PointEntity
  implements VmfWriter
{
  protected Point p;
  
  public PointEntity(Point origin)
  {
    p = new Point(x, y, z);
  }
  
  public abstract PointEntity create(Point paramPoint);
}

package addable.addable;

import addable.Addable;
import buildable.Point;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;

public class EndPortalFrame
  extends Addable
{
  public EndPortalFrame() {}
  
  public String getName()
  {
    return "endPortalFrame";
  }
  
  public void add(Point p, int material)
  {
    Point end = cf.getBestXZ(p, material);
    int parts = 4;
    Point offset = new Point(0, 0, 0);
    Point negativeOffset = new Point(0, 1, 0);
    map.addDetail(map.createCuboid(p, end, parts, offset, negativeOffset, material));
    map.markAsConverted(p, end);
  }
}

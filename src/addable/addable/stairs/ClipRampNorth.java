package addable.addable.stairs;

import addable.Addable;
import buildable.Orientation;
import buildable.Point;
import buildable.Ramp;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;




public class ClipRampNorth
  extends Addable
{
  public ClipRampNorth()
  {
    int[] temp = { 455 };
    
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "clipRampNorth";
  }
  

  public void add(Point p, int materia)
  {
    Point end = cf.getBestX(p, getMaterialUsedFor());
    Point offset = new Point(0, 0, 1);
    Point negativeOffset = new Point(0, 0, -1);
    Orientation cut1 = null;
    if (map.hasOrHadMaterial(p.getOffsetCopy(-1, 0, 0), 446)) {
      offset.move(-1, 0, 0);
    }
    else if (map.hasOrHadMaterial(p.getOffsetCopy(-1, 0, 0), 452)) {
      offset.move(-1, 0, 0);
    }
    Orientation cut2 = null;
    if (map.hasOrHadMaterial(end.getOffsetCopy(1, 0, 0), 448)) {
      negativeOffset.move(-1, 0, 0);
    }
    else if (map.hasOrHadMaterial(end.getOffsetCopy(1, 0, 0), 450)) {
      negativeOffset.move(-1, 0, 0);
    }
    
    Ramp ramp = map.createRamp(map.createCuboid(p, end, 2, offset, negativeOffset, 345), Orientation.NORTH);
    ramp.cut(cut1);
    ramp.cut(cut2);
    map.addSolid(ramp);
    map.markAsConverted(p, end);
  }
}

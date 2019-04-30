package addable.addable.stairs;

import addable.Addable;
import buildable.Orientation;
import buildable.Point;
import buildable.Ramp;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;

public class ClipRampSouth
  extends Addable
{
  public ClipRampSouth()
  {
    int[] temp = { 456 };
    
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "clipRampSouth";
  }
  

  public void add(Point p, int materia)
  {
    Point pMark = p.copy();
    Point end = cf.getBestX(p, getMaterialUsedFor());
    Point endMark = end.copy();
    Point offset = new Point(0, 0, -1);
    Point negativeOffset = new Point(0, 0, 1);
    Orientation cut1 = null;
    if (map.hasOrHadMaterial(p.getOffsetCopy(-1, 0, 0), 445)) {
      offset.move(-1, 0, 0);
      pMark.move(1, 0, 0);
    }
    else if (map.hasOrHadMaterial(p.getOffsetCopy(-1, 0, 0), 451)) {
      offset.move(-1, 0, 0);
      pMark.move(1, 0, 0);
    }
    Orientation cut2 = null;
    if (map.hasOrHadMaterial(end.getOffsetCopy(1, 0, 0), 447)) {
      negativeOffset.move(-1, 0, 0);
      endMark.move(-1, 0, 0);
    }
    else if (map.hasOrHadMaterial(end.getOffsetCopy(1, 0, 0), 449)) {
      negativeOffset.move(-1, 0, 0);
      endMark.move(-1, 0, 0);
    }
    


    Ramp ramp = map.createRamp(map.createCuboid(p, end, 2, offset, negativeOffset, 345), Orientation.SOUTH);
    ramp.cut(cut1);
    ramp.cut(cut2);
    map.addSolid(ramp);
    map.markAsConverted(p, end);
  }
}

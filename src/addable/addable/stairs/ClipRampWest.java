package addable.addable.stairs;

import addable.Addable;
import buildable.Orientation;
import buildable.Point;
import buildable.Ramp;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;

public class ClipRampWest
  extends Addable
{
  public ClipRampWest()
  {
    int[] temp = { 454, 452, 451, 448, 447 };

    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "clipRampWest";
  }
  

  public void add(Point p, int material)
  {
    Point end = cf.getBestZ(p, getMaterialUsedFor());
    Point offset = new Point(1, 0, 0);
    Point negativeOffset = new Point(-1, 0, 0);
    Orientation cut1 = null;
    if (map.getMaterial(p) == 447) {
      cut1 = Orientation.SOUTH;
      offset.move(0, 0, -1);
    }
    Orientation cut2 = null;
    if (map.getMaterial(end) == 448) {
      cut2 = Orientation.NORTH;
      negativeOffset.move(0, 0, -1);
    }
    
    Ramp ramp = map.createRamp(map.createCuboid(p, end, 2, offset, negativeOffset, 345), Orientation.WEST);
    ramp.cut(cut1);
    ramp.cut(cut2);
    map.addSolid(ramp);
    

    if (map.hasOrHadMaterial(p, 447)) {
      Point neighbour = p.getOffsetCopy(-1, 0, 0);
      int[] materialsStairs = { 445, 451 };
      if (map.hasOrHadMaterial(neighbour, materialsStairs)) {
        offset = new Point(1, 0, -1);
        negativeOffset = new Point(-1, 0, 1);
        
        map.addSolid(map.createRamp(map.createCuboid(neighbour, neighbour, 2, offset, negativeOffset, 345), Orientation.SOUTH));
      }
      else if (!map.hasOrHadMaterial(neighbour, 456)) {
        offset = new Point(2, 0, -1);
        negativeOffset = new Point(-1, 0, 1);
        
        map.addSolid(map.createRamp(map.createCuboid(neighbour, neighbour, 2, offset, negativeOffset, 345), Orientation.SOUTH));
      }
    }
    if (map.hasOrHadMaterial(end, 448)) {
      Point neighbour = end.getOffsetCopy(-1, 0, 0);
      int[] materials = { 446, 452 };
      if (map.hasOrHadMaterial(neighbour, materials)) {
        offset = new Point(1, 0, 1);
        negativeOffset = new Point(-1, 0, -1);
        
        map.addSolid(map.createRamp(map.createCuboid(neighbour, neighbour, 2, offset, negativeOffset, 345), Orientation.NORTH));
      }
      else if (!map.hasOrHadMaterial(neighbour, 455)) {
        offset = new Point(2, 0, 1);
        negativeOffset = new Point(-1, 0, -1);
        
        map.addSolid(map.createRamp(map.createCuboid(neighbour, neighbour, 2, offset, negativeOffset, 345), Orientation.NORTH));
      }
    }
    
    map.markAsConverted(p, end);
  }
}

package addable.addable.stairs;

import addable.Addable;
import buildable.Orientation;
import buildable.Point;
import buildable.Ramp;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;





public class ClipRampEast
  extends Addable
{
  public ClipRampEast()
  {
    int[] temp = { 453, 450, 449, 446, 445 };
    




    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "clipRampEast";
  }
  

  public void add(Point p, int material)
  {
    Point end = cf.getBestZ(p, getMaterialUsedFor());
    Point offset = new Point(-1, 0, 0);
    Point negativeOffset = new Point(1, 0, 0);
    Orientation cut1 = null;
    if (map.getMaterial(p) == 445) {
      offset.move(0, 0, -1);
      cut1 = Orientation.SOUTH;
    }
    Orientation cut2 = null;
    if (map.getMaterial(end) == 446) {
      negativeOffset.move(0, 0, -1);
      cut2 = Orientation.NORTH;
    }
    
    Ramp ramp = map.createRamp(map.createCuboid(p, end, 2, offset, negativeOffset, 345), Orientation.EAST);
    ramp.cut(cut1);
    ramp.cut(cut2);
    map.addSolid(ramp);
    

    if (map.hasOrHadMaterial(p, 445)) {
      Point neighbour = p.getOffsetCopy(1, 0, 0);
      int[] materials = { 447, 449 };
      if (map.hasOrHadMaterial(neighbour, 449)) {
        offset = new Point(-1, 0, -1);
        negativeOffset = new Point(1, 0, 1);
        map.addSolid(map.createRamp(map.createCuboid(neighbour, neighbour, 2, offset, negativeOffset, 345), Orientation.SOUTH));
      }
      else if (!map.hasOrHadMaterial(neighbour, 447))
      {

        if (!map.hasOrHadMaterial(neighbour, 456)) {
          offset = new Point(-1, 0, -1);
          negativeOffset = new Point(2, 0, 1);
          
          map.addSolid(map.createRamp(map.createCuboid(neighbour, neighbour, 2, offset, negativeOffset, 345), Orientation.SOUTH));
        }
      } }
    if (map.hasOrHadMaterial(end, 446)) {
      Point neighbour = end.getOffsetCopy(1, 0, 0);
      if (map.hasOrHadMaterial(neighbour, 450)) {
        offset = new Point(-1, 0, 1);
        negativeOffset = new Point(1, 0, -1);
        
        map.addSolid(map.createRamp(map.createCuboid(neighbour, neighbour, 2, offset, negativeOffset, 345), Orientation.NORTH));
      }
      else if (!map.hasOrHadMaterial(neighbour, 448))
      {

        if (!map.hasOrHadMaterial(neighbour, 455)) {
          offset = new Point(-1, 0, 1);
          negativeOffset = new Point(2, 0, -1);
          
          map.addSolid(map.createRamp(map.createCuboid(neighbour, neighbour, 2, offset, negativeOffset, 345), Orientation.NORTH));
        }
      } }
    map.markAsConverted(p, end);
  }
}

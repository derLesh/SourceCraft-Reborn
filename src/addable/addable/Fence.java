package addable.addable;

import addable.Addable;
import addable.MaterialWallFilter;
import buildable.Orientation;
import buildable.Point;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;





public class Fence
  extends Addable
{
  private static int BEAM_SIDE = 7;
  private static int BEAM_TOP_OFF = 1;
  private static int BEAM_TOP_ON = 12;
  private static int BEAM_MID_OFF = 8;
  private static int BEAM_MID_ON = 5;
  
  public Fence() {
    int[] temp = { 85, 113 };
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "fence";
  }
  
  public void add(Point p, int material)
  {
    Point end = cf.getBestY(p, material);
    
    int parts = 8;
    Point offset = new Point(3, 0, 3);
    Point negativeOffset = new Point(3, 0, 3);
    map.addDetail(map.createCuboid(p, end, parts, offset, negativeOffset, material));
    map.markAsConverted(p, end);
    
    parts = 16;
    while (p.getPosY() <= end.getPosY()) {
      if (map.hasMaterial(p.getOffsetCopy(1, 0, 0), material)) {
        offset = new Point(10, BEAM_TOP_ON, BEAM_SIDE);
        negativeOffset = new Point(10, BEAM_TOP_OFF, BEAM_SIDE);
        map.addDetail(map.createCuboid(p, p.getOffsetCopy(1, 0, 0), parts, offset, negativeOffset, material));
        offset = new Point(10, BEAM_MID_ON, BEAM_SIDE);
        negativeOffset = new Point(10, BEAM_MID_OFF, BEAM_SIDE);
        map.addDetail(map.createCuboid(p, p.getOffsetCopy(1, 0, 0), parts, offset, negativeOffset, material));
      }
      else if (map.hasOrHadMaterial(p.getOffsetCopy(1, 0, 0), new MaterialWallFilter(manager, Orientation.EAST))) {
        offset = new Point(10, BEAM_TOP_ON, BEAM_SIDE);
        negativeOffset = new Point(parts, BEAM_TOP_OFF, BEAM_SIDE);
        map.addDetail(map.createCuboid(p, p.getOffsetCopy(1, 0, 0), parts, offset, negativeOffset, material));
        offset = new Point(10, BEAM_MID_ON, BEAM_SIDE);
        negativeOffset = new Point(parts, BEAM_MID_OFF, BEAM_SIDE);
        map.addDetail(map.createCuboid(p, p.getOffsetCopy(1, 0, 0), parts, offset, negativeOffset, material));
      }
      
      if (map.hasOrHadMaterial(p.getOffsetCopy(-1, 0, 0), new MaterialWallFilter(manager, Orientation.WEST))) {
        offset = new Point(parts, BEAM_TOP_ON, BEAM_SIDE);
        negativeOffset = new Point(10, BEAM_TOP_OFF, BEAM_SIDE);
        map.addDetail(map.createCuboid(p.getOffsetCopy(-1, 0, 0), p, parts, offset, negativeOffset, material));
        offset = new Point(parts, BEAM_MID_ON, BEAM_SIDE);
        negativeOffset = new Point(10, BEAM_MID_OFF, BEAM_SIDE);
        map.addDetail(map.createCuboid(p.getOffsetCopy(-1, 0, 0), p, parts, offset, negativeOffset, material));
      }
      
      if (map.hasMaterial(p.getOffsetCopy(0, 0, 1), material)) {
        offset = new Point(BEAM_SIDE, BEAM_TOP_ON, 10);
        negativeOffset = new Point(BEAM_SIDE, BEAM_TOP_OFF, 10);
        map.addDetail(map.createCuboid(p, p.getOffsetCopy(0, 0, 1), parts, offset, negativeOffset, material));
        offset = new Point(BEAM_SIDE, BEAM_MID_ON, 10);
        negativeOffset = new Point(BEAM_SIDE, BEAM_MID_OFF, 10);
        map.addDetail(map.createCuboid(p, p.getOffsetCopy(0, 0, 1), parts, offset, negativeOffset, material));
      }
      else if (map.hasOrHadMaterial(p.getOffsetCopy(0, 0, 1), new MaterialWallFilter(manager, Orientation.SOUTH))) {
        offset = new Point(BEAM_SIDE, BEAM_TOP_ON, 10);
        negativeOffset = new Point(BEAM_SIDE, BEAM_TOP_OFF, parts);
        map.addDetail(map.createCuboid(p, p.getOffsetCopy(0, 0, 1), parts, offset, negativeOffset, material));
        offset = new Point(BEAM_SIDE, BEAM_MID_ON, 10);
        negativeOffset = new Point(BEAM_SIDE, BEAM_MID_OFF, parts);
        map.addDetail(map.createCuboid(p, p.getOffsetCopy(0, 0, 1), parts, offset, negativeOffset, material));
      }
      
      if (map.hasOrHadMaterial(p.getOffsetCopy(0, 0, -1), new MaterialWallFilter(manager, Orientation.NORTH))) {
        offset = new Point(BEAM_SIDE, BEAM_TOP_ON, parts);
        negativeOffset = new Point(BEAM_SIDE, BEAM_TOP_OFF, 10);
        map.addDetail(map.createCuboid(p.getOffsetCopy(0, 0, -1), p, parts, offset, negativeOffset, material));
        offset = new Point(BEAM_SIDE, BEAM_MID_ON, parts);
        negativeOffset = new Point(BEAM_SIDE, BEAM_MID_OFF, 10);
        map.addDetail(map.createCuboid(p.getOffsetCopy(0, 0, -1), p, parts, offset, negativeOffset, material));
      }
      
      p.move(0, 1, 0);
    }
  }
}

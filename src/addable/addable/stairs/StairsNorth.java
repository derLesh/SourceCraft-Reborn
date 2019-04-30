package addable.addable.stairs;

import addable.Addable;
import buildable.Orientation;
import buildable.Point;
import java.util.LinkedList;
import minecraft.SubBlockPosition;
import minecraft.map.MinecraftMap;








public class StairsNorth
  extends Stairs
{
  public StairsNorth()
  {
    int[] temp = { 316, 319, 322, 325, 328, 395, 417, 424, 431 };
    








    super.setMaterialUsedFor(temp);
  }
  
  public StairsNorth(int material, int materialReplacement) {
    int[] temp = { material };
    super.setMaterialUsedFor(temp);
    this.materialReplacement = materialReplacement;
  }
  
  public Iterable<Addable> getInstances()
  {
    LinkedList<Addable> list = new LinkedList();
    list.add(new StairsNorth(316, 297));
    list.add(new StairsNorth(319, 298));
    list.add(new StairsNorth(322, 299));
    list.add(new StairsNorth(325, 300));
    list.add(new StairsNorth(328, 302));
    list.add(new StairsNorth(395, 400));
    list.add(new StairsNorth(417, 401));
    list.add(new StairsNorth(424, 402));
    list.add(new StairsNorth(431, 296));
    list.add(new ClipRampNorth());
    return list;
  }
  
  public String getName()
  {
    return "stairsNorth";
  }
  
  public boolean hasWall(Orientation orientation)
  {
    return orientation != Orientation.NORTH;
  }
  
  public void getType(Point p)
  {
    int x = p.getPosX();
    int y = p.getPosY();
    int z = p.getPosZ();
    if (map.hasOrHadMaterial(x + 1, y, z, getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_EAST_NORTH);

    }
    else if (map.hasOrHadMaterial(x, y, z + 1, new StairsEast().getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_EAST_NORTH);
      setSubBlock(SubBlockPosition.TOP_EAST_SOUTH);
    }
    else if (!map.hasOrHadMaterial(x, y, z - 1, new StairsWest().getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_EAST_NORTH);
    }
    
    if (map.hasOrHadMaterial(x - 1, y, z, getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_WEST_NORTH);

    }
    else if (map.hasOrHadMaterial(x, y, z + 1, new StairsWest().getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_WEST_NORTH);
      setSubBlock(SubBlockPosition.TOP_WEST_SOUTH);
    }
    else if (!map.hasOrHadMaterial(x, y, z - 1, new StairsEast().getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_WEST_NORTH);
    }
    
    setSubBlock(SubBlockPosition.BOTTOM_EAST_SOUTH);
    setSubBlock(SubBlockPosition.BOTTOM_EAST_NORTH);
    setSubBlock(SubBlockPosition.BOTTOM_WEST_SOUTH);
    setSubBlock(SubBlockPosition.BOTTOM_WEST_NORTH);
  }
}

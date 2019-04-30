package addable.addable.stairs;

import addable.Addable;
import buildable.Orientation;
import buildable.Point;
import java.util.LinkedList;
import minecraft.SubBlockPosition;
import minecraft.map.MinecraftMap;








public class StairsSouth
  extends Stairs
{
  public StairsSouth()
  {
    int[] temp = { 315, 318, 321, 324, 327, 394, 416, 423, 430 };
    








    super.setMaterialUsedFor(temp);
  }
  
  public StairsSouth(int material, int materialReplacement) {
    int[] temp = { material };
    super.setMaterialUsedFor(temp);
    this.materialReplacement = materialReplacement;
  }
  
  public Iterable<Addable> getInstances()
  {
    LinkedList<Addable> list = new LinkedList();
    list.add(new StairsSouth(315, 297));
    list.add(new StairsSouth(318, 298));
    list.add(new StairsSouth(321, 299));
    list.add(new StairsSouth(324, 300));
    list.add(new StairsSouth(327, 302));
    list.add(new StairsSouth(394, 400));
    list.add(new StairsSouth(416, 401));
    list.add(new StairsSouth(423, 402));
    list.add(new StairsSouth(430, 296));
    list.add(new ClipRampSouth());
    return list;
  }
  
  public String getName()
  {
    return "stairsSouth";
  }
  
  public boolean hasWall(Orientation orientation)
  {
    return orientation != Orientation.SOUTH;
  }
  
  public void getType(Point p)
  {
    int x = p.getPosX();
    int y = p.getPosY();
    int z = p.getPosZ();
    if (map.hasOrHadMaterial(x + 1, y, z, getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_EAST_SOUTH);

    }
    else if (map.hasOrHadMaterial(x, y, z - 1, new StairsEast().getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_EAST_SOUTH);
      setSubBlock(SubBlockPosition.TOP_EAST_NORTH);
    }
    else if (!map.hasOrHadMaterial(x, y, z + 1, new StairsWest().getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_EAST_SOUTH);
    }
    
    if (map.hasOrHadMaterial(x - 1, y, z, getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_WEST_SOUTH);

    }
    else if (map.hasOrHadMaterial(x, y, z - 1, new StairsWest().getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_WEST_SOUTH);
      setSubBlock(SubBlockPosition.TOP_WEST_NORTH);
    }
    else if (!map.hasOrHadMaterial(x, y, z + 1, new StairsEast().getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_WEST_SOUTH);
    }
    

    setSubBlock(SubBlockPosition.BOTTOM_EAST_SOUTH);
    setSubBlock(SubBlockPosition.BOTTOM_EAST_NORTH);
    setSubBlock(SubBlockPosition.BOTTOM_WEST_SOUTH);
    setSubBlock(SubBlockPosition.BOTTOM_WEST_NORTH);
  }
}

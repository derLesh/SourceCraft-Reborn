package addable.addable.stairs;

import addable.Addable;
import buildable.Orientation;
import buildable.Point;
import java.util.LinkedList;
import minecraft.SubBlockPosition;
import minecraft.map.MinecraftMap;








public class StairsEast
  extends Stairs
{
  public StairsEast()
  {
    int[] temp = { 53, 67, 108, 109, 114, 134, 135, 136, 128 };
    








    super.setMaterialUsedFor(temp);
  }
  
  public StairsEast(int material, int materialReplacement) {
    int[] temp = { material };
    super.setMaterialUsedFor(temp);
    this.materialReplacement = materialReplacement;
  }
  

  public Iterable<Addable> getInstances()
  {
    LinkedList<Addable> list = new LinkedList();
    list.add(new StairsEast(53, 297));
    list.add(new StairsEast(67, 298));
    list.add(new StairsEast(108, 299));
    list.add(new StairsEast(109, 300));
    list.add(new StairsEast(114, 302));
    list.add(new StairsEast(134, 400));
    list.add(new StairsEast(135, 401));
    list.add(new StairsEast(136, 402));
    list.add(new StairsEast(128, 296));
    list.add(new ClipRampEast());
    return list;
  }
  
  public String getName()
  {
    return "stairsEast";
  }
  
  public boolean hasWall(Orientation orientation)
  {
    return orientation != Orientation.EAST;
  }
  
  protected final void getType(Point p)
  {
    int x = p.getPosX();
    int y = p.getPosY();
    int z = p.getPosZ();
    
    if (map.hasOrHadMaterial(x, y, z + 1, getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_EAST_SOUTH);

    }
    else if (map.hasOrHadMaterial(x - 1, y, z, new StairsSouth().getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_EAST_SOUTH);
      setSubBlock(SubBlockPosition.TOP_WEST_SOUTH);
    }
    else if (!map.hasOrHadMaterial(x + 1, y, z, new StairsNorth().getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_EAST_SOUTH);
    }
    
    if (map.hasOrHadMaterial(x, y, z - 1, getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_EAST_NORTH);

    }
    else if (map.hasOrHadMaterial(x - 1, y, z, new StairsNorth().getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_EAST_NORTH);
      setSubBlock(SubBlockPosition.TOP_WEST_NORTH);
    }
    else if (!map.hasOrHadMaterial(x + 1, y, z, new StairsSouth().getMaterialUsedFor())) {
      setSubBlock(SubBlockPosition.TOP_EAST_NORTH);
    }
    
    setSubBlock(SubBlockPosition.BOTTOM_EAST_SOUTH);
    setSubBlock(SubBlockPosition.BOTTOM_EAST_NORTH);
    setSubBlock(SubBlockPosition.BOTTOM_WEST_SOUTH);
    setSubBlock(SubBlockPosition.BOTTOM_WEST_NORTH);
  }
}

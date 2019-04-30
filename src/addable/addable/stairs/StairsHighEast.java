package addable.addable.stairs;

import addable.Addable;
import buildable.Orientation;
import buildable.Point;
import java.util.LinkedList;
import minecraft.SubBlockPosition;
import minecraft.map.MinecraftMap;







public class StairsHighEast
  extends Addable
{
  private int materialReplacement;
  
  public StairsHighEast()
  {
    int[] temp = { 368, 372, 376, 380, 384, 396, 418, 425, 432 };
    








    super.setMaterialUsedFor(temp);
  }
  
  public StairsHighEast(int material, int materialReplacement) {
    int[] temp = { material };
    super.setMaterialUsedFor(temp);
    this.materialReplacement = materialReplacement;
  }
  
  public Iterable<Addable> getInstances()
  {
    LinkedList<Addable> list = new LinkedList();
    list.add(new StairsHighEast(368, 297));
    list.add(new StairsHighEast(372, 298));
    list.add(new StairsHighEast(376, 299));
    list.add(new StairsHighEast(380, 300));
    list.add(new StairsHighEast(384, 302));
    list.add(new StairsHighEast(396, 400));
    list.add(new StairsHighEast(418, 401));
    list.add(new StairsHighEast(425, 402));
    list.add(new StairsHighEast(432, 296));
    return list;
  }
  
  public String getName()
  {
    return "stairsHighEast";
  }
  
  public boolean hasWall(Orientation orientation)
  {
    return orientation != Orientation.EAST;
  }
  
  public void add(Point p, int material)
  {
    map.markAsConverted(p);
    int x = p.getPosX();
    int y = p.getPosY();
    int z = p.getPosZ();
    if (map.hasOrHadMaterial(x, y, z + 1, getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_SOUTH, materialReplacement);

    }
    else if (map.hasOrHadMaterial(x - 1, y, z, new StairsHighSouth().getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_SOUTH, materialReplacement);
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_SOUTH, materialReplacement);
    }
    else if (!map.hasOrHadMaterial(x + 1, y, z, new StairsHighNorth().getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_SOUTH, materialReplacement);
    }
    
    if (map.hasOrHadMaterial(x, y, z - 1, getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_NORTH, materialReplacement);

    }
    else if (map.hasOrHadMaterial(x - 1, y, z, new StairsHighNorth().getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_NORTH, materialReplacement);
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_NORTH, materialReplacement);
    }
    else if (!map.hasOrHadMaterial(x + 1, y, z, new StairsHighSouth().getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_NORTH, materialReplacement);
    }
    
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_EAST_SOUTH, materialReplacement);
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_EAST_NORTH, materialReplacement);
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_WEST_SOUTH, materialReplacement);
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_WEST_NORTH, materialReplacement);
  }
}

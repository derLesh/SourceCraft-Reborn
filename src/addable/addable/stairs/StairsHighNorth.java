package addable.addable.stairs;

import addable.Addable;
import buildable.Orientation;
import buildable.Point;
import java.util.LinkedList;
import minecraft.SubBlockPosition;
import minecraft.map.MinecraftMap;






public class StairsHighNorth
  extends Addable
{
  private int materialReplacement;
  
  public StairsHighNorth()
  {
    int[] temp = { 371, 375, 379, 383, 387, 399, 421, 428, 435 };
    








    super.setMaterialUsedFor(temp);
  }
  
  public StairsHighNorth(int material, int materialReplacement) {
    int[] temp = { material };
    super.setMaterialUsedFor(temp);
    this.materialReplacement = materialReplacement;
  }
  
  public Iterable<Addable> getInstances()
  {
    LinkedList<Addable> list = new LinkedList();
    list.add(new StairsHighNorth(371, 297));
    list.add(new StairsHighNorth(375, 298));
    list.add(new StairsHighNorth(379, 299));
    list.add(new StairsHighNorth(383, 300));
    list.add(new StairsHighNorth(387, 302));
    list.add(new StairsHighNorth(399, 400));
    list.add(new StairsHighNorth(421, 401));
    list.add(new StairsHighNorth(428, 402));
    list.add(new StairsHighNorth(435, 296));
    return list;
  }
  
  public String getName()
  {
    return "stairsHighNorth";
  }
  
  public boolean hasWall(Orientation orientation)
  {
    return orientation != Orientation.NORTH;
  }
  
  public void add(Point p, int material)
  {
    map.markAsConverted(p);
    int x = p.getPosX();
    int y = p.getPosY();
    int z = p.getPosZ();
    if (map.hasOrHadMaterial(x + 1, y, z, getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_NORTH, materialReplacement);

    }
    else if (map.hasOrHadMaterial(x, y, z + 1, new StairsHighEast().getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_NORTH, materialReplacement);
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_SOUTH, materialReplacement);
    }
    else if (!map.hasOrHadMaterial(x, y, z - 1, new StairsHighWest().getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_NORTH, materialReplacement);
    }
    
    if (map.hasOrHadMaterial(x - 1, y, z, getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_NORTH, materialReplacement);

    }
    else if (map.hasOrHadMaterial(x, y, z + 1, new StairsHighWest().getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_NORTH, materialReplacement);
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_SOUTH, materialReplacement);
    }
    else if (!map.hasOrHadMaterial(x, y, z - 1, new StairsHighEast().getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_NORTH, materialReplacement);
    }
    
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_EAST_SOUTH, materialReplacement);
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_EAST_NORTH, materialReplacement);
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_WEST_SOUTH, materialReplacement);
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_WEST_NORTH, materialReplacement);
  }
}

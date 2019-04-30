package addable.addable.stairs;

import addable.Addable;
import buildable.Orientation;
import buildable.Point;
import java.util.LinkedList;
import minecraft.SubBlockPosition;
import minecraft.map.MinecraftMap;







public class StairsHighWest
  extends Addable
{
  private int materialReplacement;
  
  public StairsHighWest()
  {
    int[] temp = { 369, 373, 377, 381, 385, 397, 419, 426, 433 };
    








    super.setMaterialUsedFor(temp);
  }
  
  public StairsHighWest(int material, int materialReplacement) {
    int[] temp = { material };
    super.setMaterialUsedFor(temp);
    this.materialReplacement = materialReplacement;
  }
  
  public Iterable<Addable> getInstances()
  {
    LinkedList<Addable> list = new LinkedList();
    list.add(new StairsHighWest(369, 297));
    list.add(new StairsHighWest(373, 298));
    list.add(new StairsHighWest(377, 299));
    list.add(new StairsHighWest(381, 300));
    list.add(new StairsHighWest(385, 302));
    list.add(new StairsHighWest(397, 400));
    list.add(new StairsHighWest(419, 401));
    list.add(new StairsHighWest(426, 402));
    list.add(new StairsHighWest(433, 296));
    return list;
  }
  
  public String getName()
  {
    return "stairsHighWest";
  }
  
  public boolean hasWall(Orientation orientation)
  {
    return orientation != Orientation.WEST;
  }
  
  public void add(Point p, int material)
  {
    map.markAsConverted(p);
    int x = p.getPosX();
    int y = p.getPosY();
    int z = p.getPosZ();
    if (map.hasOrHadMaterial(x, y, z + 1, getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_SOUTH, materialReplacement);

    }
    else if (map.hasOrHadMaterial(x + 1, y, z, new StairsHighSouth().getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_SOUTH, materialReplacement);
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_SOUTH, materialReplacement);
    }
    else if (!map.hasOrHadMaterial(x - 1, y, z, new StairsHighNorth().getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_SOUTH, materialReplacement);
    }
    
    if (map.hasOrHadMaterial(x, y, z - 1, getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_NORTH, materialReplacement);

    }
    else if (map.hasOrHadMaterial(x + 1, y, z, new StairsHighNorth().getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_NORTH, materialReplacement);
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_NORTH, materialReplacement);
    }
    else if (!map.hasOrHadMaterial(x - 1, y, z, new StairsHighSouth().getMaterialUsedFor())) {
      map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_NORTH, materialReplacement);
    }
    
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_EAST_SOUTH, materialReplacement);
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_EAST_NORTH, materialReplacement);
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_WEST_SOUTH, materialReplacement);
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_WEST_NORTH, materialReplacement);
  }
}

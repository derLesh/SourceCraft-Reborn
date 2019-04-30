package addable.addable.stairs;

import addable.Addable;
import buildable.Orientation;
import buildable.Point;
import java.util.LinkedList;
import minecraft.SubBlockPosition;
import minecraft.map.MinecraftMap;

public class StairsHighSouth
        extends Addable
{
  private int materialReplacement;

  public StairsHighSouth()
  {
    int[] temp = { 370, 374, 378, 382, 386, 398, 420, 427, 434 };

    super.setMaterialUsedFor(temp);
  }

  public StairsHighSouth(int material, int materialReplacement)
  {
    int[] temp = { material };
    super.setMaterialUsedFor(temp);
    this.materialReplacement = materialReplacement;
  }

  public Iterable<Addable> getInstances()
  {
    LinkedList<Addable> list = new LinkedList();
    list.add(new StairsHighSouth(370, 297));
    list.add(new StairsHighSouth(374, 298));
    list.add(new StairsHighSouth(378, 299));
    list.add(new StairsHighSouth(382, 300));
    list.add(new StairsHighSouth(386, 302));
    list.add(new StairsHighSouth(398, 400));
    list.add(new StairsHighSouth(420, 401));
    list.add(new StairsHighSouth(427, 402));
    list.add(new StairsHighSouth(434, 296));
    return list;
  }

  public String getName()
  {
    return "stairsHighSouth";
  }

  public boolean hasWall(Orientation orientation)
  {
    return orientation != Orientation.SOUTH;
  }

  public void add(Point p, int material)
  {
    this.map.markAsConverted(p);
    int x = p.getPosX();
    int y = p.getPosY();
    int z = p.getPosZ();
    if (this.map.hasOrHadMaterial(x + 1, y, z, getMaterialUsedFor()))
    {
      this.map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_SOUTH, this.materialReplacement);
    }
    else if (this.map.hasOrHadMaterial(x, y, z - 1, new StairsHighEast().getMaterialUsedFor()))
    {
      this.map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_SOUTH, this.materialReplacement);
      this.map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_NORTH, this.materialReplacement);
    }
    else if (!this.map.hasOrHadMaterial(x, y, z + 1, new StairsHighWest().getMaterialUsedFor()))
    {
      this.map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_EAST_SOUTH, this.materialReplacement);
    }
    if (this.map.hasOrHadMaterial(x - 1, y, z, getMaterialUsedFor()))
    {
      this.map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_SOUTH, this.materialReplacement);
    }
    else if (this.map.hasOrHadMaterial(x, y, z - 1, new StairsHighWest().getMaterialUsedFor()))
    {
      this.map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_SOUTH, this.materialReplacement);
      this.map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_NORTH, this.materialReplacement);
    }
    else if (!this.map.hasOrHadMaterial(x, y, z + 1, new StairsHighEast().getMaterialUsedFor()))
    {
      this.map.addSubBlock(x, y, z, SubBlockPosition.BOTTOM_WEST_SOUTH, this.materialReplacement);
    }
    this.map.addSubBlock(x, y, z, SubBlockPosition.TOP_EAST_SOUTH, this.materialReplacement);
    this.map.addSubBlock(x, y, z, SubBlockPosition.TOP_EAST_NORTH, this.materialReplacement);
    this.map.addSubBlock(x, y, z, SubBlockPosition.TOP_WEST_SOUTH, this.materialReplacement);
    this.map.addSubBlock(x, y, z, SubBlockPosition.TOP_WEST_NORTH, this.materialReplacement);
  }
}

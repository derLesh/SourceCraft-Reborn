package addable.addable.stairs;

import addable.Addable;
import buildable.Point;
import minecraft.OrientationStairs;
import minecraft.SubBlockPosition;
import minecraft.map.MinecraftMap;



public abstract class Stairs
  extends Addable
{
  protected int materialReplacement;
  
  public Stairs() {}
  
  protected boolean[] subField = new boolean[8];
  
  protected final void clearSubField() {
    for (int i = 0; i < 8; i++) {
      subField[i] = false;
    }
  }
  
  public final void add(Point p, int material)
  {
    map.markAsConverted(p);
    clearSubField();
    getType(p);
    for (SubBlockPosition subBlockPosition : SubBlockPosition.values()) {
      if (subField[subBlockPosition.ordinal()] == 1) {
        map.addSubBlock(p, subBlockPosition, materialReplacement);
      }
    }
    addClipRamp(p);
  }
  
  abstract void getType(Point paramPoint);
  
  protected void setSubBlock(SubBlockPosition position) {
    assert (subField[SubBlockPosition.getPos(position)] == 0) : ("position = " + position);
    subField[SubBlockPosition.getPos(position)] = true;
  }
  
  private void addClipRamp(Point p, OrientationStairs o)
  {
    map.addMaterialForRerun(p, o.getMaterial());
  }
  
  protected final void addClipRamp(Point p) {
    map.enableRerun(1);
    if (subField[SubBlockPosition.TOP_EAST_SOUTH.ordinal()] != 0) {
      if (subField[SubBlockPosition.TOP_EAST_NORTH.ordinal()] != 0) {
        if (subField[SubBlockPosition.TOP_WEST_NORTH.ordinal()] != 0) {
          addClipRamp(p, OrientationStairs.BIG_EAST_NORTH);
        }
        else if (subField[SubBlockPosition.TOP_WEST_SOUTH.ordinal()] != 0) {
          addClipRamp(p, OrientationStairs.BIG_EAST_SOUTH);
        }
        else {
          addClipRamp(p, OrientationStairs.EAST);
        }
      }
      else if (subField[SubBlockPosition.TOP_WEST_SOUTH.ordinal()] != 0) {
        if (subField[SubBlockPosition.TOP_WEST_NORTH.ordinal()] != 0) {
          addClipRamp(p, OrientationStairs.BIG_WEST_SOUTH);
        }
        else {
          addClipRamp(p, OrientationStairs.SOUTH);
        }
      }
      else {
        addClipRamp(p, OrientationStairs.SMALL_EAST_SOUTH);
      }
    }
    else if (subField[SubBlockPosition.TOP_WEST_SOUTH.ordinal()] != 0) {
      if (subField[SubBlockPosition.TOP_WEST_NORTH.ordinal()] != 0) {
        if (subField[SubBlockPosition.TOP_EAST_NORTH.ordinal()] != 0) {
          addClipRamp(p, OrientationStairs.BIG_WEST_NORTH);
        }
        else {
          addClipRamp(p, OrientationStairs.WEST);
        }
      }
      else {
        addClipRamp(p, OrientationStairs.SMALL_WEST_SOUTH);
      }
    }
    else if (subField[SubBlockPosition.TOP_WEST_NORTH.ordinal()] != 0) {
      if (subField[SubBlockPosition.TOP_EAST_NORTH.ordinal()] != 0) {
        addClipRamp(p, OrientationStairs.NORTH);
      }
      else {
        addClipRamp(p, OrientationStairs.SMALL_WEST_NORTH);
      }
    }
    else {
      addClipRamp(p, OrientationStairs.SMALL_EAST_NORTH);
    }
  }
}

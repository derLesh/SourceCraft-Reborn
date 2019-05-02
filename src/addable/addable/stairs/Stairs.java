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
  protected boolean[] subField = new boolean[8];

  protected final void clearSubField()
  {
    for (int i = 0; i < 8; i++) {
      this.subField[i] = false;
    }
  }

  public final void add(Point p, int material)
  {
    this.map.markAsConverted(p);
    clearSubField();
    getType(p);
    for (SubBlockPosition subBlockPosition : SubBlockPosition.values()) {
      if (this.subField[subBlockPosition.ordinal()]) {
        this.map.addSubBlock(p, subBlockPosition, this.materialReplacement);
      }
    }
    addClipRamp(p);
  }

  abstract void getType(Point paramPoint);

  protected void setSubBlock(SubBlockPosition position)
  {
    assert (this.subField[SubBlockPosition.getPos(position)] == false) : ("position = " + position);
    this.subField[SubBlockPosition.getPos(position)] = true;
  }

  private void addClipRamp(Point p, OrientationStairs o)
  {
    this.map.addMaterialForRerun(p, o.getMaterial());
  }

  protected final void addClipRamp(Point p)
  {
    this.map.enableRerun(1);
    if (this.subField[SubBlockPosition.TOP_EAST_SOUTH.ordinal()] != false)
    {
      if (this.subField[SubBlockPosition.TOP_EAST_NORTH.ordinal()] != false)
      {
        if (this.subField[SubBlockPosition.TOP_WEST_NORTH.ordinal()] != false) {
          addClipRamp(p, OrientationStairs.BIG_EAST_NORTH);
        } else if (this.subField[SubBlockPosition.TOP_WEST_SOUTH.ordinal()] != false) {
          addClipRamp(p, OrientationStairs.BIG_EAST_SOUTH);
        } else {
          addClipRamp(p, OrientationStairs.EAST);
        }
      }
      else if (this.subField[SubBlockPosition.TOP_WEST_SOUTH.ordinal()] != false)
      {
        if (this.subField[SubBlockPosition.TOP_WEST_NORTH.ordinal()] != false) {
          addClipRamp(p, OrientationStairs.BIG_WEST_SOUTH);
        } else {
          addClipRamp(p, OrientationStairs.SOUTH);
        }
      }
      else {
        addClipRamp(p, OrientationStairs.SMALL_EAST_SOUTH);
      }
    }
    else if (this.subField[SubBlockPosition.TOP_WEST_SOUTH.ordinal()] != false)
    {
      if (this.subField[SubBlockPosition.TOP_WEST_NORTH.ordinal()] != false)
      {
        if (this.subField[SubBlockPosition.TOP_EAST_NORTH.ordinal()] != false) {
          addClipRamp(p, OrientationStairs.BIG_WEST_NORTH);
        } else {
          addClipRamp(p, OrientationStairs.WEST);
        }
      }
      else {
        addClipRamp(p, OrientationStairs.SMALL_WEST_SOUTH);
      }
    }
    else if (this.subField[SubBlockPosition.TOP_WEST_NORTH.ordinal()] != false)
    {
      if (this.subField[SubBlockPosition.TOP_EAST_NORTH.ordinal()] != false) {
        addClipRamp(p, OrientationStairs.NORTH);
      } else {
        addClipRamp(p, OrientationStairs.SMALL_WEST_NORTH);
      }
    }
    else {
      addClipRamp(p, OrientationStairs.SMALL_EAST_NORTH);
    }
  }
}

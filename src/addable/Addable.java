package addable;

import basic.Console;
import buildable.Orientation;
import buildable.Point;
import cuboidFinder.CuboidFinder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import minecraft.map.MinecraftMap;

public abstract class Addable
{
  protected CuboidFinder cf;
  protected MinecraftMap map;
  protected AddableManager manager;
  protected int[] materialUsedFor = new int[0];
  private int[][] additionalMaterial = (int[][])null;

  public Iterable<Addable> getInstances()
  {
    List<Addable> list = new LinkedList();
    try
    {
      Addable a = (Addable)getClass().getConstructors()[0].newInstance(new Object[0]);
      list.add(a);
    }
    catch (InstantiationException ex)
    {
      Console.warning("Addable " + getName() + " does not has a suitable constructor (InstantiationException)");
    }
    catch (IllegalAccessException ex)
    {
      Console.warning("Addable " + getName() + " does not has a suitable constructor (IllegalAccessException)");
    }
    catch (IllegalArgumentException ex)
    {
      Console.warning("Addable " + getName() + " does not has a suitable constructor (IllegalArgumentException)");
    }
    catch (InvocationTargetException ex)
    {
      Console.warning("Addable " + getName() + " does not has a suitable constructor (InvocationTargetException)");
    }
    return list;
  }

  public abstract String getName();

  public void setAccess(CuboidFinder cf, MinecraftMap map, AddableManager manager)
  {
    this.cf = cf;
    this.map = map;
    this.manager = manager;
  }

  public boolean isAirBlock()
  {
    return true;
  }

  public boolean hasWall(Orientation orientation)
  {
    return false;
  }

  public int[] getMaterialUsedFor()
  {
    return this.materialUsedFor;
  }

  public void setMaterialUsedFor(int[] material)
  {
    this.materialUsedFor = material;
  }

  public void setMaterialUsedFor(int material)
  {
    this.materialUsedFor = new int[1];
    this.materialUsedFor[0] = material;
  }

  public void setAdditionalMaterial(int[][] material)
  {
    this.additionalMaterial = material;
  }

  public int[][] getAdditionalMaterial()
  {
    return this.additionalMaterial;
  }

  public abstract void add(Point paramPoint, int paramInt);
}

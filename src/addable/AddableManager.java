package addable;

import addable.addable.Block;
import addable.addable.Cactus;
import addable.addable.CssLamp;
import addable.addable.EndPortalFrame;
import addable.addable.Fence;
import addable.addable.Fire;
import addable.addable.Liquid;
import addable.addable.Pane;
import addable.addable.PlayerSpawnCss;
import addable.addable.PlayerSpawnTf2;
import addable.addable.Slab;
import addable.addable.SlabHigh;
import addable.addable.SnowBlock;
import addable.addable.TfLilypad;
import addable.addable.TfTallGrass;
import addable.addable.Torch;
import addable.addable.TorchEast;
import addable.addable.TorchNorth;
import addable.addable.TorchSouth;
import addable.addable.TorchWest;
import addable.addable.TransparentBlock;
import addable.addable.VinesEast;
import addable.addable.VinesNorth;
import addable.addable.VinesSouth;
import addable.addable.VinesWest;
import addable.addable.stairs.StairsEast;
import addable.addable.stairs.StairsHighEast;
import addable.addable.stairs.StairsHighNorth;
import addable.addable.stairs.StairsHighSouth;
import addable.addable.stairs.StairsHighWest;
import addable.addable.stairs.StairsNorth;
import addable.addable.stairs.StairsSouth;
import addable.addable.stairs.StairsWest;
import buildable.Point;
import config.SourceGame;
import cuboidFinder.ArrayCuboidFinder;
import cuboidFinder.CuboidFinder;
import java.util.Stack;
import minecraft.MaterialSet;
import minecraft.map.DefaultMinecraftMap;

public class AddableManager
{
  private DefaultMinecraftMap map;
  private CuboidFinder cf;
  private Addable[] a;
  private MaterialSet[] additionalMaterial = new MaterialSet['?'];

  public AddableManager(DefaultMinecraftMap newMine, SourceGame game, String[] addablesString)
  {
    this.map = newMine;
    this.cf = new ArrayCuboidFinder(newMine);
    this.a = new Addable['?'];

    Addable[] addablesStringPool = { new Block(), new Cactus(), new CssLamp(), new Fence(), new Fire(), new Liquid(), new Pane(), new Slab(), new SnowBlock(), new StairsEast(), new StairsNorth(), new StairsSouth(), new StairsWest(), new TfLilypad(), new TfTallGrass(), new TransparentBlock(), new EndPortalFrame(), new VinesEast(), new VinesNorth(), new VinesSouth(), new VinesWest(), new TorchNorth(), new TorchSouth(), new TorchEast(), new TorchWest(), new Torch(), new SlabHigh(), new StairsHighEast(), new StairsHighNorth(), new StairsHighSouth(), new StairsHighWest(), new PlayerSpawnCss(), new PlayerSpawnTf2() };

    Stack<Addable> addable = new Stack();
    for (Addable pool : addablesStringPool) {
      for (String toBeAdded : addablesString) {
        if (pool.getName().equals(toBeAdded)) {
          for (Addable a : pool.getInstances()) {
            addable.push(a);
          }
        }
      }
    }
    loadInternPlugins(addable);
  }

  private void loadInternPlugins(Stack<Addable> addable)
  {
    for (Addable ad : addable)
    {
      ad.setAccess(this.cf, this.map, this);
      int[] materialsUsed = ad.getMaterialUsedFor();
      for (int i = 0; i < materialsUsed.length; i++)
      {
        int material = materialsUsed[i];
        addAddable(material, ad);
      }
    }
  }

  private void addAddable(int material, Addable addable)
  {
    this.a[material] = addable;
  }

  public Addable getAddable(int material)
  {
    try
    {
      if (material < 0) {
        material = -material;
      }
      if (this.a[material] == null) {}
      return this.a[material];
    }
    catch (ArrayIndexOutOfBoundsException e) {}
    return null;
  }

  public void add(int x, int y, int z)
  {
    Point p = new Point();
    int material = this.map.getMaterial(x, y, z);
    if ((material > 0) && (material < 512) &&
            (this.a[material] != null)) {
      this.a[material].add(p.set(x, y, z), material);
    }
  }

  public boolean isAirMaterial(int material)
  {
    if ((material > 0) && (material < 512) &&
            (this.a[material] != null)) {
      return this.a[material].isAirBlock();
    }
    return true;
  }
}

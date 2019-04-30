package addable.addable;

import addable.Addable;
import addable.AddableManager;
import buildable.Color;
import buildable.Point;
import buildable.entity.pointEntity.EnvFire;
import buildable.entity.pointEntity.Light;
import cuboidFinder.CuboidFinder;
import minecraft.map.MinecraftMap;








public class Fire
  extends Addable
{
  private static final int red = 255;
  private static final int green = 113;
  private static final int blue = 28;
  private static final int brigthness = 100;
  private static final int distance50 = 96;
  private static final int distance100 = 256;
  private static final Color FIRE_COLOR = new Color(255, 113, 28, 100);
  private static Light LIGHT = new Light(FIRE_COLOR, 96, 256);
  private static EnvFire ENV_FIRE;
  
  public Fire() {
    int[] temp = { 51 };
    super.setMaterialUsedFor(temp);
  }
  
  public void setAccess(CuboidFinder cf, MinecraftMap map, AddableManager manager)
  {
    super.setAccess(cf, map, manager);
    ENV_FIRE = new EnvFire(map.getScale() / 2);
  }
  
  public String getName()
  {
    return "fire";
  }
  
  public void add(Point p, int material)
  {
    map.setPointToGrid(p);
    map.movePointInGridDimension(0.5D, 0.0D, 0.5D);
    map.movePointExactly(0, 1, 0);
    map.movePointInGridDimension(randomOffset(0.5D), 0.0D, randomOffset(0.5D));
    map.addPointEntity(ENV_FIRE);
    map.movePointInGridDimension(0.0D, 0.5D, 0.0D);
    map.movePointExactly(0, -1, 0);
    map.addPointEntity(LIGHT);
    
    map.markAsConverted(p);
  }
  
  private double randomOffset(double scale) {
    return scale;
  }
}

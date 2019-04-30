package addable.addable;

import addable.Addable;
import buildable.Color;
import buildable.Point;
import buildable.entity.pointEntity.InfoParticleSystem;
import buildable.entity.pointEntity.Light;
import minecraft.map.MinecraftMap;



public class Torch
  extends Addable
{
  public static final int red = 255;
  public static final int blue = 50;
  public static final int green = 243;
  public static final int brigthness = 40;
  public static final int distance50 = 96;
  public static final int distance100 = 256;
  public static final Color LIGHT_COLOR = new Color(255, 243, 50, 40);
  public static final Light LIGHT = new Light(LIGHT_COLOR, 96, 256);
  private static final String EFFECT_NAME = "flaming_arrow";
  public static final InfoParticleSystem PARTICLE_SYSTEM = new InfoParticleSystem("flaming_arrow", 270, 0, 0);
  
  public Torch() {
    int[] temp = { 50 };
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "torch";
  }
  
  public void add(Point p, int material)
  {
    int parts = 16;
    Point offset = new Point(7, 0, 7);
    Point negativeOffset = new Point(7, 6, 7);
    map.addIllusionary(map.createCuboid(p, p, parts, offset, negativeOffset, material));
    map.setPointToGrid(p);
    map.movePointInGridDimension(0.5D, (parts - negativeOffset.getPosY()) / parts, 0.5D);
    map.addPointEntity(PARTICLE_SYSTEM);
    map.movePointInGridDimension(0.0D, 1.0D / parts, 0.0D);
    map.addPointEntity(LIGHT);
    map.markAsConverted(p);
  }
}

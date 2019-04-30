package addable.addable;

import addable.Addable;
import buildable.Point;
import buildable.entity.pointEntity.PropStatic;
import minecraft.map.MinecraftMap;


public class TfTallGrass
  extends Addable
{
  private static final String MODEL = "models/props_swamp/tallgrass_01.mdl";
  private static final PropStatic TALL_GRASS = new PropStatic("models/props_swamp/tallgrass_01.mdl");
  
  public TfTallGrass() {
    int[] temp = { 279 };
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "tfTallGrass";
  }
  
  public void add(Point p, int material)
  {
    map.setPointToGrid(p);
    map.movePointInGridDimension(0.5D, 0.0D, 0.5D);
    int verticalRotation = (int)(Math.random() * 360.0D);
    TALL_GRASS.setAngleY(verticalRotation);
    map.addPointEntity(TALL_GRASS);
    map.markAsConverted(p);
  }
}

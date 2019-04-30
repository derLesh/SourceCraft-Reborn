package addable.addable;

import addable.Addable;
import buildable.Color;
import buildable.Point;
import buildable.entity.pointEntity.Light;
import buildable.entity.pointEntity.PropStatic;
import minecraft.map.MinecraftMap;








public class CssLamp
  extends Addable
{
  private static final String TORCH_MODEL = "models/props/cs_italy/it_lantern1.mdl";
  private static final String TORCH_HOLDER_MODEL = "models/props/cs_italy/it_lampholder1.mdl";
  private static final int red = 255;
  private static final int green = 255;
  private static final int blue = 150;
  private static final int brigthness = 10;
  private static final int distance50 = 96;
  private static final int distance100 = 256;
  private static final Color LIGHT_COLOR = new Color(255, 255, 150, 10);
  private static final Light LIGHT = new Light(LIGHT_COLOR, 96, 256);
  private static final PropStatic TORCH = new PropStatic("models/props/cs_italy/it_lantern1.mdl");
  private static final PropStatic TORCH_HOLDER_EAST = new PropStatic("models/props/cs_italy/it_lampholder1.mdl", 0, 0, 0);
  private static final PropStatic TORCH_HOLDER_WEST = new PropStatic("models/props/cs_italy/it_lampholder1.mdl", 0, 180, 0);
  private static final PropStatic TORCH_HOLDER_SOUTH = new PropStatic("models/props/cs_italy/it_lampholder1.mdl", 0, 270, 0);
  private static final PropStatic TORCH_HOLDER_NORTH = new PropStatic("models/props/cs_italy/it_lampholder1.mdl", 0, 90, 0);
  
  public CssLamp() {
    int[] temp = { 50, 341, 342, 343, 344 };
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "cssLamp";
  }
  
  public void add(Point p, int material)
  {
    int d = 0;
    

    map.setPointToGrid(p);
    
    if (material == 50) {
      map.movePointInGridDimension(0.5D, 0.0D, 0.5D);
      map.movePointExactly(0, 10, 0);
    }
    else if (material == 341) {
      map.movePointInGridDimension(0.0D, 0.0D, 0.5D);
      map.movePointExactly(0, -6, 0);
      map.addPointEntity(TORCH_HOLDER_EAST);
      map.movePointExactly(8, 22, 0);
      d = 1;
    }
    else if (material == 342) {
      map.movePointInGridDimension(1.0D, 0.0D, 0.5D);
      map.movePointExactly(0, -6, 0);
      map.addPointEntity(TORCH_HOLDER_WEST);
      map.movePointExactly(-8, 22, 0);
      d = 2;
    }
    else if (material == 343) {
      map.movePointInGridDimension(0.5D, 0.0D, 0.0D);
      map.movePointExactly(0, -6, 0);
      map.addPointEntity(TORCH_HOLDER_SOUTH);
      map.movePointExactly(0, 22, 8);
      d = 4;
    }
    else if (material == 344) {
      map.movePointInGridDimension(0.5D, 0.0D, 1.0D);
      map.movePointExactly(0, -6, 0);
      map.addPointEntity(TORCH_HOLDER_NORTH);
      map.movePointExactly(0, 22, -8);
      d = 3;
    }
    
    map.addPointEntity(TORCH);
    
    map.movePointExactly(10, 0, 0);
    if (d != 2) {
      map.addPointEntity(LIGHT);
    }
    
    map.movePointExactly(-20, 0, 0);
    if (d != 1) {
      map.addPointEntity(LIGHT);
    }
    
    map.movePointExactly(10, 0, 0);
    map.movePointExactly(0, 0, 10);
    if (d != 3) {
      map.addPointEntity(LIGHT);
    }
    
    map.movePointExactly(0, 0, -20);
    if (d != 4) {
      map.addPointEntity(LIGHT);
    }
    
    map.markAsConverted(p, p);
  }
}

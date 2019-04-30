package addable.addable;

import addable.Addable;
import buildable.Point;
import buildable.entity.pointEntity.PropStatic;
import minecraft.map.MinecraftMap;


public class TfLilypad
  extends Addable
{
  private static final String MODEL = "models/props_swamp/lilypad_large.mdl";
  private static final PropStatic LILY_PAD = new PropStatic("models/props_swamp/lilypad_large.mdl");
  
  public TfLilypad() {
    int[] temp = { 111 };
    materialUsedFor = temp;
  }
  
  public String getName()
  {
    return "tfLilypad";
  }
  
  public void add(Point p, int material)
  {
    map.movePointInGridDimension(0.5D, 0.0D, 0.5D);
    int verticalAngle = (int)(Math.random() * 360.0D);
    LILY_PAD.setAngleY(verticalAngle);
    map.addPointEntity(LILY_PAD);
    map.markAsConverted(p);
  }
}

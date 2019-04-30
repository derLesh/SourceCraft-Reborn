package addable.addable;

import addable.Addable;
import buildable.Point;
import minecraft.map.MinecraftMap;



public class TorchNorth
  extends Addable
{
  public TorchNorth()
  {
    int[] temp = { 344 };
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "torchNorth";
  }
  
  public void add(Point p, int material)
  {
    int parts = 32;
    Point[] pointOffset = new Point[8];
    
    pointOffset[0] = new Point(18, 6, 32);
    pointOffset[1] = new Point(18, 24, 23);
    pointOffset[2] = new Point(18, 22, 19);
    pointOffset[3] = new Point(18, 4, 28);
    pointOffset[4] = new Point(14, 6, 32);
    pointOffset[5] = new Point(14, 24, 23);
    pointOffset[6] = new Point(14, 22, 19);
    pointOffset[7] = new Point(14, 4, 28);
    
    map.addIllusionary(map.createFree8Point(p, p, parts, pointOffset, false, material));
    map.setPointToGrid(p);
    map.movePointInGridDimension(0.5D, 0.7D, 0.65D);
    map.addPointEntity(Torch.PARTICLE_SYSTEM);
    map.movePointInGridDimension(0.0D, 1.0D / parts, 0.0D);
    map.addPointEntity(Torch.LIGHT);
    map.markAsConverted(p);
  }
}

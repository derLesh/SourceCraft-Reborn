package addable.addable;

import addable.Addable;
import buildable.Point;
import minecraft.map.MinecraftMap;







public class TorchWest
  extends Addable
{
  public TorchWest()
  {
    int[] temp = { 342 };
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "torchWest";
  }
  
  public void add(Point p, int material)
  {
    int parts = 32;
    Point[] pointOffset = new Point[8];
    
    pointOffset[0] = new Point(32, 6, 14);
    pointOffset[1] = new Point(23, 24, 14);
    pointOffset[2] = new Point(19, 22, 14);
    pointOffset[3] = new Point(28, 4, 14);
    pointOffset[4] = new Point(32, 6, 18);
    pointOffset[5] = new Point(23, 24, 18);
    pointOffset[6] = new Point(19, 22, 18);
    pointOffset[7] = new Point(28, 4, 18);
    
    map.addIllusionary(map.createFree8Point(p, p, parts, pointOffset, true, material));
    map.setPointToGrid(p);
    map.movePointInGridDimension(0.65D, 0.7D, 0.5D);
    map.addPointEntity(Torch.PARTICLE_SYSTEM);
    map.movePointInGridDimension(0.0D, 1.0D / parts, 0.0D);
    map.addPointEntity(Torch.LIGHT);
    map.markAsConverted(p);
  }
}

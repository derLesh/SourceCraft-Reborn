package addable.addable;

import addable.Addable;
import buildable.Point;
import minecraft.map.MinecraftMap;







public class TorchEast
  extends Addable
{
  public static int red = 255;
  public static int blue = 50;
  public static int green = 243;
  public static int brigthness = 40;
  public static final int distance50 = 96;
  public static final int distance100 = 256;
  
  public TorchEast()
  {
    int[] temp = { 341 };
    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "torchEast";
  }
  
  public void add(Point p, int material)
  {
    int parts = 32;
    Point[] pointOffset = new Point[8];
    pointOffset[0] = new Point(0, 6, 18);
    pointOffset[1] = new Point(9, 24, 18);
    pointOffset[2] = new Point(13, 22, 18);
    pointOffset[3] = new Point(4, 4, 18);
    pointOffset[4] = new Point(0, 6, 14);
    pointOffset[5] = new Point(9, 24, 14);
    pointOffset[6] = new Point(13, 22, 14);
    pointOffset[7] = new Point(4, 4, 14);
    
    map.addIllusionary(map.createFree8Point(p, p, parts, pointOffset, true, material));
    map.setPointToGrid(p);
    map.movePointInGridDimension(0.35D, 0.7D, 0.5D);
    map.addPointEntity(Torch.PARTICLE_SYSTEM);
    map.movePointInGridDimension(0.0D, 1.0D / parts, 0.0D);
    map.addPointEntity(Torch.LIGHT);
    map.markAsConverted(p);
  }
}

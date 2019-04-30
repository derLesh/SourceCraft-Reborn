package addable.addable;

import addable.Addable;
import buildable.Point;
import minecraft.SubBlockPosition;
import minecraft.map.MinecraftMap;








public class SlabHigh
  extends Addable
{
  public SlabHigh()
  {
    int[] temp = { 303, 304, 305, 306, 307, 308, 309, 310, 407, 408, 409, 410 };
    











    super.setMaterialUsedFor(temp);
  }
  
  public String getName()
  {
    return "slabHigh";
  }
  
  public void add(Point p, int material)
  {
    switch (material) {
    case 303: 
      material = 44;
      break;
    case 304: 
      material = 296;
      break;
    case 305: 
      material = 297;
      break;
    case 306: 
      material = 298;
      break;
    case 307: 
      material = 299;
      break;
    case 308: 
      material = 300;
      break;
    case 309: 
      material = 301;
      break;
    case 310: 
      material = 302;
      break;
    case 407: 
      material = 126;
      break;
    case 408: 
      material = 400;
      break;
    case 409: 
      material = 401;
      break;
    case 410: 
      material = 402;
    }
    
    int x = p.getPosX();
    int y = p.getPosY();
    int z = p.getPosZ();
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_EAST_SOUTH, material);
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_EAST_NORTH, material);
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_WEST_SOUTH, material);
    map.addSubBlock(x, y, z, SubBlockPosition.TOP_WEST_NORTH, material);
    map.markAsConverted(p);
  }
}

package minecraft;

import basic.Console;





public enum SubBlockPosition
{
  BOTTOM_WEST_NORTH, 
  BOTTOM_WEST_SOUTH, 
  BOTTOM_EAST_NORTH, 
  BOTTOM_EAST_SOUTH, 
  TOP_WEST_NORTH, 
  TOP_WEST_SOUTH, 
  TOP_EAST_NORTH, 
  TOP_EAST_SOUTH;
  








  private SubBlockPosition() {}
  








  public static int getPos(SubBlockPosition pos)
  {
    switch (1.$SwitchMap$minecraft$SubBlockPosition[pos.ordinal()]) {
    case 1: 
      return 0;
    case 2: 
      return 1;
    case 3: 
      return 2;
    case 4: 
      return 3;
    case 5: 
      return 4;
    case 6: 
      return 5;
    case 7: 
      return 6;
    case 8: 
      return 7;
    }
    Console.warning("default cse of subBlocks occured");
    return 0;
  }
  
























  public static Position getPosition(SubBlockPosition pos)
  {
    int x = 1;
    int y = 1;
    int z = 1;
    Position position;
    switch (1.$SwitchMap$minecraft$SubBlockPosition[pos.ordinal()]) {
    case 4: 
      position = new Position(x, y - 1, z);
      break;
    case 3: 
      position = new Position(x, y - 1, z - 1);
      break;
    case 2: 
      position = new Position(x - 1, y - 1, z);
      break;
    case 1: 
      position = new Position(x - 1, y - 1, z - 1);
      break;
    case 8: 
      position = new Position(x, y, z);
      break;
    case 7: 
      position = new Position(x, y, z - 1);
      break;
    case 6: 
      position = new Position(x - 1, y, z);
      break;
    case 5: 
      position = new Position(x - 1, y, z - 1);
      break;
    default: 
      position = new Position(1, 1, 1);
      Console.warning("default cse of subBlocks occured");
    }
    
    return position;
  }
}

package addable;

import buildable.Orientation;

public class MaterialWallFilter
  implements MaterialFilter
{
  private AddableManager manager;
  private Orientation orientation;
  
  public MaterialWallFilter(AddableManager manager, Orientation orientation)
  {
    this.manager = manager;
    this.orientation = orientation;
  }
  
  public boolean filter(int material)
  {
    Addable addable = manager.getAddable(material);
    if (addable != null) {
      return manager.getAddable(material).hasWall(orientation);
    }
    
    return false;
  }
}

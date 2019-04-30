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
    Addable addable = this.manager.getAddable(material);
    if (addable != null) {
      return this.manager.getAddable(material).hasWall(this.orientation);
    }
    return false;
  }
}

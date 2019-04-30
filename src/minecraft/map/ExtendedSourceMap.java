package minecraft.map;

import buildable.Point;
import buildable.SkinManager;

public abstract interface ExtendedSourceMap
  extends SourceMap
{
  public abstract SkinManager getSkinManager();
  
  public abstract void setScale(int paramInt);
  
  public abstract int getScale();
  
  public abstract Point getLocalPoint();
}

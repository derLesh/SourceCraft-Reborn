package minecraft.map;

import buildable.Point;
import buildable.SkinManager;

public interface ExtendedSourceMap
        extends SourceMap
{
  SkinManager getSkinManager();

  void setScale(int paramInt);

  int getScale();

  Point getLocalPoint();
}

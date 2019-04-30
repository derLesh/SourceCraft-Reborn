package addable.addable;

import addable.Addable;
import buildable.Point;
import buildable.entity.pointEntity.InfoPlayerCT;
import buildable.entity.pointEntity.InfoPlayerT;
import buildable.entity.pointEntity.PointEntityRotateable;
import cuboidFinder.CuboidFinder;
import java.util.LinkedList;
import minecraft.map.MinecraftMap;








public class PlayerSpawnCss
  extends Addable
{
  private static final int SPACE = 40;
  private PointEntityRotateable type;
  private boolean police;
  
  public PlayerSpawnCss() {}
  
  public PlayerSpawnCss(int material, PointEntityRotateable type, boolean police)
  {
    super.setMaterialUsedFor(material);
    this.type = type;
    this.police = police;
  }
  
  public Iterable<Addable> getInstances()
  {
    LinkedList<Addable> list = new LinkedList();
    list.add(new PlayerSpawnCss(120, new InfoPlayerT(0), false));
    list.add(new PlayerSpawnCss(130, new InfoPlayerCT(180), true));
    return list;
  }
  
  public String getName()
  {
    return "playerSpawnCss";
  }
  

  public void add(Point p, int material)
  {
    Point end = cf.getBestXZ(p, material);
    map.addPointEntitys(p, end, 40, type);
    end.move(0, 2, 0);
    map.addBuyZone(map.createCuboid(p, end), police);
    map.markAsConverted(p, end);
  }
}

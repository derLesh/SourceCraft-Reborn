package addable.addable;

import addable.Addable;
import buildable.Point;
import buildable.entity.pointEntity.InfoPlayerTeamSpawn;
import buildable.entity.pointEntity.PointEntityRotateable;
import cuboidFinder.CuboidFinder;
import java.util.LinkedList;
import minecraft.map.MinecraftMap;








public class PlayerSpawnTf2
  extends Addable
{
  private static final int SPACE = 50;
  private PointEntityRotateable type;
  private boolean police;
  
  public PlayerSpawnTf2() {}
  
  public PlayerSpawnTf2(int material, PointEntityRotateable type, boolean police)
  {
    super.setMaterialUsedFor(material);
    this.type = type;
    this.police = police;
  }
  
  public Iterable<Addable> getInstances()
  {
    LinkedList<Addable> list = new LinkedList();
    list.add(new PlayerSpawnTf2(120, new InfoPlayerTeamSpawn(0, 2), false));
    list.add(new PlayerSpawnTf2(130, new InfoPlayerTeamSpawn(180, 3), true));
    return list;
  }
  
  public String getName()
  {
    return "playerSpawnTf2";
  }
  

  public void add(Point p, int material)
  {
    Point end = cf.getBestXZ(p, material);
    map.addPointEntitys(p, end, 50, type);
    map.markAsConverted(p, end);
    end.move(0, 2, 0);
    map.addBuyZone(map.createCuboid(p, end), police);
  }
}

package minecraft.map;

import buildable.Cuboid;
import buildable.Orientation;
import buildable.Point;
import buildable.Ramp;
import buildable.Solid;
import buildable.entity.pointEntity.PointEntity;


public abstract class MinecraftMapDelegater extends MinecraftMap {
  ExtendedSourceMap target;
  
  public MinecraftMapDelegater(ExtendedSourceMap target)
  {
    this.target = target;
  }
  
  public void setSkyTexture(String skyTexture)
  {
    target.setSkyTexture(skyTexture);
  }
  
  public void addSolid(Solid shape)
  {
    target.addSolid(shape);
  }
  
  public void addIllusionary(Solid shape)
  {
    target.addIllusionary(shape);
  }
  
  public void addDetail(Solid solid)
  {
    target.addDetail(solid);
  }
  
  public void addDetail(Solid[] solids)
  {
    target.addDetail(solids);
  }
  
  public Cuboid createFree8Point(Point start, Point end, int parts, Point[] offset, boolean align, int material)
  {
    return target.createFree8Point(start, end, parts, offset, align, material);
  }
  
  public void addPointEntity(PointEntity p)
  {
    target.addPointEntity(p);
  }
  
  public Ramp createRamp(Cuboid cuboid, Orientation orientation)
  {
    return new Ramp(cuboid, orientation);
  }
  
  public void addPointEntity(Point p, PointEntity type)
  {
    target.addPointEntity(p, type);
  }
  
  public void addBuyZone(Solid solid, boolean isCT)
  {
    target.addBuyZone(solid, isCT);
  }
  
  public Ramp createRampCuttet(Cuboid cuboid, Orientation orientation, Orientation cut1, Orientation cut2)
  {
    return target.createRampCuttet(cuboid, orientation, cut1, cut2);
  }
}

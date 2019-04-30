package minecraft;

import buildable.Point;







public class SubBlockMaterial
{
  private Position position;
  private SubBlockPosition subBlockPosition;
  private int material;
  
  public SubBlockMaterial(Position position, SubBlockPosition subBlockPosition, int material)
  {
    this.position = position;
    this.subBlockPosition = subBlockPosition;
    this.material = material;
  }
  
  public Position getPosition() {
    return position;
  }
  
  public void setPosition(Position position) {
    this.position = position;
  }
  
  public SubBlockPosition getSubBlockPosition() {
    return subBlockPosition;
  }
  
  public void setSubBlockPosition(SubBlockPosition subBlockPosition) {
    this.subBlockPosition = subBlockPosition;
  }
  
  public int getMaterial() {
    return material;
  }
  
  public void setMaterial(int material) {
    this.material = material;
  }
  
  public Point getPoint() {
    return new Point(position.getX(), position.getY(), position.getZ());
  }
}

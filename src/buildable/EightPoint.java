package buildable;

public abstract class EightPoint extends Solid {
  protected Point a;
  protected Point b;
  protected Point c;
  protected Point d;
  protected Point e;
  protected Point f;
  protected Point g;
  protected Point h;
  
  public EightPoint() {}
  
  public final void scale(int scale) {
    a.scale(scale);
    b.scale(scale);
    c.scale(scale);
    d.scale(scale);
    e.scale(scale);
    f.scale(scale);
    g.scale(scale);
    h.scale(scale);
  }
  
  public final Point getFPoint()
  {
    return f;
  }
  
  public final Point[] getPointArray() {
    Point[] allPoints = new Point[8];
    allPoints[0] = a;
    allPoints[1] = b;
    allPoints[2] = c;
    allPoints[3] = d;
    allPoints[4] = e;
    allPoints[5] = f;
    allPoints[6] = g;
    allPoints[7] = h;
    return allPoints;
  }
  
  public final void move(int xDistance, int yDistance, int zDistance) {
    a.moveInHammer(xDistance, yDistance, zDistance);
    b.moveInHammer(xDistance, yDistance, zDistance);
    c.moveInHammer(xDistance, yDistance, zDistance);
    d.moveInHammer(xDistance, yDistance, zDistance);
    e.moveInHammer(xDistance, yDistance, zDistance);
    f.moveInHammer(xDistance, yDistance, zDistance);
    g.moveInHammer(xDistance, yDistance, zDistance);
    h.moveInHammer(xDistance, yDistance, zDistance);
  }
}

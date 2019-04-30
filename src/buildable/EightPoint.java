package buildable;

public abstract class EightPoint
        extends Solid
{
  protected Point a;
  protected Point b;
  protected Point c;
  protected Point d;
  protected Point e;
  protected Point f;
  protected Point g;
  protected Point h;

  public final void scale(int scale)
  {
    this.a.scale(scale);
    this.b.scale(scale);
    this.c.scale(scale);
    this.d.scale(scale);
    this.e.scale(scale);
    this.f.scale(scale);
    this.g.scale(scale);
    this.h.scale(scale);
  }

  public final Point getFPoint()
  {
    return this.f;
  }

  public final Point[] getPointArray()
  {
    Point[] allPoints = new Point[8];
    allPoints[0] = this.a;
    allPoints[1] = this.b;
    allPoints[2] = this.c;
    allPoints[3] = this.d;
    allPoints[4] = this.e;
    allPoints[5] = this.f;
    allPoints[6] = this.g;
    allPoints[7] = this.h;
    return allPoints;
  }

  public final void move(int xDistance, int yDistance, int zDistance)
  {
    this.a.moveInHammer(xDistance, yDistance, zDistance);
    this.b.moveInHammer(xDistance, yDistance, zDistance);
    this.c.moveInHammer(xDistance, yDistance, zDistance);
    this.d.moveInHammer(xDistance, yDistance, zDistance);
    this.e.moveInHammer(xDistance, yDistance, zDistance);
    this.f.moveInHammer(xDistance, yDistance, zDistance);
    this.g.moveInHammer(xDistance, yDistance, zDistance);
    this.h.moveInHammer(xDistance, yDistance, zDistance);
  }
}

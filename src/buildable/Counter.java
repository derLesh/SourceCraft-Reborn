package buildable;


public class Counter
{
  private int brushId;
  
  private int sideId;
  
  public Counter()
  {
    brushId = 1;
    sideId = 0;
  }
  
  public int getBrushId() {
    brushId += 1;
    return brushId;
  }
  
  public int getSideId() {
    sideId += 1;
    return sideId;
  }
}

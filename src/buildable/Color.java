package buildable;


public class Color
{
  private static final int DEFAULT_ALPHA = 0;
  
  public int red;
  
  public int green;
  
  public int blue;
  
  public int alpha;
  

  public Color(java.awt.Color color)
  {
    this(color.getRed(), color.getGreen(), color.getBlue());
  }
  
  public Color(Color original) {
    red = red;
    green = green;
    blue = blue;
    alpha = alpha;
  }
  
  public Color(int red, int green, int blue) {
    this(red, green, blue, 0);
  }
  
  public Color(int red, int green, int blue, int brightness) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    alpha = brightness;
  }
  
  public Color copy() {
    return new Color(red, green, blue, alpha);
  }
  
  public java.awt.Color getJavaAwtColorNegative() {
    return getNegative().getJavaAwtColor();
  }
  
  public Color getNegative() {
    return new Color(negate(red), negate(green), negate(blue), alpha);
  }
  
  private int negate(int value) {
    return 255 - value;
  }
  
  public java.awt.Color getJavaAwtColor() {
    return new java.awt.Color(red, green, blue);
  }
  
  public void setAlpha(int alpha) {
    this.alpha = alpha;
  }
  
  public int getAlpha() {
    return alpha;
  }
  
  public int getBlue() {
    return blue;
  }
  
  public int getGreen() {
    return green;
  }
  
  public int getRed() {
    return red;
  }
}

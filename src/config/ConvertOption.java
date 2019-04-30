package config;

import buildable.Color;
import java.util.Stack;







public class ConvertOption
{
  private String name;
  private int scale;
  private String skyTexture;
  private Color sunLight;
  private Color sunAmbient;
  private Color sunShadow;
  private Stack<String> addables;
  
  public ConvertOption(String aName)
  {
    name = aName;
    scale = 40;
    addables = new Stack();
  }
  
  public void setScale(int scale) {
    this.scale = scale;
  }
  
  public void setSkyTexture(String skyTexture) {
    this.skyTexture = skyTexture;
  }
  
  public void setSunAmbient(Color sunAmbient) {
    this.sunAmbient = sunAmbient;
  }
  
  public void setSunLight(Color sunLight) {
    this.sunLight = sunLight;
  }
  
  public void setSunShadow(Color sunShadow) {
    this.sunShadow = sunShadow;
  }
  
  public String getName() {
    return name;
  }
  
  public int getScale() {
    return scale;
  }
  
  public String getSkyTexture() {
    return skyTexture;
  }
  
  public Color getSunAmbient() {
    return sunAmbient;
  }
  
  public Color getSunLight() {
    return sunLight;
  }
  
  public Color getSunShadow() {
    return sunShadow;
  }
  
  public void addAddable(String add) {
    addables.push(add);
  }
  
  public String[] getAddablesAsStrings() {
    Stack<String> addablesNew = new Stack();
    int l = addables.size();
    String[] result = new String[l];
    for (int i = 0; i < l; i++) {
      result[i] = ((String)addables.pop());
      addablesNew.push(result[i]);
    }
    addables = addablesNew;
    return result;
  }
}

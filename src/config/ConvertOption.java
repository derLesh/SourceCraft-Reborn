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
    this.name = aName;
    this.scale = 40;
    this.addables = new Stack();
  }

  public void setScale(int scale)
  {
    this.scale = scale;
  }

  public void setSkyTexture(String skyTexture)
  {
    this.skyTexture = skyTexture;
  }

  public void setSunAmbient(Color sunAmbient)
  {
    this.sunAmbient = sunAmbient;
  }

  public void setSunLight(Color sunLight)
  {
    this.sunLight = sunLight;
  }

  public void setSunShadow(Color sunShadow)
  {
    this.sunShadow = sunShadow;
  }

  public String getName()
  {
    return this.name;
  }

  public int getScale()
  {
    return this.scale;
  }

  public String getSkyTexture()
  {
    return this.skyTexture;
  }

  public Color getSunAmbient()
  {
    return this.sunAmbient;
  }

  public Color getSunLight()
  {
    return this.sunLight;
  }

  public Color getSunShadow()
  {
    return this.sunShadow;
  }

  public void addAddable(String add)
  {
    this.addables.push(add);
  }

  public String[] getAddablesAsStrings()
  {
    Stack<String> addablesNew = new Stack();
    int l = this.addables.size();
    String[] result = new String[l];
    for (int i = 0; i < l; i++)
    {
      result[i] = ((String)this.addables.pop());
      addablesNew.push(result[i]);
    }
    this.addables = addablesNew;
    return result;
  }
}

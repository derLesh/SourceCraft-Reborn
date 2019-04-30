package config;

public class TexturePack
{
  private String name;
  private int textureSize;
  private static final String defaultName = "minecraft_original";
  private static final int defaultTextureSize = 128;
  
  public TexturePack() {
    name = "minecraft_original";
    textureSize = 128;
  }
  
  public TexturePack(String name) {
    this.name = name;
    textureSize = 128;
  }
  
  public TexturePack(String name, int size) {
    this.name = name;
    textureSize = size;
  }
  
  public int getTextureSize() {
    return textureSize;
  }
  
  public String getName() {
    return name;
  }
  
  public String getTextureFolder() {
    return name;
  }
}

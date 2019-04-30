package config;

public class TexturePack
{
  private String name;
  private int textureSize;
  private static final String defaultName = "minecraft_original";
  private static final int defaultTextureSize = 128;

  public TexturePack()
  {
    this.name = "minecraft_original";
    this.textureSize = 128;
  }

  public TexturePack(String name)
  {
    this.name = name;
    this.textureSize = 128;
  }

  public TexturePack(String name, int size)
  {
    this.name = name;
    this.textureSize = size;
  }

  public int getTextureSize()
  {
    return this.textureSize;
  }

  public String getName()
  {
    return this.name;
  }

  public String getTextureFolder()
  {
    return this.name;
  }
}

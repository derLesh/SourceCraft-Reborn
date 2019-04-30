package minecraft;

public class MaterialSet
{
  private int material;

  public void addMaterial(int materialNew)
  {
    this.material = materialNew;
  }

  public void setMaterial(int materialNew)
  {
    this.material = materialNew;
  }

  public void removeMaterial(int materialNew)
  {
    this.material = (-this.material);
  }

  public boolean hasMaterial(int materialNew)
  {
    return materialNew == this.material;
  }

  public int getMaterial()
  {
    return this.material;
  }

  public int[] toArray()
  {
    int[] result = { this.material };
    return result;
  }

  public boolean hasOrHadMaterial(int material)
  {
    return (this.material == material) || (-this.material == material);
  }

  public void markAsConverted(int material)
  {
    this.material = (-this.material);
  }

  public void markAsConverted()
  {
    this.material = (-this.material);
  }
}

package minecraft;



















public class MaterialSet
{
  private int material;
  


















  public MaterialSet() {}
  

















  public void addMaterial(int materialNew)
  {
    material = materialNew;
  }
  
  public void setMaterial(int materialNew) { material = materialNew; }
  
  public void removeMaterial(int materialNew) {
    material = (-material);
  }
  
  public boolean hasMaterial(int materialNew) { return materialNew == material; }
  

  public int getMaterial() { return material; }
  
  public int[] toArray() {
    int[] result = { material };
    return result;
  }
  
  public boolean hasOrHadMaterial(int material) {
    return (this.material == material) || (-this.material == material);
  }
  
  public void markAsConverted(int material) {
    this.material = (-this.material);
  }
  
  public void markAsConverted() {
    material = (-material);
  }
}

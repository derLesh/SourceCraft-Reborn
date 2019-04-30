package buildable;

// Should work - IDE shows no error

public class Skin
{
  public String materialFront;
  
  public String materialLeft;
  public String materialRight;
  public String materialTop;
  public String materialBottom;
  public String materialBack;
  public double scale;
  
  public Skin()
  {
    materialFront = null;
    materialLeft = null;
    materialRight = null;
    materialTop = null;
    materialBottom = null;
    materialBack = null;
    scale = 0.0D;
  }
  
  public Skin(String newMaterial, double newScale)
  {
    materialFront = newMaterial;
    materialLeft = newMaterial;
    materialRight = newMaterial;
    materialTop = newMaterial;
    materialBottom = newMaterial;
    materialBack = newMaterial;
    scale = newScale;
  }
  
  public Skin(String newMaterial, String newMaterialTop, double newScale) {
    materialFront = newMaterial;
    materialLeft = newMaterial;
    materialRight = newMaterial;
    materialBack = newMaterial;
    
    materialTop = newMaterialTop;
    materialBottom = newMaterialTop;
    
    scale = newScale;
  }
  
  public Skin(String newMaterial, String newMaterialTop, String newMaterialFront, double newScale) {
    materialLeft = newMaterial;
    materialRight = newMaterial;
    materialBack = newMaterial;
    
    materialTop = newMaterialTop;
    materialBottom = newMaterialTop;
    
    materialFront = newMaterialFront;
    
    scale = newScale;
  }
  
  public Skin(String newMaterial, String newMaterialTop, String newMaterialFront, Orientation orientation, double newScale) { materialLeft = newMaterial;
    materialRight = newMaterial;
    materialBack = newMaterial;
    materialFront = newMaterial;
    
    materialTop = newMaterialTop;
    materialBottom = newMaterialTop;

    switch (orientation) {
      case SOUTH:  materialFront = newMaterialFront; break;
      case EAST:  materialRight = newMaterialFront; break;
      case NORTH:  materialBack = newMaterialFront; break;
      case WEST:  materialLeft = newMaterialFront;
    }
    scale = newScale;
  }
  
  public Skin(String newMaterial, String newMaterialTop, String newMaterialFront, String newMaterialBottom, double newScale) {
    materialLeft = newMaterial;
    materialRight = newMaterial;
    materialBack = newMaterial;
    
    materialTop = newMaterialTop;
    materialBottom = newMaterialBottom;
    materialFront = newMaterialFront;
    
    scale = newScale;
  }
  
  public Skin(String newMaterial, String newMaterialTop, String newMaterialFront, String newMaterialBottom, Orientation orientation, double newScale) {
    materialLeft = newMaterial;
    materialRight = newMaterial;
    materialBack = newMaterial;
    materialFront = newMaterial;
    
    materialTop = newMaterialTop;
    materialBottom = newMaterialBottom;
    
    switch (orientation) {
    case SOUTH:  materialFront = newMaterialFront; break;
    case EAST:  materialRight = newMaterialFront; break;
    case NORTH:  materialBack = newMaterialFront; break;
    case WEST:  materialLeft = newMaterialFront;
    }
    scale = newScale;
  }
}

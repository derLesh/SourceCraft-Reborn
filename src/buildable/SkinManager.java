package buildable;

import minecraft.map.DefaultMinecraftMap;





public class SkinManager
{
  public static final Skin NODRAW = new Skin("tools/toolsnodraw", 0.25D);
  public static final Skin TRIGGER = new Skin("tools/toolstrigger", 0.25D);
  public static final Skin SKYBOX = new Skin("tools/toolsskybox", 0.25D);
  double textureScale;
  private Skin[] skins;
  byte[] materialTextureType;
  double[] materialTextureScale;
  String[] materialTexture;
  String[] materialTextureTop;
  String[] materialTextureFront;
  String[] materialTextureBottom;
  Orientation[] materialOrientation;
  
  public SkinManager(String folder, int textureSizeNew, int scale)
  {
    textureScale = (scale / textureSizeNew);
    folder = folder + "/";
    int length = 450;
    
    skins = new Skin['ǂ'];
    
    materialTextureType = new byte['ǂ'];
    for (int i = 0; i < 450; i++) {
      materialTextureType[i] = 0;
    }
    
    materialTextureScale = new double['ǂ'];
    for (int i = 0; i < 450; i++) {
      materialTextureScale[i] = textureScale;
    }
    
    materialTexture = new String['ǂ'];
    for (int i = 0; i < 450; i++) {
      materialTexture[i] = ("texture not found: " + i);
    }
    materialTextureTop = new String['ǂ'];
    for (int i = 0; i < 450; i++) {
      materialTextureTop[i] = "dev/dev_measuregeneric01b";
    }
    materialTextureFront = new String['ǂ'];
    for (int i = 0; i < 450; i++) {
      materialTextureFront[i] = "dev/dev_measuregeneric01b";
    }
    materialTextureBottom = new String['ǂ'];
    for (int i = 0; i < 450; i++) {
      materialTextureBottom[i] = "dev/dev_measuregeneric01b";
    }
    

    materialOrientation = new Orientation['ǂ'];
    for (int i = 0; i < 450; i++) {
      materialOrientation[i] = Orientation.NORTH;
    }
    

    materialTexture[1] = (folder + "STONE");
    
    materialTextureType[2] = 3;
    materialTexture[2] = (folder + "GRASS SIDE");
    materialTextureTop[2] = (folder + "GRASS");
    materialTextureFront[2] = (folder + "GRASS SIDE");
    materialTextureBottom[2] = (folder + "DIRT");
    
    materialTexture[3] = (folder + "DIRT");
    materialTexture[4] = (folder + "cobblestone");
    materialTexture[5] = (folder + "wooden plank");
    
    materialTexture[7] = (folder + "BEDROCK");
    
    materialTextureType[8] = 3;
    
    materialTextureTop[8] = (folder + "water_animation");
    materialTexture[8] = "TOOLS/TOOLSNODRAW";
    materialTextureFront[8] = "TOOLS/TOOLSNODRAW";
    materialTextureBottom[8] = "TOOLS/TOOLSNODRAW";
    materialTextureType[9] = 3;
    
    materialTextureTop[9] = (folder + "water_animation");
    materialTexture[9] = "TOOLS/TOOLSNODRAW";
    materialTextureFront[9] = "TOOLS/TOOLSNODRAW";
    materialTextureBottom[9] = "TOOLS/TOOLSNODRAW";
    
    materialTexture[10] = (folder + "lava_animation");
    
    materialTexture[11] = (folder + "lava_animation");
    
    materialTexture[12] = (folder + "SAND");
    materialTexture[13] = (folder + "GRAVEL");
    materialTexture[14] = (folder + "GOLD ORE");
    materialTexture[15] = (folder + "IRON ORE");
    materialTexture[16] = (folder + "COAL ORE");
    
    materialTextureType[17] = 1;
    materialTexture[17] = (folder + "wood");
    materialTextureTop[17] = (folder + "wood top");
    
    materialTexture[18] = (folder + "LEAVES");
    materialTexture[19] = (folder + "SPONGE");
    
    materialTexture[20] = (folder + "glass");
    
    materialTexture[21] = (folder + "LAPIS LAZULI ORE");
    materialTexture[22] = (folder + "LAPIS LAZULI BLOCK");
    
    materialTextureType[23] = 2;
    materialTexture[23] = (folder + "FURNACE SIDE");
    materialTextureFront[23] = (folder + "DISPENSER FRONT");
    materialTextureTop[23] = (folder + "FURNACE TOP");
    
    skins[24] = new Skin(folder + "sandstone side", folder + "sandstone top", folder + "sandstone side", folder + "sandstone bottom", textureScale);
    








    materialTexture[25] = (folder + "JUKEBOX SIDE");
    

    materialTexture[29] = (folder + "STICKY PISTON");
    


    materialTexture[33] = (folder + "PISTON");
    
    materialTexture[35] = (folder + "WHITE WOOL");
    
    materialTexture[41] = (folder + "GOLD BLOCK");
    materialTexture[42] = (folder + "IRON BLOCK");
    
    materialTextureType[43] = 1;
    materialTexture[43] = (folder + "STONE SLAB");
    materialTextureTop[43] = (folder + "STONE SLAB TOP");
    
    materialTextureType[44] = 1;
    materialTexture[44] = (folder + "STONE SLAB");
    materialTextureTop[44] = (folder + "STONE SLAB TOP");
    

    materialTexture[45] = (folder + "BRICK");
    materialTexture[46] = (folder + "TNT TOP");
    materialTexture[47] = (folder + "BOOKCASE");
    materialTexture[48] = (folder + "MOSS STONE");
    materialTexture[49] = (folder + "OBSIDIAN");
    
    materialTextureType[50] = 3;
    materialTexture[50] = (folder + "torch");
    materialTextureFront[50] = (folder + "torch");
    materialTextureTop[50] = (folder + "torch fit top");
    materialTextureBottom[50] = (folder + "torch fit bottom");
    

    materialTexture[52] = (folder + "MOB SPAWNER");
    materialTexture[53] = materialTexture[5];
    
    materialTextureType[54] = 2;
    materialTexture[54] = (folder + "CHEST SIDE");
    materialTextureFront[54] = (folder + "CHEST FRONT");
    materialTextureTop[54] = (folder + "CHEST TOP");
    

    materialTexture[56] = (folder + "DIAMOND ORE");
    materialTexture[57] = (folder + "DIAMOND BLOCK");
    materialTexture[58] = (folder + "WORKBENCH2");
    
    materialTexture[60] = (folder + "SOIL DRY");
    
    materialTextureType[61] = 2;
    materialTexture[61] = (folder + "FURNACE SIDE");
    materialTextureFront[61] = (folder + "FURNACE");
    materialTextureTop[61] = (folder + "FURNACE TOP");
    
    materialTextureType[62] = 2;
    materialTexture[62] = (folder + "FURNACE SIDE");
    materialTextureFront[62] = (folder + "BURNING FURNACE");
    materialTextureTop[62] = (folder + "FURNACE TOP");
    materialTextureBottom[62] = (folder + "FURNACE TOP");
    
    materialTexture[67] = (folder + "COBBLESTONE");
    
    materialTexture[73] = (folder + "REDSTONE ORE");
    materialTexture[74] = (folder + "REDSTONE ORE");
    
    materialTexture[78] = (folder + "SNOW BLOCK");
    
    materialTexture[79] = (folder + "ICE");
    materialTexture[80] = (folder + "SNOW BLOCK");
    
    materialTextureType[81] = 3;
    materialTexture[81] = (folder + "CACTUS SIDE");
    materialTextureFront[81] = (folder + "CACTUS SIDE");
    materialTextureTop[81] = (folder + "CACTUS TOP");
    materialTextureBottom[81] = (folder + "CACTUS BOTTOM");
    
    materialTexture[82] = (folder + "CLAY BLOCK");
    
    materialTexture[84] = (folder + "JUKEBOX TOP");
    materialTexture[85] = materialTexture[5];
    
    skins[86] = new Skin(folder + "pumpkin side", folder + "pumpkin top", folder + "pumpkin front", folder + "pumpkin bottom", Orientation.SOUTH, textureScale);
    


    materialTexture[87] = (folder + "NETHERRACK");
    
    materialTexture[88] = (folder + "SOUL SAND");
    materialTexture[89] = (folder + "GLOWSTONE");
    
    materialTexture[91] = (folder + "PUMPKIN CUT");
    
    materialTextureType[95] = 2;
    materialTexture[95] = (folder + "CHEST SIDE");
    materialTextureFront[95] = (folder + "CHEST FRONT");
    materialTextureTop[95] = (folder + "CHEST TOP");
    
    materialTexture[97] = materialTexture[1];
    materialTexture[98] = (folder + "STONE BRICK");
    materialTexture[99] = (folder + "RED MUSHROOM CAP");
    materialTexture[100] = (folder + "BROWN MUSHROOM CAP");
    materialTexture[101] = (folder + "IRON BARS");
    materialTexture[102] = "glass/glasswindow002a";
    
    materialTextureType[103] = 1;
    materialTexture[103] = (folder + "MELON");
    materialTextureTop[103] = (folder + "MELON TOP");
    
    materialTexture[106] = (folder + "vines");
    
    materialTexture[108] = materialTexture[45];
    materialTexture[109] = materialTexture[98];
    
    materialTextureType[110] = 3;
    materialTexture[110] = (folder + "MYCELIUM SIDE");
    materialTextureTop[110] = (folder + "MYCELIUM");
    materialTextureFront[110] = (folder + "MYCELIUM SIDE");
    materialTextureBottom[110] = (folder + "DIRT");
    
    materialTexture[111] = (folder + "LILY PAD");
    materialTexture[112] = (folder + "NETHER BRICK");
    materialTexture[113] = (folder + "NETHER BRICK");
    materialTexture[114] = (folder + "NETHER BRICK");
    

    materialTextureType[120] = 3;
    materialTexture[120] = (folder + "end portal frame side");
    materialTextureTop[120] = (folder + "end portal frame top");
    materialTextureFront[120] = (folder + "end portal frame side");
    materialTextureBottom[120] = (folder + "end stone");
    
    materialTexture[121] = (folder + "end stone");
    materialTexture[122] = (folder + "obsidian");
    materialTexture[123] = (folder + "lamp off");
    materialTexture[124] = (folder + "lamp on");
    
    materialTexture[126] = (folder + "wooden plank");
    
    materialTextureType[''] = 3;
    materialTexture[''] = (folder + "SANDSTONE SIDE");
    materialTextureFront[''] = (folder + "SANDSTONE SIDE");
    materialTextureTop[''] = (folder + "SANDSTONE TOP");
    materialTextureBottom[''] = (folder + "SANDSTONE BOTTOM");
    
    materialTexture[''] = (folder + "pine wooden plank");
    materialTexture[''] = (folder + "birch wooden plank");
    materialTexture[''] = (folder + "jungle wooden plank");
    
    materialTexture[''] = (folder + "emerald ore");
    
    materialTexture[''] = (folder + "emerald block");
    



    materialTexture['Ā'] = "ERROR";
    
    materialTextureType['ā'] = 1;
    materialTexture['ā'] = (folder + "pine");
    materialTextureTop['ā'] = (folder + "wood top");
    
    materialTextureType['Ă'] = 1;
    materialTexture['Ă'] = (folder + "birch");
    materialTextureTop['Ă'] = (folder + "wood top");
    
    materialTexture['ă'] = (folder + "PINE LEAVES");
    materialTexture['Ą'] = materialTexture[18];
    materialTexture['ą'] = (folder + "ORANGE WOOL");
    materialTexture['Ć'] = (folder + "MAGENTA WOOL");
    materialTexture['ć'] = (folder + "LIGHT BLUE WOOL");
    materialTexture['Ĉ'] = (folder + "YELLOW WOOL");
    materialTexture['ĉ'] = (folder + "LIGHT GREEN WOOL");
    materialTexture['Ċ'] = (folder + "PINK WOOL");
    materialTexture['ċ'] = (folder + "GRAY WOOL");
    materialTexture['Č'] = (folder + "LIGHT GRAY WOOL");
    materialTexture['č'] = (folder + "CYAN WOOL");
    materialTexture['Ď'] = (folder + "PURPLE WOOL");
    materialTexture['ď'] = (folder + "BLUE WOOL");
    materialTexture['Đ'] = (folder + "BROWN WOOL");
    materialTexture['đ'] = (folder + "DARK GREEN WOOL");
    materialTexture['Ē'] = (folder + "RED WOOL");
    materialTexture['ē'] = (folder + "BLACK WOOL");
    
    materialTexture['Ĕ'] = (folder + "SOIL WET");
    

    materialTexture['ĕ'] = (folder + "MOSSY STONE BRICK");
    materialTexture['Ė'] = (folder + "CRACKED STONE BRICK");
    
    materialTexture['ė'] = (folder + "TALL GRASS");
    materialTexture['Ę'] = (folder + "FERM");
    

    materialTextureType['ę'] = 3;
    materialTexture['ę'] = (folder + "SANDSTONE SIDE");
    materialTextureFront['ę'] = (folder + "SANDSTONE SIDE");
    materialTextureTop['ę'] = (folder + "SANDSTONE TOP");
    materialTextureBottom['ę'] = (folder + "SANDSTONE BOTTOM");
    materialTexture['Ě'] = (folder + "WOODEN PLANK");
    materialTexture['ě'] = (folder + "COBBLESTONE");
    materialTexture['Ĝ'] = (folder + "BRICK");
    materialTexture['ĝ'] = (folder + "STONE BRICK");
    materialTexture['Ğ'] = (folder + "STONE");
    materialTexture['ğ'] = "unused";
    














    skins['Ĩ'] = skins[24];
    




    materialTexture['ĩ'] = (folder + "WOODEN PLANK");
    materialTexture['Ī'] = (folder + "COBBLESTONE");
    materialTexture['ī'] = (folder + "BRICK");
    materialTexture['Ĭ'] = (folder + "STONE BRICK");
    materialTextureType['ĭ'] = 1;
    materialTexture['ĭ'] = (folder + "STONE SLAB");
    materialTextureTop['ĭ'] = (folder + "STONE SLAB TOP");
    materialTexture['Į'] = (folder + "nether brick");
    

    materialTexture['į'] = (folder + "stone");
    skins['İ'] = skins[24];
    




    materialTexture['ı'] = (folder + "wooden plank");
    materialTexture['Ĳ'] = (folder + "cobblestone");
    materialTexture['ĳ'] = (folder + "brick");
    materialTexture['Ĵ'] = (folder + "stone brick");
    materialTextureType['ĵ'] = 1;
    materialTexture['ĵ'] = (folder + "stone slab");
    materialTextureTop['ĵ'] = (folder + "stone slab top");
    materialTexture['Ķ'] = (folder + "S 15");
    


    materialTexture['ķ'] = (folder + "COBBLESTONE");
    materialTexture['ĸ'] = (folder + "STONE BRICK");
    
    materialTexture['Ĺ'] = "tools/toolsplayerclip";
    

    materialTexture['ĺ'] = (folder + "WOODEN PLANK");
    materialTexture['Ļ'] = (folder + "WOODEN PLANK");
    materialTexture['ļ'] = (folder + "WOODEN PLANK");
    

    materialTexture['Ľ'] = (folder + "COBBLESTONE");
    materialTexture['ľ'] = (folder + "COBBLESTONE");
    materialTexture['Ŀ'] = (folder + "COBBLESTONE");
    

    materialTexture['ŀ'] = (folder + "BRICK");
    materialTexture['Ł'] = (folder + "BRICK");
    materialTexture['ł'] = (folder + "BRICK");
    

    materialTexture['Ń'] = (folder + "STONE BRICK");
    materialTexture['ń'] = (folder + "STONE BRICK");
    materialTexture['Ņ'] = (folder + "STONE BRICK");
    

    materialTexture['ņ'] = (folder + "NETHER BRICK");
    materialTexture['Ň'] = (folder + "NETHER BRICK");
    materialTexture['ň'] = (folder + "NETHER BRICK");
    

    materialTextureType['ŉ'] = 2;
    materialTexture['ŉ'] = (folder + "FURNACE SIDE");
    materialTextureFront['ŉ'] = (folder + "FURNACE");
    materialTextureTop['ŉ'] = (folder + "FURNACE TOP");
    materialOrientation['ŉ'] = Orientation.SOUTH;
    materialTextureType['Ŋ'] = 2;
    materialTexture['Ŋ'] = (folder + "FURNACE SIDE");
    materialTextureFront['Ŋ'] = (folder + "FURNACE");
    materialTextureTop['Ŋ'] = (folder + "FURNACE TOP");
    materialOrientation['Ŋ'] = Orientation.WEST;
    materialTextureType['ŋ'] = 2;
    materialTexture['ŋ'] = (folder + "FURNACE SIDE");
    materialTextureFront['ŋ'] = (folder + "FURNACE");
    materialTextureTop['ŋ'] = (folder + "FURNACE TOP");
    materialOrientation['ŋ'] = Orientation.EAST;
    

    materialTextureType['Ō'] = 2;
    materialTexture['Ō'] = (folder + "FURNACE SIDE");
    materialTextureFront['Ō'] = (folder + "BURNING FURNACE");
    materialTextureTop['Ō'] = (folder + "FURNACE TOP");
    materialOrientation['Ō'] = Orientation.SOUTH;
    materialTextureType['ō'] = 2;
    materialTexture['ō'] = (folder + "FURNACE SIDE");
    materialTextureFront['ō'] = (folder + "BURNING FURNACE");
    materialTextureTop['ō'] = (folder + "FURNACE TOP");
    materialOrientation['ō'] = Orientation.WEST;
    materialTextureType['Ŏ'] = 2;
    materialTexture['Ŏ'] = (folder + "FURNACE SIDE");
    materialTextureFront['Ŏ'] = (folder + "BURNING FURNACE");
    materialTextureTop['Ŏ'] = (folder + "FURNACE TOP");
    materialOrientation['Ŏ'] = Orientation.EAST;
    

    materialTextureType['ŏ'] = 2;
    materialTexture['ŏ'] = (folder + "CHEST SIDE");
    materialTextureFront['ŏ'] = (folder + "CHEST FRONT");
    materialTextureTop['ŏ'] = (folder + "CHEST TOP");
    materialOrientation['ŏ'] = Orientation.SOUTH;
    materialTextureType['Ő'] = 2;
    materialTexture['Ő'] = (folder + "CHEST SIDE");
    materialTextureFront['Ő'] = (folder + "CHEST FRONT");
    materialTextureTop['Ő'] = (folder + "CHEST TOP");
    materialOrientation['Ő'] = Orientation.WEST;
    materialTextureType['ő'] = 2;
    materialTexture['ő'] = (folder + "CHEST SIDE");
    materialTextureFront['ő'] = (folder + "CHEST FRONT");
    materialTextureTop['ő'] = (folder + "CHEST TOP");
    materialOrientation['ő'] = Orientation.EAST;
    

    materialTextureType['Œ'] = 2;
    materialTexture['Œ'] = (folder + "FURNACE SIDE");
    materialTextureFront['Œ'] = (folder + "DISPENSER FRONT");
    materialTextureTop['Œ'] = (folder + "FURNACE TOP");
    materialOrientation['Œ'] = Orientation.SOUTH;
    materialTextureType['œ'] = 2;
    materialTexture['œ'] = (folder + "FURNACE SIDE");
    materialTextureFront['œ'] = (folder + "DISPENSER FRONT");
    materialTextureTop['œ'] = (folder + "FURNACE TOP");
    materialOrientation['œ'] = Orientation.WEST;
    materialTextureType['Ŕ'] = 2;
    materialTexture['Ŕ'] = (folder + "FURNACE SIDE");
    materialTextureFront['Ŕ'] = (folder + "DISPENSER FRONT");
    materialTextureTop['Ŕ'] = (folder + "FURNACE TOP");
    materialOrientation['Ŕ'] = Orientation.EAST;
    
    materialTextureType['ŕ'] = 3;
    materialTexture['ŕ'] = (folder + "torch side");
    materialTextureFront['ŕ'] = (folder + "torch back");
    materialTextureTop['ŕ'] = (folder + "torch fit top");
    materialTextureBottom['ŕ'] = (folder + "torch fit bottom");
    materialOrientation['ŕ'] = Orientation.WEST;
    materialTextureType['Ŗ'] = 3;
    materialTexture['Ŗ'] = (folder + "torch side");
    materialTextureFront['Ŗ'] = (folder + "torch back");
    materialTextureTop['Ŗ'] = (folder + "torch fit top");
    materialTextureBottom['Ŗ'] = (folder + "torch fit bottom");
    materialOrientation['Ŗ'] = Orientation.WEST;
    materialTextureType['ŗ'] = 3;
    materialTexture['ŗ'] = (folder + "torch side");
    materialTextureFront['ŗ'] = (folder + "torch back");
    materialTextureTop['ŗ'] = (folder + "torch fit top");
    materialTextureBottom['ŗ'] = (folder + "torch fit bottom");
    materialOrientation['ŗ'] = Orientation.WEST;
    materialTextureType['Ř'] = 3;
    materialTexture['Ř'] = (folder + "torch side");
    materialTextureFront['Ř'] = (folder + "torch back");
    materialTextureTop['Ř'] = (folder + "torch fit top");
    materialTextureBottom['Ř'] = (folder + "torch fit bottom");
    materialOrientation['Ř'] = Orientation.WEST;
    
    materialTexture['ř'] = "tools/toolsplayerclip";
    
    materialTextureType['Ś'] = 3;
    materialTexture['Ś'] = (folder + "SNOW SIDE");
    materialTextureTop['Ś'] = (folder + "SNOW BLOCK");
    materialTextureFront['Ś'] = (folder + "SNOW SIDE");
    materialTextureBottom['Ś'] = (folder + "DIRT");
    
    materialTexture['ś'] = (folder + "vines");
    materialTexture['Ŝ'] = (folder + "vines");
    materialTexture['ŝ'] = (folder + "vines");
    materialTexture['Ş'] = (folder + "vines");
    materialTexture['ş'] = (folder + "vines");
    materialTexture['Š'] = (folder + "vines");
    materialTexture['š'] = (folder + "vines");
    materialTexture['Ţ'] = (folder + "vines");
    materialTexture['ţ'] = (folder + "vines");
    materialTexture['Ť'] = (folder + "vines");
    materialTexture['ť'] = (folder + "vines");
    materialTexture['Ŧ'] = (folder + "vines");
    materialTexture['ŧ'] = (folder + "vines");
    materialTexture['Ũ'] = (folder + "vines");
    materialTexture['ũ'] = (folder + "vines");
    
    materialTexture['Ū'] = (folder + "pine plank");
    materialTexture['ū'] = (folder + "birch plank");
    materialTexture['Ŭ'] = (folder + "jungle plank");
    
    materialTextureType['ŭ'] = 1;
    materialTexture['ŭ'] = (folder + "jungle wood");
    materialTextureTop['ŭ'] = (folder + "wood top");
    
    materialTexture['Ů'] = (folder + "jungle leaves transparent");
    
    materialTexture['ů'] = (folder + "circle stone");
    
    materialTexture['Ű'] = (folder + "wooden plank");
    materialTexture['ű'] = (folder + "wooden plank");
    materialTexture['Ų'] = (folder + "wooden plank");
    materialTexture['ų'] = (folder + "wooden plank");
    
    materialTexture['Ŵ'] = (folder + "cobblestone");
    materialTexture['ŵ'] = (folder + "cobblestone");
    materialTexture['Ŷ'] = (folder + "cobblestone");
    materialTexture['ŷ'] = (folder + "cobblestone");
    
    materialTexture['Ÿ'] = (folder + "BRICK");
    materialTexture['Ź'] = (folder + "BRICK");
    materialTexture['ź'] = (folder + "BRICK");
    materialTexture['Ż'] = (folder + "BRICK");
    
    materialTexture['ż'] = (folder + "STONE BRICK");
    materialTexture['Ž'] = (folder + "STONE BRICK");
    materialTexture['ſ'] = (folder + "STONE BRICK");
    materialTexture['ž'] = (folder + "STONE BRICK");
    
    materialTexture['ƀ'] = (folder + "nether brick");
    materialTexture['Ɓ'] = (folder + "nether brick");
    materialTexture['Ƃ'] = (folder + "nether brick");
    materialTexture['ƃ'] = (folder + "nether brick");
    
    materialTexture['Ɖ'] = (folder + "todo");
    materialTexture['Ɗ'] = (folder + "todo");
    materialTexture['Ƌ'] = (folder + "todo");
    materialTexture['ƌ'] = (folder + "todo");
    materialTexture['ƍ'] = (folder + "todo");
    materialTexture['Ǝ'] = (folder + "todo");
    materialTexture['Ə'] = (folder + "todo");
    
    materialTexture['Ɛ'] = (folder + "pine wooden plank");
    materialTexture['Ƒ'] = (folder + "birch wooden plank");
    materialTexture['ƒ'] = (folder + "jungle wooden plank");
    materialTexture['Ɠ'] = (folder + "todo");
    materialTexture['Ɣ'] = (folder + "todo");
    materialTexture['ƕ'] = (folder + "todo");
    materialTexture['Ɩ'] = (folder + "todo");
    materialTexture['Ƙ'] = (folder + "pine wooden plank");
    materialTexture['ƙ'] = (folder + "birch wooden plank");
    materialTexture['ƚ'] = (folder + "jungle wooden plank");
    materialTexture['ƛ'] = (folder + "todo");
    materialTexture['Ɯ'] = (folder + "todo");
    materialTexture['Ɲ'] = (folder + "todo");
    materialTexture['ƞ'] = (folder + "todo");
    
    materialTexture['Ɵ'] = (folder + "todo");
    materialTexture['Ơ'] = (folder + "todo");
    materialTexture['ơ'] = (folder + "todo");
    materialTexture['Ƣ'] = (folder + "todo");
    materialTexture['ƣ'] = (folder + "todo");
    materialTexture['Ƥ'] = (folder + "todo");
    materialTexture['ƥ'] = (folder + "todo");
    
    materialTexture['Ʀ'] = (folder + "todo");
    materialTexture['Ƨ'] = (folder + "todo");
    materialTexture['ƨ'] = (folder + "todo");
    materialTexture['Ʃ'] = (folder + "todo");
    materialTexture['ƪ'] = (folder + "todo");
    materialTexture['ƫ'] = (folder + "todo");
    materialTexture['Ƭ'] = (folder + "todo");
    
    materialTextureType['ƭ'] = 3;
    materialTexture['ƭ'] = (folder + "sandstone side");
    materialTextureFront['ƭ'] = (folder + "sandstone side");
    materialTextureTop['ƭ'] = (folder + "sandstone top");
    materialTextureBottom['ƭ'] = (folder + "sandstone bottom");
    materialTextureType['Ʈ'] = 3;
    materialTexture['Ʈ'] = (folder + "sandstone side");
    materialTextureFront['Ʈ'] = (folder + "sandstone side");
    materialTextureTop['Ʈ'] = (folder + "sandstone top");
    materialTextureBottom['Ʈ'] = (folder + "sandstone bottom");
    materialTextureType['Ư'] = 3;
    materialTexture['Ư'] = (folder + "sandstone side");
    materialTextureFront['Ư'] = (folder + "sandstone side");
    materialTextureTop['Ư'] = (folder + "sandstone top");
    materialTextureBottom['Ư'] = (folder + "sandstone bottom");
    
    materialTextureType['ư'] = 3;
    materialTexture['ư'] = (folder + "sandstone side");
    materialTextureFront['ư'] = (folder + "sandstone side");
    materialTextureTop['ư'] = (folder + "sandstone top");
    materialTextureBottom['ư'] = (folder + "sandstone bottom");
    materialTextureType['Ʊ'] = 3;
    materialTexture['Ʊ'] = (folder + "sandstone side");
    materialTextureFront['Ʊ'] = (folder + "sandstone side");
    materialTextureTop['Ʊ'] = (folder + "sandstone top");
    materialTextureBottom['Ʊ'] = (folder + "sandstone bottom");
    materialTextureType['Ʋ'] = 3;
    materialTexture['Ʋ'] = (folder + "sandstone side");
    materialTextureFront['Ʋ'] = (folder + "sandstone side");
    materialTextureTop['Ʋ'] = (folder + "sandstone top");
    materialTextureBottom['Ʋ'] = (folder + "sandstone bottom");
    materialTextureType['Ƴ'] = 3;
    materialTexture['Ƴ'] = (folder + "sandstone side");
    materialTextureFront['Ƴ'] = (folder + "sandstone side");
    materialTextureTop['Ƴ'] = (folder + "sandstone top");
    materialTextureBottom['Ƴ'] = (folder + "sandstone bottom");
  }
  
  public Skin getSkin(int m)
  {
    if (skins[m] != null) {
      return skins[m];
    }
    
    String texture = materialTexture[m];
    String textureTop = materialTextureTop[m];
    int type = materialTextureType[m];
    double s = materialTextureScale[m];
    Orientation o = materialOrientation[m];
    if (type == 0) {
      return new Skin(texture, s);
    }
    if (type == 1) {
      return new Skin(texture, textureTop, s);
    }
    if (type == 2) {
      return new Skin(texture, textureTop, materialTextureFront[m], o, s);
    }
    return new Skin(texture, textureTop, materialTextureFront[m], materialTextureBottom[m], o, s);
  }
  
  public void removeData(DefaultMinecraftMap map)
  {
    int arraySizeX = map.getArraySizeX();
    int arraySizeZ = map.getArraySizeZ();
    int arraySizeY = map.getArraySizeY();
    for (int y = 1; y < arraySizeY; y++) {
      for (int x = 1; x < arraySizeX - 1; x++) {
        for (int z = 1; z < arraySizeZ - 1; z++) {
          removeData(map, x, y, z);
        }
      }
    }
  }
  


  private void removeData(DefaultMinecraftMap map, int x, int y, int z)
  {
    int data = map.getData(x, y, z);
    int newMaterial; switch (map.getMaterial(x, y, z)) {
    case 2: 
      if ((map.hasMaterial(x, y + 1, z, 78)) || (map.hasMaterial(x, y + 1, z, 80))) {
        map.setMaterial(x, y, z, 346);
      }
    
    case 17: 
      if (map.getData(x, y, z) == 1) {
        map.setMaterial(x, y, z, 257);
      }
      else if (map.getData(x, y, z) == 2) {
        map.setMaterial(x, y, z, 258);
      }
      else if (map.getData(x, y, z) == 3) {
        map.setMaterial(x, y, z, 365);
      }
      break;
    case 18: 
      if ((map.getData(x, y, z) == 1) || (map.getData(x, y, z) == 5) || (map.getData(x, y, z) == 9) || (map.getData(x, y, z) == 13)) {
        map.setMaterial(x, y, z, 259);
      }
      else if ((map.getData(x, y, z) == 2) || (map.getData(x, y, z) == 6) || (map.getData(x, y, z) == 10) || (map.getData(x, y, z) == 14)) {
        map.setMaterial(x, y, z, 260);
      }
      else if ((map.getData(x, y, z) == 3) || (map.getData(x, y, z) == 7) || (map.getData(x, y, z) == 11) || (map.getData(x, y, z) == 15)) {
        map.setMaterial(x, y, z, 366);
      }
      break;
    case 35: 
      if (map.getData(x, y, z) != 0) {
        map.setMaterial(x, y, z, 260 + map.getData(x, y, z));
      }
      else if (map.getData(x, y, z) == 2) {
        map.setMaterial(x, y, z, 262);
      }
      else if (map.getData(x, y, z) == 3) {
        map.setMaterial(x, y, z, 263);
      }
      else if (map.getData(x, y, z) == 4) {
        map.setMaterial(x, y, z, 264);
      }
      else if (map.getData(x, y, z) == 5) {
        map.setMaterial(x, y, z, 265);
      }
      else if (map.getData(x, y, z) == 6) {
        map.setMaterial(x, y, z, 266);
      }
      else if (map.getData(x, y, z) == 7) {
        map.setMaterial(x, y, z, 267);
      }
      else if (map.getData(x, y, z) == 8) {
        map.setMaterial(x, y, z, 268);
      }
      else if (map.getData(x, y, z) == 9) {
        map.setMaterial(x, y, z, 269);
      }
      else if (map.getData(x, y, z) == 10) {
        map.setMaterial(x, y, z, 270);
      }
      else if (map.getData(x, y, z) == 11) {
        map.setMaterial(x, y, z, 271);
      }
      else if (map.getData(x, y, z) == 12) {
        map.setMaterial(x, y, z, 272);
      }
      else if (map.getData(x, y, z) == 13) {
        map.setMaterial(x, y, z, 273);
      }
      else if (map.getData(x, y, z) == 14) {
        map.setMaterial(x, y, z, 274);
      }
      else if (map.getData(x, y, z) == 15) {
        map.setMaterial(x, y, z, 275);
      }
      break;
    case 60: 
      if (map.getData(x, y, z) != 0) {
        map.setMaterial(x, y, z, 276);
      }
      break;
    case 98: 
      if (map.getData(x, y, z) == 1) {
        map.setMaterial(x, y, z, 277);
      }
      else if (map.getData(x, y, z) == 2) {
        map.setMaterial(x, y, z, 278);
      }
      else if (map.getData(x, y, z) == 3) {
        map.setMaterial(x, y, z, 367);
      }
      break;
    case 31: 
      if (map.getData(x, y, z) == 1) {
        map.setMaterial(x, y, z, 279);
      }
      else if (map.getData(x, y, z) == 2) {
        map.setMaterial(x, y, z, 280);
      }
      break;
    case 43: 
      if (map.getData(x, y, z) != 0) {
        map.setMaterial(x, y, z, 280 + map.getData(x, y, z));
      }
    














    case 44: 
      if (data != 0) {
        map.setMaterial(x, y, z, 295 + data);
      }
      break;
    case 97: 
      if (map.getData(x, y, z) == 1) {
        map.setMaterial(x, y, z, 311);
      }
      else if (map.getData(x, y, z) == 2) {
        map.setMaterial(x, y, z, 312);
      }
      
      break;
    case 53: 
      newMaterial = 53;
      switch (data) {
      case 1: 
        newMaterial = 314;
        break;
      case 2: 
        newMaterial = 315;
        break;
      case 3: 
        newMaterial = 316;
        break;
      case 4: 
        newMaterial = 368;
        break;
      case 5: 
        newMaterial = 369;
        break;
      case 6: 
        newMaterial = 370;
        break;
      case 7: 
        newMaterial = 371;
      }
      
      map.setMaterial(x, y, z, newMaterial);
      break;
    case 67: 
      newMaterial = 67;
      switch (data) {
      case 1: 
        newMaterial = 317;
        break;
      case 2: 
        newMaterial = 318;
        break;
      case 3: 
        newMaterial = 319;
        break;
      case 4: 
        newMaterial = 372;
        break;
      case 5: 
        newMaterial = 373;
        break;
      case 6: 
        newMaterial = 374;
        break;
      case 7: 
        newMaterial = 375;
      }
      
      map.setMaterial(x, y, z, newMaterial);
      break;
    case 108: 
      newMaterial = 108;
      switch (data) {
      case 1: 
        newMaterial = 320;
        break;
      case 2: 
        newMaterial = 321;
        break;
      case 3: 
        newMaterial = 322;
        break;
      case 4: 
        newMaterial = 376;
        break;
      case 5: 
        newMaterial = 377;
        break;
      case 6: 
        newMaterial = 378;
        break;
      case 7: 
        newMaterial = 379;
      }
      
      map.setMaterial(x, y, z, newMaterial);
      break;
    case 109: 
      newMaterial = 109;
      switch (data) {
      case 1: 
        newMaterial = 323;
        break;
      case 2: 
        newMaterial = 324;
        break;
      case 3: 
        newMaterial = 325;
        break;
      case 4: 
        newMaterial = 380;
        break;
      case 5: 
        newMaterial = 381;
        break;
      case 6: 
        newMaterial = 382;
        break;
      case 7: 
        newMaterial = 383;
      }
      
      map.setMaterial(x, y, z, newMaterial);
      break;
    case 114: 
      switch (data) {
      case 1: 
        map.setMaterial(x, y, z, 326);
        break;
      case 2: 
        map.setMaterial(x, y, z, 327);
        break;
      case 3: 
        map.setMaterial(x, y, z, 328);
        break;
      case 4: 
        map.setMaterial(x, y, z, 384);
        break;
      case 5: 
        map.setMaterial(x, y, z, 385);
        break;
      case 6: 
        map.setMaterial(x, y, z, 386);
        break;
      case 7: 
        map.setMaterial(x, y, z, 387);
      }
      
      break;
    case 61: 
      if (map.getData(x, y, z) == 3) {
        map.setMaterial(x, y, z, 329);
      }
      else if (map.getData(x, y, z) == 4) {
        map.setMaterial(x, y, z, 330);
      }
      else if (map.getData(x, y, z) == 5) {
        map.setMaterial(x, y, z, 331);
      }
      break;
    case 62: 
      if (map.getData(x, y, z) == 3) {
        map.setMaterial(x, y, z, 332);
      }
      else if (map.getData(x, y, z) == 4) {
        map.setMaterial(x, y, z, 333);
      }
      else if (map.getData(x, y, z) == 5) {
        map.setMaterial(x, y, z, 334);
      }
      break;
    case 54: 
      if (map.getData(x, y, z) == 3) {
        map.setMaterial(x, y, z, 335);
      }
      else if (map.getData(x, y, z) == 4) {
        map.setMaterial(x, y, z, 336);
      }
      else if (map.getData(x, y, z) == 5) {
        map.setMaterial(x, y, z, 337);
      }
      break;
    case 23: 
      if (map.getData(x, y, z) == 3) {
        map.setMaterial(x, y, z, 338);
      }
      else if (map.getData(x, y, z) == 4) {
        map.setMaterial(x, y, z, 339);
      }
      else if (map.getData(x, y, z) == 5) {
        map.setMaterial(x, y, z, 340);
      }
      break;
    case 50: 
      if (map.getData(x, y, z) == 1) {
        map.setMaterial(x, y, z, 341);
      }
      else if (map.getData(x, y, z) == 2) {
        map.setMaterial(x, y, z, 342);
      }
      else if (map.getData(x, y, z) == 3) {
        map.setMaterial(x, y, z, 343);
      }
      else if (map.getData(x, y, z) == 4) {
        map.setMaterial(x, y, z, 344);
      }
      break;
    case 106: 
      if (data != 0) {
        map.setMaterial(x, y, z, 346 + data);
      }
      
      break;
    case 5: 
      if (map.getData(x, y, z) == 1) {
        map.setMaterial(x, y, z, 362);
      }
      if (map.getData(x, y, z) == 2) {
        map.setMaterial(x, y, z, 363);
      }
      if (map.getData(x, y, z) == 3) {
        map.setMaterial(x, y, z, 364);
      }
      



      break;
    case 26: 
      break;
    case 134: 
      newMaterial = 134;
      switch (data) {
      case 1: 
        newMaterial = 393;
        break;
      case 2: 
        newMaterial = 394;
        break;
      case 3: 
        newMaterial = 395;
        break;
      case 4: 
        newMaterial = 396;
        break;
      case 5: 
        newMaterial = 397;
        break;
      case 6: 
        newMaterial = 398;
        break;
      case 7: 
        newMaterial = 399;
      }
      
      map.setMaterial(x, y, z, newMaterial);
      break;
    case 126: 
      newMaterial = 126;
      switch (data) {
      case 1: 
        newMaterial = 400;
        break;
      case 2: 
        newMaterial = 401;
        break;
      case 3: 
        newMaterial = 402;
        break;
      case 8: 
        newMaterial = 407;
        break;
      case 9: 
        newMaterial = 408;
        break;
      case 10: 
        newMaterial = 409;
        break;
      case 11: 
        newMaterial = 410;
      }
      
      map.setMaterial(x, y, z, newMaterial);
      break;
    case 135: 
      newMaterial = 135;
      switch (data) {
      case 1: 
        newMaterial = 415;
        break;
      case 2: 
        newMaterial = 416;
        break;
      case 3: 
        newMaterial = 417;
        break;
      case 4: 
        newMaterial = 418;
        break;
      case 5: 
        newMaterial = 419;
        break;
      case 6: 
        newMaterial = 420;
        break;
      case 7: 
        newMaterial = 421;
      }
      
      map.setMaterial(x, y, z, newMaterial);
      break;
    case 136: 
      newMaterial = 136;
      switch (data) {
      case 1: 
        newMaterial = 422;
        break;
      case 2: 
        newMaterial = 423;
        break;
      case 3: 
        newMaterial = 424;
        break;
      case 4: 
        newMaterial = 425;
        break;
      case 5: 
        newMaterial = 426;
        break;
      case 6: 
        newMaterial = 427;
        break;
      case 7: 
        newMaterial = 428;
      }
      
      map.setMaterial(x, y, z, newMaterial);
      break;
    case 128: 
      newMaterial = 128;
      switch (data) {
      case 1: 
        newMaterial = 429;
        break;
      case 2: 
        newMaterial = 430;
        break;
      case 3: 
        newMaterial = 431;
        break;
      case 4: 
        newMaterial = 432;
        break;
      case 5: 
        newMaterial = 433;
        break;
      case 6: 
        newMaterial = 434;
        break;
      case 7: 
        newMaterial = 435;
      }
      
      map.setMaterial(x, y, z, newMaterial);
    }
  }
}

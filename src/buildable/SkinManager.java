package buildable;

import minecraft.map.DefaultMinecraftMap;

// I think this file is used for all the textures

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
    this.textureScale = (scale / textureSizeNew);
    folder = folder + "/";
    int length = 450;

    this.skins = new Skin['a'];

    this.materialTextureType = new byte['a'];
    for (int i = 0; i < 450; i++) {
      this.materialTextureType[i] = Byte.parseByte("a");
    }
    this.materialTextureScale = new double['a'];
    for (int i = 0; i < 450; i++) {
      this.materialTextureScale[i] = this.textureScale;
    }
    this.materialTexture = new String['a'];
    for (int i = 0; i < 450; i++) {
      this.materialTexture[i] = ("texture not found: " + i);
    }
    this.materialTextureTop = new String['a'];
    for (int i = 0; i < 450; i++) {
      this.materialTextureTop[i] = "dev/dev_measuregeneric01b";
    }
    this.materialTextureFront = new String['a'];
    for (int i = 0; i < 450; i++) {
      this.materialTextureFront[i] = "dev/dev_measuregeneric01b";
    }
    this.materialTextureBottom = new String['a'];
    for (int i = 0; i < 450; i++) {
      this.materialTextureBottom[i] = "dev/dev_measuregeneric01b";
    }
    this.materialOrientation = new Orientation['a'];
    for (int i = 0; i < 450; i++) {
      this.materialOrientation[i] = Orientation.NORTH;
    }
    this.materialTexture[1] = (folder + "STONE");

    this.materialTextureType[2] = 3;
    this.materialTexture[2] = (folder + "GRASS SIDE");
    this.materialTextureTop[2] = (folder + "GRASS");
    this.materialTextureFront[2] = (folder + "GRASS SIDE");
    this.materialTextureBottom[2] = (folder + "DIRT");

    this.materialTexture[3] = (folder + "DIRT");
    this.materialTexture[4] = (folder + "cobblestone");
    this.materialTexture[5] = (folder + "wooden plank");

    this.materialTexture[7] = (folder + "BEDROCK");

    this.materialTextureType[8] = 3;

    this.materialTextureTop[8] = (folder + "water_animation");
    this.materialTexture[8] = "TOOLS/TOOLSNODRAW";
    this.materialTextureFront[8] = "TOOLS/TOOLSNODRAW";
    this.materialTextureBottom[8] = "TOOLS/TOOLSNODRAW";
    this.materialTextureType[9] = 3;

    this.materialTextureTop[9] = (folder + "water_animation");
    this.materialTexture[9] = "TOOLS/TOOLSNODRAW";
    this.materialTextureFront[9] = "TOOLS/TOOLSNODRAW";
    this.materialTextureBottom[9] = "TOOLS/TOOLSNODRAW";

    this.materialTexture[10] = (folder + "lava_animation");

    this.materialTexture[11] = (folder + "lava_animation");

    this.materialTexture[12] = (folder + "SAND");
    this.materialTexture[13] = (folder + "GRAVEL");
    this.materialTexture[14] = (folder + "GOLD ORE");
    this.materialTexture[15] = (folder + "IRON ORE");
    this.materialTexture[16] = (folder + "COAL ORE");

    this.materialTextureType[17] = 1;
    this.materialTexture[17] = (folder + "wood");
    this.materialTextureTop[17] = (folder + "wood top");

    this.materialTexture[18] = (folder + "LEAVES");
    this.materialTexture[19] = (folder + "SPONGE");

    this.materialTexture[20] = (folder + "glass");

    this.materialTexture[21] = (folder + "LAPIS LAZULI ORE");
    this.materialTexture[22] = (folder + "LAPIS LAZULI BLOCK");

    this.materialTextureType[23] = 2;
    this.materialTexture[23] = (folder + "FURNACE SIDE");
    this.materialTextureFront[23] = (folder + "DISPENSER FRONT");
    this.materialTextureTop[23] = (folder + "FURNACE TOP");

    this.skins[24] = new Skin(folder + "sandstone side", folder + "sandstone top", folder + "sandstone side", folder + "sandstone bottom", this.textureScale);

    this.materialTexture[25] = (folder + "JUKEBOX SIDE");

    this.materialTexture[29] = (folder + "STICKY PISTON");

    this.materialTexture[33] = (folder + "PISTON");

    this.materialTexture[35] = (folder + "WHITE WOOL");

    this.materialTexture[41] = (folder + "GOLD BLOCK");
    this.materialTexture[42] = (folder + "IRON BLOCK");

    this.materialTextureType[43] = 1;
    this.materialTexture[43] = (folder + "STONE SLAB");
    this.materialTextureTop[43] = (folder + "STONE SLAB TOP");

    this.materialTextureType[44] = 1;
    this.materialTexture[44] = (folder + "STONE SLAB");
    this.materialTextureTop[44] = (folder + "STONE SLAB TOP");

    this.materialTexture[45] = (folder + "BRICK");
    this.materialTexture[46] = (folder + "TNT TOP");
    this.materialTexture[47] = (folder + "BOOKCASE");
    this.materialTexture[48] = (folder + "MOSS STONE");
    this.materialTexture[49] = (folder + "OBSIDIAN");

    this.materialTextureType[50] = 3;
    this.materialTexture[50] = (folder + "torch");
    this.materialTextureFront[50] = (folder + "torch");
    this.materialTextureTop[50] = (folder + "torch fit top");
    this.materialTextureBottom[50] = (folder + "torch fit bottom");

    this.materialTexture[52] = (folder + "MOB SPAWNER");
    this.materialTexture[53] = this.materialTexture[5];

    this.materialTextureType[54] = 2;
    this.materialTexture[54] = (folder + "CHEST SIDE");
    this.materialTextureFront[54] = (folder + "CHEST FRONT");
    this.materialTextureTop[54] = (folder + "CHEST TOP");

    this.materialTexture[56] = (folder + "DIAMOND ORE");
    this.materialTexture[57] = (folder + "DIAMOND BLOCK");
    this.materialTexture[58] = (folder + "WORKBENCH2");

    this.materialTexture[60] = (folder + "SOIL DRY");

    this.materialTextureType[61] = 2;
    this.materialTexture[61] = (folder + "FURNACE SIDE");
    this.materialTextureFront[61] = (folder + "FURNACE");
    this.materialTextureTop[61] = (folder + "FURNACE TOP");

    this.materialTextureType[62] = 2;
    this.materialTexture[62] = (folder + "FURNACE SIDE");
    this.materialTextureFront[62] = (folder + "BURNING FURNACE");
    this.materialTextureTop[62] = (folder + "FURNACE TOP");
    this.materialTextureBottom[62] = (folder + "FURNACE TOP");

    this.materialTexture[67] = (folder + "COBBLESTONE");

    this.materialTexture[73] = (folder + "REDSTONE ORE");
    this.materialTexture[74] = (folder + "REDSTONE ORE");

    this.materialTexture[78] = (folder + "SNOW BLOCK");

    this.materialTexture[79] = (folder + "ICE");
    this.materialTexture[80] = (folder + "SNOW BLOCK");

    this.materialTextureType[81] = 3;
    this.materialTexture[81] = (folder + "CACTUS SIDE");
    this.materialTextureFront[81] = (folder + "CACTUS SIDE");
    this.materialTextureTop[81] = (folder + "CACTUS TOP");
    this.materialTextureBottom[81] = (folder + "CACTUS BOTTOM");

    this.materialTexture[82] = (folder + "CLAY BLOCK");

    this.materialTexture[84] = (folder + "JUKEBOX TOP");
    this.materialTexture[85] = this.materialTexture[5];

    this.skins[86] = new Skin(folder + "pumpkin side", folder + "pumpkin top", folder + "pumpkin front", folder + "pumpkin bottom", Orientation.SOUTH, this.textureScale);

    this.materialTexture[87] = (folder + "NETHERRACK");

    this.materialTexture[88] = (folder + "SOUL SAND");
    this.materialTexture[89] = (folder + "GLOWSTONE");

    this.materialTexture[91] = (folder + "PUMPKIN CUT");

    this.materialTextureType[95] = 2;
    this.materialTexture[95] = (folder + "CHEST SIDE");
    this.materialTextureFront[95] = (folder + "CHEST FRONT");
    this.materialTextureTop[95] = (folder + "CHEST TOP");

    this.materialTexture[97] = this.materialTexture[1];
    this.materialTexture[98] = (folder + "STONE BRICK");
    this.materialTexture[99] = (folder + "RED MUSHROOM CAP");
    this.materialTexture[100] = (folder + "BROWN MUSHROOM CAP");
    this.materialTexture[101] = (folder + "IRON BARS");
    this.materialTexture[102] = "glass/glasswindow002a";

    this.materialTextureType[103] = 1;
    this.materialTexture[103] = (folder + "MELON");
    this.materialTextureTop[103] = (folder + "MELON TOP");

    this.materialTexture[106] = (folder + "vines");

    this.materialTexture[108] = this.materialTexture[45];
    this.materialTexture[109] = this.materialTexture[98];

    this.materialTextureType[110] = 3;
    this.materialTexture[110] = (folder + "MYCELIUM SIDE");
    this.materialTextureTop[110] = (folder + "MYCELIUM");
    this.materialTextureFront[110] = (folder + "MYCELIUM SIDE");
    this.materialTextureBottom[110] = (folder + "DIRT");

    this.materialTexture[111] = (folder + "LILY PAD");
    this.materialTexture[112] = (folder + "NETHER BRICK");
    this.materialTexture[113] = (folder + "NETHER BRICK");
    this.materialTexture[114] = (folder + "NETHER BRICK");

    this.materialTextureType[120] = 3;
    this.materialTexture[120] = (folder + "end portal frame side");
    this.materialTextureTop[120] = (folder + "end portal frame top");
    this.materialTextureFront[120] = (folder + "end portal frame side");
    this.materialTextureBottom[120] = (folder + "end stone");

    this.materialTexture[121] = (folder + "end stone");
    this.materialTexture[122] = (folder + "obsidian");
    this.materialTexture[123] = (folder + "lamp off");
    this.materialTexture[124] = (folder + "lamp on");

    this.materialTexture[126] = (folder + "wooden plank");

    this.materialTextureType['b'] = 3;
    this.materialTexture['b'] = (folder + "SANDSTONE SIDE");
    this.materialTextureFront['b'] = (folder + "SANDSTONE SIDE");
    this.materialTextureTop['b'] = (folder + "SANDSTONE TOP");
    this.materialTextureBottom['b'] = (folder + "SANDSTONE BOTTOM");

    this.materialTexture['b'] = (folder + "pine wooden plank");
    this.materialTexture['b'] = (folder + "birch wooden plank");
    this.materialTexture['b'] = (folder + "jungle wooden plank");

    this.materialTexture['b'] = (folder + "emerald ore");

    this.materialTexture['b'] = (folder + "emerald block");

    this.materialTexture['C'] = "ERROR";

    this.materialTextureType['c'] = 1;
    this.materialTexture['c'] = (folder + "pine");
    this.materialTextureTop['c'] = (folder + "wood top");

    this.materialTextureType['C'] = 1;
    this.materialTexture['C'] = (folder + "birch");
    this.materialTextureTop['C'] = (folder + "wood top");

    this.materialTexture['d'] = (folder + "PINE LEAVES");
    this.materialTexture['D'] = this.materialTexture[18];
    this.materialTexture['e'] = (folder + "ORANGE WOOL");
    this.materialTexture['E'] = (folder + "MAGENTA WOOL");
    this.materialTexture['f'] = (folder + "LIGHT BLUE WOOL");
    this.materialTexture['F'] = (folder + "YELLOW WOOL");
    this.materialTexture['g'] = (folder + "LIGHT GREEN WOOL");
    this.materialTexture['G'] = (folder + "PINK WOOL");
    this.materialTexture['h'] = (folder + "GRAY WOOL");
    this.materialTexture['H'] = (folder + "LIGHT GRAY WOOL");
    this.materialTexture['i'] = (folder + "CYAN WOOL");
    this.materialTexture['I'] = (folder + "PURPLE WOOL");
    this.materialTexture['j'] = (folder + "BLUE WOOL");
    this.materialTexture['J'] = (folder + "BROWN WOOL");
    this.materialTexture['k'] = (folder + "DARK GREEN WOOL");
    this.materialTexture['K'] = (folder + "RED WOOL");
    this.materialTexture['l'] = (folder + "BLACK WOOL");

    this.materialTexture['L'] = (folder + "SOIL WET");

    this.materialTexture['m'] = (folder + "MOSSY STONE BRICK");
    this.materialTexture['M'] = (folder + "CRACKED STONE BRICK");

    this.materialTexture['n'] = (folder + "TALL GRASS");
    this.materialTexture['N'] = (folder + "FERM");

    this.materialTextureType['o'] = 3;
    this.materialTexture['O'] = (folder + "SANDSTONE SIDE");
    this.materialTextureFront['p'] = (folder + "SANDSTONE SIDE");
    this.materialTextureTop['P'] = (folder + "SANDSTONE TOP");
    this.materialTextureBottom['q'] = (folder + "SANDSTONE BOTTOM");
    this.materialTexture['Q'] = (folder + "WOODEN PLANK");
    this.materialTexture['r'] = (folder + "COBBLESTONE");
    this.materialTexture['R'] = (folder + "BRICK");
    this.materialTexture['s'] = (folder + "STONE BRICK");
    this.materialTexture['S'] = (folder + "STONE");
    this.materialTexture['t'] = "unused";

    this.skins['T'] = this.skins[24];

    this.materialTexture['u'] = (folder + "WOODEN PLANK");
    this.materialTexture['U'] = (folder + "COBBLESTONE");
    this.materialTexture['v'] = (folder + "BRICK");
    this.materialTexture['V'] = (folder + "STONE BRICK");
    this.materialTextureType['w'] = 1;
    this.materialTexture['W'] = (folder + "STONE SLAB");
    this.materialTextureTop['x'] = (folder + "STONE SLAB TOP");
    this.materialTexture['X'] = (folder + "nether brick");

    this.materialTexture['y'] = (folder + "stone");
    this.skins['Y'] = this.skins[24];

    this.materialTexture['z'] = (folder + "wooden plank");
    this.materialTexture['Z'] = (folder + "cobblestone");
    this.materialTexture['ü'] = (folder + "brick");
    this.materialTexture['Ü'] = (folder + "stone brick");
    this.materialTextureType['ö'] = 1;
    this.materialTexture['ö'] = (folder + "stone slab");
    this.materialTextureTop['À'] = (folder + "stone slab top");
    this.materialTexture['Â'] = (folder + "S 15");

    this.materialTexture['Á'] = (folder + "COBBLESTONE");
    this.materialTexture['Ã'] = (folder + "STONE BRICK");

    this.materialTexture['Å'] = "tools/toolsplayerclip";

    this.materialTexture['Æ'] = (folder + "WOODEN PLANK");
    this.materialTexture['Ç'] = (folder + "WOODEN PLANK");
    this.materialTexture['È'] = (folder + "WOODEN PLANK");

    this.materialTexture['É'] = (folder + "COBBLESTONE");
    this.materialTexture['Ê'] = (folder + "COBBLESTONE");
    this.materialTexture['Ë'] = (folder + "COBBLESTONE");

    this.materialTexture['Ì'] = (folder + "BRICK");
    this.materialTexture['Í'] = (folder + "BRICK");
    this.materialTexture['Î'] = (folder + "BRICK");

    this.materialTexture['Ï'] = (folder + "STONE BRICK");
    this.materialTexture['Ð'] = (folder + "STONE BRICK");
    this.materialTexture['Ñ'] = (folder + "STONE BRICK");

    this.materialTexture['Ò'] = (folder + "NETHER BRICK");
    this.materialTexture['Ó'] = (folder + "NETHER BRICK");
    this.materialTexture['Ô'] = (folder + "NETHER BRICK");

    this.materialTextureType['?'] = 2;
    this.materialTexture['?'] = (folder + "FURNACE SIDE");
    this.materialTextureFront['?'] = (folder + "FURNACE");
    this.materialTextureTop['?'] = (folder + "FURNACE TOP");
    this.materialOrientation['?'] = Orientation.SOUTH;
    this.materialTextureType['?'] = 2;
    this.materialTexture['?'] = (folder + "FURNACE SIDE");
    this.materialTextureFront['?'] = (folder + "FURNACE");
    this.materialTextureTop['?'] = (folder + "FURNACE TOP");
    this.materialOrientation['?'] = Orientation.WEST;
    this.materialTextureType['?'] = 2;
    this.materialTexture['?'] = (folder + "FURNACE SIDE");
    this.materialTextureFront['?'] = (folder + "FURNACE");
    this.materialTextureTop['?'] = (folder + "FURNACE TOP");
    this.materialOrientation['?'] = Orientation.EAST;

    this.materialTextureType['?'] = 2;
    this.materialTexture['?'] = (folder + "FURNACE SIDE");
    this.materialTextureFront['?'] = (folder + "BURNING FURNACE");
    this.materialTextureTop['?'] = (folder + "FURNACE TOP");
    this.materialOrientation['?'] = Orientation.SOUTH;
    this.materialTextureType['?'] = 2;
    this.materialTexture['?'] = (folder + "FURNACE SIDE");
    this.materialTextureFront['?'] = (folder + "BURNING FURNACE");
    this.materialTextureTop['?'] = (folder + "FURNACE TOP");
    this.materialOrientation['?'] = Orientation.WEST;
    this.materialTextureType['?'] = 2;
    this.materialTexture['?'] = (folder + "FURNACE SIDE");
    this.materialTextureFront['?'] = (folder + "BURNING FURNACE");
    this.materialTextureTop['?'] = (folder + "FURNACE TOP");
    this.materialOrientation['?'] = Orientation.EAST;

    this.materialTextureType['?'] = 2;
    this.materialTexture['?'] = (folder + "CHEST SIDE");
    this.materialTextureFront['?'] = (folder + "CHEST FRONT");
    this.materialTextureTop['?'] = (folder + "CHEST TOP");
    this.materialOrientation['?'] = Orientation.SOUTH;
    this.materialTextureType['?'] = 2;
    this.materialTexture['?'] = (folder + "CHEST SIDE");
    this.materialTextureFront['?'] = (folder + "CHEST FRONT");
    this.materialTextureTop['?'] = (folder + "CHEST TOP");
    this.materialOrientation['?'] = Orientation.WEST;
    this.materialTextureType['?'] = 2;
    this.materialTexture['?'] = (folder + "CHEST SIDE");
    this.materialTextureFront['?'] = (folder + "CHEST FRONT");
    this.materialTextureTop['?'] = (folder + "CHEST TOP");
    this.materialOrientation['?'] = Orientation.EAST;

    this.materialTextureType['?'] = 2;
    this.materialTexture['?'] = (folder + "FURNACE SIDE");
    this.materialTextureFront['?'] = (folder + "DISPENSER FRONT");
    this.materialTextureTop['?'] = (folder + "FURNACE TOP");
    this.materialOrientation['?'] = Orientation.SOUTH;
    this.materialTextureType['?'] = 2;
    this.materialTexture['?'] = (folder + "FURNACE SIDE");
    this.materialTextureFront['?'] = (folder + "DISPENSER FRONT");
    this.materialTextureTop['?'] = (folder + "FURNACE TOP");
    this.materialOrientation['?'] = Orientation.WEST;
    this.materialTextureType['?'] = 2;
    this.materialTexture['?'] = (folder + "FURNACE SIDE");
    this.materialTextureFront['?'] = (folder + "DISPENSER FRONT");
    this.materialTextureTop['?'] = (folder + "FURNACE TOP");
    this.materialOrientation['?'] = Orientation.EAST;

    this.materialTextureType['?'] = 3;
    this.materialTexture['?'] = (folder + "torch side");
    this.materialTextureFront['?'] = (folder + "torch back");
    this.materialTextureTop['?'] = (folder + "torch fit top");
    this.materialTextureBottom['?'] = (folder + "torch fit bottom");
    this.materialOrientation['?'] = Orientation.WEST;
    this.materialTextureType['?'] = 3;
    this.materialTexture['?'] = (folder + "torch side");
    this.materialTextureFront['?'] = (folder + "torch back");
    this.materialTextureTop['?'] = (folder + "torch fit top");
    this.materialTextureBottom['?'] = (folder + "torch fit bottom");
    this.materialOrientation['?'] = Orientation.WEST;
    this.materialTextureType['?'] = 3;
    this.materialTexture['?'] = (folder + "torch side");
    this.materialTextureFront['?'] = (folder + "torch back");
    this.materialTextureTop['?'] = (folder + "torch fit top");
    this.materialTextureBottom['?'] = (folder + "torch fit bottom");
    this.materialOrientation['?'] = Orientation.WEST;
    this.materialTextureType['?'] = 3;
    this.materialTexture['?'] = (folder + "torch side");
    this.materialTextureFront['?'] = (folder + "torch back");
    this.materialTextureTop['?'] = (folder + "torch fit top");
    this.materialTextureBottom['?'] = (folder + "torch fit bottom");
    this.materialOrientation['?'] = Orientation.WEST;

    this.materialTexture['?'] = "tools/toolsplayerclip";

    this.materialTextureType['?'] = 3;
    this.materialTexture['?'] = (folder + "SNOW SIDE");
    this.materialTextureTop['?'] = (folder + "SNOW BLOCK");
    this.materialTextureFront['?'] = (folder + "SNOW SIDE");
    this.materialTextureBottom['?'] = (folder + "DIRT");

    this.materialTexture['?'] = (folder + "vines");
    this.materialTexture['?'] = (folder + "vines");
    this.materialTexture['?'] = (folder + "vines");
    this.materialTexture['?'] = (folder + "vines");
    this.materialTexture['?'] = (folder + "vines");
    this.materialTexture['?'] = (folder + "vines");
    this.materialTexture['?'] = (folder + "vines");
    this.materialTexture['?'] = (folder + "vines");
    this.materialTexture['?'] = (folder + "vines");
    this.materialTexture['?'] = (folder + "vines");
    this.materialTexture['?'] = (folder + "vines");
    this.materialTexture['?'] = (folder + "vines");
    this.materialTexture['?'] = (folder + "vines");
    this.materialTexture['?'] = (folder + "vines");
    this.materialTexture['?'] = (folder + "vines");

    this.materialTexture['?'] = (folder + "pine plank");
    this.materialTexture['?'] = (folder + "birch plank");
    this.materialTexture['?'] = (folder + "jungle plank");

    this.materialTextureType['?'] = 1;
    this.materialTexture['?'] = (folder + "jungle wood");
    this.materialTextureTop['?'] = (folder + "wood top");

    this.materialTexture['?'] = (folder + "jungle leaves transparent");

    this.materialTexture['?'] = (folder + "circle stone");

    this.materialTexture['?'] = (folder + "wooden plank");
    this.materialTexture['?'] = (folder + "wooden plank");
    this.materialTexture['?'] = (folder + "wooden plank");
    this.materialTexture['?'] = (folder + "wooden plank");

    this.materialTexture['?'] = (folder + "cobblestone");
    this.materialTexture['?'] = (folder + "cobblestone");
    this.materialTexture['?'] = (folder + "cobblestone");
    this.materialTexture['?'] = (folder + "cobblestone");

    this.materialTexture['?'] = (folder + "BRICK");
    this.materialTexture['?'] = (folder + "BRICK");
    this.materialTexture['?'] = (folder + "BRICK");
    this.materialTexture['?'] = (folder + "BRICK");

    this.materialTexture['?'] = (folder + "STONE BRICK");
    this.materialTexture['?'] = (folder + "STONE BRICK");
    this.materialTexture['?'] = (folder + "STONE BRICK");
    this.materialTexture['?'] = (folder + "STONE BRICK");

    this.materialTexture['?'] = (folder + "nether brick");
    this.materialTexture['?'] = (folder + "nether brick");
    this.materialTexture['?'] = (folder + "nether brick");
    this.materialTexture['?'] = (folder + "nether brick");

    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");

    this.materialTexture['?'] = (folder + "pine wooden plank");
    this.materialTexture['?'] = (folder + "birch wooden plank");
    this.materialTexture['?'] = (folder + "jungle wooden plank");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "pine wooden plank");
    this.materialTexture['?'] = (folder + "birch wooden plank");
    this.materialTexture['?'] = (folder + "jungle wooden plank");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");

    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");

    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");
    this.materialTexture['?'] = (folder + "todo");

    this.materialTextureType['?'] = 3;
    this.materialTexture['?'] = (folder + "sandstone side");
    this.materialTextureFront['?'] = (folder + "sandstone side");
    this.materialTextureTop['?'] = (folder + "sandstone top");
    this.materialTextureBottom['?'] = (folder + "sandstone bottom");
    this.materialTextureType['?'] = 3;
    this.materialTexture['?'] = (folder + "sandstone side");
    this.materialTextureFront['?'] = (folder + "sandstone side");
    this.materialTextureTop['?'] = (folder + "sandstone top");
    this.materialTextureBottom['?'] = (folder + "sandstone bottom");
    this.materialTextureType['?'] = 3;
    this.materialTexture['?'] = (folder + "sandstone side");
    this.materialTextureFront['?'] = (folder + "sandstone side");
    this.materialTextureTop['?'] = (folder + "sandstone top");
    this.materialTextureBottom['?'] = (folder + "sandstone bottom");

    this.materialTextureType['?'] = 3;
    this.materialTexture['?'] = (folder + "sandstone side");
    this.materialTextureFront['?'] = (folder + "sandstone side");
    this.materialTextureTop['?'] = (folder + "sandstone top");
    this.materialTextureBottom['?'] = (folder + "sandstone bottom");
    this.materialTextureType['?'] = 3;
    this.materialTexture['?'] = (folder + "sandstone side");
    this.materialTextureFront['?'] = (folder + "sandstone side");
    this.materialTextureTop['?'] = (folder + "sandstone top");
    this.materialTextureBottom['?'] = (folder + "sandstone bottom");
    this.materialTextureType['?'] = 3;
    this.materialTexture['?'] = (folder + "sandstone side");
    this.materialTextureFront['?'] = (folder + "sandstone side");
    this.materialTextureTop['?'] = (folder + "sandstone top");
    this.materialTextureBottom['?'] = (folder + "sandstone bottom");
    this.materialTextureType['?'] = 3;
    this.materialTexture['?'] = (folder + "sandstone side");
    this.materialTextureFront['?'] = (folder + "sandstone side");
    this.materialTextureTop['?'] = (folder + "sandstone top");
    this.materialTextureBottom['?'] = (folder + "sandstone bottom");
  }

  public Skin getSkin(int m)
  {
    if (this.skins[m] != null) {
      return this.skins[m];
    }
    String texture = this.materialTexture[m];
    String textureTop = this.materialTextureTop[m];
    int type = this.materialTextureType[m];
    double s = this.materialTextureScale[m];
    Orientation o = this.materialOrientation[m];
    if (type == 0) {
      return new Skin(texture, s);
    }
    if (type == 1) {
      return new Skin(texture, textureTop, s);
    }
    if (type == 2) {
      return new Skin(texture, textureTop, this.materialTextureFront[m], o, s);
    }
    return new Skin(texture, textureTop, this.materialTextureFront[m], this.materialTextureBottom[m], o, s);
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
    int newMaterial;
    switch (map.getMaterial(x, y, z))
    {
      case 2:
        if ((map.hasMaterial(x, y + 1, z, 78)) || (map.hasMaterial(x, y + 1, z, 80))) {
          map.setMaterial(x, y, z, 346);
        }
      case 17:
        if (map.getData(x, y, z) == 1) {
          map.setMaterial(x, y, z, 257);
        } else if (map.getData(x, y, z) == 2) {
          map.setMaterial(x, y, z, 258);
        } else if (map.getData(x, y, z) == 3) {
          map.setMaterial(x, y, z, 365);
        }
        break;
      case 18:
        if ((map.getData(x, y, z) == 1) || (map.getData(x, y, z) == 5) || (map.getData(x, y, z) == 9) || (map.getData(x, y, z) == 13)) {
          map.setMaterial(x, y, z, 259);
        } else if ((map.getData(x, y, z) == 2) || (map.getData(x, y, z) == 6) || (map.getData(x, y, z) == 10) || (map.getData(x, y, z) == 14)) {
          map.setMaterial(x, y, z, 260);
        } else if ((map.getData(x, y, z) == 3) || (map.getData(x, y, z) == 7) || (map.getData(x, y, z) == 11) || (map.getData(x, y, z) == 15)) {
          map.setMaterial(x, y, z, 366);
        }
        break;
      case 35:
        if (map.getData(x, y, z) != 0) {
          map.setMaterial(x, y, z, 260 + map.getData(x, y, z));
        } else if (map.getData(x, y, z) == 2) {
          map.setMaterial(x, y, z, 262);
        } else if (map.getData(x, y, z) == 3) {
          map.setMaterial(x, y, z, 263);
        } else if (map.getData(x, y, z) == 4) {
          map.setMaterial(x, y, z, 264);
        } else if (map.getData(x, y, z) == 5) {
          map.setMaterial(x, y, z, 265);
        } else if (map.getData(x, y, z) == 6) {
          map.setMaterial(x, y, z, 266);
        } else if (map.getData(x, y, z) == 7) {
          map.setMaterial(x, y, z, 267);
        } else if (map.getData(x, y, z) == 8) {
          map.setMaterial(x, y, z, 268);
        } else if (map.getData(x, y, z) == 9) {
          map.setMaterial(x, y, z, 269);
        } else if (map.getData(x, y, z) == 10) {
          map.setMaterial(x, y, z, 270);
        } else if (map.getData(x, y, z) == 11) {
          map.setMaterial(x, y, z, 271);
        } else if (map.getData(x, y, z) == 12) {
          map.setMaterial(x, y, z, 272);
        } else if (map.getData(x, y, z) == 13) {
          map.setMaterial(x, y, z, 273);
        } else if (map.getData(x, y, z) == 14) {
          map.setMaterial(x, y, z, 274);
        } else if (map.getData(x, y, z) == 15) {
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
        } else if (map.getData(x, y, z) == 2) {
          map.setMaterial(x, y, z, 278);
        } else if (map.getData(x, y, z) == 3) {
          map.setMaterial(x, y, z, 367);
        }
        break;
      case 31:
        if (map.getData(x, y, z) == 1) {
          map.setMaterial(x, y, z, 279);
        } else if (map.getData(x, y, z) == 2) {
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
        } else if (map.getData(x, y, z) == 2) {
          map.setMaterial(x, y, z, 312);
        }
        break;
      case 53:
        newMaterial = 53;
        switch (data)
        {
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
        switch (data)
        {
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
        switch (data)
        {
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
        switch (data)
        {
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
        switch (data)
        {
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
        } else if (map.getData(x, y, z) == 4) {
          map.setMaterial(x, y, z, 330);
        } else if (map.getData(x, y, z) == 5) {
          map.setMaterial(x, y, z, 331);
        }
        break;
      case 62:
        if (map.getData(x, y, z) == 3) {
          map.setMaterial(x, y, z, 332);
        } else if (map.getData(x, y, z) == 4) {
          map.setMaterial(x, y, z, 333);
        } else if (map.getData(x, y, z) == 5) {
          map.setMaterial(x, y, z, 334);
        }
        break;
      case 54:
        if (map.getData(x, y, z) == 3) {
          map.setMaterial(x, y, z, 335);
        } else if (map.getData(x, y, z) == 4) {
          map.setMaterial(x, y, z, 336);
        } else if (map.getData(x, y, z) == 5) {
          map.setMaterial(x, y, z, 337);
        }
        break;
      case 23:
        if (map.getData(x, y, z) == 3) {
          map.setMaterial(x, y, z, 338);
        } else if (map.getData(x, y, z) == 4) {
          map.setMaterial(x, y, z, 339);
        } else if (map.getData(x, y, z) == 5) {
          map.setMaterial(x, y, z, 340);
        }
        break;
      case 50:
        if (map.getData(x, y, z) == 1) {
          map.setMaterial(x, y, z, 341);
        } else if (map.getData(x, y, z) == 2) {
          map.setMaterial(x, y, z, 342);
        } else if (map.getData(x, y, z) == 3) {
          map.setMaterial(x, y, z, 343);
        } else if (map.getData(x, y, z) == 4) {
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
        switch (data)
        {
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
        switch (data)
        {
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
        switch (data)
        {
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
        switch (data)
        {
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
        switch (data)
        {
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

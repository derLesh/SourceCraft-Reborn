package buildable;

import java.io.IOException;
import java.io.Writer;


public class Free8Point
  extends Cuboid
{
  private int lightmapscale;
  private boolean align;
  
  public Free8Point(Point[] p, Skin skin, boolean align)
  {
    super(p, skin);
    this.align = align;
  }
  
  public void writeVmf(Counter cc, Writer w) throws IOException
  {
    if (align == true) {
      w.write("\tsolid\n\t{\n\t\t\"id\" \"" + cc.getBrushId() + "\"\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + b.getString() + ") (" + f.getString() + ") (" + g.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialTop + "\"\n                         \"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + e.getString() + ") (" + a.getString() + ") (" + d.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialBottom + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + a.getString() + ") (" + e.getString() + ") (" + f.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialLeft + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + h.getString() + ") (" + d.getString() + ") (" + c.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialRight + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + e.getString() + ") (" + h.getString() + ") (" + g.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialBack + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + d.getString() + ") (" + a.getString() + ") (" + b.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialFront + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n");
      w.write("\t\teditor\n\t\t{\n\t\t\t\"color\" \"0 215 172\"\n\t\t\t\"visgroupshown\" \"1\"\n\t\t\t\"visgroupautoshown\" \"1\"\n\t\t}\n\t}\n");
    }
    else {
      w.write("\tsolid\n\t{\n\t\t\"id\" \"" + cc.getBrushId() + "\"\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + b.getString() + ") (" + f.getString() + ") (" + g.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialTop + "\"\n                         \"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + e.getString() + ") (" + a.getString() + ") (" + d.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialBottom + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + a.getString() + ") (" + e.getString() + ") (" + f.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialLeft + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + h.getString() + ") (" + d.getString() + ") (" + c.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialRight + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + e.getString() + ") (" + h.getString() + ") (" + g.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialBack + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + d.getString() + ") (" + a.getString() + ") (" + b.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialFront + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n");
      w.write("\t\teditor\n\t\t{\n\t\t\t\"color\" \"0 215 172\"\n\t\t\t\"visgroupshown\" \"1\"\n\t\t\t\"visgroupautoshown\" \"1\"\n\t\t}\n\t}\n");
    }
  }
}

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

  public void writeVmf(Counter cc, Writer w)
          throws IOException
  {
    if (this.align)
    {
      w.write("\tsolid\n\t{\n\t\t\"id\" \"" + cc.getBrushId() + "\"\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.b.getString() + ") (" + this.f.getString() + ") (" + this.g.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialTop + "\"\n                         \"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.e.getString() + ") (" + this.a.getString() + ") (" + this.d.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialBottom + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.a.getString() + ") (" + this.e.getString() + ") (" + this.f.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialLeft + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.h.getString() + ") (" + this.d.getString() + ") (" + this.c.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialRight + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.e.getString() + ") (" + this.h.getString() + ") (" + this.g.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialBack + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.d.getString() + ") (" + this.a.getString() + ") (" + this.b.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialFront + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n");
      w.write("\t\teditor\n\t\t{\n\t\t\t\"color\" \"0 215 172\"\n\t\t\t\"visgroupshown\" \"1\"\n\t\t\t\"visgroupautoshown\" \"1\"\n\t\t}\n\t}\n");
    }
    else
    {
      w.write("\tsolid\n\t{\n\t\t\"id\" \"" + cc.getBrushId() + "\"\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.b.getString() + ") (" + this.f.getString() + ") (" + this.g.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialTop + "\"\n                         \"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.e.getString() + ") (" + this.a.getString() + ") (" + this.d.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialBottom + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.a.getString() + ") (" + this.e.getString() + ") (" + this.f.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialLeft + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.h.getString() + ") (" + this.d.getString() + ") (" + this.c.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialRight + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.e.getString() + ") (" + this.h.getString() + ") (" + this.g.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialBack + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + cc.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.d.getString() + ") (" + this.a.getString() + ") (" + this.b.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialFront + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n");
      w.write("\t\teditor\n\t\t{\n\t\t\t\"color\" \"0 215 172\"\n\t\t\t\"visgroupshown\" \"1\"\n\t\t\t\"visgroupautoshown\" \"1\"\n\t\t}\n\t}\n");
    }
  }
}

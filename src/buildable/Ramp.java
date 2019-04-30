package buildable;

import java.io.IOException;
import java.io.Writer;

public class Ramp extends Cuboid
{
  private Orientation orientation;

  public Ramp(Cuboid cuboid, Orientation orientation)
  {
    super(cuboid);
    this.orientation = orientation;
  }

  public Ramp(Point aPoint, Point gPoint, Skin skin, Orientation o)
  {
    super(aPoint, gPoint, skin);
    this.orientation = o;
  }

  public Ramp(Point fPoint, int xLength, int yLength, int zLength, int scale, Skin skin, Orientation o)
  {
    super(fPoint, xLength, yLength, zLength, scale, skin);
    this.orientation = o;
    scale(scale);
  }

  public void writeVmf(Counter counter, Writer w)
          throws IOException
  {
    if (this.orientation == Orientation.WEST)
    {
      counter.getSideId();
      w.write("\tsolid\n\t{\n\t\t\"id\" \"" + counter.getBrushId() + "\"\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + this.h.getString() + ") (" + this.d.getString() + ") (" + this.b.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialTop + "\"\n                         \"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + this.d.getString() + ") (" + this.h.getString() + ") (" + this.e.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialBottom + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + this.b.getString() + ") (" + this.a.getString() + ") (" + this.e.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialLeft + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.f.getString() + ") (" + this.e.getString() + ") (" + this.h.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialBack + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.a.getString() + ") (" + this.b.getString() + ") (" + this.d.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialFront + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n");
      w.write("\t\teditor\n\t\t{\n\t\t\t\"color\" \"0 215 172\"\n\t\t\t\"visgroupshown\" \"1\"\n\t\t\t\"visgroupautoshown\" \"1\"\n\t\t}\n\t}\n");
    }
    else if (this.orientation == Orientation.EAST)
    {
      counter.getSideId();
      w.write("\tsolid\n\t{\n\t\t\"id\" \"" + counter.getBrushId() + "\"\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + this.a.getString() + ") (" + this.e.getString() + ") (" + this.g.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialTop + "\"\n                         \"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + this.d.getString() + ") (" + this.h.getString() + ") (" + this.e.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialBottom + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + this.h.getString() + ") (" + this.d.getString() + ") (" + this.c.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialLeft + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.d.getString() + ") (" + this.a.getString() + ") (" + this.c.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialBack + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.g.getString() + ") (" + this.e.getString() + ") (" + this.h.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialFront + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n");
      w.write("\t\teditor\n\t\t{\n\t\t\t\"color\" \"0 215 172\"\n\t\t\t\"visgroupshown\" \"1\"\n\t\t\t\"visgroupautoshown\" \"1\"\n\t\t}\n\t}\n");
    }
    else if (this.orientation == Orientation.NORTH)
    {
      counter.getSideId();
      w.write("\tsolid\n\t{\n\t\t\"id\" \"" + counter.getBrushId() + "\"\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + this.g.getString() + ") (" + this.d.getString() + ") (" + this.a.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialTop + "\"\n                         \"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + this.d.getString() + ") (" + this.h.getString() + ") (" + this.e.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialBottom + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + this.a.getString() + ") (" + this.e.getString() + ") (" + this.f.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialLeft + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.d.getString() + ") (" + this.g.getString() + ") (" + this.h.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialBack + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.f.getString() + ") (" + this.e.getString() + ") (" + this.h.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialFront + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n");
      w.write("\t\teditor\n\t\t{\n\t\t\t\"color\" \"0 215 172\"\n\t\t\t\"visgroupshown\" \"1\"\n\t\t\t\"visgroupautoshown\" \"1\"\n\t\t}\n\t}\n");
    }
    else
    {
      counter.getSideId();
      w.write("\tsolid\n\t{\n\t\t\"id\" \"" + counter.getBrushId() + "\"\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + this.d.getString() + ") (" + this.a.getString() + ") (" + this.b.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialTop + "\"\n                         \"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + this.a.getString() + ") (" + this.d.getString() + ") (" + this.h.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialBottom + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + this.a.getString() + ") (" + this.e.getString() + ") (" + this.b.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialLeft + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.d.getString() + ") (" + this.c.getString() + ") (" + this.h.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialBack + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + this.h.getString() + ") (" + this.c.getString() + ") (" + this.b.getString() + ")\"\n\t\t\t\"material\" \"" + this.skin.materialFront + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + this.skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + this.skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n");
      w.write("\t\teditor\n\t\t{\n\t\t\t\"color\" \"0 215 172\"\n\t\t\t\"visgroupshown\" \"1\"\n\t\t\t\"visgroupautoshown\" \"1\"\n\t\t}\n\t}\n");
    }
  }
}

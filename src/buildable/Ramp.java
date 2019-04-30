package buildable;

import java.io.IOException;
import java.io.Writer;






public class Ramp
  extends Cuboid
{
  private Orientation orientation;
  
  public Ramp(Cuboid cuboid, Orientation orientation)
  {
    super(cuboid);
    this.orientation = orientation;
  }
  
  public Ramp(Point aPoint, Point gPoint, Skin skin, Orientation o) {
    super(aPoint, gPoint, skin);
    orientation = o;
  }
  
  public Ramp(Point fPoint, int xLength, int yLength, int zLength, int scale, Skin skin, Orientation o) {
    super(fPoint, xLength, yLength, zLength, scale, skin);
    orientation = o;
    scale(scale);
  }
  
  public void writeVmf(Counter counter, Writer w) throws IOException
  {
    if (orientation == Orientation.WEST) {
      counter.getSideId();
      w.write("\tsolid\n\t{\n\t\t\"id\" \"" + counter.getBrushId() + "\"\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + h.getString() + ") (" + d.getString() + ") (" + b.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialTop + "\"\n                         \"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + d.getString() + ") (" + h.getString() + ") (" + e.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialBottom + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + b.getString() + ") (" + a.getString() + ") (" + e.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialLeft + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + f.getString() + ") (" + e.getString() + ") (" + h.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialBack + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + a.getString() + ") (" + b.getString() + ") (" + d.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialFront + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n");
      w.write("\t\teditor\n\t\t{\n\t\t\t\"color\" \"0 215 172\"\n\t\t\t\"visgroupshown\" \"1\"\n\t\t\t\"visgroupautoshown\" \"1\"\n\t\t}\n\t}\n");
    }
    else if (orientation == Orientation.EAST) {
      counter.getSideId();
      w.write("\tsolid\n\t{\n\t\t\"id\" \"" + counter.getBrushId() + "\"\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + a.getString() + ") (" + e.getString() + ") (" + g.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialTop + "\"\n                         \"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + d.getString() + ") (" + h.getString() + ") (" + e.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialBottom + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + h.getString() + ") (" + d.getString() + ") (" + c.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialLeft + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + d.getString() + ") (" + a.getString() + ") (" + c.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialBack + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + g.getString() + ") (" + e.getString() + ") (" + h.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialFront + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n");
      w.write("\t\teditor\n\t\t{\n\t\t\t\"color\" \"0 215 172\"\n\t\t\t\"visgroupshown\" \"1\"\n\t\t\t\"visgroupautoshown\" \"1\"\n\t\t}\n\t}\n");
    }
    else if (orientation == Orientation.NORTH) {
      counter.getSideId();
      w.write("\tsolid\n\t{\n\t\t\"id\" \"" + counter.getBrushId() + "\"\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + g.getString() + ") (" + d.getString() + ") (" + a.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialTop + "\"\n                         \"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + d.getString() + ") (" + h.getString() + ") (" + e.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialBottom + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + a.getString() + ") (" + e.getString() + ") (" + f.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialLeft + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + d.getString() + ") (" + g.getString() + ") (" + h.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialBack + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + f.getString() + ") (" + e.getString() + ") (" + h.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialFront + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n");
      w.write("\t\teditor\n\t\t{\n\t\t\t\"color\" \"0 215 172\"\n\t\t\t\"visgroupshown\" \"1\"\n\t\t\t\"visgroupautoshown\" \"1\"\n\t\t}\n\t}\n");
    }
    else {
      counter.getSideId();
      w.write("\tsolid\n\t{\n\t\t\"id\" \"" + counter.getBrushId() + "\"\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + d.getString() + ") (" + a.getString() + ") (" + b.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialTop + "\"\n                         \"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + a.getString() + ") (" + d.getString() + ") (" + h.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialBottom + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 -1 0 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + (counter.getSideId() - 1) + "\"\n\t\t\t\"plane\" \"(" + a.getString() + ") (" + e.getString() + ") (" + b.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialLeft + "\"\n\t\t\t\"uaxis\" \"[0 1 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + d.getString() + ") (" + c.getString() + ") (" + h.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialBack + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n\t\tside\n\t\t{\n");
      w.write("\t\t\t\"id\" \"" + counter.getSideId() + "\"\n\t\t\t\"plane\" \"(" + h.getString() + ") (" + c.getString() + ") (" + b.getString() + ")\"\n\t\t\t\"material\" \"" + skin.materialFront + "\"\n\t\t\t\"uaxis\" \"[1 0 0 0] " + skin.scale + "\"\n\t\t\t\"vaxis\" \"[0 0 -1 0] " + skin.scale + "\"\n\t\t\t\"rotation\" \"0\"\n\t\t\t\"lightmapscale\" \"16\"\n\t\t\t\"smoothing_groups\" \"0\"\n\t\t}\n");
      w.write("\t\teditor\n\t\t{\n\t\t\t\"color\" \"0 215 172\"\n\t\t\t\"visgroupshown\" \"1\"\n\t\t\t\"visgroupautoshown\" \"1\"\n\t\t}\n\t}\n");
    }
  }
}

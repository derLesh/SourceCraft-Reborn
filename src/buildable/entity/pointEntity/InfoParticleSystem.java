package buildable.entity.pointEntity;

import buildable.Counter;
import buildable.Point;
import java.io.IOException;
import java.io.Writer;


public class InfoParticleSystem
  extends PointEntity
{
  private final String effectName;
  private final int angleX;
  private final int angleY;
  private final int angleZ;
  
  public InfoParticleSystem(String effectName, int angleX, int angleY, int angleZ)
  {
    this(Point.DEFAULT, effectName, angleX, angleY, angleZ);
  }
  
  public InfoParticleSystem(Point origin, String effectName, int angleX, int angleY, int angleZ) {
    super(origin);
    this.effectName = effectName;
    this.angleX = angleX;
    this.angleY = angleY;
    this.angleZ = angleZ;
  }
  
  public InfoParticleSystem create(Point origin)
  {
    return new InfoParticleSystem(origin, effectName, angleX, angleY, angleZ);
  }
  
  public void writeVmf(Counter counter, Writer w)
    throws IOException
  {
    w.write("entity\n");
    w.write("{\n");
    w.write("\t\"id\" \"" + counter.getBrushId() + "\"\n");
    w.write("\t\"classname\" \"info_particle_system\"\n");
    w.write("\t\"angles\" \"" + angleX + " " + angleY + " " + angleZ + "\"\n");
    w.write("\t\"cpoint1_parent\" \"0\"\n");
    w.write("\t\"cpoint2_parent\" \"0\"\n");
    w.write("\t\"cpoint3_parent\" \"0\"\n");
    w.write("\t\"cpoint4_parent\" \"0\"\n");
    w.write("\t\"cpoint5_parent\" \"0\"\n");
    w.write("\t\"cpoint6_parent\" \"0\"\n");
    w.write("\t\"cpoint7_parent\" \"0\"\n");
    w.write("\t\"effect_name\" \"" + effectName + "\"\n");
    w.write("\t\"flag_as_weather\" \"0\"\n");
    w.write("\t\"start_active\" \"1\"\n");
    w.write("\t\"origin\" \"" + p.getString() + "\"\n");
    w.write("\teditor\n");
    w.write("\t{\n");
    w.write("\t\t\"color\" \"220 30 220\"\n");
    w.write("\t\t\"visgroupshown\" \"1\"\n");
    w.write("\t\t\"visgroupautoshown\" \"1\"\n");
    w.write("\t\t\"logicalpos\" \"[0 0]\"\n");
    w.write("\t}\n");
    w.write("}\n");
  }
}

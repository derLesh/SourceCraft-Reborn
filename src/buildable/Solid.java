package buildable;

import minecraft.VmfWriter;

public abstract class Solid implements VmfWriter
{
  protected Skin skin;

  public final void setSkin(Skin skin)
  {
    this.skin = skin;
  }
}

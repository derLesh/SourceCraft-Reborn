package config;

public class PlacesWriter
        extends Writer
{
  private Config config;

  public PlacesWriter(Config config)
  {
    super("places.txt");
    this.config = config;
  }

  public void writePlaces()
  {
    writePlaces(false);
    end();
  }

  private void writePlaces(boolean successor)
  {
    Place[] places = this.config.getPlaces();
    if (places != null)
    {
      initiateSection("places");

      int length = places.length;
      if (length > 0)
      {
        for (int i = 0; i < length - 1; i++) {
          writePlace(places[i], true);
        }
        writePlace(places[(length - 1)], false);
      }
      finishSection(successor);
    }
  }

  private void writePlace(Place place, boolean successor)
  {
    initiateSection(place.getName());

    writeFinalArgument("world", place.getWorld(), true);
    writeFinalArgument("x", place.getX(), true);
    writeFinalArgument("z", place.getZ(), true);
    writeFinalArgument("#x", place.getNumX(), true);
    writeFinalArgument("#z", place.getNumZ(), true);
    writeFinalArgument("yStart", place.getYStart(), true);
    writeFinalArgument("yEnd", place.getYEnd(), false);

    finishSection(successor);
  }
}

package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;





public class PlacesReader
  extends Reader
{
  public static final String fileName = "places.txt";
  private Config config;
  
  public PlacesReader(Config config)
    throws FileNotFoundException
  {
    super("places.txt");
    this.config = config;
    readConfig();
  }
  
  private void readConfig() {
    try {
      do {
        String argument = nextArgument();
        
        if (argument.equals("places")) {
          readPlaces();
        }
      } while (s.read() == 44);
    } catch (IOException ex) {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private void readPlaces() {
    try {
      do {
        String placeName = nextArgument();
        
        readPlace(placeName);
      } while (s.read() == 44);
      
      while (s.read() != 62) {}
    }
    catch (IOException ex) {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  private void readPlace(String placeName)
  {
    Place place = new Place(placeName);
    try {
      do {
        String argument = nextArgument();
        
        if (argument.equals("world")) {
          String world = nextData();
          place.setWorld(world);
        }
        else if (argument.equals("x")) {
          String x = nextData();
          place.setX(new Integer(x).intValue());
        }
        else if (argument.equals("z")) {
          String z = nextData();
          place.setZ(new Integer(z).intValue());
        }
        else if (argument.equals("#x")) {
          String numX = nextData();
          place.setNumX(new Integer(numX).intValue());
        }
        else if (argument.equals("#z")) {
          String numZ = nextData();
          place.setNumZ(new Integer(numZ).intValue());
        }
        else if (argument.equals("yStart")) {
          String yStart = nextData();
          place.setYStart(new Integer(yStart).intValue());
        }
        else if (argument.equals("yEnd")) {
          String yEnd = nextData();
          place.setYEnd(new Integer(yEnd).intValue());
        }
        
      } while (s.read() == 44);
      
      while (s.read() != 62) {}
    }
    catch (IOException ex) {
      Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
    }
    config.addPlace(place);
  }
}

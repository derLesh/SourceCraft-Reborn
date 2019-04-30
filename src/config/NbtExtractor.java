package config;

import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import minecraft.RegionFile;




public class NbtExtractor
{
  public NbtExtractor() {}
  
  public static void main(String[] args)
  {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Choose region folder");
    File minecraftSavesDirectory = new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\.minecraft\\saves");
    fileChooser.setCurrentDirectory(minecraftSavesDirectory);
    try {
      int state = fileChooser.showOpenDialog(null);
      if (state == 0) {
        File file = fileChooser.getSelectedFile();
        extractNbt(file);
      }
    }
    catch (HeadlessException e) {
      System.out.println(e.getMessage());
    }
  }
  
  public static void extractNbt(File file) {
    RegionFile regionfile = new RegionFile(file);
    DataInputStream inputStream = regionfile.getChunkDataInputStream(1, 1);
    if (inputStream == null) {
      System.out.println("inputStream null");
    }
    
    System.out.println("save file");
    JFileChooser data_save = new JFileChooser();
    data_save.setSelectedFile(new File("test.nbt"));
    int state = data_save.showSaveDialog(null);
    if (state == 0) {
      File fileSave = data_save.getSelectedFile();
      try {
        FileWriter w = new FileWriter(fileSave);
        w = new FileWriter(file);
        int amount = inputStream.available();
        while (amount > 0) {
          int read = inputStream.readByte();
          w.write(read);
          amount--;
        }
      } catch (IOException ex) {
        Logger.getLogger(NbtExtractor.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}

package minecraft;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class TextureFolderMover
{
  public TextureFolderMover() {}
  
  public static void main(String[] args)
  {
    File srcFolder = new File("c:\\hoho");
    File destFolder = new File("c:\\hoho-new");
    if (!srcFolder.exists())
    {
      System.out.println("Directory does not exist.");
      
      System.exit(0);
    }
    else
    {
      try {
        copyFolder(srcFolder, destFolder);
      }
      catch (IOException e) {
        e.printStackTrace();
        
        System.exit(0);
      }
    }
    System.out.println("Done");
  }
  
  public static void moveFolder(File destFolder, File srcFolder)
  {
    try {
      copyFolder(srcFolder, destFolder);
    }
    catch (IOException e) {
      e.printStackTrace();
      
      System.exit(0);
    }
  }
  
  public static void copyFolder(File source, File dest) throws IOException {
    if (source.isDirectory())
    {
      if (!dest.exists()) {
        dest.mkdir();
      }
      


      String[] files = source.list();
      
      for (String file : files)
      {
        File srcFile = new File(source, file);
        File destFile = new File(dest, file);
        
        copyFolder(srcFile, destFile);
      }
      
    }
    else
    {
      InputStream in = new java.io.FileInputStream(source);
      OutputStream out = new FileOutputStream(dest);
      
      byte[] buffer = new byte['Ѐ'];
      
      int length;
      
      while ((length = in.read(buffer)) > 0) {
        out.write(buffer, 0, length);
      }
      
      in.close();
      out.close();
    }
  }
}

package minecraft;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class NbtReader
{
  DataInputStream s;
  
  public NbtReader(DataInputStream newStream)
  {
    s = newStream;
  }
  
  public abstract void readChunkData();
  
  public boolean skipTag()
  {
    try {
      int type = s.readUnsignedByte();
      if (type == 0) {
        return false;
      }
      String title = readTitle();
      if (type == 1) {
        s.readUnsignedByte();
      }
      if (type == 2) {
        s.readUnsignedByte();
        s.readUnsignedByte();
      }
      if ((type == 3) || (type == 5)) {
        for (int i = 0; i < 4; i++) {
          s.readByte();
        }
      }
      if ((type == 4) || (type == 6)) {
        for (int i = 0; i < 8; i++) {
          s.readByte();
        }
      }
      if (type == 7) {
        int length = nbtLength();
        readForLength(length);
      }
      if (type == 8) {
        skipString();
      }
      if (type == 9) {
        skipList();
      }
      if (type == 10) {
        skipCompound();
      }
      if (type == 11) {
        skipIntArray();
      }
      return true;
    }
    catch (FileNotFoundException e) {
      System.out.println("File missing");
    }
    catch (IOException e) {
      System.out.println("skipTag " + e.toString());
    }
    return true;
  }
  
  public void skipIntArray()
  {
    try {
      int length = s.readInt();
      for (int i = 0; i < length; i++) {
        s.readInt();
      }
    } catch (IOException ex) {
      Logger.getLogger(NbtMcaReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public String readTitle()
  {
    try {
      byte type = s.readByte();
      int length = s.readUnsignedByte();
      String title = "";
      for (int i = 0; i < length; i++) {
        Byte b = Byte.valueOf(s.readByte());
        char c = (char)(b.byteValue() & 0xFF);
        title = title + c;
      }
      return title;
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File missing");
    }
    catch (IOException e) {
      System.out.println("nbtTitle-Exception:");
      System.out.println(e.toString());
    }
    return "";
  }
  
  public int nbtLength()
  {
    try
    {
      return s.readInt();
    }
    catch (FileNotFoundException e) {
      System.out.println("File missing");
    }
    catch (IOException e) {
      System.out.println(e.toString());
    }
    return 0;
  }
  

  public boolean skipTagInTagList(int type)
  {
    try
    {
      if (type == 0) {
        System.out.println("skipTagInTagList: unexpected 0tag");
        return false;
      }
      String title = "";
      if (type == 1) {
        s.readUnsignedByte();
      }
      if (type == 2) {
        s.readUnsignedByte();
        s.readUnsignedByte();
      }
      if ((type == 3) || (type == 5)) {
        for (int i = 0; i < 4; i++) {
          s.readByte();
        }
      }
      if ((type == 4) || (type == 6)) {
        for (int i = 0; i < 8; i++) {
          s.readByte();
        }
      }
      if (type == 7) {
        int length = nbtLength();
        readForLength(length);
      }
      if (type == 8) {
        int length = s.readByte();
        length = length * 256 + s.readByte();
        readForLength(length);
      }
      if (type == 9) {
        skipList();
      }
      if (type == 10) {
        skipCompound();
      }
      return true;
    }
    catch (FileNotFoundException e) {
      System.out.println("File missing");
    }
    catch (IOException e) {
      System.out.println("skipTagInTagList " + e.toString());
    }
    return true;
  }
  
  public void skipCompound() {
    while (skipTag() == true) {}
  }
  
  public void readForLength(int length) {
    try { byte dummy;
      for (int i = 0; i < length; i++) {
        dummy = s.readByte();
      }
    }
    catch (FileNotFoundException e) {
      System.out.println("File missing");
    }
    catch (IOException e) {
      System.out.println(e.toString());
    }
  }
  
  public int readByte() {
    try {
      return s.readUnsignedByte();
    }
    catch (IOException ex) {
      Logger.getLogger(NbtMcaReader.class.getName()).log(Level.SEVERE, null, ex); }
    return -1;
  }
  


  public void skipList()
  {
    try
    {
      int tagId = s.readByte();
      int listLength = nbtLength();
      if (tagId != 0) {
        for (int i = 0; i < listLength; i++) {
          skipTagInTagList(tagId);
        }
      }
    }
    catch (FileNotFoundException e) {
      System.out.println("File missing");
    }
    catch (IOException e) {
      System.out.println("skipList " + e.toString());
    }
  }
  
  public void skipString()
  {
    try {
      int length = s.readByte();
      length = length * 256 + s.readByte();
      readForLength(length);
    } catch (IOException ex) {
      Logger.getLogger(NbtMcaReader.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}

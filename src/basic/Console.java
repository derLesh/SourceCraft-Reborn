package basic;

import java.io.PrintStream;

public class Console
{
  public Console() {}
  
  public static int readIntOrDefault(int defaultInt)
  {
    String line = null;
    int value = 0;
    try {
      java.io.BufferedReader is = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
      line = is.readLine();
      value = Integer.parseInt(line);
    }
    catch (NumberFormatException ex)
    {
      return defaultInt;
    }
    catch (java.io.IOException e) {
      System.err.println("Unexpected IO ERROR: " + e);
    }
    return value;
  }
  
  public static int readInt() {
    String line = null;
    int value = 0;
    try {
      java.io.BufferedReader is = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
      line = is.readLine();
      value = Integer.parseInt(line);
    }
    catch (NumberFormatException ex) {
      System.out.println("Couldn't read number. Enter a whole number:");
      return readInt();
    }
    catch (java.io.IOException e) {
      System.err.println("Unexpected IO ERROR: " + e);
    }
    return value;
  }
  
  public static String readString()
  {
    String line = null;
    try {
      java.io.BufferedReader is = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
      line = is.readLine();
    }
    catch (java.io.IOException e) {
      System.err.println("Unexpected IO ERROR: " + e);
    }
    return line;
  }
  
  public static void debug(String message) {
    System.out.println(message);
  }
  
  public static void devWarning(String warning) {
    System.out.println(warning);
  }
  
  public static void warning(String message) {
    System.out.println("[Warning] " + message);
  }
  
  public static void info(String message) { System.out.println(message); }
  
  public static void dev(String message) {
    System.out.println("[Dev]     " + message);
  }
  
  public static void error(String message) {
    System.out.println("[Error]   " + message);
    System.out.println("Press 'enter' to exit.");
    readString();
    System.exit(0);
  }
}

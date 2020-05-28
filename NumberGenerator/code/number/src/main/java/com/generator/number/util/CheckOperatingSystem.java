package com.generator.number.util;

import org.springframework.stereotype.Component;

@Component
public class CheckOperatingSystem {
  private static String OS;
  
  public String findCurrentOS() {
    OS = System.getProperty("os.name").toLowerCase();
    if (OS.contains("win"))
      return "windows"; 
    if (OS.contains("mac"))
      return "mac"; 
    if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"))
      return "unix"; 
    if (OS.contains("sunos"))
      return "solaris"; 
    return OS;
  }
}

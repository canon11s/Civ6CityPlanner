package util;

import java.awt.*;

public enum CityColor {
  YELLOW, RED, BLUE, PURPLE, GREEN, ORANGE, NONE;

  public static String[] stringArray() {
    return new String[]{"None", "Red", "Orange", "Yellow", "Green", "Blue", "Purple"};
  }

  public static CityColor stringToEnum(String toString) {
    switch(toString) {
      case "None":
        return NONE;
      case "Red":
        return RED;
      case "Orange":
        return ORANGE;
      case "Yellow":
        return YELLOW;
      case "Green":
        return GREEN;
      case "Blue":
        return BLUE;
      case "Purple":
        return PURPLE;
    }
    throw new IllegalArgumentException("String " + toString + " not recognized in City Color enum.");
  }

  public Color toColor() {
    switch (this) {
      case RED:
        return Color.RED;
      case BLUE:
        return Color.BLUE;
      case GREEN:
        return Color.GREEN;
      case ORANGE:
        return Color.ORANGE;
      case PURPLE:
        return new Color(128, 0, 128);
      case YELLOW:
        return Color.YELLOW;
    }
    throw new IllegalStateException("No color to return");
  }
}

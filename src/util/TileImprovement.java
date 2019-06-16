package util;

import model.Tile;

public enum TileImprovement {
  CITY_CENTER, COMMERCIAL_HUB, THEATER_SQUARE, ENCAMPMENT, GENERIC_IMPROVEMENT, NONE;

  public static String[] stringArray() {
    return new String[]{"None", "Generic Improvement", "City Center", "Commercial Hub", "Theater Square", "Encampment"};
  }

  public static TileImprovement stringToEnum(String toString) {
    switch (toString) {
      case "None":
        return NONE;
      case "City Center":
        return  CITY_CENTER;
      case "Commercial Hub":
        return COMMERCIAL_HUB;
      case "Generic Improvement":
        return GENERIC_IMPROVEMENT;
    }
    throw new IllegalArgumentException("String " + toString + " not recognized in Tile Improvement enum");
  }
}

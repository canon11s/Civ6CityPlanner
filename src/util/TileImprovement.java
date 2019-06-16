package util;

import model.Tile;

public enum TileImprovement {
  CITY_CENTER, COMMERCIAL_HUB, THEATER_SQUARE, ENCAMPMENT, CAMPUS, HOLY_SITE, AQUEDUCT,
  INDUSTRIAL_ZONE, NEIGHBORHOOD, HARBOR, ENTERTAINMENT_COMPLEX, AERODROME, SPACEPORT, GENERIC_IMPROVEMENT, NONE;

  public static String[] stringArray() {
    return new String[]{"None", "City Center",
            "Generic Improvement", "Aerodrome", "Aqueduct", "Campus", "Commercial Hub",
            "Encampment",
            "Entertainment Complex", "Harbor", "Holy Site", "Industrial Zone", "Neighborhood"
            ,"Spaceport", "Theater Square"};
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
      case "Campus":
        return CAMPUS;
      case "Theater Square":
        return THEATER_SQUARE;
      case "Encampment":
        return ENCAMPMENT;
      case "Holy Site":
        return HOLY_SITE;
      case "Aqueduct":
        return AQUEDUCT;
      case "Aerodrome":
        return AERODROME;
      case "Spaceport":
        return SPACEPORT;
      case "Entertainment Complex":
        return ENTERTAINMENT_COMPLEX;
      case "Harbor":
        return HARBOR;
      case "Industrial Zone":
        return INDUSTRIAL_ZONE;
      case "Neighborhood":
        return NEIGHBORHOOD;

    }
    throw new IllegalArgumentException("String " + toString + " not recognized in Tile Improvement enum");
  }
}

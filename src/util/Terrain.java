package util;
import java.awt.Color;

public enum Terrain {
    GRASSLAND, PLAINS, DESERT, TUNDRA, SNOW, COAST, LAKE, OCEAN;

    public Color fillColor() {
      switch (this) {
        case GRASSLAND:
          return new Color(110, 149, 46);
        case PLAINS:
          return new Color(162, 164, 54);
        case DESERT:
          return new Color(231, 191, 111);
        case TUNDRA:
          return new Color(161, 146, 94);
        case SNOW:
          return new Color(220, 237, 245);
        case COAST:
          return new Color(44, 84, 118);
        case LAKE:
          return new Color(34, 94, 128);
        case OCEAN:
          return new Color(43, 46, 85);
      }
      return Color.BLACK;
    }

    public static String[] stringArray() {
      return new String[]{"Grassland", "Plains", "Desert", "Tundra", "Snow", "Coast", "Lake", "Ocean"};
    }

    public static Terrain stringToEnum(String str) {
      switch (str) {
        case "Grassland":
          return GRASSLAND;
        case "Plains":
          return PLAINS;
        case "Desert":
          return DESERT;
        case "Tundra":
          return TUNDRA;
        case "Snow":
          return SNOW;
        case "Coast":
          return COAST;
        case "Lake":
          return LAKE;
        case "Ocean":
          return OCEAN;
      }
      throw new IllegalArgumentException("String '" + str + "' not found in enum.");
    }

  public static String enumToString(Terrain terr) {
    switch (terr) {
      case GRASSLAND:
        return "Grassland";
      case PLAINS:
        return "Plains";
      case DESERT:
        return "Desert";
      case TUNDRA:
        return "Tundra";
      case SNOW:
        return "Snow";
      case COAST:
        return "Coast";
      case LAKE:
        return "Lake";
      case OCEAN:
        return "Ocean";
    }
    throw new IllegalArgumentException("Enum value " + terr.toString() + " not found in enum.");
  }
}

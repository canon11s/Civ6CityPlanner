package model;

/**
 * Representation of a single tile on the game board
 */
public class Tile {
  /**
   * Fields for a tile
   */
  private util.Terrain terrain;
  private boolean hills;
  private util.Feature feature;

  public Tile() {
    terrain = util.Terrain.GRASSLAND;
    hills = false;
    feature = util.Feature.NONE;
  }

  public void setTerrain(util.Terrain terrain) {
    this.terrain = terrain;
  }

  public util.Terrain getTerrain() {
    return this.terrain;
  }

  public void setHills(boolean hills) {
    this.hills = hills;
  }

  public void setFeature(util.Feature feature) {
    this.feature = feature;
  }

  public void removeFeature() throws IllegalStateException {
    if (feature == util.Feature.NONE) {
      throw new IllegalStateException("Can not remove feature; no feature present.");
    } else {
      feature = util.Feature.NONE;
    }
  }

  public StringBuilder tileToString() {
    String hillsStr;
    if (hills) {
      hillsStr = "1";
    } else {
      hillsStr = "0";
    }
    return new StringBuilder(terrain.toString() + " " + hillsStr + " " + feature.toString());
  }
}

package model;

import util.CityColor;
import util.TileImprovement;

/**
 * Representation of a single tile on the game board
 */
public class Tile {
  /**
   * terrain
   * hills
   * feature
   * rivers - boolean array containing rivers information:
   * rivers[0] - left-most, vertical river. rivers[1] is one position clockwise,
   * continuing up through rivers[5].
   */
  private util.Terrain terrain;
  private boolean hills;
  private util.Feature feature;
  private boolean leftRiver;
  private boolean upperLeftRiver;
  private boolean upperRightRiver;
  private boolean rightRiver;
  private boolean bottomRightRiver;
  private boolean bottomLeftRiver;
  private util.TileImprovement improvement;
  private util.CityColor color;

  public Tile() {
    terrain = util.Terrain.GRASSLAND;
    hills = false;
    feature = util.Feature.NONE;
    leftRiver = false;
    upperLeftRiver = false;
    upperRightRiver = false;
    rightRiver = false;
    bottomRightRiver = false;
    bottomLeftRiver = false;
    color = CityColor.NONE;
    improvement = TileImprovement.NONE;
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

  public boolean hasHills() {
    return this.hills;
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
    return new StringBuilder(terrain + " " + hillsStr + " " + feature.toString());
  }

  public boolean[] getRivers() {
    return new boolean[]{leftRiver, upperLeftRiver, upperRightRiver,
            rightRiver, bottomRightRiver, bottomLeftRiver};
  }

  public void setRiver(boolean river, util.TileEdge edge) {
    switch (edge) {
      case LEFT:
        leftRiver = river;
        break;
      case RIGHT:
        rightRiver = river;
        break;
      case UPPER_LEFT:
        upperLeftRiver = river;
        break;
      case BOTTOM_LEFT:
        bottomLeftRiver = river;
        break;
      case UPPER_RIGHT:
        upperRightRiver = river;
        break;
      case BOTTOM_RIGHT:
        bottomRightRiver = river;
        break;
    }
  }

  public boolean hasRiver(util.TileEdge edge) {
    switch (edge) {
      case LEFT:
        return leftRiver;
      case RIGHT:
        return rightRiver;
      case UPPER_LEFT:
        return upperLeftRiver;
      case BOTTOM_LEFT:
        return bottomLeftRiver;
      case UPPER_RIGHT:
        return upperRightRiver;
      case BOTTOM_RIGHT:
        return bottomRightRiver;
    }
    throw new IllegalArgumentException("Edge not recognized");
  }

  public void changeImprovement(CityColor color, TileImprovement improvement) {
    this.color = color;
    this.improvement = improvement;
  }

  public CityColor getColor() {
    return color;
  }

  public TileImprovement getImprovement() {
    return improvement;
  }

  public boolean hasImprovement() {
    return !(color == CityColor.NONE && improvement == TileImprovement.NONE);
  }
}

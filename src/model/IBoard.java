package model;

/**
 * Interface to represent the board of a civ 6 game. Orientation of the hex tiles is the same as is
 * present in Civ 6: vertical on either side, with a point on top and bottom.
 */
public interface IBoard {



  /**
   * Set the size dimension of the IBoard - distance from center hex to outer edge
   *
   * @param size desired size of the board, must be >= 3
   */
  void setSize(int size);

  /**
   * Get the size of the IBoard - distance from center hex to outer edge
   *
   * @return int size of the board
   */
  int getSize();

  /**
   * Reset the game board to standard tiles - grassland plain
   */
  void resetTiles();

  /**
   * Reset the size of the game board to standard size - 5 tiles from center to edge
   */
  void resetSize();

  /**
   * Sets the terrain of the tile at the given coordinate
   * @param tileQ q position of the tile, must be >= 0
   * @param tileR r position of the tile, must be >= 0
   * @param terr terrain type the tile will be set to
   * @throws IllegalArgumentException if q, r is not valid
   */
  void setTileTerrain(int tileQ, int tileR, util.Terrain terr) throws IllegalArgumentException;

  /**
   * Gets the terrain of the tile at the given coordinate
   * @param tileQ q position of the tile, must be >= 0
   * @param tileR r position of the tile, must be >= 0
   * @throws IllegalArgumentException if q, r is not valid
   */
  util.Terrain getTileTerrain(int tileQ, int tileR) throws IllegalArgumentException;

  /**
   * Sets the hills of the tile to the given type
   * @param tileQ q position of the tile, must be >= 0
   * @param tileR r position of the tile, must be >= 0
   * @param hills whether the tile contains hills
   * @throws IllegalArgumentException if q, r invalid, or hill type is not valid for tile type
   */
  void setTileHills(int tileQ, int tileR, boolean hills) throws IllegalArgumentException;

  /**
   *
   * @param tileQ q position of the tile, must be >= 0
   * @param tileR r position of the tile, must be >= 0
   * @return boolean if tile has hills
   * @throws IllegalArgumentException if q, r invalid
   */
  boolean hasHills(int tileQ, int tileR) throws IllegalArgumentException;

  /**
   *
   * @param tileQ q position of the tile, must be >= 0
   * @param tileR r position of the tile, must be >= 0
   * @param feat feature that will be added to the given tile
   * @throws IllegalArgumentException q, r not valid, or feature not valid for the tile type
   */
  void setTileFeature(int tileQ, int tileR, util.Feature feat) throws IllegalArgumentException;

  /**
   *
   * @param tileQ q position of the tile, must be >= 0
   * @param tileR r position of the tile, must be >= 0
   * @throws IllegalArgumentException if q, r is not valid
   * @throws IllegalStateException if no feature on given tile
   */
  void removeTileFeature(int tileQ, int tileR) throws IllegalArgumentException, IllegalStateException;

  /**
   * Convert the board to a string, for saving purposes
   * @return
   */
  String boardToString();
}

package model;

public class Board implements IBoard {

  /**
   * Fields of the board
   *
   */
  private int size;
  private Tile[][] board;

  public Board() {
    size = 5;
    board = new Tile[2 * size + 1][2 * size + 1];
    for (int i = 0; i <= 2 * size; i++) {
      for (int j = Math.max(size - i, 0); j <= Math.min(2 * size, 3 * size - i); j++) {
        board[i][j] = new Tile();
      }
    }
  }

  @Override
  public void setSize(int size) {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public void resetTiles() {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public void resetSize() {
    throw new UnsupportedOperationException("Not yet implemented.");
  }

  @Override
  public void setTileTerrain(int tileQ, int tileR, util.Terrain terr) throws IllegalArgumentException {
    board[tileQ ][tileR].setTerrain(terr);
  }

  @Override
  public util.Terrain getTileTerrain(int tileQ, int tileR) throws IllegalArgumentException {
    return board[tileQ][tileR].getTerrain();
  }

  @Override
  public void setTileHills(int tileQ, int tileR, boolean hills) throws IllegalArgumentException {
    try {
      board[tileQ][tileR].setHills(hills);
    } catch (IllegalStateException i) {
      throw new IllegalArgumentException(i.getMessage());
    }
  }

  @Override
  public void setTileFeature(int tileQ, int tileR, util.Feature feat) throws IllegalArgumentException {

    try {
      board[tileQ][tileR].setFeature(feat);
    } catch (IllegalStateException i) {
      throw new IllegalArgumentException(i.getMessage());
    }
  }

  @Override
  public void removeTileFeature(int tileQ, int tileR) throws IllegalArgumentException, IllegalStateException {
    try {
      board[tileQ][tileR].removeFeature();
    } catch (IllegalStateException i) {
      throw new IllegalStateException(i.getMessage());
    }
  }

  @Override
  public String boardToString() {
    StringBuilder boardStr = new StringBuilder();
    for (int i = 0; i < board[0].length; i++) {
      for (Tile t: board[i]) {
        boardStr = boardStr.append(t.tileToString());
      }
    }
    return boardStr.toString();
  }
}

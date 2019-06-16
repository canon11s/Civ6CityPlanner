package model;

import util.TileEdge;

public class Board implements IBoard {

  /**
   * Fields of the board
   *
   */
  private int size;
  private Tile[][] board;

  public Board() {
    size = 10;
    board = new Tile[2 * size + 1][2 * size + 1];
    for (int i = 0; i <= 2 * size; i++) {
      for (int j = Math.max(size - i, 0); j <= Math.min(2 * size, 3 * size - i); j++) {
        board[i][j] = new Tile();
      }
    }
//    for (boolean[] a : rivers) {
//      for (boolean b : a) {
//        if (b) {
//          System.out.print('X');
//        } else {
//          System.out.print('O');
//        }
//        System.out.print(" ");
//      }
//      System.out.print("\n");
//    }
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

  public boolean hasHills(int tileQ, int tileR) throws IllegalArgumentException {
    try {
      return board[tileQ][tileR].hasHills();
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
  public void getTileFeature(int tileQ, int tileR) throws IllegalArgumentException {

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

  @Override
  public boolean[] getRivers(int tileQ, int tileR) {
    return board[tileQ][tileR].getRivers();
  }

  @Override
  public void flipRiver(int tileQ, int tileR, TileEdge edge) {
    boolean river = !board[tileQ][tileR].hasRiver(edge);
    board[tileQ][tileR].setRiver(river, edge);
    System.out.println("(" + Integer.toString(tileQ) + ", " + Integer.toString(tileR) + ")");
    switch (edge) {
      case LEFT:
        if (tileQ > Math.max(size - tileR, 0)) {
          board[tileQ - 1][tileR].setRiver(river, TileEdge.RIGHT);
        }
        break;
      case RIGHT:
        if (tileQ < Math.min(2 * size, 3 * size - tileR)) {
          board[tileQ + 1][tileR].setRiver(river, TileEdge.LEFT);
        }
        break;
      case UPPER_LEFT:
        if (tileR > size - tileQ && tileR != 0) {
          board[tileQ][tileR - 1].setRiver(river, TileEdge.BOTTOM_RIGHT);
        }
        break;
      case BOTTOM_LEFT:
        if (tileQ != 0 && tileR != 2 * size) {
          board[tileQ - 1][tileR + 1].setRiver(river, TileEdge.UPPER_RIGHT);
        }
        break;
      case UPPER_RIGHT:
        if (tileQ != 4 && tileR != 0) {
          board[tileQ + 1][tileR - 1].setRiver(river, TileEdge.BOTTOM_LEFT);
        }
        break;
      case BOTTOM_RIGHT:
        if (tileQ < 3 * size - tileR && tileR != 2 * size) {
          board[tileQ][tileR + 1].setRiver(river, TileEdge.UPPER_LEFT);
        }
        break;
    }
  }
}

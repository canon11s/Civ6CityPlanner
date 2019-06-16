package controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import model.IBoard;
import util.ControllerMode;
import util.TileEdge;
import view.IView;

public class Controller {
  private IBoard board;
  private IView view;
  private util.ControllerMode mode;
  private util.Terrain terrain;
  private util.Feature feature;
  private util.CityColor color;
  private util.TileImprovement improvement;
  private boolean hills;
  private ArrayList<util.ControllerMode> modesUsed;

  public Controller(IBoard board, IView view) {
    this.board = board;
    this.view = view;
    this.mode = util.ControllerMode.NONE;
    this.configureButtonListener();
    this.modesUsed = new ArrayList<>();
  }

  public void mouseClick(int pixelX, int pixelY, int hexRadius) {
    Point p;
    int q, r;
      switch (this.mode) {
        case CHANGE_TERRAIN:
          p = pixToTileCoord(pixelX, pixelY, hexRadius);
          q = p.x;
          r = p.y;
          if (r < 0 || r > 2 * board.getSize() || q < Math.max(board.getSize() - r, 0) || q > Math.min(2 * board.getSize(), 3 * board.getSize() - r)) {
            JOptionPane.showMessageDialog(null, "Actions can not be performed outside of the tiled board.");
          } else {
            board.setTileTerrain(q, r, terrain);
            board.setTileFeature(q, r, feature);
            board.setTileHills(q, r, hills);
            view.redraw();
          }
          break;
        case EDIT_RIVER:
          p = pixToTileCoord(pixelX, pixelY, hexRadius);
          q = p.x;
          r = p.y;
          if (r < 0 || r > 2 * board.getSize() || q < Math.max(board.getSize() - r, 0) || q > Math.min(2 * board.getSize(), 3 * board.getSize() - r)) {
            JOptionPane.showMessageDialog(null, "Actions can not be performed outside of the tiled board.");
          } else {
            board.flipRiver(q, r, closestEdge(q, r, pixelX, pixelY, hexRadius));
          }
          view.redraw();
          break;
        case PLACE_IMPROVEMENT:
          p = pixToTileCoord(pixelX, pixelY, hexRadius);
          q = p.x;
          r = p.y;
          if (r < 0 || r > 2 * board.getSize() || q < Math.max(board.getSize() - r, 0) || q > Math.min(2 * board.getSize(), 3 * board.getSize() - r)) {
            JOptionPane.showMessageDialog(null, "Actions can not be performed outside of the tiled board.");
          } else {
            board.placeImprovement(q, r, color, improvement);
          }
          view.redraw();
          break;
        case NONE:
          JOptionPane.showMessageDialog(null, "Select a mode to perform an action on the board.");
          break;

      }
  }

  private void configureButtonListener() {
    Map<String,Runnable> buttonClickedMap = new HashMap<String,Runnable>();
    ButtonListener buttonListener = new ButtonListener();

    //add all the buttons. The action command needs to match the string in the map
    buttonClickedMap.put("Edit Terrain", new EditTerrainAction());
    buttonClickedMap.put("Edit River", new EditRiverAction());
    buttonClickedMap.put("Zoom In", new ZoomInAction());
    buttonClickedMap.put("Zoom Out", new ZoomOutAction());
    buttonClickedMap.put("Place Improvement", new PlaceImprovementAction());

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }

  class EditTerrainAction implements Runnable {
    public void run() {
      if (!modesUsed.contains(ControllerMode.CHANGE_TERRAIN)) {
        modesUsed.add(ControllerMode.CHANGE_TERRAIN);
        JOptionPane.showMessageDialog(null,
                "Click a tile to change the tile to the input settings. Note: click 'Set " +
                        "Tile' again if tile settings are changed.");
      }
      mode = ControllerMode.CHANGE_TERRAIN;
      terrain = view.chosenTerrain();
      feature = view.chosenFeature();
      hills = view.chosenHills();
    }
  }

  class PlaceImprovementAction implements Runnable {
    public void run() {
      if (!modesUsed.contains(ControllerMode.PLACE_IMPROVEMENT)) {
        modesUsed.add(ControllerMode.PLACE_IMPROVEMENT);
        JOptionPane.showMessageDialog(null,
                "Click a tile to place the selected improvement.");
      }
      mode = ControllerMode.PLACE_IMPROVEMENT;
      color = view.chosenColor();
      improvement = view.chosenImprovement();
    }
  }

  class EditRiverAction implements Runnable {
    public void run() {
      if (!modesUsed.contains(ControllerMode.EDIT_RIVER)) {
        modesUsed.add(ControllerMode.EDIT_RIVER);
        JOptionPane.showMessageDialog(null, "Click on 2 consecutive corners to add/remove a river.");
      }
      mode = ControllerMode.EDIT_RIVER;
    }
  }

  class ZoomInAction implements Runnable {
    public void run() {
      view.increaseHexRadius();
    }
  }

  class ZoomOutAction implements Runnable {
    public void run() {
      view.decreaseHexRadius();
    }
  }


  private Point pixToTileCoord(int xPixel, int yPixel, int hexRadius) {
    double q = (Math.sqrt(3)/3 * xPixel  -  1./3 * yPixel) / hexRadius;
    double r = 2./3 * yPixel / hexRadius;
    double x = q;
    double y = r;
    double z = -q-r;
    long rx = Math.round(x);
    long ry = Math.round(y);
    long rz = Math.round(z);
    double x_diff = Math.abs(rx - x);
    double y_diff = Math.abs(ry - y);
    double z_diff = Math.abs(rz - z);

    if (x_diff > y_diff && x_diff > z_diff) {
      rx = -ry - rz;
    }
    else if (y_diff > z_diff) {
      ry = -rx - rz;
    }
    return new Point((int) rx, (int) ry);
  }

  private util.TileEdge closestEdge(int q, int r, int xPixel, int yPixel, int hexRadius) {
    Point center = new Point((int) (hexRadius * (Math.sqrt(3) * q  +  Math.sqrt(3)/2. * r)),
            (int) (hexRadius * (3./2. * r)));
    Point click = new Point(xPixel, yPixel);

    if (xPixel < center.x) {
      //in left half
      Point leftEdgeCenter = new Point((int) (center.x - hexRadius * Math.sqrt(3) / 2.),
              center.y);
      Point upEdgeCenter = new Point((int) (center.x - hexRadius * Math.sqrt(3) / 4),
              (int) (center.y - hexRadius * 3. / 4.));
      Point bottomEdgeCenter = new Point((int) (center.x - hexRadius * Math.sqrt(3) / 4),
              (int) (center.y + hexRadius * 3. / 4.));
      double leftEdgeDist = click.distance(leftEdgeCenter);
      double upEdgeDist = click.distance(upEdgeCenter);
      double bottomEdgeDist = click.distance(bottomEdgeCenter);

      if (leftEdgeDist < bottomEdgeDist && leftEdgeDist < upEdgeDist) {
        return TileEdge.LEFT;
      } else if (bottomEdgeDist < upEdgeDist) {
        return TileEdge.BOTTOM_LEFT;
      } else {
        return TileEdge.UPPER_LEFT;
      }

    } else {
      //in right half
      Point rightEdgeCenter = new Point((int) (center.x + hexRadius * Math.sqrt(3) / 2.),
              center.y);
      Point upEdgeCenter = new Point((int) (center.x + hexRadius * Math.sqrt(3) / 4),
              (int) (center.y - hexRadius * 3. / 4.));
      Point bottomEdgeCenter = new Point((int) (center.x + hexRadius * Math.sqrt(3) / 4),
              (int) (center.y + hexRadius * 3. / 4.));

      double rightEdgeDist = click.distance(rightEdgeCenter);
      double upEdgeDist = click.distance(upEdgeCenter);
      double bottomEdgeDist = click.distance(bottomEdgeCenter);

      if (rightEdgeDist < bottomEdgeDist && rightEdgeDist < upEdgeDist) {
        return TileEdge.RIGHT;
      } else if (bottomEdgeDist < upEdgeDist) {
        return TileEdge.BOTTOM_RIGHT;
      } else {
        return TileEdge.UPPER_RIGHT;
      }
    }
  }
}

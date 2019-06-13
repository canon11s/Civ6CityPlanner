package controller;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import model.IBoard;
import util.ControllerMode;
import view.IView;

public class Controller {
  private IBoard board;
  private IView view;
  private util.ControllerMode mode;
  private util.Terrain terrain;
  private util.Feature feature;
  private boolean hills;

  public Controller(IBoard board, IView view) {
    this.board = board;
    this.view = view;
    this.mode = util.ControllerMode.NONE;
    this.configureButtonListener();
  }

  public void mouseClick(int q, int r) {
    if (r < 0 || r > 2 * board.getSize() || q < Math.max(board.getSize() - r, 0) || q > Math.min(2 * board.getSize(), 3 * board.getSize() - r)) {
      JOptionPane.showMessageDialog(null, "Actions can not be performed outside of the tiled board.");
    } else {
      switch (this.mode) {
        case CHANGE_TERRAIN:
          board.setTileTerrain(q, r, terrain);
          board.setTileFeature(q, r, feature);
          board.setTileHills(q, r, hills);
          view.redraw();
          break;
        case NONE:
          JOptionPane.showMessageDialog(null, "Select a mode to perform an action on the board.");
          break;
      }
    }
  }

  private void configureButtonListener() {
    Map<String,Runnable> buttonClickedMap = new HashMap<String,Runnable>();
    ButtonListener buttonListener = new ButtonListener();

    //add all the buttons. The action command needs to match the string in the map
    buttonClickedMap.put("Edit Terrain", new EditTerrainAction());

    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }

  class EditTerrainAction implements Runnable {
    public void run() {
      mode = ControllerMode.CHANGE_TERRAIN;
      terrain = view.chosenTerrain();
      feature = view.chosenFeature();
      hills = view.chosenHills();
    }
  }
}

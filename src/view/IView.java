package view;

import java.awt.event.ActionListener;

import controller.Controller;

public interface IView {
  /**
   * Show the board.
   */
  void display();

  /**
   * Set the size of the hex tiles in the view - for visibility
   * @param radius pixels size of one hex tile
   */
  void setHexRadius(int radius);

  /**
   * Redraw the scene
   */
  void redraw();

  /**
   * Set the controller for the view
   */
  void setController(Controller controller);

  void addActionListener(ActionListener actionListener);

  util.Terrain chosenTerrain();

  util.Feature chosenFeature();

  boolean chosenHills();
}

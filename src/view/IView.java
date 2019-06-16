package view;

import java.awt.event.ActionListener;

import controller.Controller;

public interface IView {
  /**
   * Show the board.
   */
  void display();

  /**
   * Increase hex size - zoom in effect
   */
  void increaseHexRadius();

  /**
   * Decrease hex size - zoom out effect
   */
  void decreaseHexRadius();

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

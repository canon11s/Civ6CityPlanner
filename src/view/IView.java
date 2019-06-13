package view;

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
}

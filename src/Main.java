import controller.Controller;
import model.Board;
import model.IBoard;
import util.Terrain;
import view.View;

public final class Main {
  public static void main(String[] args) {
    IBoard board = new Board();
    View view = new View(board);
    Controller controller = new Controller(board, view);
    view.setController(controller);
    view.display();
  }
}

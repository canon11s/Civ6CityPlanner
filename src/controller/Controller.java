package controller;

import java.awt.*;

import model.IBoard;
import view.IView;

public class Controller {
  private IBoard board;
  private IView view;
  private util.ControllerMode mode;

  public Controller(IBoard board, IView view) {
    this.board = board;
    this.view = view;
    this.mode = util.ControllerMode.NONE;
  }

  public void mouseClick(int x, int y) {

  }
}

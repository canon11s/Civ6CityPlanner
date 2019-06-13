package view;

import java.awt.*;

import javax.swing.*;

import model.IBoard;

public class View extends JFrame implements IView {
  private BoardPanel boardPanel;
  private int hexRadius;

  public View(IBoard board) {
    super("Civilization 6 City Planner");
    hexRadius = 50;
    this.setBackground(Color.GRAY);
    //Add some things to the GUI
    this.boardPanel = new BoardPanel(board);
    JScrollPane scrollPane = new JScrollPane(boardPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.setPreferredSize(new Dimension(800, 800));
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(new JButton("Change terrain"));
    buttonPanel.add(new JButton("Change hills"));
    buttonPanel.add(new JButton("Add settlement"));
    buttonPanel.add(new JButton("Add district"));
    mainPanel.add(scrollPane);
    mainPanel.add(buttonPanel);
    this.add(mainPanel);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  @Override
  public void setHexRadius(int radius) {
    hexRadius = radius;
    boardPanel.setHexRadius(radius);
  }

  @Override
  public void redraw() {
    boardPanel.repaint();
  }
}

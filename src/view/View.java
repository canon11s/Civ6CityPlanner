package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.Controller;
import model.IBoard;

public class View extends JFrame implements IView {
  private BoardPanel boardPanel;
  private int hexRadius;
  private JButton editTerrain;
  private JComboBox terrainComboBox;
  private JCheckBox hillsCheckBox;
  private JComboBox featureComboBox;
  private JScrollPane scrollPane;


  public View(IBoard board) {
    super("Civilization 6 City Planner");
    hexRadius = 50;
    this.setBackground(new Color(109, 99, 52));
    //boardPanel - section to house game board
    this.boardPanel = new BoardPanel(board);
    //Scroll bars for easier use
    scrollPane = new JScrollPane(boardPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    //Layout stuff
    this.setPreferredSize(new Dimension(800, 800));
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    JPanel buttonPanel = new JPanel(new FlowLayout());
    //Terrain, hills, feature combo boxes
    String[] terrainStrings = util.Terrain.stringArray();
    terrainComboBox = new JComboBox(terrainStrings);
    hillsCheckBox = new JCheckBox("Hills?");
    String[] featureStrings = util.Feature.stringArray();
    featureComboBox = new JComboBox(featureStrings);
    //Button to be enter setting terrain mode
    editTerrain = new JButton("Set tiles");
    editTerrain.setActionCommand("Edit Terrain");
    buttonPanel.add(terrainComboBox);
    buttonPanel.add(hillsCheckBox);
    buttonPanel.add(featureComboBox);
    buttonPanel.add(editTerrain);

    mainPanel.add(scrollPane);
    mainPanel.add(buttonPanel);
    this.add(mainPanel);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
  }

  @Override
  public void display() {
    scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum());
    scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getValue() / 2);
    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() / 2);
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

  @Override
  public void setController(Controller controller) {
    boardPanel.setController(controller);
  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    editTerrain.addActionListener(actionListener);
  }

  @Override
  public util.Terrain chosenTerrain() {
    return util.Terrain.stringToEnum(terrainComboBox.getSelectedItem().toString());
  }

  @Override
  public util.Feature chosenFeature() {
    return util.Feature.stringToEnum(featureComboBox.getSelectedItem().toString());
  }

  @Override
  public boolean chosenHills() {
    return hillsCheckBox.isSelected();
  }


}

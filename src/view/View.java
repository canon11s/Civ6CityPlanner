package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.Controller;
import model.IBoard;
import util.CityColor;
import util.Feature;
import util.Terrain;
import util.TileImprovement;

public class View extends JFrame implements IView {
  private BoardPanel boardPanel;
  private int hexRadius;
  private JButton editTerrain;
  private JButton editRiver;
  private JButton zoomIn;
  private JButton zoomOut;
  private JButton placeImprovement;
  private JComboBox colorComboBox;
  private JComboBox improvementComboBox;
  private JComboBox terrainComboBox;
  private JCheckBox hillsCheckBox;
  private JComboBox featureComboBox;
  private JScrollPane scrollPane;
  private int[] hexSizes = new int[]{20, 30, 50, 90, 150};


  public View(IBoard board) {
    super("Civilization 6 City Planner");
    hexRadius = 50;
    this.getContentPane().setBackground(new Color(109, 99, 52));
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
    JPanel terrainPanel = new JPanel(new FlowLayout());
    //Terrain, hills, feature combo boxes
    terrainComboBox = new JComboBox(Terrain.stringArray());
    hillsCheckBox = new JCheckBox("Hills?");
    featureComboBox = new JComboBox(Feature.stringArray());
    //Button to be enter setting terrain mode
    editTerrain = new JButton("Set tiles");
    editTerrain.setActionCommand("Edit Terrain");
    terrainPanel.add(terrainComboBox);
    terrainPanel.add(hillsCheckBox);
    terrainPanel.add(featureComboBox);
    terrainPanel.add(editTerrain);

    JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
    separator.setPreferredSize(new Dimension(10, 20));
    terrainPanel.add(separator);

    editRiver = new JButton("Add/Remove River");
    editRiver.setActionCommand("Edit River");

    terrainPanel.add(editRiver);

    JPanel improvementPanel = new JPanel();
    JPanel settingsPanel = new JPanel();

    //Tabbed pane for control
    JTabbedPane controlPane = new JTabbedPane();
    controlPane.addTab("Terrain and Rivers", terrainPanel);
    controlPane.addTab("Settlement", improvementPanel);
    controlPane.addTab("Settings", settingsPanel);

    JLabel zoomLabel = new JLabel("Zoom");
    zoomIn = new JButton("+");
    zoomOut = new JButton("-");

    settingsPanel.add(zoomOut);
    settingsPanel.add(zoomLabel);
    settingsPanel.add(zoomIn);
    zoomIn.setActionCommand("Zoom In");
    zoomOut.setActionCommand("Zoom Out");

    placeImprovement = new JButton("Place");
    placeImprovement.setActionCommand("Place Improvement");
    colorComboBox = new JComboBox(CityColor.stringArray());
    improvementComboBox = new JComboBox(TileImprovement.stringArray());

    improvementPanel.add(colorComboBox);
    improvementPanel.add(improvementComboBox);
    improvementPanel.add(placeImprovement);

    mainPanel.add(scrollPane);
    mainPanel.add(controlPane);
    this.add(mainPanel);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
  }

  @Override
  public void display() {
    centerBoard();
    this.setVisible(true);
  }

  @Override
  public void increaseHexRadius() {
    if (hexRadius == hexSizes[0]) {
      hexRadius = hexSizes[1];
    } else if (hexRadius == hexSizes[1]) {
      hexRadius = hexSizes[2];
    } else if (hexRadius == hexSizes[2]) {
      hexRadius = hexSizes[3];
    } else if (hexRadius == hexSizes[3]) {
      hexRadius = hexSizes[4];
    } else if (hexRadius != hexSizes[4]) {
      throw new IllegalStateException("Unrecognized hex radius: " + Integer.toString(hexRadius));
    }
    boardPanel.setHexRadius(hexRadius);
    redraw();
    centerBoard();
  }

  @Override
  public void decreaseHexRadius() {
    if (hexRadius == hexSizes[4]) {
      hexRadius = hexSizes[3];
    } else if (hexRadius == hexSizes[3]) {
      hexRadius = hexSizes[2];
    } else if (hexRadius == hexSizes[2]) {
      hexRadius = hexSizes[1];
    } else if (hexRadius == hexSizes[1]) {
      hexRadius = hexSizes[0];
    } else if (hexRadius != hexSizes[0]) {
      throw new IllegalStateException("Unrecognized hex radius: " + hexRadius);
    }
    boardPanel.setHexRadius(hexRadius);
    redraw();
    centerBoard();
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
  public void centerBoard() {
    scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getMaximum());
    scrollPane.getHorizontalScrollBar().setValue(scrollPane.getHorizontalScrollBar().getValue() / 2);
    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
    scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getValue() / 2);
  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    editTerrain.addActionListener(actionListener);
    editRiver.addActionListener(actionListener);
    zoomIn.addActionListener(actionListener);
    zoomOut.addActionListener(actionListener);
    placeImprovement.addActionListener(actionListener);
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
  public util.CityColor chosenColor() {
    return util.CityColor.stringToEnum(colorComboBox.getSelectedItem().toString());
  }

  @Override
  public util.TileImprovement chosenImprovement() {
    return util.TileImprovement.stringToEnum(improvementComboBox.getSelectedItem().toString());
  }



  @Override
  public boolean chosenHills() {
    return hillsCheckBox.isSelected();
  }
}

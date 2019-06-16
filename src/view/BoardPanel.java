package view;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;


import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Controller;
import model.IBoard;
import util.CityColor;
import util.TileImprovement;

public class BoardPanel extends JPanel {
  private int hexRadius;
  private IBoard board;
  private int borderThickness;
  private Controller controller;


  public BoardPanel(IBoard board) {

    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent me) {
        requestFocusInWindow();
        controller.mouseClick(me.getX(), me.getY(), hexRadius);
      }
    });

    addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent ke) {
      }
    });

    hexRadius = 50;
    this.board = board;
    borderThickness = 5;
    this.setBackground(new Color(109, 99, 52));
    this.setPreferredSize(new Dimension(2 * (int) (hexRadius * (Math.sqrt(3) * board.getSize()  +  Math.sqrt(3)/2. * board.getSize())),
           2 * (int) (hexRadius * (3./2 * board.getSize()))));
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponents(g);
    Graphics2D g2 = (Graphics2D) g;
    //Paint hex board
    g2.setStroke(new BasicStroke(borderThickness));
    for (int i = 0; i <= 2 * board.getSize(); i++) {
      for (int j = Math.max(board.getSize() - i, 0); j <= Math.min(2 * board.getSize(), 3 * board.getSize() - i); j++) {
        g2.setPaint(board.getTileTerrain(i, j).fillColor());
        g2.fill(createHexagon((int) (hexRadius * (Math.sqrt(3) * i  +  Math.sqrt(3)/2. * j)),
                (int) (hexRadius * (3./2 * j))));
        g2.setPaint(Color.BLACK);
        g2.draw(createHexagon((int) (hexRadius * (Math.sqrt(3) * i  +  Math.sqrt(3)/2. * j)),
                (int) (hexRadius * (3./2 * j))));
      }
    }
    //Draw hills
    for (int i = 0; i <= 2 * board.getSize(); i++) {
      for (int j = Math.max(board.getSize() - i, 0); j <= Math.min(2 * board.getSize(), 3 * board.getSize() - i); j++) {
        if (board.hasHills(i, j)) {
          drawHills((int) (hexRadius * (Math.sqrt(3) * i  +  Math.sqrt(3)/2. * j)), (int) (hexRadius * (3./2 * j) - hexRadius / 2), g2);
        }
      }
    }
    //Draw cities/improvements/districts
    for (int i = 0; i <= 2 * board.getSize(); i++) {
      for (int j = Math.max(board.getSize() - i, 0); j <= Math.min(2 * board.getSize(), 3 * board.getSize() - i); j++) {
        if (board.hasImprovement(i, j)) {
          drawImprovement((int) (hexRadius * (Math.sqrt(3) * i  +  Math.sqrt(3)/2. * j)), (int) (hexRadius * (3./2 * j) + hexRadius / 2),
                  board.getColor(i, j), board.getImprovement(i, j) ,g2);
        }
      }
    }
    //Draw rivers
    for (int i = 0; i <= 2 * board.getSize(); i++) {
      for (int j = Math.max(board.getSize() - i, 0); j <= Math.min(2 * board.getSize(), 3 * board.getSize() - i); j++) {
        drawRivers((int) (hexRadius * (Math.sqrt(3) * i  +  Math.sqrt(3)/2. * j)), (int)
                (hexRadius * (3./2 * j)), board.getRivers(i, j), g2);
      }
    }
  }

  private void drawImprovement(int x, int y, CityColor color, TileImprovement improvement, Graphics2D g2) {
    if (color != CityColor.NONE) {
      g2.setPaint(color.toColor());
      g2.fill(new Ellipse2D.Double(x - hexRadius / 3, y - hexRadius / 3, hexRadius * 2 / 3, hexRadius * 2 / 3));
    }
    try {
      BufferedImage image;
      switch (improvement) {
        case CITY_CENTER:
          image = ImageIO.read(new File("resources/tile/CityCenter.png"));
          g2.drawImage(image, x - hexRadius / 4, y - hexRadius / 4, hexRadius / 2, hexRadius / 2, null);
          break;
        case COMMERCIAL_HUB:
          image = ImageIO.read(new File("resources/tile/CommercialHub.png"));
          g2.drawImage(image, x - hexRadius / 4, y - hexRadius / 4, hexRadius / 2, hexRadius / 2, null);
          break;
      }
    } catch (IOException e) {
      throw new IllegalStateException("Could not read desired file. " + improvement.toString() + " image.");
    }

  }

  public void setHexRadius(int size) {
    this.hexRadius = size;
    this.borderThickness = hexRadius / 10;
    this.setPreferredSize(new Dimension((int) (hexRadius * (Math.sqrt(3) * 2 * board.getSize()
            +  Math.sqrt(3)/2. * 2 * board.getSize())),
            (int) (hexRadius * (3./2 * 2 * board.getSize()))));
  }

  private void drawRivers(int x, int y, boolean[] rivers, Graphics2D g2) {
    g2.setPaint(Color.BLUE);
    g2.setStroke(new BasicStroke(borderThickness * 2));
    if (rivers[0]) {
      g2.drawLine((int) (x - hexRadius * Math.sqrt(3) / 2), (y - hexRadius / 2),
              (int) (x - hexRadius * Math.sqrt(3) / 2), (y + hexRadius / 2));
    }
    if (rivers[1]) {
      g2.drawLine((int) (x - hexRadius * Math.sqrt(3) / 2), (int) (y - hexRadius / 2),
              x, y - hexRadius);
    }
    if (rivers[2]) {
      g2.drawLine(x, y - hexRadius,
              (int) (x + hexRadius * Math.sqrt(3) / 2), (y - hexRadius / 2));
    }
    if (rivers[3]) {
      g2.drawLine((int) (x + hexRadius * Math.sqrt(3) / 2), (int) (y - hexRadius / 2),
              (int) (x + hexRadius * Math.sqrt(3) / 2), (int) (y + hexRadius / 2));
    }
    if (rivers[4]) {
      g2.drawLine((int) (x + hexRadius * Math.sqrt(3) / 2), (int) (y + hexRadius / 2),
              x, y + hexRadius);
    }
    if (rivers[5]) {
      g2.drawLine(x, y + hexRadius,
              (int) (x - hexRadius * Math.sqrt(3) / 2), (int) (y + hexRadius / 2));
    }

  }

  private void drawHills(int x, int y, Graphics2D g2) {
    g2.drawArc(x - hexRadius / 4, y - hexRadius / 8, hexRadius / 2, hexRadius / 2, 0, 180);
    g2.drawArc(x - hexRadius * 5 / 8, y + hexRadius / 8, hexRadius / 2, hexRadius / 3, 0, 180);
    g2.drawArc(x + hexRadius / 8, y + hexRadius / 6, hexRadius / 3, hexRadius / 3, 0, 180);
//    int x_delta = (int) (hexRadius * Math.sqrt(3) / 2);
//
//    try {
//      BufferedImage image = ImageIO.read(new File("resources/tile/Grassland.png"));
//      g2.drawImage(image, x - x_delta, y - hexRadius, (int) (hexRadius * Math.sqrt(3)), 2 * hexRadius, null);
//    } catch (IOException a) {
//      System.out.println("Image failure: " + a.getMessage());
//    }

  }

  private Polygon createHexagon(int x, int y) {
    Polygon polygon = new Polygon();
    polygon.addPoint((int)(x - hexRadius * Math.sqrt(3) / 2), y + hexRadius / 2);
    polygon.addPoint((int)(x - hexRadius * Math.sqrt(3) / 2), y - hexRadius / 2);
    polygon.addPoint(x, y - hexRadius);
    polygon.addPoint((int)(x + hexRadius * Math.sqrt(3) / 2), y - hexRadius / 2);
    polygon.addPoint((int)(x + hexRadius * Math.sqrt(3) / 2), y + hexRadius / 2);
    polygon.addPoint(x, y + hexRadius);
    return polygon;
  }



  public void setController(Controller controller) {
    this.controller = controller;
  }
}



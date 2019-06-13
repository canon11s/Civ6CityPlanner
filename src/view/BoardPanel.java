package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.*;

import controller.Controller;
import model.IBoard;

public class BoardPanel extends JPanel {
  private int hexRadius;
  private IBoard board;
  private int borderThickness;
  private Controller controller;


  public BoardPanel(IBoard board) {
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent me) {
        Point p = pixToCoord(me.getX(), me.getY());
//        controller.mouseClick(p.x, p.y);
      }
    });
    hexRadius = 50;
    this.board = board;
    borderThickness = 3;
    this.setPreferredSize(new Dimension((int) (hexRadius * (Math.sqrt(3) * 2 * board.getSize()
            +  Math.sqrt(3)/2. * 2 * board.getSize())),
            (int) (hexRadius * (3./2 * 2 * board.getSize()))));
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
  }

  public void setHexRadius(int size) {
    hexRadius = size;
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

  private Point pixToCoord(int xPixel, int yPixel) {
    double q = (Math.sqrt(3)/3 * xPixel  -  1./3 * yPixel) / hexRadius;
    double r = 2./3 * yPixel / hexRadius;
    double x = q;
    double y = r;
    double z = -q-r;
    int rx = (int) x;
    int ry = (int) y;
    int rz = (int) z;
    double x_diff = Math.abs(rx - x);
    double y_diff = Math.abs(ry - y);
    double z_diff = Math.abs(rz - z);

    if (x_diff > y_diff && x_diff > z_diff) {
      rx = -ry - rz;
    }
    else if (y_diff > z_diff) {
      ry = -rx - rz;
    }
    else {
      rz = -rx - ry;
    }
    System.out.println(Double.toString(rx) + " " + Double.toString(ry));
    return new Point(0, 0);
  }
}



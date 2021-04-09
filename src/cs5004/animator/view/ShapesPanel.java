package cs5004.animator.view;

import java.awt.*;
import javax.swing.*;

import cs5004.animator.shape.IShape;
import cs5004.animator.shape.Shape;


public class ShapesPanel extends JPanel  {
//  int seconds = 0;
  int x = 0;
  int y = 0;
  Color color;
  int width;
  int height;
  int r;
  int g;
  int b;
  public ShapesPanel() {

  }

  public  void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setBackground(new Color(255, 255, 255));

    Graphics2D g2D = (Graphics2D) g;
    this.color = new Color(this.getR(), this.getG(), this.getB());
    g2D.setColor(color);
    g2D.drawRect(x, y, width, height);
    g2D.fillRect(x, y, width, height);
  }

  public void setShapes(IShape shape) {
    if (shape.getType() == Shape.RECTANGLE) {
      RectangleGraphic rectangle1 =  new RectangleGraphic();
      rectangle1.setShapes(shape);
    }
  }

  public int getR() {
    return r;
  }

  public int getG() {
    return g;
  }

  public int getB() {
    return b;
  }


}

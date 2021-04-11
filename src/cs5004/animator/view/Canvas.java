package cs5004.animator.view;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import cs5004.animator.shape.IShape;

public class Canvas extends JFrame {
  ShapesPanel panel;
  JScrollPane scrollPane;

  // displaying the panel with the shapes
  public Canvas(double width, double height, List<IShape> model) {
    super("Easy Animator");
    setSize((int) width, (int) height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.panel = new ShapesPanel(model);
    this.panel.setPreferredSize(new Dimension((int)width * 2, (int) height * 2));
    scrollPane = new JScrollPane(panel);
    scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    this.setVisible(true);
    this.add(scrollPane);
    panel.setVisible(true);


    //parameters Jtext, JScrollPane.SCROLLBAR_AS_NEEDED wrap shape panel in scroll pane
    //scrollPane = new JScrollPane();
  }

  public void currentView(List<IShape> currentShapes) {
    // gets the shapes in correct position and color
    this.panel.setShapes(currentShapes);
    this.panel.repaint();
  }
}

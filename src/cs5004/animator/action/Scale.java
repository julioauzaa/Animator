package cs5004.animator.action;

import cs5004.animator.shape.IShape;
import cs5004.animator.shape.Shape;

/**
 * Action class for scaling the shapes. This action is of type Action.SCALE.
 */
public class Scale extends AbstractAction {

  /**
   * Constructor for Scale class.
   * @param name shape identifier.
   * @param currentShape shape to which scaling action is to be applied.
   * @param newWidth new width for the shape.
   * @param newHeight new height for the shape.
   * @param startTime start time of the Scale action.
   * @param endTime end time of the Scale action.
   */
  public Scale(String name, IShape currentShape, double newWidth, double newHeight,
               int startTime, int endTime) {
    super(name, currentShape, newWidth, newHeight, startTime, endTime);

    if (currentShape.getType() == Shape.SQUARE) {
      this.oldWidth = currentShape.getLength();
      this.oldHeight = currentShape.getLength();
      this.currentShape.setLength(newHeight);
    } else if (currentShape.getType() == Shape.CIRCLE) {
      this.oldWidth = currentShape.getRadius();
      this.oldHeight = currentShape.getRadius();
      this.currentShape.setRadius(newWidth);
    } else {
      this.oldWidth = currentShape.getWidth();
      this.oldHeight = currentShape.getHeight();
      this.currentShape.setWidth(newWidth);
      this.currentShape.setHeight(newHeight);
    }
    this.newWidth = newWidth;
    this.newHeight = newHeight;

    this.type = Action.SCALE;
  }

  @Override
  public IShape getShapeAtTick(double tick, IShape shape) {
    if (tick < 0) {
      throw new IllegalArgumentException("Ticks cannot be negative");
    }
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    IShape copy = shape.copy();
    if (shape.isVisible()) {
      copy.setVisible(shape.isVisible());
    }

    if (tick <= this.time.getStartTime()) {
      return copy;
    } else if (tick > this.time.getEndTime()) {
      copy.setWidth(newWidth);
      copy.setHeight(newHeight);
      return copy;
    }

    double percent = (tick - this.time.getStartTime())
                     / (this.time.getEndTime() - this.time.getStartTime());

    double currentWidth = (percent * (newWidth - oldWidth) + oldWidth);
    double currentHeight = (percent * (newHeight - oldHeight) + oldHeight);
    copy.setWidth(currentWidth);
    copy.setHeight(currentHeight);
    return copy;
  }

  @Override
  public String toString() {
    if (currentShape.getType() == Shape.ELLIPSE) {
      return name + " scales from X radius:, " + oldWidth + "Y radius: "
             + oldHeight + " to X radius, " + newWidth + "Y radius "
             + newHeight + "from time t="
             + this.time.getStartTime() + " to t=" + this.time.getEndTime();
    } else if (currentShape.getType() == Shape.CIRCLE) {
      return name + " scales from Radius: " + oldWidth + " to Radius: "
             + newWidth + ", Y radius " + newHeight + "from time t="
             + this.time.getStartTime() + " to t=" + this.time.getEndTime();
    } else if (currentShape.getType() == Shape.SQUARE) {
      return name + " scales from Length: " + oldWidth + " to Length: "
             + newWidth + "from time t=" + this.time.getStartTime()
             + " to t=" + this.time.getEndTime();
    } else {
      return name + " scales from Width: " + oldWidth + ", Height: "
             + oldHeight + " to Width: " + newWidth + ", Height: "
             + newHeight + " from time t="
             + this.time.getStartTime() + " to t=" + this.time.getEndTime();
    }
  }

}

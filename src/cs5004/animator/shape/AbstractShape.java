package cs5004.animator.shape;

import cs5004.animator.tools.Position;
import cs5004.animator.tools.RGB;
import cs5004.animator.tools.Time;

/**
 * This class represents a shape. It implements the interface IShape. A shape has a name, a type, a
 * position, a show time, and a color. If the shape is a square, it has a length. If
 * the shape is a circle, it has a radius. Else, the shape has a width and a height.
 */
public abstract class AbstractShape implements IShape {
  protected String name;
  protected Shape type;
  protected Position position;
  protected Time showTime;
  protected RGB color;
  protected double length;
  protected double radius;
  protected double width;
  protected double height;
  protected boolean visible;

  /**
   * Constructs an IShape object. If the shape to construct is a circle or a square, the width and
   * height are equal. They represent the radius and the length respectively. The type field is
   * initialized in the concrete class of the shape.
   *
   * @param name      the name of the shape.
   * @param color     the color of the shape.
   * @param width     the width of the shape.
   * @param height    the height of the shape.
   * @param x         the X position of the shape.
   * @param y         the Y position of the shape.
   * @param startTime the start time of existence of the shape.
   * @param endTime   the end time of existence of the shape.
   * @throws IllegalArgumentException if the name is null or empty. if the color is null.
   */
  public AbstractShape(String name, RGB color, double width, double height,
                       double x, double y, int startTime, int endTime) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid name");
    } else if (color == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }

    if (width <= 0) {
      throw new IllegalArgumentException("Width cannot be less than or equal to zero");
    } else if (height <= 0) {
      throw new IllegalArgumentException("Height cannot be less than or equal to zero");
    }

    this.name = name;
    this.color = color;
    this.position = new Position(x, y);
    this.showTime = new Time(startTime, endTime);
    this.visible = false;
    this.width = width;
    this.height = height;
  }

  /**
   * Constructor of shapes for the builder.
   *
   * @param name of the shape.
   */

  public AbstractShape(String name) {
    this.name = name;

    this.position = null;
    this.showTime = null;
    this.color = null;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Shape getType() {
    return this.type;
  }

  @Override
  public boolean isVisible() {
    return visible;
  }

  @Override
  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  @Override
  public Time getShowTime() {
    return this.showTime;
  }

  @Override
  public void setShowTime(int startTime, int endTime) {
    this.showTime = new Time(startTime, endTime);
  }

  @Override
  public Position getPosition() {
    return this.position;
  }

  @Override
  public void setPosition(double newX, double newY) {
    this.position = new Position(newX, newY);
  }

  @Override
  public RGB getColor() {
    return this.color;
  }

  @Override
  public void setColor(RGB newColor) {
    if (newColor == null) {
      throw new IllegalArgumentException("Color cannot be null");
    }
    this.color = newColor;
  }

  @Override
  public double getWidth() {
    if (this.type != Shape.CIRCLE) {
      return this.width;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public double getHeight() {
    if (this.type != Shape.CIRCLE) {
      return this.height;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public void setWidth(double newWidth) {
    if (newWidth <= 0) {
      throw new IllegalArgumentException("New width cannot be negative");
    }

    if (this.type != Shape.CIRCLE) {
      this.width = newWidth;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public void setHeight(double newHeight) {
    if (newHeight <= 0) {
      throw new IllegalArgumentException("New height cannot be negative");
    }

    if (this.type != Shape.CIRCLE) {
      this.height = newHeight;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public double getRadius() {
    if (this.type == Shape.CIRCLE) {
      return this.radius;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public void setRadius(double newRadius) {
    if (newRadius <= 0) {
      throw new IllegalArgumentException("New radius cannot be negative");
    }

    if (this.type == Shape.CIRCLE) {
      this.radius = newRadius;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public double getLength() {
    if (this.type == Shape.SQUARE) {
      return this.length;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public void setLength(double newLength) {
    if (newLength <= 0) {
      throw new IllegalArgumentException("New length cannot be negative");
    }

    if (this.type == Shape.SQUARE) {
      this.length = newLength;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public String toString() {
    return "Name: " + this.name + "\n"
           + "Type: " + this.type.toString() + "\n"
           + "Min corner: " + this.position.toString()
           + String.format(", Width: %.1f, Height: %.1f", this.width, this.height)
           + ", Color: " + this.color.toString() + "\n"
           + "Appears at t=" + this.showTime.getStartTime() + "\n"
           + "Disappears at t=" + this.showTime.getEndTime();
  }

}
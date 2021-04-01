package cs5004.animator;

/**
 * This class represents a shape.
 * It implements the interface IShape.
 * A shape has a name, a type, a position, a total time of existence, and a color.
 * If the shape is a square, it has a length.
 * If the shape is a circle, it has a radius.
 * Else, the shape has a width and a height.
 */
public abstract class AbstractShape implements IShape {
  protected String name;
  protected Shape type;
  protected Position position;
  protected Time totalTime;
  protected RGB color;
  protected double length;
  protected double radius;
  protected double width;
  protected double height;

  /**
   * Constructs an IShape object.
   * If the shape to construct is a circle, the width represents the radius.
   * If the shape to construct is a square, the width represents the length.
   * The type field is initialized in the concrete class of the shape.
   * @param name the name of the shape.
   * @param color the color of the shape.
   * @param width the width of the shape.
   * @param height the height of the shape.
   * @param x the X position of the shape.
   * @param y the Y position of the shape.
   * @param startTime the start time of existence of the shape.
   * @param endTime the end time of existence of the shape.
   */
  public AbstractShape(String name, RGB color, double width, double height,
                       double x, double y, int startTime, int endTime) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    } else if (name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }

    if (width <= 0) {
      throw new IllegalArgumentException("Invalid width");
    } else if (height <= 0) {
      throw new IllegalArgumentException("Invalid height");
    }

    this.name = name;
    this.color = color;
    this.position = new Position(x, y);
    this.totalTime = new Time(startTime, endTime);

    this.width = width;
    this.height = height;
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
  public Time getTotalTime() {
    return this.totalTime;
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
  public RGB getColor () {
    return this.color;
  }

  @Override
  public void setColor(RGB newColor) {
    this.color = newColor;
  }

  @Override
  public double getWidth() {
    if (this.type != Shape.CIRCLE && this.type != Shape.SQUARE) {
      return this.width;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public double getHeight() {
    if (this.type != Shape.CIRCLE && this.type != Shape.SQUARE) {
      return this.height;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public void setWidth (double newWidth) {
    if (newWidth <= 0) {
      throw new IllegalArgumentException("New width cannot be negative");
    }

    if (this.type != Shape.CIRCLE && this.type != Shape.SQUARE) {
      this.width = newWidth;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public void setHeight (double newHeight) {
    if (newHeight <= 0) {
      throw new IllegalArgumentException("New height cannot be negative");
    }

    if (this.type != Shape.CIRCLE && this.type != Shape.SQUARE) {
      this.height = newHeight;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public double getRadius () {
    if (this.type == Shape.CIRCLE) {
      return this.radius;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public void setRadius ( double newRadius){
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
  public double getLength () {
    if (this.type == Shape.SQUARE) {
      return this.length;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  @Override
  public void setLength ( double newLength){
    if (newLength <= 0) {
      throw new IllegalArgumentException("New length cannot be negative");
    }

    if (this.type == Shape.SQUARE) {
      this.length = newLength;
    } else {
      throw new IllegalArgumentException("Invalid shape");
    }
  }

  //Name: R
  //Type: rectangle
  //Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
  //Appears at t=1
  //Disappears at t=100
  public String toString() {
    return "Name: " + this.name + "\n" + "Type: " + this.type.toString() + "\n" + "Min corner: " + "("
        + this.getPosition().getX() + "," + this.getPosition().getY() + "), "
        + "Width: " + this.getWidth() + "," + "Height: " + this.getHeight()
        + "Color: " + "(" + this.color + ")\n"
        + "Appears at t=" + this.getTotalTime().getStartTime() + "\n"
        + "Disappears at t=" + this.getTotalTime().getEndTime() + "\n";
  }

}
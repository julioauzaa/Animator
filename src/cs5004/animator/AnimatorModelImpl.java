package cs5004.animator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cs5004.action.Action;
import cs5004.action.ChangeColor;
import cs5004.action.IAction;
import cs5004.action.Move;
import cs5004.action.Scale;
import cs5004.shape.Circle;
import cs5004.shape.IShape;
import cs5004.shape.Oval;
import cs5004.utilities.RGB;
import cs5004.shape.Rectangle;
import cs5004.shape.Rhombus;
import cs5004.shape.Shape;
import cs5004.shape.Square;
import cs5004.shape.Triangle;

/**
 * This class represents the Model component of an animator.
 * The class implements the interface IAnimatorModel.
 * The class has a log of shapes, a log of actions, and a chronological order of actions.
 */
public class AnimatorModelImpl implements IAnimatorModel {
  private HashMap<String, IShape> logOfShapes;
  private HashMap<String, List<IAction>> logOfActions;
  // chronological order of actions for toString method
  private List<String> chronologicalOrderOfActions;

  /**
   * Constructs an Animator model object.
   */
  public AnimatorModelImpl() {
    this.logOfShapes = new HashMap<>();
    this.logOfActions = new HashMap<>();
    this.chronologicalOrderOfActions = new LinkedList<>();
  }

  @Override
  public void createShape(String name, Shape shape, RGB color, double width, double height,
                          double x, double y, int startTime, int endTime) {

    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid name");
    } else if (shape == null) {
      throw new IllegalArgumentException("Invalid shape");
    } else if (color == null) {
      throw new IllegalArgumentException("Invalid color");
    }

    for (Map.Entry<String, IShape> entry : logOfShapes.entrySet()) {
      if (entry.getValue().getName().equals(name)) {
        throw new IllegalArgumentException("Name already exists");
      }
    }

    if (shape == Shape.CIRCLE) {
      logOfShapes.put(name, new Circle(name, color, width, width, x, y, startTime, endTime));
    } else if (shape == Shape.SQUARE) {
      logOfShapes.put(name, new Square(name, color, width, width, x, y, startTime, endTime));
    } else if (shape == Shape.RECTANGLE) {
      logOfShapes.put(name, new Rectangle(name, color, width, height, x, y, startTime, endTime));
    } else if (shape == Shape.TRIANGLE) {
      logOfShapes.put(name, new Triangle(name, color, width, height, x, y, startTime, endTime));
    } else if (shape == Shape.RHOMBUS) {
      logOfShapes.put(name, new Rhombus(name, color, width, height, x, y, startTime, endTime));
    } else if (shape == Shape.OVAL) {
      logOfShapes.put(name, new Oval(name, color, width, height, x, y, startTime, endTime));
    }
  }

  @Override
  public void move(String name, double newX, double newY, int startTime, int endTime) {
    IShape currentShape = getCurrentShape(name);

    checkRange(name, startTime, endTime);

    if (!checkOverlap(name, Action.MOVE, startTime, endTime)) {
      throw new IllegalArgumentException("Move overlap");
    }

    IAction newMove = new Move(name, currentShape, newX, newY, startTime, endTime);
    addActionToShape(name, newMove);
    chronologicalOrderOfActions.add(newMove.toString());
  }

  @Override
  public void changeColor(String name, RGB newColor, int startTime, int endTime) {
    IShape currentShape = getCurrentShape(name);

    if (newColor == null) {
      throw new IllegalArgumentException("Invalid color");
    }

    checkRange(name, startTime, endTime);

    if (!checkOverlap(name, Action.CHANGECOLOR, startTime, endTime)) {
      throw new IllegalArgumentException("Change color overlap");
    }

    IAction newChangeColor = new ChangeColor(name, currentShape, newColor, startTime, endTime);
    addActionToShape(name, newChangeColor);
    chronologicalOrderOfActions.add(newChangeColor.toString());
  }

  @Override
  public void scale(String name, double newWidth, double newHeight, int startTime, int endTime) {
    IShape currentShape = getCurrentShape(name);

    checkRange(name, startTime, endTime);

    if (!checkOverlap(name, Action.SCALE, startTime, endTime)) {
      throw new IllegalArgumentException("Scale overlap");
    }

    IAction newScale = new Scale(name, currentShape, newWidth, newHeight, startTime, endTime);
    addActionToShape(name, newScale);
    chronologicalOrderOfActions.add(newScale.toString());
  }

  @Override
  public List<IShape> getShapesAtTicks(int tick) {
    if (tick < 1) {
      throw new IllegalArgumentException("Tick must be greater than 0.");
    }
    if (logOfActions.size() == 0 || tick > logOfActions.size()) {
      throw new IllegalArgumentException("Tick not found.");
    }
    List<IShape> frameOfShapes = new LinkedList<>();
    for (Map.Entry<String, IShape> objects : logOfShapes.entrySet()) {
      IShape accumulatorShape = objects.getValue().copy();

      if (logOfActions.size() > 0 && logOfActions.get(objects.getKey()) != null) {
        for (IAction actions : logOfActions.get(objects.getKey())) {
          accumulatorShape = actions.getShapeAtTick(tick, accumulatorShape);
        }
      }
      frameOfShapes.add(accumulatorShape);
    }
    return frameOfShapes;
  }

  // NEEDS TO MODIFY
  // adds any action
  public void addAction(String name, IAction action) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    } else if (name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be empty");
    } else if (action == null) {
      throw new IllegalArgumentException("Actions cannot be null");
    }
    addActionToShape(name, action);
    chronologicalOrderOfActions.add(action.toString());
  }

  @Override
  public String toString() {
    StringBuilder accString = new StringBuilder();
    accString.append("Shapes:\n");

    for (Map.Entry shape : logOfShapes.entrySet()) {
      accString.append(shape.getValue().toString());
      accString.append("\n\n");
    }

//    for (String action : chronologicalOrderOfActions) {
//      accString.append("Shape " + action);
//      accString.append("\n");
//    }

    for (int i = 0; i < chronologicalOrderOfActions.size(); i++) {
      if (i != chronologicalOrderOfActions.size() - 1) {
        accString.append("Shape " +chronologicalOrderOfActions.get(i));
        accString.append("\n");
      }
      else{
        accString.append("Shape " +chronologicalOrderOfActions.get(i));
      }
    }
    return accString.toString();
  }

  /**
   * Private method that adds an action to the log.
   * @param name name of the shape.
   * @param action action to add to the log.
   */
  private void addActionToShape(String name, IAction action) {
    if (logOfActions.get(name) == null) {
      logOfActions.put(name, new LinkedList<>());
    }
    logOfActions.get(name).add(action);
  }

  /**
   * Private method that returns a shape from the log.
   * @param name the name of the shape.
   * @return the shape from the log.
   * @throws IllegalArgumentException if name is null or empty.
   *                                  if the shape does not exist in the log.
   */
  private IShape getCurrentShape(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid name");
    }

    for (Map.Entry<String, IShape> objects : logOfShapes.entrySet()) {
      if (objects.getKey().equals(name)) {
        IShape accumulatorShape = objects.getValue().copy();

        if (logOfActions.size() > 0 && logOfActions.get(objects.getKey()) != null) {
          for (IAction actions : logOfActions.get(objects.getKey())) {
            accumulatorShape = actions.getCurrentShape();
          }
        }
        return accumulatorShape;
      }
    }

    throw new IllegalArgumentException("Shape does not exist");
  }

  /**
   * Private method to determine if an action overlaps with another of the same type.
   * @param name name of the Shape.
   * @param type type of the Action.
   * @param startTime the start time of the new action being tested.
   * @param endTime the end time of the new action being tested.
   * @return true if there is no overlap; false otherwise.
   */
  private boolean checkOverlap(String name, Action type, int startTime, int endTime) {
    for (Map.Entry<String, List<IAction>> entry : logOfActions.entrySet()) {
      if (entry.getKey().equals(name)) {
        List<IAction> actions = entry.getValue();
        for (IAction action : actions) {
          if (action.getType() == type) {
            if (startTime > action.getTime().getStartTime() &&
                    startTime < action.getTime().getEndTime() ||
                    endTime >= action.getTime().getStartTime() &&
                            endTime <= action.getTime().getEndTime()) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  /**
   * Private method to determine if an action is within the range of execution of a shape.
   * @param name name of the Shape.
   * @param startTime the start time of the new action being tested.
   * @param endTime the end time of the new action being tested.
   * @throws IllegalArgumentException if the action is out of range.
   */
  private void checkRange(String name, int startTime, int endTime) {
    for (Map.Entry<String, IShape> entry : logOfShapes.entrySet()) {
      if (entry.getKey().equals(name)) {
        IShape shape = entry.getValue();
        if (startTime < shape.getTotalTime().getStartTime() ||
                endTime > shape.getTotalTime().getEndTime()) {
          throw new IllegalArgumentException("Action is out of range");
        }
      }
    }
  }


}

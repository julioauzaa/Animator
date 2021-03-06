package cs5004.animator.tools;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

import javax.swing.JOptionPane;

import cs5004.animator.view.IAnimatorView;
import cs5004.animator.view.PlayBack;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.VisualView;

/**
 * This class contains static methods that help the animator implementation.
 */
public class Helpers {

  /**
   * Creates a file.
   * @param name name of the file.
   * @param format format of the file.
   * @param content content of the file.
   * @return the file name.
   * @throws IllegalArgumentException if the name, format, or content are invalid.
   */
  public static String createFile(String name, String format, String content) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Invalid name");
    } else if (format == null || format.isBlank()) {
      throw new IllegalArgumentException("Invalid format");
    } else if (content == null || content.isBlank()) {
      throw new IllegalArgumentException("Invalid content");
    }

    String fileName = name + "." + format;

    try {
      Writer newFile = new FileWriter(fileName);
      newFile.write(content);
      newFile.close();
    } catch (IOException e) {
      showMessage("An error occurred while creating the file", 2);
      System.exit(0);
    }

    return fileName;
  }

  /**
   * Parses command-line arguments.
   * @param args the command-line arguments.
   * @return a String array of 4 elements with the commands passed.
   *         commands[0] = input file.
   *         commands[1] = view type.
   *         commands[2] = output file.
   *         commands[3] = speed.
   * @throws NullPointerException if args is null.
   */
  public static String[] parseCommands(String[] args) {
    Objects.requireNonNull(args, "Must have non-null args source");

    String[] commands = new String[4];
    for (int i = 0; i < 4; i++) {
      commands[i] = "";
    }

    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-in")) {
        try {
          commands[0] = args[i + 1];
        } catch (IndexOutOfBoundsException e) {
          commands[0] = "";
        }
      }

      if (args[i].equals("-view")) {
        try {
          commands[1] = args[i + 1];
        } catch (IndexOutOfBoundsException e) {
          commands[1] = "";
        }
      }

      if (args[i].equals("-out")) {
        try {
          commands[2] = args[i + 1];
        } catch (IndexOutOfBoundsException e) {
          commands[2] = "";
        }
      }

      if (args[i].equals("-speed")) {
        try {
          commands[3] = args[i + 1];
        } catch (IndexOutOfBoundsException e) {
          commands[3] = "";
        }
      }
    }

    if (commands[0].equals("") && commands[1].equals("")) {
      showMessage("Input file and view type are mandatory", 2);
      System.exit(0);
    } else if (commands[0].equals("")) {
      showMessage("Input file is mandatory", 2);
      System.exit(0);
    } else if (commands[1].equals("")) {
      showMessage("View type is mandatory", 2);
      System.exit(0);
    } else if (!commands[3].equals("")) {
      if (Integer.parseInt(commands[3]) <= 0) {
        showMessage("Speed cannot be less than or equal to zero\nSet to default",
                2);
        commands[3] = "";
      }
    }

    return commands;
  }

  /**
   * Shows a pop-up message.
   * @param message the message.
   * @param iconNumber 1 for plain messages.
   *                   2 for error messages.
   * @throws IllegalArgumentException if the message or the icon number are invalid.
   */
  public static void showMessage(String message, int iconNumber) {
    if (message == null || message.isBlank()) {
      throw new IllegalArgumentException("Invalid message");
    }

    switch (iconNumber) {
      case 1:
        JOptionPane.showMessageDialog(null, message, "Success",
                JOptionPane.PLAIN_MESSAGE);
        break;
      case 2:
        JOptionPane.showMessageDialog(null, message, "Error",
                JOptionPane.ERROR_MESSAGE);
        break;
      default:
        throw new IllegalArgumentException("Invalid icon number");
    }
  }

  /**
   * Generates a Readable source of characters.
   * @param inputFile the name of the file.
   * @return a Readable source of characters.
   * @throws NullPointerException if input file is invalid.
   */
  public static Readable checkInputFile(String inputFile) {
    Objects.requireNonNull(inputFile, "Must have non-null input file");

    Readable in = null;

    try {
      in = new FileReader(inputFile);
    } catch (FileNotFoundException e) {
      showMessage("Input file not found", 2);
      System.exit(0);
    }

    return in;
  }

  /**
   * Gets the speed of the animation.
   * The default speed is 1.
   * @param givenSpeed the given speed.
   * @return the speed of the animation.
   * @throws NullPointerException if speed is invalid.
   */
  public static int getSpeed(String givenSpeed) {
    Objects.requireNonNull(givenSpeed, "Must have non-null speed");

    int speed = 1;

    if (!givenSpeed.equals("")) {
      speed = Integer.parseInt(givenSpeed);
      if (speed <= 0) {
        speed = 1;
      }
    }

    return speed;
  }

  /**
   * Gets an instance of an IAnimatorView from a view type.
   * @param viewType the view type.
   * @return an instance of an IAnimatorView.
   * @throws NullPointerException if the view is invalid.
   */
  public static IAnimatorView getView(String viewType) {
    Objects.requireNonNull(viewType, "Must have non-null view type");

    IAnimatorView view = null;

    switch (viewType) {
      case "text":
        view = new TextView();
        break;
      case "svg":
        view = new SVGView();
        break;
      case "visual":
        view = new VisualView();
        break;
      case "playback":
        view = new PlayBack();
        break;
      default:
        // no action is intended when no other case applies.
    }

    return view;
  }

}

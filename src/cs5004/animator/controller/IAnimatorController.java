package cs5004.animator.controller;

/**
 * This interface represents all the operations that an animator controller must support.
 */
public interface IAnimatorController {

  /**
   * Getter for the TextView. Returns a String representation of the animation.
   *
   * @return String representation of the animation.
   */
  String getTextView();

  /**
   * Getter for the SVG view.
   */
  String getSVGView();

  /**
   * Getter for the VisualView.
   */
  void getVisualView();

  /**
   *  Generates a visual representation of the animation.
   */
  void getPlayBackView();

  /**
   * Generates the view.
   */
  void generateView();

  /**
   * Initiates mouse handlers for Mouse and for Keyboard.
   * Sets command listeners for Mouse and Keyboard in PlayBackView.
   * Makes the PlayBack frame visible.
   */
  void go();



}

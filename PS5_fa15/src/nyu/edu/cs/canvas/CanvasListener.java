package nyu.edu.cs.canvas;

import java.awt.Color;

/**
 * This is the listener interface for the Canvas application. The methods in
 * this interface outlines the basic requirements of the view(or any other class
 * implementing this interface) class.
 * 
 * @author Suruchi
 *
 */
public interface CanvasListener {

  /**
   * This method is used to draw lines on the canvas
   * 
   * @param x1
   *          The x coordinate of the start point
   * @param y1
   *          The y coordinate of the start point
   * @param x2
   *          The x coordinate of the end point
   * @param y2
   *          The y coordinate of the end point
   */
  void draw(int x1, int y1, int x2, int y2);

  /**
   * The method is used to restore the canvas to the default state
   */
  void restoreDefaults();

  /**
   * This method is used to update the color of the line that is drawn on the
   * canvas
   * 
   * @param c
   *          The color user has selected for the line.
   */
  void updateLineColor(Color c);
}

package nyu.edu.cs.canvas;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This Singleton class acts as the model for the Canvas application
 * 
 * @author Suruchi
 *
 */
public class CanvasModel {

  /**
   * This method is used to return an object of the CanvasModel.
   * 
   * @return object of CanvasModel
   */
  public static CanvasModel getInstance() {
    if (model == null) {
      model = new CanvasModel();
    }
    return model;

  }

  private static CanvasModel model = null;

  private List<CanvasListener> listeners = new ArrayList<CanvasListener>();

  /**
   * The constructor is private since this is a Singleton class
   */
  private CanvasModel() {

  }

  /**
   * This method is used to add listeners to the private list of CanvasListeners
   * 
   * @param listener
   *          The CanvasListener to be added to the list
   */
  public void addListener(CanvasListener listener) {
    if (listener instanceof CanvasListener) {
      listeners.add(listener);
    }
  }

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
  public void drawLine(int x1, int y1, int x2, int y2) {
    for (CanvasListener listener : listeners) {
      listener.draw(x1, y1, x2, y2);
    }
  }

  /**
   * The method is used to restore the canvas to it's default setting.
   */
  public void restoreDefaults() {
    for (CanvasListener listener : listeners) {
      listener.restoreDefaults();
    }
  }

  /**
   * This method is used to update the color of the line that is drawn on the
   * canvas
   * 
   * @param color
   *          The color user has selected for the line.
   */
  public void updateLineColor(Color color) {
    for (CanvasListener listener : listeners) {
      listener.updateLineColor(color);
    }
  }
}

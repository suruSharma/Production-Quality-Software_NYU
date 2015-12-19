package nyu.edu.cs.canvas;

/**
 * This is the main class that launches the Canvas. The default color of the
 * lines drawn is black and the UI provides the user with options to change the
 * color to red, blue and green. The user can also clear the canvas by clicking on the clear button. 
 * Any changes made in one canvas will be reflected in any other open canvas. 
 * 
 * @author Suruchi
 *
 */
public class Canvas {

  public static void main(String[] args) {
    new Canvas().startCanvas();
  }

  /**
   * This method launches two canvases for the user to draw on. Any changes made
   * in one canvas will be reflected on the other canvas.
   */
  private void startCanvas() {
    CanvasModel model = CanvasModel.getInstance();
    new CanvasView(model);
    new CanvasView(model);
  }

}

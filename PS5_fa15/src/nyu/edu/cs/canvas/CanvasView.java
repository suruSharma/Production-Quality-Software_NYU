package nyu.edu.cs.canvas;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

/**
 * This class constructs the view for the Canvas application.
 * 
 * @author Suruchi
 *
 */
@SuppressWarnings("serial")
public class CanvasView extends JFrame implements CanvasListener {

  /**
   * This class is used to track the movements of the mouse over the canvas.
   * 
   * @author Suruchi
   *
   */
  private class MouseActionListener extends MouseMotionAdapter {

    @Override
    public void mouseDragged(MouseEvent e) {
      drawLine(new Point(e.getX(), e.getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
  }

  /**
   * This class is used to keep note of the mouse pressed and mouse released
   * actions.
   * 
   * @author Suruchi
   *
   */
  private class MouseActionsListener extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e) {
      startPoint = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      startPoint = null;
    }
  }

  private CanvasModel model;
  private JPanel mainPanel;
  private JPanel optionsPanel;
  private Point startPoint;
  private Canvas canvas;
  private Color lineColor;

  /**
   * Public constructor of the CanvasView.
   * 
   * @param model
   *          Object of the CanvasModel class.
   */
  public CanvasView(CanvasModel model) {
    this.model = model;
    model.addListener(this);
    init();
  }

  /**
   * This method is used to create the options panel that appears at the bottom
   * of the canvas frame.
   * 
   * @return object of <Component> class that represents the options panel.
   */
  private Component createOptionPanel() {
    optionsPanel = new JPanel(new FlowLayout());

    JButton clearButton = new JButton();
    clearButton.setPreferredSize(new Dimension(100, 50));
    clearButton.setText("Clear");
    clearButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        model.restoreDefaults();
      }
    });

    JButton black = new JButton();
    black.setPreferredSize(new Dimension(100, 50));
    black.setBackground(Color.BLACK);
    black.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        model.updateLineColor(Color.BLACK);
      }
    });

    JButton red = new JButton();
    red.setPreferredSize(new Dimension(100, 50));
    red.setBackground(Color.RED);
    red.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        model.updateLineColor(Color.RED);
      }
    });

    JButton blue = new JButton();
    blue.setPreferredSize(new Dimension(100, 50));
    blue.setBackground(Color.BLUE);
    blue.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        model.updateLineColor(Color.BLUE);
      }
    });

    JButton green = new JButton();
    green.setPreferredSize(new Dimension(100, 50));
    green.setBackground(Color.GREEN);
    green.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        model.updateLineColor(Color.GREEN);
      }
    });

    optionsPanel.add(clearButton);
    optionsPanel.add(black);
    optionsPanel.add(red);
    optionsPanel.add(blue);
    optionsPanel.add(green);

    return optionsPanel;
  }

  @Override
  public void draw(int x1, int y1, int x2, int y2) {
    Graphics graphics = canvas.getGraphics();

    graphics.setColor(lineColor);
    graphics.drawLine(x1, y1, x2, y2);
  }

  /**
   * This method calls the drawLine method in the model and draws a line on the
   * canvas
   * 
   * @param end
   *          The point the mouse was dragged to.
   */
  public void drawLine(Point end) {
    if (startPoint == null || startPoint == end) {
      return;
    }
    model.drawLine(startPoint.x, startPoint.y, end.x, end.y);
    startPoint = end;
  }

  /**
   * This method is used to initialize all the necessary class variables and
   * create the view for the canvas app with the necessary action listeners.
   */
  private void init() {
    setTitle("Canvas");
    setUndecorated(true);
    setSize(600, 600);
    setResizable(false);
    setDefaultLookAndFeelDecorated(false);
    getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel(new BorderLayout());
    canvas = new Canvas();
    lineColor = new Color(000);

    canvas.setBackground(Color.WHITE);
    canvas.addMouseListener(new MouseActionsListener());
    canvas.addMouseMotionListener(new MouseActionListener());

    mainPanel.add(canvas, BorderLayout.CENTER);
    mainPanel.add(createOptionPanel(), BorderLayout.SOUTH);

    getContentPane().add(mainPanel);
    
    setVisible(true);

  }

  @Override
  public void restoreDefaults() {
    canvas.setBackground(Color.WHITE);
    lineColor = Color.BLACK;
    canvas.repaint();
    startPoint = null;
  }

  @Override
  public void updateLineColor(Color c) {
    lineColor = c;
  }
}

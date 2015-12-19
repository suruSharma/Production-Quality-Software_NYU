package nyu.edu.cs.canvas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CanvasModelTest {

  CanvasModel model;

  @Before
  public void setup() {
    model = CanvasModel.getInstance();
  }

  @Test
  public final void testGetInstance() {
    CanvasModel testModel = CanvasModel.getInstance();
    assertTrue("Get instance method returns different instances", model.equals(testModel));
  }

}

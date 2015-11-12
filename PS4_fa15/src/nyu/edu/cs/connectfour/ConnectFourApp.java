package nyu.edu.cs.connectfour;

/**
 * The main class that launches the ConnectFour game. To play the game, the user
 * has to select a mode - Human vs Human(default) or Human vs Computer and then
 * click the Start button. If a mode is not selected, an error with be thrown.
 * Once the game starts, this start button will change into a New Game button
 * and the radio buttons to select the mode of the game will be disabled.
 * Clicking on the New Game button will start a new game. No details about the
 * previous game are stored in memory.
 * 
 * 
 * @author Suruchi
 *
 */
public class ConnectFourApp {

  /**
   * This method initializes the view object with the ConnectFourModel and
   * launches the game interface
   */
  private void startGame() {
    ConnectFourModel model = new ConnectFourModel();
    new ConnectFourView(model);
  }

  public static void main(String args[]) {
    new ConnectFourApp().startGame();
  }

}

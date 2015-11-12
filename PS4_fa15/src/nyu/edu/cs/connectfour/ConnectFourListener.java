package nyu.edu.cs.connectfour;

/**
 * This is a listener interface for the ConnectFour game. The methods outline
 * the basic requirements of the view class.
 * 
 * @author Suruchi
 *
 */
public interface ConnectFourListener {

  /**
   * This method is used to alert the player of any invalid moves.
   * 
   * @param message
   *          The message to be displayed in the alert box.
   */
  public void alert(String message);

  /**
   * This method is used to declare the winner of the game.
   * 
   * @param message
   *          The message to be displayed in the pop-up.
   */
  public void declareWinner(String message);

  /**
   * This method is used to end the current game once the total moves are over.
   */
  public void gameOver();

  /**
   * This method is used to enable components and start the game
   */
  public void gameStart();

  /**
   * This method is used to make the move(highlight selected box) on the board.
   * 
   * @param playerId
   *          The id of the player who made the last move.
   * @param r
   *          The row where the last move was made.
   * @param c
   *          The column where the last move was made.
   */
  public void makeMove(int playerId, int r, int c);

  /**
   * This method is used to restore the game and all related values to the
   * default(initial) state.
   */
  public void restoreDefaults();
}
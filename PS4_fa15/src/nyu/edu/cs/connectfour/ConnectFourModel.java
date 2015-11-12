package nyu.edu.cs.connectfour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import nyu.edu.cs.connectfour.util.Constants;

/**
 * This class contains the logic required to play the ConnectFour game
 * 
 * @author Suruchi
 *
 */
public class ConnectFourModel {
  private ConnectFourPlayer firstPlayer;
  private ConnectFourPlayer secondPlayer;
  private ConnectFourPlayer currentPlayer;
  private ConnectFourBoard board = new ConnectFourBoard();
  private List<ConnectFourListener> listeners = new ArrayList<ConnectFourListener>();

  /**
   * This method is used to add all available implementations of the
   * <ConnectFourListener> interface to the listeners list. This helps to
   * implement the required ObserverPattern design pattern.
   * 
   * @param listener
   *          Implementations of the <ConnectFourListener> interface.
   */
  public void addListener(ConnectFourListener listener) {
    if (listener instanceof ConnectFourListener) {
      listeners.add(listener);
    }
  }

  /**
   * This method is used to execute the alert method of all implementations of
   * the <ConnectFourListener> interface present in the listeners list.
   * 
   * @param message
   *          The <String> message that is to be displayed in the alert box on
   *          the GUI.
   */
  private void alertPlayer(String message) {
    for (ConnectFourListener listener : listeners) {
      listener.alert(message);
    }
  }

  /**
   * This method is used to execute the declareWinner method of all
   * implementations of the <ConnectFourListener> interface present in the
   * listeners list.
   * 
   * @param message
   *          The <String> that is displayed when a player wins.
   */
  private void declareWinner(String message) {
    for (ConnectFourListener listener : listeners) {
      listener.declareWinner(message);
    }
  }

  /**
   * This method is used to execute the gameOver method of all implementations
   * of the <ConnectFourListener> interface present in the listeners list.
   * 
   * @param message
   *          The <String> that is displayed when a player wins.
   */
  private void gameOver() {
    for (ConnectFourListener listener : listeners) {
      listener.gameOver();
    }
  }

  /**
   * This method is used to fetch the instance of the <ConnectFourBoard> class.
   * 
   * @return an object of <ConnectFourBoard>.
   */
  public ConnectFourBoard getBoard() {
    return board;
  }

  /**
   * 
   * This method is used to fetch an instance of <ConnectFourPlayer> that
   * represents the current player on the board.
   * 
   * @return an object of <ConnectFourPlayer> representing the current player on
   *         the board.
   */
  public ConnectFourPlayer getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * This method is used to fetch an instance of <ConnectFourPlayer> that
   * represents the first player.
   * 
   * @return an object of <ConnectFourPlayer> representing the first player.
   */
  public ConnectFourPlayer getFirstPlayer() {
    return firstPlayer;
  }

  /**
   * This method is used to get the list of available listeners.
   * 
   * @return a list of <ConnectFourListener>.
   */
  public List<ConnectFourListener> getListeners() {
    return Collections.unmodifiableList(listeners);
  }

  /**
   * This method is used to fetch an instance of <ConnectFourPlayer> that
   * represents the second player.
   * 
   * @return an object of <ConnectFourPlayer> representing the second player.
   */
  public ConnectFourPlayer getSecondPlayer() {
    return secondPlayer;
  }

  /**
   * This method updates the board(view and int[][]) and alerts the player if
   * the column that is selected is already full. It also updates the int[][]
   * game board and updates the current player to the first player and second
   * player alternately.
   * 
   * @param c
   *          The int value of the column in which the player/computer has
   *          chosen.
   */
  private void makeMove(int c) {
    int r = board.updateBoard(c, currentPlayer.getId());
    if (r == -1) {
      alertPlayer("This column is full. Please select some other column");
    } else {
      updateGameBoard(currentPlayer.getId(), r, c);
      board.setLastPlayer(currentPlayer.getId());
      currentPlayer.setMoves(currentPlayer.getMoves() + 1);
      board.setLastRow(r);
      board.setLastCol(c);
      if (currentPlayer.getMoves() >= Constants.BLOCKSTOWIN && board.findWinner()) {
        declareWinner(currentPlayer.getName() + " has won!");
      } else if (board.getTotalMoves() == 0) {
        gameOver();
      }
      if (currentPlayer.equals(firstPlayer)) {
        currentPlayer = secondPlayer;
      } else {
        currentPlayer = firstPlayer;
      }
    }
  }

  /**
   * This method is executed every time a player clicks on a column on the game
   * board. The makeMove method is called to make the actual move. If the next
   * player to make the move is the computer, this method calls the
   * playComputersMove method.
   * 
   * @param c
   *          The int value of the column in which the player has clicked.
   */
  public void play(int c) {
    makeMove(c);
    if (!currentPlayer.isHuman()) {
      playComputersMove();
    }
  }

  /**
   * This method selects the column for the computer and passes the calculated
   * column to the makeMove method. If it is the first move for the computer, a
   * column is randomly selected.
   */
  private void playComputersMove() {
    board.setLastPlayer(currentPlayer.getId());
    if (currentPlayer.getMoves() == 0) {
      Random rand = new Random();
      int randomNum = rand.nextInt(Constants.COLS);
      makeMove(randomNum);
    } else {
      int c = board.findBestColForComputer();
      makeMove(c);
    }

  }

  /**
   * This method is used to reset the game board and restore the view to the
   * default(initial) state.
   */
  public void resetGame() {
    board.resetBoard();
    restoreDefaults();
  }

  /**
   * This method is used to execute the restoreDefaults method of all
   * implementations of the <ConnectFourListener> interface present in the
   * listeners list.
   */
  private void restoreDefaults() {
    for (ConnectFourListener listener : listeners) {
      listener.restoreDefaults();
    }
  }

  /**
   * This method is used to set the <ConnectFourBoard> object.
   * 
   * @param board
   *          Instance of <ConnectFourBoard>
   */
  public void setBoard(ConnectFourBoard board) {
    this.board = board;
  }

  /**
   * This method is used to set the current player.
   * 
   * @param currentPlayer
   *          Instance of <ConnectFourPlayer>
   */
  public void setCurrentPlayer(ConnectFourPlayer currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  /**
   * This method is used to set the first player.
   * 
   * @param firstPlayer
   *          Instance of <ConnectFourPlayer>
   */
  public void setFirstPlayer(ConnectFourPlayer firstPlayer) {
    this.firstPlayer = firstPlayer;
  }

  /**
   * This method is used to set a list of <ConnectFourListener>.
   * 
   * @param listeners
   *          Instance of List<ConnectFourListener>
   */
  public void setListeners(List<ConnectFourListener> listeners) {
    this.listeners = listeners;
  }

  /**
   * This method is used set the second player
   * 
   * @param secondPlayer
   *          Instance of <ConnectFourPlayer>
   */
  public void setSecondPlayer(ConnectFourPlayer secondPlayer) {
    this.secondPlayer = secondPlayer;
  }

  /**
   * This method is used to start the game
   */
  public void startGame() {
    board.resetBoard();
    this.startGameEvent();
    currentPlayer = firstPlayer;
  }

  /**
   * This method is used to execute the startGameEvent method of all
   * implementations of the <ConnectFourListener> interface present in the
   * listeners list.
   */
  private void startGameEvent() {
    for (ConnectFourListener listener : listeners) {
      listener.gameStart();
    }
  }

  /**
   * This method is used to execute the updateGameBoard method of all
   * implementations of the <ConnectFourListener> interface present in the
   * listeners list.
   */
  private void updateGameBoard(int playerId, int row, int col) {
    for (ConnectFourListener listener : listeners) {
      listener.makeMove(playerId, row, col);
    }
  }
}

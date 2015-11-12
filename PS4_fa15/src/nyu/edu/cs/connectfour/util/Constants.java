package nyu.edu.cs.connectfour.util;

import java.awt.Color;

/**
 * This class holds all the variables that are used in the ConnectFour game. The
 * values of variables in this class can be modified to change the parameters of
 * the game such as the grid size, number of blocks required to win and other
 * such parameters.
 * 
 * @author Suruchi
 *
 */
public class Constants {

  //Count of rows and columns in the game grid
  public static final int ROWS = 6;
  public static final int COLS = 7;

  //Number of consecutive blocks required to win
  public static final int BLOCKSTOWIN = 4;

  //Colors that are used to denote players
  public static final Color COLOR_PLAYER1 = Color.BLUE;
  public static final Color COLOR_PLAYER2 = Color.RED;

  //Player Mode : MODE_HC : Human vs Human; MODE_HC : Human vs Computer
  public static final int MODE_HH = 1;
  public static final int MODE_HC = 2;

  //Values denoting button action
  public static final int BUTTON_ACTION_START = 1;
  public static final int BUTTON_ACTION_NEW = 2;

  //Constants denoting the playerId
  public static final int PLAYER1 = 1;
  public static final int PLAYER2 = 2;

}

package nyu.edu.cs.connectfour;

import static org.junit.Assert.*;
import nyu.edu.cs.connectfour.util.Constants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConnectFourModelTest {

  ConnectFourModel testModel;
  ConnectFourView testView;
  ConnectFourBoard testBoard;
  ConnectFourPlayer testHumanPlayer;
  ConnectFourPlayer testComputerPlayer;
  int[][] testBoardArr;
  int[] actualAvailableRows;

  @Before
  public void setUp() {
    testModel = new ConnectFourModel();
    testBoard = new ConnectFourBoard();
    testModel.setBoard(testBoard);
    testHumanPlayer = new ConnectFourPlayer("TestHumanPlayer", Constants.PLAYER1, true);
    testComputerPlayer = new ConnectFourPlayer("TestComputerPlayer", Constants.PLAYER2, false);
    testModel.setFirstPlayer(testHumanPlayer);
    testModel.setSecondPlayer(testComputerPlayer);
    actualAvailableRows = new int[Constants.COLS];
    testBoardArr = new int[Constants.ROWS][Constants.COLS];
    for (int r = 0; r < Constants.ROWS; r++) {
      for (int c = 0; c < Constants.COLS; c++) {
        testBoardArr[r][c] = -1;
      }
    }

  }

  @After
  public void cleanUp() {
    //Added this to destroy the GUI window that is created in the testAddListener test case
    testView = null;
  }

  @Test
  public void testAddListener() {
    testView = new ConnectFourView(testModel);
    assertTrue("Listener is not added correctly", testModel.getListeners().contains(testView));
  }

  @Test
  public void testPlay() {
    testModel.setCurrentPlayer(testHumanPlayer);
    testModel.play(3);
    assertEquals("Number of moves of player is updated correctly", 1, testHumanPlayer.getMoves());
    assertEquals("Integer board is not updated correctly", Constants.PLAYER1, testModel.getBoard()
        .getBoard()[0][3]);
  }

  @Test
  public void testResetGame() {
    testModel.resetGame();
    assertEquals("Total moves not reset correctly", testModel.getBoard().getTotalMoves(),
        Constants.ROWS * Constants.COLS);
    assertArrayEquals("Available rows array is not reset", actualAvailableRows, testModel
        .getBoard().getAvailableRow());
    int[][] actualBoard = testModel.getBoard().getBoard();
    assertArrayEquals("Row 0 of board is not reset correctly", testBoardArr[0], actualBoard[0]);
    assertArrayEquals("Row 1 of board is not reset correctly", testBoardArr[1], actualBoard[1]);
    assertArrayEquals("Row 2 of board is not reset correctly", testBoardArr[2], actualBoard[2]);
    assertArrayEquals("Row 3 of board is not reset correctly", testBoardArr[3], actualBoard[3]);
    assertArrayEquals("Row 4 of board is not reset correctly", testBoardArr[4], actualBoard[4]);
    assertArrayEquals("Row 5 of board is not reset correctly", testBoardArr[5], actualBoard[5]);
  }

  @Test
  public void testStartGame() {
    testModel.startGame();

    assertEquals("Current player is not set correctly", testModel.getFirstPlayer(), testModel
        .getCurrentPlayer());
    assertEquals("Total moves not reset correctly", testModel.getBoard().getTotalMoves(),
        Constants.ROWS * Constants.COLS);
    assertArrayEquals("Available rows array is not reset", actualAvailableRows, testModel
        .getBoard().getAvailableRow());
  }

}

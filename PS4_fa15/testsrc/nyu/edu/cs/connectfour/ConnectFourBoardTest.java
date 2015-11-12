package nyu.edu.cs.connectfour;

import static org.junit.Assert.*;
import nyu.edu.cs.connectfour.util.Constants;

import org.junit.Before;
import org.junit.Test;

public class ConnectFourBoardTest {

  int[][] testBoardArr;
  int[] testAvailableRowArr;
  ConnectFourBoard testBoard;

  @Before
  public void setup() {
    testBoardArr = new int[Constants.ROWS][Constants.COLS];
    testAvailableRowArr = new int[Constants.COLS];
    for (int r = 0; r < Constants.ROWS; r++) {
      for (int c = 0; c < Constants.COLS; c++) {
        testBoardArr[r][c] = -1;
      }
    }
    testAvailableRowArr = new int[Constants.COLS];
    testBoard = new ConnectFourBoard();
    testBoard.setLastPlayer(Constants.PLAYER2);
  }

  @Test
  public void testFindBestColForComputer_Vertical() {
    testAvailableRowArr[3] = 3;
    testBoard.setAvailableRow(testAvailableRowArr.clone());
    int expectedResult = 3;
    testBoardArr[0][expectedResult] = Constants.PLAYER2;
    testBoardArr[1][expectedResult] = Constants.PLAYER2;
    testBoardArr[2][expectedResult] = Constants.PLAYER2;
    testBoard.setBoard(testBoardArr.clone());
    assertEquals("Invalid column is returned", expectedResult, testBoard.findBestColForComputer());
  }

  @Test
  public void testFindBestColForComputer_RightDiagonal() {
    testAvailableRowArr[0] = 1;
    testAvailableRowArr[1] = 2;
    testAvailableRowArr[2] = 3;
    testAvailableRowArr[3] = 3;
    testBoard.setAvailableRow(testAvailableRowArr.clone());
    testBoardArr[0][0] = Constants.PLAYER2;
    testBoardArr[1][1] = Constants.PLAYER2;
    testBoardArr[2][2] = Constants.PLAYER2;
    testBoard.setBoard(testBoardArr.clone());
    assertEquals("Invalid column is returned", 3, testBoard.findBestColForComputer());
  }

  @Test
  public void testFindBestColForComputer_LeftDiagonal() {
    testAvailableRowArr[5] = 1;
    testAvailableRowArr[4] = 2;
    testAvailableRowArr[3] = 3;
    testAvailableRowArr[2] = 3;
    testBoard.setAvailableRow(testAvailableRowArr.clone());
    testBoardArr[0][5] = Constants.PLAYER2;
    testBoardArr[1][4] = Constants.PLAYER2;
    testBoardArr[2][3] = Constants.PLAYER2;
    testBoard.setBoard(testBoardArr.clone());
    assertEquals("Invalid column is returned", 2, testBoard.findBestColForComputer());
  }

  @Test
  public void testFindBestColForComputer_Horizontal() {
    testAvailableRowArr[0] = 1;
    testAvailableRowArr[1] = 1;
    testAvailableRowArr[2] = 1;
    testAvailableRowArr[3] = 0;
    testBoard.setAvailableRow(testAvailableRowArr.clone());
    testBoardArr[0][0] = Constants.PLAYER2;
    testBoardArr[0][1] = Constants.PLAYER2;
    testBoardArr[0][2] = Constants.PLAYER2;
    testBoard.setBoard(testBoardArr.clone());
    assertEquals("Invalid column is returned", 3, testBoard.findBestColForComputer());
  }

  @Test
  public void testFindWinner_Vertical() {
    int testCol = 2;
    testAvailableRowArr[3] = testCol;
    testBoard.setAvailableRow(testAvailableRowArr.clone());
    testBoardArr[0][testCol] = Constants.PLAYER2;
    testBoardArr[1][testCol] = Constants.PLAYER2;
    testBoardArr[2][testCol] = Constants.PLAYER2;
    testBoardArr[3][testCol] = Constants.PLAYER2;
    testBoard.setBoard(testBoardArr);
    testBoard.setLastCol(testCol);
    testBoard.setLastRow(3);
    assertTrue("Invalid value for winner", testBoard.findWinner());
  }

  @Test
  public void testFindWinner_Horizontal() {
    int testRow = 2;
    int testCol = 3;
    testAvailableRowArr[testCol] = testRow;
    testBoard.setAvailableRow(testAvailableRowArr.clone());
    testBoardArr[testRow][0] = Constants.PLAYER2;
    testBoardArr[testRow][1] = Constants.PLAYER2;
    testBoardArr[testRow][2] = Constants.PLAYER2;
    testBoardArr[testRow][3] = Constants.PLAYER2;
    testBoard.setBoard(testBoardArr);
    testBoard.setLastCol(testCol);
    testBoard.setLastRow(testRow);
    assertTrue("Invalid value for winner", testBoard.findWinner());
  }

  @Test
  public void testFindWinner_RightDiagonal() {
    testAvailableRowArr[0] = 0;
    testBoard.setAvailableRow(testAvailableRowArr.clone());
    testBoardArr[0][0] = Constants.PLAYER2;
    testBoardArr[1][1] = Constants.PLAYER2;
    testBoardArr[2][2] = Constants.PLAYER2;
    testBoardArr[3][3] = Constants.PLAYER2;
    testBoard.setBoard(testBoardArr);
    testBoard.setLastCol(0);
    testBoard.setLastRow(0);
    assertTrue("Invalid value for winner", testBoard.findWinner());
  }

  @Test
  public void testFindWinner_LeftDiagonal() {
    testAvailableRowArr[3] = 4;
    testBoard.setAvailableRow(testAvailableRowArr.clone());
    testBoardArr[3][3] = Constants.PLAYER2;
    testBoardArr[2][4] = Constants.PLAYER2;
    testBoardArr[4][2] = Constants.PLAYER2;
    testBoardArr[5][1] = Constants.PLAYER2;
    testBoard.setBoard(testBoardArr);
    testBoard.setLastCol(3);
    testBoard.setLastRow(3);
    assertTrue("Invalid value for winner", testBoard.findWinner());
  }

  @Test
  public void testFindWinner_None() {
    assertFalse("No winner should be found", testBoard.findWinner());
  }

  @Test
  public void testResetBoard() {
    testBoard.resetBoard();
    assertEquals("Total moves not reset correctly", testBoard.getTotalMoves(), Constants.ROWS
        * Constants.COLS);
    assertArrayEquals("Available rows array is not reset", testAvailableRowArr, testBoard
        .getAvailableRow());
    int[][] actualBoard = testBoard.getBoard();
    assertArrayEquals("Row 0 of board is not reset correctly", testBoardArr[0], actualBoard[0]);
    assertArrayEquals("Row 1 of board is not reset correctly", testBoardArr[1], actualBoard[1]);
    assertArrayEquals("Row 2 of board is not reset correctly", testBoardArr[2], actualBoard[2]);
    assertArrayEquals("Row 3 of board is not reset correctly", testBoardArr[3], actualBoard[3]);
    assertArrayEquals("Row 4 of board is not reset correctly", testBoardArr[4], actualBoard[4]);
    assertArrayEquals("Row 5 of board is not reset correctly", testBoardArr[5], actualBoard[5]);

  }

  @Test
  public void testUpdateBoard() {
    int testCol = 1;
    int testMoves = 14;
    int testRow = 2;
    testAvailableRowArr[testCol] = testRow;
    testBoard.setAvailableRow(testAvailableRowArr.clone());
    testBoard.setTotalMoves(testMoves);
    int returnedRow = testBoard.updateBoard(testCol, Constants.PLAYER1);
    assertEquals("Game board is not updated correctly", testBoard.getBoard()[testRow][testCol],
        Constants.PLAYER1);
    assertEquals("Total moves is not updated correctly", testBoard.getTotalMoves(), testMoves - 1);
    assertEquals("Available row is not updated correctly", testBoard.getAvailableRow()[testCol],
        testAvailableRowArr[testCol] + 1);
    assertEquals("Invalid row value is returned", returnedRow, testRow);
  }

  @Test
  public void testUpdateBoardWhenColumnIsFull() {
    int testCol = 4;
    int testRow = Constants.ROWS;
    testAvailableRowArr[testCol] = testRow;
    testBoard.setAvailableRow(testAvailableRowArr.clone());
    int returnedRow = testBoard.updateBoard(testCol, Constants.PLAYER1);
    assertEquals("Invalid row value is returned", returnedRow, -1);
  }
}

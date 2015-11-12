package nyu.edu.cs.connectfour;

import nyu.edu.cs.connectfour.util.Constants;

/**
 * This class is used to maintain an easily maintainable image of the actual
 * game board.
 * 
 * @author Suruchi
 *
 */
public class ConnectFourBoard {

  private int[][] board = new int[Constants.ROWS][Constants.COLS];
  private int[] availableRow = new int[Constants.COLS];
  private int lastPlayer;
  private int lastRow;
  private int lastCol;
  private int totalMoves = Constants.ROWS * Constants.COLS;

  /**
   * This method find the best column for the computer to play its move. The
   * column which leads to the longest length of the players blocks in any
   * direction is returned.
   * 
   * @return int value representing the best column for the computer to make its
   *         move.
   */
  public int findBestColForComputer() {
    int col = -1;
    int len = 0;

    for (int c = 0; c < Constants.COLS; c++) {
      for (int r = availableRow[c]; r < Constants.ROWS; r++) {
        int verticalLength = getVerticalLength(c, r);
        if (verticalLength > len) {
          col = c;
          len = verticalLength;
        }

        int horizontalLength = getHorizontalLength(c, r);
        if (horizontalLength > len) {
          col = c;
          len = horizontalLength;
        }

        int leftDiagonalLength = getLeftDiagonalLength(c, r);
        if (leftDiagonalLength > len) {
          col = c;
          len = leftDiagonalLength;
        }

        int rightDiagonalLength = getRightDiagonalLength(c, r);
        if (rightDiagonalLength > len) {
          col = c;
          len = rightDiagonalLength;
        }

      }
    }
    return col;
  }

  /**
   * This method is called after a player has made the number of moves required
   * to win(4 in this case).
   * 
   * @return true if the player has successfully connected four blocks else
   *         returns false.
   */
  public boolean findWinner() {
    if (getVerticalLength(lastCol, lastRow) >= Constants.BLOCKSTOWIN) {
      return true;
    }
    if (getHorizontalLength(lastCol, lastRow) >= Constants.BLOCKSTOWIN) {
      return true;
    }
    if (getLeftDiagonalLength(lastCol, lastRow) >= Constants.BLOCKSTOWIN) {
      return true;
    }
    if (getRightDiagonalLength(lastCol, lastRow) >= Constants.BLOCKSTOWIN) {
      return true;
    }
    return false;
  }

  /**
   * Gets the array that represents the next usable row in a column. The array
   * indices represent the column number.
   * 
   * @return int[] of available rows.
   */
  public int[] getAvailableRow() {
    return availableRow.clone();
  }

  /**
   * This method returns a 2D array that represents the board. Moves made by the
   * player are recorded in this array and it used for determining the winner
   * and predicting the next move when playing with a computer.
   * 
   * @return int[][] array representation of game board.
   */
  public int[][] getBoard() {
    return board.clone();
  }

  /**
   * This method is used to check if the last move by a player has connected
   * four diagonal blocks horizontally.
   * 
   * @return The maximum horizontal length that matches the current player.
   */
  private int getHorizontalLength(int col, int row) {
    int len = 0;

    int c = col;
    int r = row;

    //move to the right (the column is included while going to the right)
    while (c < Constants.COLS) {
      if (board[r][c] != lastPlayer) {
        break;
      }
      c++;
      len++;
    }

    c = col - 1;

    //move to the left (the column is excluded while going to the left)
    while (c > -1) {
      if (board[r][c] != lastPlayer) {
        break;
      }
      c--;
      len++;
    }
    return len;
  }

  /**
   * Gets the column number where the last move was made, irrespective of the
   * player.
   * 
   * @return int value of the last column value.
   */
  public int getLastCol() {
    return lastCol;
  }

  /**
   * Gets the ID of the player who made the last move
   * 
   * @return int ID of the last player.
   */
  public int getLastPlayer() {
    return lastPlayer;
  }

  /**
   * Gets the row number where the last move was made, irrespective of the
   * player.
   * 
   * @return int value of the last row value.
   */
  public int getLastRow() {
    return lastRow;
  }

  /**
   * This method is used to check if the last move by a player has connected
   * four diagonal blocks diagonally along the left diagonal(\).
   * 
   * @return The maximum diagonal length that matches the current player.
   */
  private int getLeftDiagonalLength(int col, int row) {
    int len = 0;

    int c = col;
    int r = row;

    //move upwards along the left diagonal (current row and column are included)
    while (r < Constants.ROWS && c > -1) {
      if (board[r][c] != lastPlayer) {
        break;
      }
      r++;
      c--;
      len++;
    }

    c = col + 1;
    r = row - 1;

    //move downwards along the left diagonal (current row and column are not included)
    while (r > -1 && c < Constants.COLS) {
      if (board[r][c] != lastPlayer) {
        break;
      }
      r--;
      c++;
      len++;
    }

    return len;
  }

  /**
   * This method is used to check if the last move by a player has connected
   * four diagonal blocks diagonally along the right diagonal(/).
   * 
   * @return The maximum diagonal length that matches the current player.
   */
  private int getRightDiagonalLength(int col, int row) {
    int len = 0;

    int c = col;
    int r = row;

    //move upwards along the diagonal (current row and column are included)
    while (r < Constants.ROWS && c < Constants.COLS) {
      if (board[r][c] != lastPlayer) {
        break;
      }
      r++;
      c++;
      len++;
    }

    //move downwards along the right diagonal(current row and column are excluded)
    c = col - 1;
    r = row - 1;

    while (r > -1 && c > -1) {
      if (board[r][c] != lastPlayer) {
        break;
      }
      r--;
      c--;
      len++;
    }

    return len;
  }

  /**
   * Getter for the totalMoves variable.
   * 
   * @return
   */
  public int getTotalMoves() {
    return totalMoves;
  }

  /**
   * This method is used to check if the last move by a player has connected
   * four diagonal blocks vertically.
   * 
   * @return The maximum vertical length that matches the current player.
   */
  private int getVerticalLength(int col, int row) {
    int len = 0;

    int c = col;
    int r = row;

    //move upwards from this point(the row is included while going up)
    while (r < Constants.ROWS) {
      if (board[r][c] != lastPlayer) {
        break;
      }
      len++;
      r++;
    }

    r = row - 1;

    //move downwards from this point ( the row is excluded while going down)
    while (r > -1) {
      if (board[r][c] != lastPlayer) {
        break;
      }

      len++;
      r--;
    }

    return len;
  }

  /**
   * This method resets the int[][] representing the board to the default value
   * of -1, the totalMoves to Constants.ROWS * Constants.COLS and the int[] of
   * availableRows to 0.
   */
  public void resetBoard() {
    for (int r = 0; r < Constants.ROWS; r++) {
      for (int c = 0; c < Constants.COLS; c++) {
        board[r][c] = -1;
      }
    }
    availableRow = new int[Constants.COLS];
    totalMoves = Constants.ROWS * Constants.COLS;
  }

  /**
   * This method is used to set the int[] representing the available row in
   * every column.
   * 
   * @param availableRow
   */
  public void setAvailableRow(int[] availableRow) {
    this.availableRow = availableRow;
  }

  /**
   * This method is used to set the int[][] representing the game board
   * 
   * @param board
   */
  public void setBoard(int[][] board) {
    this.board = board;
  }

  /**
   * This method is used to set the column in which the last move was made.
   * 
   * @param lastCol
   */
  public void setLastCol(int lastCol) {
    this.lastCol = lastCol;
  }

  /**
   * This method is used to set the player who made the last move.
   * 
   * @param lastPlayer
   */
  public void setLastPlayer(int lastPlayer) {
    this.lastPlayer = lastPlayer;
  }

  /**
   * This method is used to set the row in which the last move was made.
   * 
   * @param lastRow
   */
  public void setLastRow(int lastRow) {
    this.lastRow = lastRow;
  }

  /**
   * Setter for the totalMoves variable.
   * 
   * @param totalMoves
   */
  public void setTotalMoves(int totalMoves) {
    this.totalMoves = totalMoves;
  }

  /**
   * This method is used to update the int[][] representation of the game board,
   * the availableRows int[] and the totalMoves remaining.
   * 
   * @param column
   * @param playerId
   * @return the next available row in the column. -1 is returned if the column
   *         is full. An error message is displayed if the column is full.
   */
  public int updateBoard(int column, int playerId) {
    int r = availableRow[column];
    if (r == Constants.ROWS) {
      return -1;
    }
    board[r][column] = playerId;
    availableRow[column]++;
    totalMoves--;
    return r;
  }
}

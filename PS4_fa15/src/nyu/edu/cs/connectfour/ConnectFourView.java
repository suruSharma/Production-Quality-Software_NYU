package nyu.edu.cs.connectfour;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import nyu.edu.cs.connectfour.util.Constants;

import com.sun.glass.events.KeyEvent;

/**
 * This is the view class for the ConnectFour game. This implementation includes
 * a grid (made of JButtons) that represents the game board.
 * 
 * @author Suruchi
 *
 */
public class ConnectFourView implements ConnectFourListener {

  /**
   * ActionListener class to handle clicks on the buttons in the game board
   * grid.
   * 
   * @author Suruchi
   *
   */
  private class BoardButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (active) {
        JButton btn = (JButton) e.getSource();
        int c = (int) btn.getClientProperty("column");
        model.play(c);
      } else {
        JOptionPane.showMessageDialog(mainFrame,
            "Either you haven't clicked the start button or it's not your turn", "Wrong move",
            JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  /**
   * ActionListener class to handle the change of the Human vs Human and Human
   * vs Computer radio buttons.
   * 
   * @author Suruchi
   */
  private class PlayerActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
      AbstractButton aButton = (AbstractButton) actionEvent.getSource();
      gameMode = (int) aButton.getClientProperty("value");
      setPlayers();
    }
  }

  /**
   * ActionListener class to handle the change click of the Start/New Game
   * method.
   * 
   * @author Suruchi
   *
   */
  private class StartButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (gameMode == -1) {
        JOptionPane.showMessageDialog(mainFrame, "Select a game mode", "Invalid game mode",
            JOptionPane.ERROR_MESSAGE);
      } else {
        active = true;
        int action = (int) start.getClientProperty("action");
        if (action == Constants.BUTTON_ACTION_START) {
          hh.setEnabled(false);
          hc.setEnabled(false);
          start.setText("New Game");
          start.putClientProperty("action", Constants.BUTTON_ACTION_NEW);
          model.startGame();
        } else {
          restoreDefaults();
        }
      }
    }
  }

  private JFrame mainFrame;
  private JPanel mainPanel;
  private JPanel centerPanel;
  private ConnectFourModel model;
  private JButton[][] boardButtons;
  private int gameMode = 1;
  private JButton start;
  private JPanel boardPanel = new JPanel(new GridLayout(Constants.ROWS, Constants.COLS));
  private ConnectFourPlayer firstPlayer;
  private ConnectFourPlayer secondPlayer;
  private boolean active = false;
  private JRadioButton hh;
  private JRadioButton hc;

  /**
   * Parameterized constructor
   * 
   * @param model
   *          The model to be associated with the view.
   */
  public ConnectFourView(ConnectFourModel model) {
    this.model = model;
    model.addListener(this);
    init();
  }

  @Override
  public void alert(String message) {
    JOptionPane.showMessageDialog(mainFrame, message, "Invalid move", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void declareWinner(String message) {
    JOptionPane.showMessageDialog(mainFrame, message, "Winner", JOptionPane.PLAIN_MESSAGE);
    restoreDefaults();
  }

  @Override
  public void gameStart() {
    for (JButton[] boardRow : boardButtons) {
      for (JButton b : boardRow) {
        b.setText("");
      }
    }
  }

  /**
   * Method to get the central panel containing the ConnectFour grid.
   * 
   * @return boardPanel JPanel consisting the game board.
   */
  private JPanel getCenterPanel() {
    for (int r = Constants.ROWS - 1; r >= 0; r--) {
      for (int c = 0; c < Constants.COLS; c++) {
        boardPanel.add(boardButtons[r][c]);
      }
    }
    return boardPanel;
  }

  /**
   * Method to get the top (northern) panel containing the game mode radio
   * buttons and the Start/New Game button.
   * 
   * @return topPanel JPanel containing the game controls.
   */
  private JPanel getTopPanel() {
    JPanel topPanel = new JPanel(new FlowLayout());
    hh = new JRadioButton("Human vs Human");
    hh.setMnemonic(KeyEvent.VK_H);
    hh.putClientProperty("value", Constants.MODE_HH);
    hh.addActionListener(new PlayerActionListener());
    hh.setSelected(true);
    hc = new JRadioButton("Human vs Computer");
    hc.setMnemonic(KeyEvent.VK_C);
    hc.putClientProperty("value", Constants.MODE_HC);
    hc.addActionListener(new PlayerActionListener());
    ButtonGroup group = new ButtonGroup();
    group.add(hh);
    group.add(hc);
    start = new JButton("Start");
    start.putClientProperty("action", Constants.BUTTON_ACTION_START);
    start.addActionListener(new StartButtonActionListener());
    topPanel.add(hh);
    topPanel.add(hc);
    topPanel.add(start);
    return topPanel;
  }

  /**
   * This method initializes the view
   */
  private void init() {
    setBoardButtons();
    mainFrame = new JFrame("Connect Four");
    mainPanel = new JPanel(new BorderLayout());
    mainPanel.add(getTopPanel(), BorderLayout.NORTH);
    centerPanel = getCenterPanel();
    mainPanel.add(centerPanel, BorderLayout.CENTER);
    mainFrame.setSize(500, 500);
    mainFrame.setResizable(false);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.getContentPane().add(mainPanel);
    setPlayers();
    mainFrame.setVisible(true);
  }

  @Override
  public void makeMove(int playerId, int r, int c) {
    if (playerId == Constants.PLAYER1) {
      boardButtons[r][c].setBackground(Constants.COLOR_PLAYER1);
    } else {
      boardButtons[r][c].setBackground(Constants.COLOR_PLAYER2);
    }

  }

  @Override
  public void restoreDefaults() {
    active = false;
    start.setText("Start");
    start.putClientProperty("action", Constants.BUTTON_ACTION_START);
    boardPanel.removeAll();
    boardPanel.revalidate();
    setBoardButtons();
    getCenterPanel();
    gameMode = Constants.MODE_HH;
    hh.setEnabled(true);
    hc.setEnabled(true);
    hh.setSelected(true);
    hc.setSelected(false);
    setPlayers();

  }

  /**
   * This method sets the default properties of the boardButtons
   */
  private void setBoardButtons() {
    boardButtons = new JButton[Constants.ROWS][Constants.COLS];
    for (int r = Constants.ROWS - 1; r >= 0; r--) {
      for (int c = 0; c < Constants.COLS; c++) {
        boardButtons[r][c] = new JButton("");
        boardButtons[r][c].putClientProperty("column", c);
        boardButtons[r][c].putClientProperty("row", r);
        boardButtons[r][c].addActionListener(new BoardButtonActionListener());
      }
    }
  }

  /**
   * This method is used to set the players of the game in the model. In Human
   * vs Human the default names of the players are "Player 1" and "Player 2". In
   * Human vs COmputer the default names of the players are "Human" and
   * "Computer". Changes will have to be made in this method if one wishes to
   * accept the names of the players from the user of the game.
   */
  private void setPlayers() {
    if (gameMode == Constants.MODE_HH) {
      firstPlayer = new ConnectFourPlayer("Player 1", 1, true);
      secondPlayer = new ConnectFourPlayer("Player 2", 2, true);
    } else {
      firstPlayer = new ConnectFourPlayer("Human", 1, true);
      secondPlayer = new ConnectFourPlayer("Computer", 2, false);
    }
    model.setFirstPlayer(firstPlayer);
    model.setSecondPlayer(secondPlayer);
  }

  @Override
  public void gameOver() {
    JOptionPane.showMessageDialog(mainFrame, "Game over!!", "Game over", JOptionPane.ERROR_MESSAGE);
    restoreDefaults();
  }

}

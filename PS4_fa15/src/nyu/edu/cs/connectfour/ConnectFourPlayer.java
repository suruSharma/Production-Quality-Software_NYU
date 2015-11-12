package nyu.edu.cs.connectfour;

/**
 * This class represents the players in the ConnectFourGame
 * 
 * @author Suruchi
 *
 */
public class ConnectFourPlayer {

  String name;
  boolean human;
  int moves;
  int id;

  /**
   * Parameterized constructor that is used to create the player object.
   * 
   * @param name
   *          The name of the player.
   * @param id
   *          The id of the player.
   * @param human
   *          Boolean to indicate that the player is human.
   */
  public ConnectFourPlayer(String name, int id, boolean human) {
    this.name = name;
    this.human = human;
    this.id = id;
    this.moves = 0;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ConnectFourPlayer other = (ConnectFourPlayer) obj;
    if (human != other.human)
      return false;
    if (id != other.id)
      return false;
    if (moves != other.moves)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

  /**
   * Method to get the ID of a player.
   * 
   * @return The ID of a player.
   */
  public int getId() {
    return id;
  }

  /**
   * Getter for the total moves made by a player.
   * 
   * @return The number of moves made by a player
   */
  public int getMoves() {
    return moves;
  }

  /**
   * Getter to get the name of the player.
   * 
   * @return The name of the player.
   */
  public String getName() {
    return name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (human ? 1231 : 1237);
    result = prime * result + id;
    result = prime * result + moves;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  /**
   * Method to check if the player is human.
   * 
   * @return true if player is human, false otherwise.
   */
  public boolean isHuman() {
    return human;
  }

  /**
   * Set the human boolean for a player.
   * 
   * @param human
   *          true if the player is human, false otherwise.
   */
  public void setHuman(boolean human) {
    this.human = human;
  }

  /**
   * Set the ID of a player.
   * 
   * @param id
   *          The ID of a player.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Set the number of moves made by a player.
   * 
   * @param moves
   *          The number of moves made by a player.
   */
  public void setMoves(int moves) {
    this.moves = moves;
  }

  /**
   * Setter for setting the name of the player.
   * 
   * @param name
   *          The name of the player.
   */
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "ConnectFourPlayer [name=" + name + ", human=" + human + ", moves=" + moves + ", id="
        + id + "]";
  }

}

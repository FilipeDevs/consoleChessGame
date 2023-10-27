package g58093.chess.view;
import g58093.chess.model.Position;

/**
 *
 * @author 58093
 */
public interface View {
    
    /**
     * Displays a title and a message of welcome to the players.
     */
    public void displayTitle();
    
    /**
     * Displays the winner. If the game ends in a stalemate there is no winner 
     * so it displays that the game ended in a draw.
     */
    public void displayWinner();
    
    /**
     * Displays a message when a player is in a check state. 
     * The message informs the player of the situation
     */
    public void displayCheck();
    
    /**
     * Displays a message when a player is in a check mate state. 
     * The message informs the player of the situation
     */
    public void displayMat();
    
    /**
     * Displays a message when a player is in a stalemate state. 
     * The message informs the player of the situation
     */
    public void displayStaleMat();
    
    
   /**
     * Displays the board of the game. The display of the board follows the conventional chess numbering. 
     * The columns are numbered from left to right using lowercase letters (from a to h)
     * and the rows from bottom to top using the numbers from 1 to 8.
     */
    public void displayBoard();
    
    /**
     * Displays a message inviting the current player (white or black) to play.
     */
    public void displayPlayer();
    
    /**
     * Requests from the user, a position on the board.
     * @return The position that the player input.
     */
    public Position askPosition();
    
    /**
     * Displays a given error message.
     * @param message The message that will be displayed as an error.
     */
    public void displayError(String message);

}

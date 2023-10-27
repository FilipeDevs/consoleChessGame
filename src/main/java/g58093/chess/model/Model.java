package g58093.chess.model;

import g58093.chess.model.pieces.Piece;
import java.util.List;

/**
 * Represents the Model Interface.
 *
 */
public interface Model {

    /**
     * Start the game: create the pieces and put them on the board, initialize
     * the current player to 'WHITE'.
     */
    public void start();

    /**
     * Gets the current state of the current game.
     *
     * @return The state of the game.
     */
    public GameState getState();

    /**
     * Checks is a move from a given position to another given position is valid.
     * A valid move is a move that neither sets nor leaves the current player in
     * a check mate position.
     *
     * @param oldPos Starting position.
     * @param newPos Arrival position.
     * @throws IllegalArgumentException if oldPos doesn't have a piece or the
     * move is not possible.
     * @return True if the move is valid, false otherwise.
     */
    public boolean isValidMove(Position oldPos, Position newPos);

    /**
     * Get the piece of the board located on a given position
     *
     * @param pos the position
     * @return the piece located at P
     * @throws IllegalArgumentException pos is not located on the board.
     */
    public Piece getPiece(Position pos);

    /**
     * Getter for the current player.
     *
     * @return the current player.
     */
    public Player getCurrentPlayer();

    /**
     * Getter for the second player.
     *
     * @return the player that is waiting
     */
    public Player getOppositePlayer();

    /**
     * Check if the square at the given position is occupied by a piece of the
     * current player.
     *
     * @param pos the postion
     * @return true if the position is occupied by a piece of the current
     * player, false otherwise.
     * @throws IllegalArgumentException if the position is not located on the
     * board.
     */
    public boolean isCurrentPlayerPosition(Position pos);

    /**
     * Moves a piece from one position of the chess board to another one. 
     * Checks and updates the state of the game after the move was validated.
     *
     * @param oldPos the current position
     * @param newPos the new position of the board where to put the piece
     * @throws IllegalArgumentException if 1) oldPos or newPos are not located
     * on the board, or 2) oldPos does not contain a piece, or 3) the piece does
     * not belong to the current player, or 4) the move is not valid for the
     * piece located at position oldPos
     */
    public void movePiecePosition(Position oldPos, Position newPos);

    

    /**
     * Get the possible moves for the piece located at the specified position.
     *
     * @param position the position of the piece
     * @return the liste of admissible positions.
     */
    public List<Position> getPossibleMoves(Position position);
}

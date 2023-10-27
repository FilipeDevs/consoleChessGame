package g58093.chess.model;

import g58093.chess.model.pieces.Piece;

/**
 * Represents one of the 64 squares of the board.
 */
public class Square {
    
    private Piece piece;
    
    /**
     * Creates a square in the board with a piece. 
     * @param piece Piece that the current square will contain 
     * (can be 'null' if there is no piece yet).
     */
    public Square(Piece piece) {
        this.piece = piece;
    }

    /**
     * Gets current Piece in the current Square.
     * @return Current Piece.
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Sets a Piece in the current Square.
     * @param piece Piece that will be placed in current Square.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    /**
     * Checks if the current Square is free (is there a Piece in current Square ?).
     * @return true if current square is free, false otherwise.
     */
    public boolean isFree() {
        return this.piece == null;
    }
}

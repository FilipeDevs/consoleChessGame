package g58093.chess.model.pieces;

import g58093.chess.model.Board;
import g58093.chess.model.Color;
import g58093.chess.model.Direction;
import g58093.chess.model.Position;
import java.util.List;
import java.util.Objects;

/**
 * Represents a piece of the game
 *
 */
public abstract class Piece {

    private final Color color;

    /**
     * Creates a Piece with a color.
     *
     * @param color Color given to the piece.
     */
    public Piece(Color color) {
        this.color = color;
    }

    /**
     * Gets current color of current Piece.
     *
     * @return current color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Creates a list of the possible moves that a pawn in a given position can
     * make.
     *
     * @param position Given position from where the possibles moves will be
     * calculated.
     * @param board The board of the current game.
     * @return A list containing all the possible moves that a pawn can do from
     * a given position.
     */
    public abstract List<Position> getPossibleMoves(Position position, Board board);

    /**
     * Returns positions in which a piece in a given position can capture
     * another piece.
     *
     * @param position Given position from where the method will start checking
     * the possible capture positions.
     * @param board The board.
     * @return Returns a list of positions (can be empty) in which a piece can
     * capture another piece.
     */
    public List<Position> getCapturePositions(Position position, Board board) {
        return getPossibleMoves(position, board);
    }

    /**
     * Reviews a given a direction from a given position and advances until one of the 3 options occur:
     * 1. Board limit is reached 2. Position is not free 3. Own piece is found. 
     * If a position is valid (the move is possible) the method adds it to a list.
     * @param tab List to be updated with the possible moves.
     * @param dir Given direction to be reviewed.
     * @param pos Start position.
     * @param board The board of the game.
     * @return Returns a list containing all the possible moves from a given position to a given direction.
     */
    protected List<Position> checkDirection(List<Position> tab, Direction dir, Position pos, Board board) {
        List<Position> posDir = tab;
        boolean senti = true; // Set to false if the piece can't advance further.
        Position posMov = pos;

        while (senti) {
            posMov = posMov.next(dir);

            if (board.contains(posMov)) {
                if (board.isFree(posMov)) {
                    posDir.add(posMov);
                } else if (board.containsOppositeColor(posMov, this.getColor())) { 
                    posDir.add(posMov);
                    senti = false; // When it's a capture position the piece can not go beyond that even if it's free.
                } else {
                     senti = false; // Own piece found so can't advance further.
                }
            } else {
                senti = false; // Limit of board reached.
            }
        }
        

        return posDir;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.color);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Piece other = (Piece) obj;
        if (this.color != other.color) {
            return false;
        }
        return true;
    }

}

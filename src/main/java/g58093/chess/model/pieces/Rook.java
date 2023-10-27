package g58093.chess.model.pieces;

import g58093.chess.model.Board;
import g58093.chess.model.Color;
import g58093.chess.model.Direction;
import g58093.chess.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the rook piece of the game.
 *
 */
public class Rook extends Piece {

    /**
     * Creates a rook piece with a given color.
     *
     * @param color Color applied to the rook piece.
     */
    public Rook(Color color) {
        super(color);
    }

    /**
     * Returns a list including all the positions in which a rook piece can move
     * from a given position.
     *
     * @param position Position from where the possible moves will be
     * calculated.
     * @param board The board of current the game.
     * @return A list containing all the possible moves from a rook.
     */
    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
        List<Position> posMov = new ArrayList<>();

        posMov = checkDirection(posMov, Direction.N, position, board); // North
        posMov = checkDirection(posMov, Direction.S, position, board); // South
        posMov = checkDirection(posMov, Direction.W, position, board); // West
        posMov = checkDirection(posMov, Direction.E, position, board); // East

        return posMov;
    }

}

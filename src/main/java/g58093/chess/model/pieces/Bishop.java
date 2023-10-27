package g58093.chess.model.pieces;

import g58093.chess.model.Board;
import g58093.chess.model.Color;
import g58093.chess.model.Direction;
import g58093.chess.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the bishop piece of the game.
 *
 */
public class Bishop extends Piece {

    /**
     * Creates a Bishop with a given a color.
     *
     * @param color Color applied to the bishop piece.
     */
    public Bishop(Color color) {
        super(color);
    }

    /**
     * Returns a list including all the positions in which a bishop piece can move
     * from a given position.
     *
     * @param position Position from where the possible moves will be calculated.
     * @param board The board of current the game.
     * @return A list containing all the possible moves from a bishop.
     */
    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
        List<Position> posMov = new ArrayList<>();

        posMov = checkDirection(posMov, Direction.NW, position, board); // NW
        posMov = checkDirection(posMov, Direction.SW, position, board); // SW
        posMov = checkDirection(posMov, Direction.NE, position, board); // NE
        posMov = checkDirection(posMov, Direction.SE, position, board); // SE

        return posMov;
    }

}

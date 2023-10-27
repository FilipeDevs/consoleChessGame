package g58093.chess.model.pieces;

import g58093.chess.model.Board;
import g58093.chess.model.Color;
import g58093.chess.model.Direction;
import g58093.chess.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the king piece of the game.
 *
 */
public class King extends Piece {

    /**
     * Creates a king with a given color.
     *
     * @param color Color applied to king.
     */
    public King(Color color) {
        super(color);
    }

    /**
     * Returns a list including all the positions in which a king piece can move
     * from a given position.
     *
     * @param position Position from where the possible moves will be
     * calculated.
     * @param board The board of current the game.
     * @return A list containing all the possible moves from a king.
     */
    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
        List<Position> posMov = new ArrayList<>();
        List<Position> posMovAll = List.of( // List containing all the moves from a king.
                position.next(Direction.N),
                position.next(Direction.S),
                position.next(Direction.NW),
                position.next(Direction.NE),
                position.next(Direction.W),
                position.next(Direction.E),
                position.next(Direction.SW),
                position.next(Direction.SE)
        );

        for (var pos : posMovAll) {
            // If the position respects the limits of the board and is free or has a enemy piece, it is a possible move.
            if (board.contains(pos) && (board.containsOppositeColor(pos, this.getColor()) || board.isFree(pos))) {
                posMov.add(pos);
            }
        }

        return posMov;
    }
}

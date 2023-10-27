package g58093.chess.model.pieces;

import g58093.chess.model.Board;
import g58093.chess.model.Color;
import g58093.chess.model.Direction;
import g58093.chess.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the knight piece of the game.
 *
 */
public class Knight extends Piece {

    /**
     * Creates a Knight with a given color.
     *
     * @param color Color applied to Knight.
     */
    public Knight(Color color) {
        super(color);
    }

    /**
     * Returns a list including all the positions in which a knight can move
     * from a given position.
     *
     * @param position Position from where the possible moves will be
     * calculated.
     * @param board The board of current the game.
     * @return A list containing all the possible moves from a knight.
     */
    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
        List<Position> posMov = new ArrayList<>();
        List<Position> posMovAll = List.of( // List containing all the moves from a knight.
                knightMove(position, Direction.NE, Direction.N), // = 2 north 1 east
                knightMove(position, Direction.NW, Direction.N), // = 2 north 1 west
                knightMove(position, Direction.SE, Direction.S), // = 2 south 1 east
                knightMove(position, Direction.SW, Direction.S), // = 2 south 1 west
                knightMove(position, Direction.SW, Direction.W), // = 2 west 1 south
                knightMove(position, Direction.NW, Direction.W), // = 2 west 1 north
                knightMove(position, Direction.SE, Direction.E), // = 2 east 1 south
                knightMove(position, Direction.NE, Direction.E) // = 2 east 1 north
        );

        for (var pos : posMovAll) {
            // If the position respects the limits of the board and is free or has a enemy piece, it is a possible move.
            if (board.contains(pos) && (board.containsOppositeColor(pos, this.getColor()) || board.isFree(pos))) {
                posMov.add(pos);
            }
        }

        return posMov;
    }

    /**
     * Returns a position in which the method adds 2 given directions from a
     * given a position.
     *
     * @param pos Given position where the directions will be added.
     * @param dir1 First given direction.
     * @param dir2 Second given direction.
     * @return Returns final position after the addition of the two directions.
     */
    private Position knightMove(Position pos, Direction dir1, Direction dir2) {
        Position res = pos.next(dir1);
        res = res.next(dir2);
        return res;
    }

}

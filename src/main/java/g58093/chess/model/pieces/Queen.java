package g58093.chess.model.pieces;

import g58093.chess.model.Board;
import g58093.chess.model.Color;
import g58093.chess.model.Direction;
import g58093.chess.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Represents the queen pice of the game.
 *
 */
public class Queen extends Piece {

    /**
     * Creates a queen piece with a given color.
     *
     * @param color Color applied to the queen piece.
     */
    public Queen(Color color) {
        super(color);
    }

    /**
     * Returns a list including all the positions in which a queen piece can move
     * from a given position.
     *
     * @param position Position from where the possible moves will be
     * calculated.
     * @param board The board of current the game.
     * @return A list containing all the possible moves from a queen.
     */
    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
        List<Position> posMov = new ArrayList<>();
        
        posMov = checkDirection(posMov, Direction.N, position, board); // North
        posMov = checkDirection(posMov, Direction.S, position, board); // South
        posMov = checkDirection(posMov, Direction.NE, position, board); // NE
        posMov = checkDirection(posMov, Direction.NW, position, board); // NW
        posMov = checkDirection(posMov, Direction.SE, position, board); // SE
        posMov = checkDirection(posMov, Direction.SW, position, board); // SW
        posMov = checkDirection(posMov, Direction.W, position, board); // W
        posMov = checkDirection(posMov, Direction.E, position, board); // E
   
        
        
        return posMov;
    }
    
    
}

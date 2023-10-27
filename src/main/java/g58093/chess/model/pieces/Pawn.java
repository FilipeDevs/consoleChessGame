package g58093.chess.model.pieces;

import g58093.chess.model.Board;
import g58093.chess.model.Color;
import g58093.chess.model.Direction;
import g58093.chess.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the pawn piece of the game.
 *
 */
public class Pawn extends Piece {

    /**
     * Creates a pawn with a given color.
     *
     * @param color Color applied to pawn.
     */
    public Pawn(Color color) {
        super(color);
    }

    /**
     * Updates a given list by adding a potential diagonal possible move towards
     * a given direction from a given position in that list. The position will
     * only be added if it respects the rules of capture of a pawn.
     *
     * @param tab Given list.
     * @param pos Given position.
     * @param dir Given direction.
     * @param board Board of the game.
     * @param color Color of the pawn.
     * @return List updated with a new possible move (or not if it's not
     * possible).
     */
    private List<Position> diagonalMove(List<Position> tab, Position pos, Direction dir, Board board, Color color) {
        List<Position> posMovs = tab;

        pos = checkNextMove(pos, dir, board);

        if (!board.isFree(pos) && board.containsOppositeColor(pos, color)) {
            posMovs.add(pos);
        }

        return posMovs;
    }

    /**
     * Updates a given list by adding a potential vertical possible move towards
     * a given direction from a given position in that list. The position will
     * only be added if it respects the rules of movement of a pawn. The method
     * also checks if a 2 vertical move is possible if the pawn is in it start
     * position. If yes the move is also added.
     *
     * @param tab Given list.
     * @param pos Given position.
     * @param dir Given direction.
     * @param board Board of the game.
     * @param color Color of the pawn.
     * @return List updated with 1 or 2 possible moves (or 0 if either are not
     * possible).
     */
    private List<Position> initMove(List<Position> tab, Position pos, Direction dir, Board board, Color color) {

        List<Position> posMovs = tab;
        Position init = pos;
        pos = checkNextMove(pos, dir, board);

        if (board.isFree(pos)) {
            posMovs.add(pos);
            pos = pos.next(dir);
        }
        if (board.isFree(pos) && board.getInitialPawnRow(color) == init.getRow()) {
            posMovs.add(pos);
        }

        return posMovs;
    }

    /**
     * Creates a list of the possible moves that a pawn in a given position can
     * make.
     *
     * @param position Given position from where the possible moves will be
     * calculated.
     * @param board The board of the current game.
     * @return A list containing all the possible moves that a pawn can do from
     * a given position.
     */
    @Override
    public List<Position> getPossibleMoves(Position position, Board board) {
        List<Position> capturePos = new ArrayList<>();

        // White pawns
        if (board.getPiece(position).getColor().equals(Color.WHITE)) {
            // Possible move positions
            capturePos = diagonalMove(capturePos, position, Direction.NE, board, Color.WHITE);
            capturePos = diagonalMove(capturePos, position, Direction.NW, board, Color.WHITE);
            capturePos = initMove(capturePos, position, Direction.N, board, Color.WHITE);

        } else { // Black pawns
            capturePos = diagonalMove(capturePos, position, Direction.SE, board, Color.BLACK);
            capturePos = diagonalMove(capturePos, position, Direction.SW, board, Color.BLACK);
            capturePos = initMove(capturePos, position, Direction.S, board, Color.BLACK);

        }

        return capturePos;
    }

    /**
     * Checks if a move towards a given direction, from a given position is
     * respected within the boundaries of the board. If yes it returns a
     * position after the selected move. If not it returns the given position.
     *
     * @param position Position from where the move will take place.
     * @param dir The direction where the given position will move.
     * @param board The board of the game.
     * @return A position after checking the calculated move.
     */
    private Position checkNextMove(Position position, Direction dir, Board board) {
        Position pos = board.contains(position.next(dir)) ? position.next(dir) : position;
        return pos;
    }

    /**
     * Returns positions in which a pawn in a given position can capture another
     * piece. The way a possible capture position is checked from a pawn is
     * different to other pieces (a pawn can move vertically and only captures
     * diagonally). So the method will only check the diagonal positions to the
     * pawn.
     *
     * @param position Given position from where the method will start checking
     * the possible capture positions.
     * @param board The board.
     * @return Returns a list of positions (can be empty) in which a pawn can
     * capture another piece.
     */
    @Override
    public List<Position> getCapturePositions(Position position, Board board) {
        List<Position> capturePos = new ArrayList<>();

        // White pawns
        if (board.getPiece(position).getColor().equals(Color.WHITE)) {
            // Possible move positions
            capturePos = diagonalMove(capturePos, position, Direction.NE, board, Color.WHITE);
            capturePos = diagonalMove(capturePos, position, Direction.NW, board, Color.WHITE);

        } else { // Black pawns
            capturePos = diagonalMove(capturePos, position, Direction.SE, board, Color.BLACK);
            capturePos = diagonalMove(capturePos, position, Direction.SW, board, Color.BLACK);

        }

        return capturePos;
    }

}

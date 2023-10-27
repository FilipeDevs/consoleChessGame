package g58093.chess.model;

import g58093.chess.model.pieces.Bishop;
import g58093.chess.model.pieces.King;
import g58093.chess.model.pieces.Knight;
import g58093.chess.model.pieces.Pawn;
import g58093.chess.model.pieces.Piece;
import g58093.chess.model.pieces.Queen;
import g58093.chess.model.pieces.Rook;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game. This class gathers the elements necessary for the game,
 * and implements the different stages of it. It is also a point of access for
 * the view and controller.
 *
 */
public class Game implements Model {

    private final Board board;
    private final Player white;
    private final Player black;
    private Player currentPlayer;
    private King whiteKing;
    private King blackKing;
    private GameState state;

    /**
     * Creates a new Game: a white and black player is created, a new empty
     * board is created.
     */
    public Game() {
        this.board = new Board();
        this.white = new Player(Color.WHITE);
        this.black = new Player(Color.BLACK);
    }

    /**
     * Start the game: creates the pieces and puts them on the board, initializes
     * the current player to 'WHITE' and the state to PLAY.
     */
    @Override
    public void start() {
        this.currentPlayer = white;

        for (int i = 0; i < 8; i++) {
            board.setPiece(new Pawn(Color.WHITE), new Position(board.getInitialPawnRow(Color.WHITE), i));
            board.setPiece(new Pawn(Color.BLACK), new Position(board.getInitialPawnRow(Color.BLACK), i));
        }

        whiteKing = new King(Color.WHITE);
        blackKing = new King(Color.BLACK);

        board.setPiece(new Knight(Color.WHITE), new Position(0, 1));
        board.setPiece(new Knight(Color.WHITE), new Position(0, 6));
        board.setPiece(new Knight(Color.BLACK), new Position(7, 1));
        board.setPiece(new Knight(Color.BLACK), new Position(7, 6));

        board.setPiece(new Rook(Color.WHITE), new Position(0, 0));
        board.setPiece(new Rook(Color.WHITE), new Position(0, 7));
        board.setPiece(new Rook(Color.BLACK), new Position(7, 0));
        board.setPiece(new Rook(Color.BLACK), new Position(7, 7));

        board.setPiece(new Bishop(Color.WHITE), new Position(0, 2));
        board.setPiece(new Bishop(Color.WHITE), new Position(0, 5));
        board.setPiece(new Bishop(Color.BLACK), new Position(7, 2));
        board.setPiece(new Bishop(Color.BLACK), new Position(7, 5));

        board.setPiece(whiteKing, new Position(0, 4));
        board.setPiece(blackKing, new Position(7, 4));

        board.setPiece(new Queen(Color.WHITE), new Position(0, 3));
        board.setPiece(new Queen(Color.BLACK), new Position(7, 3));

        state = GameState.PLAY;
    }

    /**
     * Gets the current state of the current game.
     *
     * @return The state of the game.
     */
    @Override
    public GameState getState() {
        return state;
    }

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
    @Override
    public boolean isValidMove(Position oldPos, Position newPos) {
        if (board.isFree(oldPos)) {
            throw new IllegalArgumentException("Position de départ n'a aucune piéce!");
        } else if (!board.getPiece(oldPos).getPossibleMoves(oldPos, board).contains(newPos)) {
            throw new IllegalArgumentException("Mouvement pour cette piéce impossible!");
        }
        Piece pieceOcp = board.getPiece(newPos);

        boolean res = true;
        
        // Simulate the actual move
        board.setPiece(board.getPiece(oldPos), newPos);
        board.dropPiece(oldPos);

        // Select the king of the current player
        King inDanger = this.getCurrentPlayer().getColor().equals(Color.WHITE) ? whiteKing : blackKing;
        Position kingPos = board.getPiecePosition(inDanger);

        List<Position> capturePos = getCapturePositions(this.getOppositePlayer()); // All positions where the enemy player can capture a piece.

        if (capturePos.contains(kingPos)) { // If the king is one of the capture positions of the enemy the move is not valid.
            res = false;
        }

        // Reset the simulation of the move
        board.setPiece(board.getPiece(newPos), oldPos);
        board.setPiece(pieceOcp, newPos);

        return res;
    }

    /**
     * Returns a list containing all the capture positions that a given player
     * has.
     *
     * @param player The player.
     * @return List containing the capture positions of all the pieces of a
     * player.
     */
    private List<Position> getCapturePositions(Player player) {
        List<Position> capturePos = new ArrayList<>();

        for (var elem : board.getPositionsOccupiedBy(player)) {
            for (var pos : this.getPossibleMoves(elem)) {
                if (!board.isFree(pos)) { // if there is a piece in this position
                    if (!board.getPiece(pos).getColor().equals(player.getColor())) {
                        capturePos.add(pos);
                    }
                }

            }
        }

        return capturePos;
    }

    /**
     * Checks if a given player pieces has any valid moves.
     *
     * @param player A player.
     * @return True if the player has at least 1 valid move, false otherwise (0
     * valid moves).
     */
    private boolean checkValidMoves(Player player) {

        boolean res = false;

        List<Position> occupiedPos = board.getPositionsOccupiedBy(player);
        List<Position> possibleMov;

        for (int i = 0; i < occupiedPos.size() && !res; i++) {
            possibleMov = this.getPossibleMoves(occupiedPos.get(i));
            for (int j = 0; j < possibleMov.size() && !res; j++) {
                res = this.isValidMove(occupiedPos.get(i), possibleMov.get(j));
            }
        }

        return res;
    }

    /**
     * Get the piece of the board located on a given position.
     *
     * @param pos The position.
     * @return The piece located at pos.
     * @throws IllegalArgumentException The position is not located on the board.
     */
    @Override
    public Piece getPiece(Position pos) {
        if (!board.contains(pos)) {
            throw new IllegalArgumentException("Given position is not on the board!");
        }
        return board.getPiece(pos);
    }

    /**
     * Getter for the current player.
     *
     * @return the current player.
     */
    @Override
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Getter for the second player.
     *
     * @return the player that is waiting
     */
    @Override
    public Player getOppositePlayer() {
        return this.currentPlayer.getColor().equals(Color.WHITE) ? this.black : this.white;
    }

    /**
     * Check if the square at the given position is occupied by a piece of the
     * current player.
     *
     * @param pos the position
     * @return true if the position is occupied by a piece of the current
     * player, false otherwise.
     * @throws IllegalArgumentException if the position is not located on the
     * board.
     */
    @Override
    public boolean isCurrentPlayerPosition(Position pos) {
        if (!board.contains(pos)) {
            throw new IllegalArgumentException("Given position is not on the board!");
        }
        return board.getPiece(pos).getColor().equals(this.currentPlayer.getColor());
    }

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
    @Override
    public void movePiecePosition(Position oldPos, Position newPos) {
        if (!board.contains(oldPos) || !board.contains(newPos)) {
            throw new IllegalArgumentException("Position entré ne se trouve pas dans le plateau!");
        } else if (board.isFree(oldPos)) {
            throw new IllegalArgumentException("Première position n'a pas de pion.");
        } else if (!board.getPiece(oldPos).getColor().equals(this.currentPlayer.getColor())) {
            throw new IllegalArgumentException("Pièce selectionné ne vous appartient pas!");
        } else if (!board.getPiece(oldPos).getPossibleMoves(oldPos, this.board).contains(newPos)) {
            throw new IllegalArgumentException("Le déplacement est invalide!");
        }
            if (this.isValidMove(oldPos, newPos)) {
                Piece piece = board.getPiece(oldPos);
                board.setPiece(piece, newPos);
                board.dropPiece(oldPos);
                state = GameState.PLAY; // reset state.
            } else {
                throw new IllegalArgumentException("Déplacement impossible: votre roi sera en état d'échec !");
            }
        
        this.currentPlayer = this.getOppositePlayer(); // Change current player.

        // Select the king of the current player
        King inDanger = this.getCurrentPlayer().getColor().equals(Color.BLACK) ? blackKing : whiteKing;
        
        // After the move is applied, check the state of the game.

        // CHECK / CHECKMATE
        if (getCapturePositions(this.getOppositePlayer()).contains(board.getPiecePosition(inDanger))) {
            state = checkValidMoves(this.getCurrentPlayer()) ? GameState.CHECK : GameState.CHECK_MATE;
            
        } else if (!checkValidMoves(this.getCurrentPlayer())) { // STALEMATE, player has no more valid moves but the king is not in danger.
            state = GameState.STALE_MATE;
        }

    }

    /**
     * Get the possible moves for the piece located at the specified position.
     *
     * @param position the position of the piece
     * @return the liste of admissible positions.
     */
    @Override
    public List<Position> getPossibleMoves(Position position) {
        return board.getPiece(position).getPossibleMoves(position, this.board);
    }

}

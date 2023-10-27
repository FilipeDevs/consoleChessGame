package g58093.chess.model;

import g58093.chess.model.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the board of the game.
 */
public class Board {
    
    private final Square[][] plateau;
    
    /**
     * Creates a empty Board. This is where the squares and pieces will be placed.
     */
    public Board() {
        
        plateau = new Square[8][8];
        
        // Fills the board with squares but with no pieces (null)
        for(int i = 0; i < plateau.length; i++) {
            for(int j = 0; j < plateau.length; j++) {
                plateau[i][j] = new Square(null);
            }
        }
    }
    
    /**
     * Checks if row and column of given position are respected within the boundaries of the board.
     * @param pos Position that will be checked.
     * @return Position is respected or not.
     */
    public boolean contains(Position pos) {
        return pos.getRow() < plateau.length && pos.getRow() >= 0  
                && pos.getColumn() < plateau.length && pos.getColumn() >= 0;
    }
    
    /**
     * Places a given piece in a given position on the board. 
     * @param piece The piece that will be placed in one of the squares of the board.
     * @param position The position where the piece will be placed.
     * @throws IllegalArgumentException Checks if the position is within the boundaries of the board, 
     * if not an exception is triggered.
     */
    public void setPiece(Piece piece, Position position) {
        if(!this.contains(position)) {
            throw new IllegalArgumentException("Given position is not in board!");
        }
     
         plateau[position.getRow()][position.getColumn()] = new Square(piece);
    } 
    
    /**
     * Gets the current piece of a square in given position of the board.
     * @param pos The position in the board where it will get the piece.
     * @return The piece in the position requested.
     * @throws IllegalArgumentException Checks if the position is within the boundaries of the board, 
     * if not an exception is triggered.
     */
    public Piece getPiece(Position pos) {
        if(!this.contains(pos)) {
            throw new IllegalArgumentException("Given position is not in board!");
        }
        
        return plateau[pos.getRow()][pos.getColumn()].getPiece();
    }
    
    /**
     * Sends back a number depending in given color.
     * @param color A color.
     * @return Returns 6 if the color is Black, 1 if it's White.
     */
    public int getInitialPawnRow(Color color) {
        return color.equals(Color.BLACK) ? 6 : 1;
    }
    
    /**
     * Deletes a piece of a square in given position of the board.
     * @param pos The position where the piece will be deleted.
     * @throws IllegalArgumentException Checks if the position is within the boundaries of the board, 
     * if not an exception is triggered.
     */
    public void dropPiece(Position pos) {
        if(!this.contains(pos)) {
            throw new IllegalArgumentException("Given position is not in board!");
        }
        
       plateau[pos.getRow()][pos.getColumn()].setPiece(null);
    }
    
    /**
     * Checks if given square position on the board is free (does it have a piece or not ?).
     * @param pos Position of square on the board that will be checked.
     * @return The given position of a square on the board is free or not.
     * @throws IllegalArgumentException Checks if the position is within the boundaries of the board, 
     * if not an exception is triggered.
     */
    public boolean isFree(Position pos){
        if(!this.contains(pos)) {
            throw new IllegalArgumentException("Given position is not in board!");
        }
        
        return plateau[pos.getRow()][pos.getColumn()].isFree();
    }
    
    /**
     * Checks if given position (of a square on the board) contains a piece with 
     * the opposite color of given color.
     * @param pos Position where it will be checked.
     * @param col A color.
     * @return The piece in given position is the opposite or not of given color.
     * @throws IllegalArgumentException Checks if the position is within the boundaries of the board, 
     * if not an exception is triggered.
     */
    public boolean containsOppositeColor (Position pos, Color col) {
        if(!this.contains(pos)) {
            throw new IllegalArgumentException("Given position is not in board!");
        } else if (plateau[pos.getRow()][pos.getColumn()].isFree()) { // if there is no piece in a case, there is no color !
            return false;
        }
        
        return !plateau[pos.getRow()][pos.getColumn()].getPiece().getColor().equals(col);
    }
    
    /**
     * Creates a list containing all the cases that are occupied on the board by a given player.
     * @param player The player that is occupying (or not) the cases on the board.
     * @return List containing all the cases that are occupied by the player.
     */
    public List<Position> getPositionsOccupiedBy(Player player) {
        List<Position> OccupiedPos = new ArrayList<>();
        
        for(int i = 0; i < plateau.length; i++) {
            for(int j = 0; j < plateau.length; j++) {
                // Checks if the case is not free and that the piece inside the case is from the given player.
                if((!plateau[i][j].isFree()) && plateau[i][j].getPiece().getColor().equals(player.getColor())) {
                    Position pos = new Position(i,j);
                    OccupiedPos.add(pos);
                }
            }
        }
        
        return OccupiedPos;
    }
    
    /**
     * Returns the position on the board of a given piece. If the piece is not on the board 
     * the method returns null.
     * @param piece Given piece to find its position.
     * @return Returns the position of the piece or null if not on the board.
     */
    public Position getPiecePosition (Piece piece) {
        Position pos = null; // piece not found by default.
        boolean found = true; // if piece is found set to false.
        
        for(int i = 0; i < plateau.length && found; i++) {
            for(int j = 0; j < plateau.length && found; j++) {
                if(!plateau[i][j].isFree()) { // if there is a piece in this position
                    if(plateau[i][j].getPiece().equals(piece)) {
                    pos = new Position(i,j);
                    found = false;
                }
                }
                
            }
        }
        
        return pos;
    }
    
}

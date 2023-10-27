package g58093.chess.model;

/**
 * Represents the position of a pawn in the game.
 */
public class Position {
    
    private final int row;
    private final int column;
    
    /**
     * Creates a Position. A position is composed of a row and a column.
     * @param row row.
     * @param column column.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Gets current row.
     * @return current row.
     */
    public int getRow() {
        return row;
    }
     
    /**
     * Gets current column.
     * @return current column.
     */
    public int getColumn() {
        return column;
    }
    
    /**
     * Moves current position with a given Direction to a new position
     * @param dir Direction in which the current position has to be changed
     * @return New updated Position after the shift
     */
    public Position next(Direction dir) {
        return new Position(this.row + dir.getDeltaRow(), this.column + dir.getDeltaColumn());
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.row;
        hash = 59 * hash + this.column;
        return hash;
    }

    /**
     * 
     * @param obj
     * @return 
     */
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
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }
    
    
}

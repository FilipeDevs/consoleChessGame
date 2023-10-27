package g58093.chess.model;

/**
 * Represents a direction of displacement in the board.
 */
public enum Direction {
    
    NW(1,-1), N(1,0), NE(1,1), W(0,-1), E(0,1), SW(-1,-1), S(-1,0), SE(-1,1);
    
    private final int deltaRow;
    private final int deltaColumn;
    
    /**
     * Creates a Direction. The direction is composed of a row and column.
     * @param deltaR Represents the shifting for the lines.
     * @param deltaC Represents the shfiting for the columns.
     */
    private Direction(int deltaR, int deltaC) {
        deltaRow = deltaR;
        deltaColumn = deltaC;
    }

    /**
     * Gets current deltaRow.
     * @return current deltaRow
     */
    public int getDeltaRow() {
        return deltaRow;
    }

    /**
     * Gets current deltaColumn.
     * @return current deltaColumn.
     */
    public int getDeltaColumn() {
        return deltaColumn;
    } 
    
}

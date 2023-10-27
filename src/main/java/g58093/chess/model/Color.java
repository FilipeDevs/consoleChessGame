package g58093.chess.model;

/**
 * Represents a color of a Player in the game.
 */
public enum Color {
    
    WHITE, BLACK;
    
    /**
     * Returns opposite color of current color. 
     * @return If it is WHITE it sends back BLACK, BLACK otherwise.
     */
    public Color opposite(){
        
        // Returns opposite color depedending on the current color.
        return this.equals(Color.BLACK) ? Color.WHITE : Color.BLACK;
    }
    
    
        
    
}

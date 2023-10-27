package g58093.chess.model;

import java.util.Objects;

/**
 * Represents a Player in the game.
 */
public class Player {
    
    private final Color color;
    
    /**
     * Creates a Player
     * @param color color given to Player
     */
    public Player(Color color) {
        this.color = color;
    }

    /**
     * Gets current Color of Player.
     * @return current color of player.
     */
    public Color getColor() {
        return this.color;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.color);
        return hash;
    }

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
        final Player other = (Player) obj;
        if (this.color != other.color) {
            return false;
        }
        return true;
    }
    
    
    
    
}

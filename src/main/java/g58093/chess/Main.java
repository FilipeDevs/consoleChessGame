package g58093.chess;

import g58093.chess.controller.Controller;
import g58093.chess.model.Game;
import g58093.chess.model.Model;
import g58093.chess.view.TextView;

/**
 * Main class of the game.
 */
public class Main {

    public static void main(String[] args) {
        Model game = new Game();
        Controller controller = new Controller(game, new TextView(game));
        controller.play();
        
    }
}

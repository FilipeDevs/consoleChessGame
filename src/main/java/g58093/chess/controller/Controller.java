/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g58093.chess.controller;

import g58093.chess.model.Model;
import g58093.chess.model.Position;
import g58093.chess.view.View;

/**
 * Represents the controller of the game. Links the View and the Model.
 */
public class Controller {

    private final View view;
    private final Model model;

    /**
     * Creates a Controller. 
     * @param model The Model.
     * @param view The View.
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Responsible for piloting the game. Implements all the methods necessary to make the game functional.
     */
    public void play() {
        boolean gameIsOver = false;
        view.displayTitle(); // Displays the title
        model.start(); // Starts the game
        view.displayBoard();
        
        while (!gameIsOver) {
            
            view.displayPlayer();
            Position posDepart = view.askPosition(); 
            Position posArrivée = view.askPosition();
            // If a shift of position of the selected pawn is not possible a exception will be triggered, 
            // The exception will be handled by displaying a message of the problem.
            try {
                model.movePiecePosition(posDepart, posArrivée);
                    }
                catch(IllegalArgumentException a) {
                    view.displayError(a.getMessage());
                }
            view.displayBoard();
            
            // Check game state.
            switch (model.getState()) {
                case CHECK_MATE:
                    view.displayMat();
                    gameIsOver = true;
                    break;
                case STALE_MATE:
                    view.displayStaleMat();
                    gameIsOver = true;
                    break;
                case CHECK:
                    view.displayCheck();
                    break;
                default:
                    break;
            }
            
        }
        
        view.displayWinner();

    }

}

package g58093.chess.view;

import g58093.chess.model.Color;
import g58093.chess.model.GameState;
import g58093.chess.model.Model;
import g58093.chess.model.Position;
import g58093.chess.model.pieces.Bishop;
import g58093.chess.model.pieces.King;
import g58093.chess.model.pieces.Knight;
import g58093.chess.model.pieces.Pawn;
import g58093.chess.model.pieces.Piece;
import g58093.chess.model.pieces.Queen;
import g58093.chess.model.pieces.Rook;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents the view of the game. It is responsible for all the display of the
 * game.
 *
 * @author g58093
 */
public class TextView implements View {

    private final Model model;

    /**
     * Creates a View with a Model.
     *
     * @param model The model that the current View will use.
     */
    public TextView(Model model) {
        this.model = model;
    }

    /**
     * Displays a title and a message of welcome to the players.
     */
    @Override
    public void displayTitle() {
        System.out.print("JEU DES ECHECS\nBienvenue chers joueurs!\n");

    }

    /**
     * Displays the winner. If the game ends in a stalemate there is no winner 
     * so it displays that the game ended in a draw.
     */
    @Override
    public void displayWinner() {
        if(model.getState() != GameState.STALE_MATE) {
            String res = this.model.getOppositePlayer().getColor().equals(Color.BLACK) ? "Noir" : "Blanc";
            System.out.println("Le jeu est terminée. Nous avons un gagnant!");
            System.out.print("Le grand gagnant est le joueur " + res);
        } else {
            System.out.println("Le jeu est terminée. C'est un match nul !");
        }
        
    }
    
    /**
     * Displays a message when a player is in a check state. 
     * The message informs the player of the situation
     */
    @Override
    public void displayCheck() {
        String player = model.getCurrentPlayer().getColor().equals(Color.WHITE) ? "Blanc" : "Noir";
        System.out.println("Le joueur " + player + " est en situation d'échec!");
        System.out.println("Votre roi est en danger mais vous pouvez encore le défendre !");
    }
    
    /**
     * Displays a message when a player is in a check mate state. 
     * The message informs the player of the situation
     */
    @Override
    public void displayMat() {
        String player = model.getCurrentPlayer().getColor().equals(Color.WHITE) ? "Blanc" : "Noir";
        System.out.println("Le joueur " + player + " est en situation d'échec et mat!");
        System.out.println("Votre roi est en danger et vous n'avez plus moyen de le défendre !");
    }
    
    /**
     * Displays a message when a player is in a stalemate state. 
     * The message informs the player of the situation
     */
    @Override
    public void displayStaleMat() {
        String player = model.getCurrentPlayer().getColor().equals(Color.WHITE) ? "Blanc" : "Noir";
        System.out.println("Le joueur " + player + " est en situation de pat !");
        System.out.println("Votre roi n'est pas en danger mais n'importe quel déplacement que vous faites vous serez en situation d'échec et mat !");
    }
    

    /**
     * Displays the board of the game. The display of the board follows the
     * conventional chess numbering. The columns are numbered from left to right
     * using lowercase letters (from a to h) and the rows from bottom to top
     * using the numbers from 1 to 8.
     */
    @Override
    public void displayBoard() {
        String rowLine = "  -------------------------";
        String player;
        System.out.println(rowLine);
        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h"};
        for (int i = 7; i > -1; i--) {

            System.out.print(i + 1 + " |");
            for (int j = 0; j < 8; j++) {
                Position pos = new Position(i, j);
                // Print a empty square, or a white pawn or a black pawn depending on the piece on this position
                player = "  "; // no piece by default
                Piece piece = model.getPiece(pos);
                if(piece instanceof Pawn) { 
                    player = model.getPiece(pos).getColor().equals(Color.WHITE) ? "PB" : "PN";
                    
                } else if(piece instanceof Knight) { 
                    player = model.getPiece(pos).getColor().equals(Color.WHITE) ? "CB" : "CN";
                    
                } else if(piece instanceof Rook) {
                    player = model.getPiece(pos).getColor().equals(Color.WHITE) ? "TB" : "TN";
                    
                } else if(piece instanceof Bishop) {
                    player = model.getPiece(pos).getColor().equals(Color.WHITE) ? "FB" : "FN";
                    
                } else if(piece instanceof King) {
                    player = model.getPiece(pos).getColor().equals(Color.WHITE) ? "*B" : "*N";
                    
                } else if(piece instanceof Queen) {
                    player = model.getPiece(pos).getColor().equals(Color.WHITE) ? "#B" : "#N";
                    
                } 
                

                System.out.print(player + "|");
            }
            System.out.println("");
            System.out.println(rowLine);
        }

        System.out.print("  ");
        for (var elem : letters) {
            System.out.print("  " + elem);
        }
        System.out.println("");

    }

    /**
     * Displays a message inviting the current player (white or black) to play.
     */
    @Override
    public void displayPlayer() {
        String player = model.getCurrentPlayer().getColor().equals(Color.WHITE) ? "Blanc" : "Noir";
        System.out.println("C'est au tour du joueur " + player + " de jouer. "
                + "Vous allez etre demandé d'entrer 2 positions (une de départ et une autre d'arrivée d'une de de vos piéces)");
    }

    /**
     * Checks if a char at a given index in a given String is a number (int).
     *
     * @param s The String containing the possible number.
     * @param index1 The index that indicates the position of the possible
     * number.
     * @return True if the char is a number, false otherwise.
     */
    private boolean checkNumber(String s, int index1) {
        return Character.isDigit(s.charAt(index1));
    }

    /**
     * Checks if a char at a given index in a given String is a lowercase
     * letter.
     *
     * @param s The String containing the possible lowercase letter.
     * @param index1 The index that indicates the position of the possible
     * lowercase letter.
     * @return True if the char is a lowercase letter, false otherwise.
     */
    private boolean checkLetter(String s, int index1) {
        return Character.isLowerCase(s.charAt(index1));
    }

    /**
     * Checks if a given row respects the boundaries of the board. (1 to 8).
     *
     * @param s String containing the row number.
     * @return True if the row respects the boudaries of the board, false
     * otherwise.
     */
    private boolean checkRow(String s) {
        return Integer.parseInt(s) > 0 && Integer.parseInt(s) < 9;
    }

    /**
     * Checks if a given column respects the boundaries of the board. (a to h).
     *
     * @param s String containing the column letter.
     * @return True if the column respects the boudaries of the board, false
     * otherwise.
     */
    private boolean checkCol(String s) {
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        return letters.contains(s);
    }

    /**
     * Reads the input (a position) from the user. The input has to respect a
     * form type: a row number (1 to 8) and a letter (a to h) column (ex: "5a",
     * "7f", "8c"). This method checks if the input from the player respects the
     * from type dictated above. The method also checks the validation of the
     * input, namely that the row and column of the position respect the
     * boundaries of the board. If the input is not valid a error messsage will
     * be displayed explaining the reason of the refuted position and the player
     * is invited to input a valid position once more.
     *
     * @return A String that contains a valid position on the board.
     */
    private String lireEntier() {
        System.out.println("Entrez une position. L'entrée doit être de la forme: '5a' -> Numéro de ligne et une lettre de colonne.");
        Scanner clavier = new Scanner(System.in);
        String userPos = "";
        boolean invalidInput = true;

        while (invalidInput) {

            userPos = clavier.next(); // Player has to input a new valid position.
            if (userPos.length() != 2) {
                displayError("L'entrée est invalide. L'entrée est censé avoir 2 caractéres! Une ligne et une colonne");
            } else if (!checkNumber(userPos, 0)) { // row number not found
                displayError("Vous n'avez pas entré de numéro de ligne!");
            } else if (!checkLetter(userPos, 1)) { // column letter not found
                displayError("Vous n'avez pas entré de lettre de colonne!");
            } else if (!checkRow(userPos.substring(0, 1))) { // row number goes beyond the boundaries of the board
                displayError("Vérifiez le numéro de ligne de votre position ! Il déborde le plateau!");
            } else if (!checkCol(userPos.substring(1, 2))) {  // column letter goes beyond the boundaries of the board
                displayError("Vérifiez la lettre de colonne de votre position ! Il déborde le plateau!");
            } else {
                invalidInput = false; // input is valid 
            }

        }

        return userPos;

    }

    /**
     * Requests from the user, a position on the board.
     *
     * @return The position that the player input.
     */
    @Override
    public Position askPosition() {

        List<String> letters = List.of("a", "b", "c", "d", "e", "f", "g", "h");

        String pos = lireEntier();
        // Converts the position on input to a logic position of the board.
        int row = Integer.parseInt(pos.substring(0, 1)) - 1;
        int col = letters.indexOf(pos.substring(1));

        return new Position(row, col);
    }

    /**
     * Displays a given error message.
     *
     * @param message The message that will be displayed as an error.
     */
    @Override
    public void displayError(String message) {
        System.out.println("****ERREUR***** -> " + message);
    }

}

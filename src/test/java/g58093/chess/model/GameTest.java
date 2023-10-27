/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g58093.chess.model;

import g58093.chess.model.pieces.Pawn;
import g58093.chess.model.pieces.Piece;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;


public class GameTest {

    private Game game;

    public GameTest() {
    }

    @BeforeEach     // Exécutée avant chaque test
    public void setUp() {
        game = new Game();
        game.start();
    }

    //********** Test start **************//
    @Test
    public void testStartCurrentPlayerIsWhite() {
        Player exp = new Player(Color.WHITE);
        Player res = game.getCurrentPlayer();

        // Checks if the current player at the start of the game is white.
        assertEquals(exp, res);
    }

    //********* Test oppositePlayer *****//
    @Test
    public void testGetOppositePlayer() {
        Player exp = new Player(Color.BLACK);
        Player res = game.getOppositePlayer();

        assertEquals(exp, res);
    }

    //**** Test isCurrentPlayerPosition ***//
    @Test
    public void testIsCurrentPlayerPositionWhite() {
        boolean res = game.isCurrentPlayerPosition(new Position(1, 5));

        assertEquals(true, res);
    }

    @Test
    public void testIsCurrentPlayerPositionBlack() {
        game.movePiecePosition(new Position(1, 5), new Position(2, 5)); // after this move black is the current player
        boolean res = game.isCurrentPlayerPosition(new Position(6, 5));

        assertEquals(true, res);
    }

    //***** Test testMovePiecePosition ***//
    @Test
    public void testMovePiecePositionWhite() {
        Position oldPos = new Position(1, 4);
        Position newPos = new Position(3, 4);
        game.movePiecePosition(oldPos, newPos);

        Piece res = game.getPiece(newPos);
        Piece exp = new Pawn(Color.WHITE);

        // Checks if the piece moved and belongs to the currentPlayer (in this case white)
        assertEquals(res, exp);

    }

    @Test
    public void testMovePiecePositionBlack() {

        // Moves the white pawn so the next turn is the black pawn.
        game.movePiecePosition(new Position(1, 4), new Position(3, 4));

        Position oldPos = new Position(6, 4);
        Position newPos = new Position(4, 4);

        game.movePiecePosition(oldPos, newPos);

        Piece res = game.getPiece(newPos);
        Piece exp = new Pawn(Color.BLACK);

        // Checks if the piece moved and belongs to the currentPlayer (in this case black)
        assertEquals(res, exp);

    }

    @Test
    public void testMovePiecePositionDeleteOldPosWhite() {

        Position oldPos = new Position(1, 4);
        Position newPos = new Position(3, 4);
        game.movePiecePosition(oldPos, newPos);

        Piece res = game.getPiece(oldPos);
        Piece exp = null;

        // Checks after the move that the old square position of the pawn (white) is empty.
        assertEquals(res, exp);

    }

    @Test
    public void testMovePiecePositionDeleteOldPosBlack() {

        // Moves the white pawn so the next turn is the black pawn.
        game.movePiecePosition(new Position(1, 4), new Position(3, 4));

        Position oldPos = new Position(6, 4);
        Position newPos = new Position(4, 4);
        game.movePiecePosition(oldPos, newPos);

        Piece res = game.getPiece(oldPos);
        Piece exp = null;

        // Checks after the move that the old square position of the pawn (black) is empty.
        assertEquals(res, exp);

    }

    @Test
    public void testExceptionMovePiece1() {
        // Position not in board exception
        assertThrows(IllegalArgumentException.class, () -> {
            game.movePiecePosition(new Position(-1, 0), new Position(-2, 3));
        });

    }

    @Test
    public void testExceptionMovePiece2() {
        // Start position does not contain a piece exception
        assertThrows(IllegalArgumentException.class, () -> {
            game.movePiecePosition(new Position(0, 0), new Position(2, 0));
        });
    }

    @Test
    public void testExceptionMovePiece3() {
        // Selected start position does not belong to current player (white in this case)
        assertThrows(IllegalArgumentException.class, () -> {
            game.movePiecePosition(new Position(6, 0), new Position(5, 0));
        });
    }

    @Test
    public void testExceptionMovePiece4() {
        // Move is not possible from start position
        assertThrows(IllegalArgumentException.class, () -> {
            game.movePiecePosition(new Position(1, 0), new Position(4, 0));
        });
    }

    /**
     * ** test getPossibleMoves ***
     */
    @Test
    public void testGetPossibleMovesWhiteStart() {
        Position pos = new Position(1, 6);

        List<Position> expected = List.of(
                new Position(2, 6),
                new Position(3, 6)
        );

        List<Position> result = game.getPossibleMoves(pos);

        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void testGetPossibleMovesBlackStart() {
        Position pos = new Position(6, 6);

        List<Position> expected = List.of(
                new Position(5, 6),
                new Position(4, 6)
        );

        List<Position> result = game.getPossibleMoves(pos);

        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void testGetPossibleMovesWhite3Moves() {
        Position pos = new Position(3, 6);

        game.movePiecePosition(new Position(1, 6), pos);
        game.movePiecePosition(new Position(6, 5), (new Position(4, 5)));
        game.movePiecePosition(new Position(1, 0), new Position(2, 0));
        game.movePiecePosition(new Position(6, 7), new Position(4, 7));
        // After the setup of moves just above, the white pawn on 'pos' can move towards N, NE and NW

        List<Position> expected = List.of(
                new Position(4, 6),
                new Position(4, 7),
                new Position(4, 5)
        );

        List<Position> result = game.getPossibleMoves(pos);

        assertEqualsIgnoringOrder(expected, result);
    }

    @Test
    public void testGetPossibleMovesBlack3Moves() {
        Position pos = new Position(4, 5);

        game.movePiecePosition(new Position(1, 6), new Position(3, 6));
        game.movePiecePosition(new Position(6, 5), pos);
        game.movePiecePosition(new Position(1, 4), new Position(3, 4));

        // After the setup of moves just above, the black pawn on 'pos' can move towards S, SE and SW
        List<Position> expected = List.of(
                new Position(3, 5),
                new Position(3, 6),
                new Position(3, 4)
        );

        List<Position> result = game.getPossibleMoves(pos);

        assertEqualsIgnoringOrder(expected, result);
    }

    /** Tests State **/
    @Test
    public void testMovePieceStatePlay() {
        Position pos = new Position(4, 5);

        game.movePiecePosition(new Position(1, 6), new Position(3, 6));
        game.movePiecePosition(new Position(6, 5), pos);
        game.movePiecePosition(new Position(1, 4), new Position(3, 4));

        assertEquals(GameState.PLAY, game.getState());
    }

    @Test
    public void testMovePieceStateCheck_Mate1() {

        game.movePiecePosition(new Position(1, 5), new Position(2, 5));
        game.movePiecePosition(new Position(6, 4), new Position(5, 4));
        game.movePiecePosition(new Position(1, 6), new Position(3, 6));
        game.movePiecePosition(new Position(7, 3), new Position(3, 7));

        assertEquals(GameState.CHECK_MATE, game.getState());
    }

    @Test
    public void testMovePieceStateCheck_Mate2() {

        game.movePiecePosition(new Position(1, 4), new Position(3, 4));
        game.movePiecePosition(new Position(6, 4), new Position(4, 4));
        game.movePiecePosition(new Position(0, 5), new Position(3, 2));
        game.movePiecePosition(new Position(7, 1), new Position(5, 2));
        game.movePiecePosition(new Position(0, 3), new Position(4, 7));
        game.movePiecePosition(new Position(7, 6), new Position(5, 5));
        game.movePiecePosition(new Position(4, 7), new Position(6, 5));

        assertEquals(GameState.CHECK_MATE, game.getState());
    }

    @Test
    public void testMovePieceStateStaleMate() {

        game.movePiecePosition(new Position(1, 4), new Position(2, 4)); 
        game.movePiecePosition(new Position(6, 0), new Position(4, 0));

        game.movePiecePosition(new Position(0, 3), new Position(4, 7));
        game.movePiecePosition(new Position(7, 0), new Position(5, 0));

        game.movePiecePosition(new Position(4, 7), new Position(4, 0));
        game.movePiecePosition(new Position(6, 7), new Position(4, 7));

        game.movePiecePosition(new Position(1, 7), new Position(3, 7));
        game.movePiecePosition(new Position(5, 0), new Position(5, 7));

        game.movePiecePosition(new Position(4, 0), new Position(6, 2));
        game.movePiecePosition(new Position(6, 5), new Position(5, 5));

        game.movePiecePosition(new Position(6, 2), new Position(6, 3));
        game.movePiecePosition(new Position(7, 4), new Position(6, 5));

        game.movePiecePosition(new Position(6, 3), new Position(6, 1));
        game.movePiecePosition(new Position(7, 3), new Position(2, 3));

        game.movePiecePosition(new Position(6, 1), new Position(7, 1));
        game.movePiecePosition(new Position(2, 3), new Position(6, 7));

        game.movePiecePosition(new Position(7, 1), new Position(7, 2));
        game.movePiecePosition(new Position(6, 5), new Position(5, 6));

        game.movePiecePosition(new Position(7, 2), new Position(5, 4));

        assertEquals(GameState.STALE_MATE, game.getState());
    }
    
    @Test
    public void testMovePieceStateCheck1() {

        game.movePiecePosition(new Position(1, 4), new Position(2, 4));
        game.movePiecePosition(new Position(6, 3), new Position(5, 3));
        game.movePiecePosition(new Position(0, 4), new Position(1, 4));
        game.movePiecePosition(new Position(7, 2), new Position(3, 6));
        
        assertEquals(GameState.CHECK, game.getState());
    }
    
    @Test 
    public void testMovePieceStateCheck2() {
        
        game.movePiecePosition(new Position(1, 4), new Position(3, 4));
        game.movePiecePosition(new Position(6, 5), new Position(4, 5));
        game.movePiecePosition(new Position(3, 4), new Position(4, 5));
        game.movePiecePosition(new Position(6, 4), new Position(5, 4));
        game.movePiecePosition(new Position(1, 0), new Position(2, 0));
        game.movePiecePosition(new Position(7, 3), new Position(4, 6));
        game.movePiecePosition(new Position(1, 7), new Position(2, 7));
        game.movePiecePosition(new Position(4, 6), new Position(2, 4));
        
        assertEquals(GameState.CHECK, game.getState());
    }

    /*
     *      Permet de tester si deux listes de positions sont identiques à l'ordre
     *      des éléments prêts. Cette méthode est appelée
     *      par les méthodes de test.
     */
    private void assertEqualsIgnoringOrder(List<Position> expected, List<Position> actual) {
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }
}

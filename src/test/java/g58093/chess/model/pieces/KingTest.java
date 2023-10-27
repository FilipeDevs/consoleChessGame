/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g58093.chess.model.pieces;

import g58093.chess.model.Board;
import g58093.chess.model.Color;
import g58093.chess.model.Position;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class KingTest {
    
    Board board;
    
    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    // The king has all the possible moves
    @Test
    public void testGetPossibleMovesAll() {
        Piece pawn = new Pawn(Color.BLACK);
        board.setPiece(pawn, new Position(4, 2));
        board.setPiece(pawn, new Position(3, 2));

        Piece king = new King(Color.WHITE);
        board.setPiece(king, new Position(3, 3));
        
        List<Position> exp = List.of(
                new Position(4, 2),
                new Position(3, 2),
                new Position(2, 2),
                new Position(4, 3),
                new Position(2, 3),
                new Position(4, 4),
                new Position(3, 4),
                new Position(2, 4)
        );
        
        List <Position> res = king.getPossibleMoves(new Position(3, 3), board);
        
        assertEqualsIgnoringOrder(res,exp);
    }
    
    // King is blocked
    @Test
    public void testGetPossibleMovesBlocked() {
        Piece pawn = new Pawn(Color.WHITE);
        board.setPiece(pawn, new Position(4, 2));
        board.setPiece(pawn, new Position(3, 2));
        board.setPiece(pawn, new Position(2, 2));
        board.setPiece(pawn, new Position(4, 3));
        board.setPiece(pawn, new Position(2, 3));
        board.setPiece(pawn, new Position(4, 4));
        board.setPiece(pawn, new Position(3, 4));
        board.setPiece(pawn, new Position(2, 4));

        Piece king = new King(Color.WHITE);
        board.setPiece(king, new Position(3, 3));
        
        List<Position> exp = List.of();
        
        List <Position> res = king.getPossibleMoves(new Position(3, 3), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    // ****Corner Tests**** //
    
    
    @Test
    public void testGetPossibleMovesTopRightCorner() {
        
        Piece king = new King(Color.WHITE);
        board.setPiece(king, new Position(7, 7));
        
        List<Position> exp = List.of(new Position(7,6), 
                new Position(6,6),
                new Position(6,7)
                );
        
        List <Position> res = king.getPossibleMoves(new Position(7, 7), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    @Test
    public void testGetPossibleMovesTopLeftCorner() {
        
        Piece king = new King(Color.WHITE);
        board.setPiece(king, new Position(7, 0));
        
        List<Position> exp = List.of(new Position(7,1), 
                new Position(6,1),
                new Position(6,0)
                );
        
        List <Position> res = king.getPossibleMoves(new Position(7, 0), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    @Test
    public void testGetPossibleMovesBottomRightCorner() {

        Piece king = new King(Color.WHITE);
        board.setPiece(king, new Position(0, 7));
        
        List<Position> exp = List.of(new Position(1,7), 
                new Position(1,6),
                new Position(0,6)
                );
        
        List <Position> res = king.getPossibleMoves(new Position(0, 7), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    @Test
    public void testGetPossibleMovesBottomLeftCorner() {

        Piece king = new King(Color.WHITE);
        board.setPiece(king, new Position(0, 0));
        
        List<Position> exp = List.of(new Position(1,0), 
                new Position(1,1),
                new Position(0,1)
                );
        
        List <Position> res = king.getPossibleMoves(new Position(0, 0), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    // ****Border tests**** //
    
    @Test
    public void testGetPossibleMovesLeftBorder() {
        
        Piece king = new King(Color.WHITE);
        board.setPiece(king, new Position(4, 0));
        
        List<Position> exp = List.of(
                new Position(5,0),
                new Position(5,1),
                new Position(4,1),
                new Position(3,1),
                new Position(3,0)
        );
        
        List <Position> res = king.getPossibleMoves(new Position(4, 0), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    @Test
    public void testGetPossibleMovesRightBorder() {
        
        Piece king = new King(Color.WHITE);
        board.setPiece(king, new Position(4, 7));
        
        List<Position> exp = List.of(
                new Position(5,6),
                new Position(5,7),
                new Position(4,6),
                new Position(3,6),
                new Position(3,7)
        );
        
        List <Position> res = king.getPossibleMoves(new Position(4, 7), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    @Test
    public void testGetPossibleMovesTopBorder() {
        

        Piece king = new King(Color.WHITE);
        board.setPiece(king, new Position(7, 3));
        
        List<Position> exp = List.of(
                new Position(7,4),
                new Position(7,2),
                new Position(6,2),
                new Position(6,3),
                new Position(6,4)
        );
        
        List <Position> res = king.getPossibleMoves(new Position(7, 3), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    @Test
    public void testGetPossibleMovesBottomBorder() {

        Piece king = new King(Color.WHITE);
        board.setPiece(king, new Position(0, 3));
        
        List<Position> exp = List.of(
                new Position(1,2),
                new Position(1,3),
                new Position(1,4),
                new Position(0,2),
                new Position(0,4)
        );
        
        List <Position> res = king.getPossibleMoves(new Position(0, 3), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    // ****General tests**** // 
    @Test
    public void testGetPossibleMoves1() {
        Piece pawn = new Pawn(Color.WHITE);
        
        board.setPiece(pawn, new Position(4, 2));
        board.setPiece(pawn, new Position(4, 3));
        board.setPiece(pawn, new Position(4, 4));
        board.setPiece(pawn, new Position(3, 2));
        
        Piece pawn2 = new Pawn(Color.BLACK);
        board.setPiece(pawn2, new Position(2, 3));

        Piece king = new King(Color.WHITE);
        board.setPiece(king, new Position(3, 3));
        
        List<Position> exp = List.of(
                new Position(2, 2),
                new Position(2, 3),
                new Position(2, 4),
                new Position(3, 4)
        );
        
        List <Position> res = king.getPossibleMoves(new Position(3, 3), board);
        
        assertEqualsIgnoringOrder(res,exp);

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

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


public class KnightTest {

    Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    // The knight has all the possible moves.
    @Test
    public void testGetPossibleMovesAll() {
        Piece pawn = new Pawn(Color.BLACK);
        board.setPiece(pawn, new Position(5, 4));
        board.setPiece(pawn, new Position(5, 2));

        Piece knight = new Knight(Color.WHITE);
        board.setPiece(knight, new Position(3, 3));
        
        List<Position> exp = List.of(
                new Position(5,4),
                new Position(5,2),
                new Position(1,2),
                new Position(1,4),
                new Position(2,1),
                new Position(4,1),
                new Position(2,5),
                new Position(4,5)
                
        );
        
        List <Position> res = knight.getPossibleMoves(new Position(3, 3), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    // Knight is blocked
    @Test
    public void testGetPossibleMovesBlocked() {
        Piece pawn = new Pawn(Color.WHITE);
        board.setPiece(pawn, new Position(5, 4));
        board.setPiece(pawn, new Position(5, 2));
        board.setPiece(pawn, new Position(1, 2));
        board.setPiece(pawn, new Position(1, 4));
        board.setPiece(pawn, new Position(2, 1));
        board.setPiece(pawn, new Position(4, 1));
        board.setPiece(pawn, new Position(2, 5));
        board.setPiece(pawn, new Position(4, 5));

        Piece knight = new Knight(Color.WHITE);
        board.setPiece(knight, new Position(3, 3));
        
        List<Position> exp = List.of();
        
        List <Position> res = knight.getPossibleMoves(new Position(3, 3), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    // ****Corner Tests**** //
    
    
    @Test
    public void testGetPossibleMovesTopRightCorner() {
        
        Piece knight = new Knight(Color.WHITE);
        board.setPiece(knight, new Position(7, 7));
        
        List<Position> exp = List.of(new Position(6,5), new Position(5,6));
        
        List <Position> res = knight.getPossibleMoves(new Position(7, 7), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    @Test
    public void testGetPossibleMovesTopLeftCorner() {
        
        Piece knight = new Knight(Color.WHITE);
        board.setPiece(knight, new Position(7, 0));
        
        List<Position> exp = List.of(new Position(6,2), (new Position(5,1)));
        
        List <Position> res = knight.getPossibleMoves(new Position(7, 0), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    @Test
    public void testGetPossibleMovesBottomRightCorner() {

        Piece knight = new Knight(Color.WHITE);
        board.setPiece(knight, new Position(0, 7));
        
        List<Position> exp = List.of(new Position(1,5), new Position(2,6));
        
        List <Position> res = knight.getPossibleMoves(new Position(0, 7), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    @Test
    public void testGetPossibleMovesBottomLeftCorner() {
        

        Piece knight = new Knight(Color.WHITE);
        board.setPiece(knight, new Position(0, 0));
        
        List<Position> exp = List.of(new Position(2,1), new Position(1,2));
        
        List <Position> res = knight.getPossibleMoves(new Position(0, 0), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    // ****Border tests**** //
    
    @Test
    public void testGetPossibleMovesLeftBorder() {
        
        Piece knight = new Knight(Color.WHITE);
        board.setPiece(knight, new Position(4, 0));
        
        List<Position> exp = List.of(
                new Position(6,1),
                new Position(5,2),
                new Position(3,2),
                new Position(2,1)
        );
        
        List <Position> res = knight.getPossibleMoves(new Position(4, 0), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    @Test
    public void testGetPossibleMovesRightBorder() {
        
        Piece knight = new Knight(Color.WHITE);
        board.setPiece(knight, new Position(4, 7));
        
        List<Position> exp = List.of(
                new Position(6,6),
                new Position(5,5),
                new Position(3,5),
                new Position(2,6)
        );
        
        List <Position> res = knight.getPossibleMoves(new Position(4, 7), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    @Test
    public void testGetPossibleMovesTopBorder() {
        

        Piece knight = new Knight(Color.WHITE);
        board.setPiece(knight, new Position(7, 3));
        
        List<Position> exp = List.of(
                new Position(6,1),
                new Position(5,2),
                new Position(5,4),
                new Position(6,5)
        );
        
        List <Position> res = knight.getPossibleMoves(new Position(7, 3), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    @Test
    public void testGetPossibleMovesBottomBorder() {

        Piece knight = new Knight(Color.WHITE);
        board.setPiece(knight, new Position(0, 3));
        
        List<Position> exp = List.of(
                new Position(2,2),
                new Position(2,4),
                new Position(1,5),
                new Position(1,1)
        );
        
        List <Position> res = knight.getPossibleMoves(new Position(0, 3), board);
        
        assertEqualsIgnoringOrder(res,exp);

    }
    
    
    // ****General tests**** // 
    @Test
    public void testGetPossibleMoves1() {
        Piece pawn = new Pawn(Color.WHITE);
        
        board.setPiece(pawn, new Position(5, 4));
        board.setPiece(pawn, new Position(5, 2));
        board.setPiece(pawn, new Position(1, 2));
        board.setPiece(pawn, new Position(1, 4));
        
        Piece pawn2 = new Pawn(Color.BLACK);
        board.setPiece(pawn2, new Position(2, 1));
        board.setPiece(pawn2, new Position(4, 1));
        board.setPiece(pawn2, new Position(2, 5));
        board.setPiece(pawn2, new Position(4, 5));

        Piece knight = new Knight(Color.WHITE);
        board.setPiece(knight, new Position(3, 3));
        
        List<Position> exp = List.of(
                new Position(2, 1),
                new Position(4, 1),
                new Position(2, 5),
                new Position(4, 5)
        );
        
        List <Position> res = knight.getPossibleMoves(new Position(3, 3), board);
        
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

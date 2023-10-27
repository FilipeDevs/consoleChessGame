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


public class PawnTest {
    
    Board board;
    
    public PawnTest() {
    }
    
    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    
    /*****WHITE PAWNS*****/
    
    @Test
    public void testGetPossibleWhiteMovesP() {
        Position position = new Position(1,1);
        Piece piece = new Pawn(Color.WHITE);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(2, 1),
                new Position(3, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
        
    @Test
    public void testGetPossibleMovesWhiteAllMoves() {
        Position position = new Position(1,1);
        Piece piece = new Pawn(Color.WHITE);
        Piece pieceB = new Pawn(Color.BLACK);
        board.setPiece(piece, position);
        board.setPiece(pieceB, new Position(2,0));
        board.setPiece(pieceB, new Position(2,2));
        
        List<Position> expected = List.of(
                new Position(2, 1),
                new Position(3, 1),
                new Position(2, 0),
                new Position(2, 2)
        );
        
        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
    @Test
    public void testGetPossibleMovesWhiteMiddle() {
        Position position = new Position(4,4);
        Piece piece = new Pawn(Color.WHITE);
        Piece pieceB = new Pawn(Color.BLACK);
        board.setPiece(piece, position);
        board.setPiece(pieceB, new Position(5,3));
        board.setPiece(pieceB, new Position(5,5));
        
        List<Position> expected = List.of(
                new Position(5, 4),
                new Position(5, 3),
                new Position(5, 5)
        );
        
        
        
        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
    @Test
    public void testGetPossibleMovesWhiteOnlyForward() {
        Position position = new Position(2,2);
        Piece piece = new Pawn(Color.WHITE);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(3, 2)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
    @Test 
    public void testGetPossibleMovesWhiteCorner1() {
        Position position = new Position(0,0);
        Piece piece = new Pawn(Color.WHITE);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(1, 0)
        );
        
        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
    @Test 
    public void testGetPossibleMovesWhiteCorner2() {
        Position position = new Position(0,7);
        Piece piece = new Pawn(Color.WHITE);
        Piece pieceB = new Pawn(Color.BLACK);
        board.setPiece(pieceB, new Position(1,6));
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(1,7),
                new Position(1,6)
        );
        
        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
    @Test 
    public void testGetPossibleMovesWhiteCorner3() {
        Position position = new Position(7,7);
        Piece piece = new Pawn(Color.WHITE);
        board.setPiece(piece, position);

        List<Position> expected = List.of();
        
        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    @Test 
    public void testGetPossibleMovesWhiteCorner4() {
        Position position = new Position(7,0);
        Piece piece = new Pawn(Color.WHITE);
        board.setPiece(piece, position);

        List<Position> expected = List.of();
        
        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
    
    /*****BLACK PAWNS******/
    
    @Test
    public void testGetPossibleMovesBlackP() {
        Position position = new Position(6,6);
        Piece piece = new Pawn(Color.BLACK);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(5, 6),
                new Position(4, 6)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
        
    @Test
    public void testGetPossibleMovesBlackAllMoves() {
        Position position = new Position(6,4);
        Piece piece = new Pawn(Color.BLACK);
        Piece pieceB = new Pawn(Color.WHITE);
        board.setPiece(piece, position);
        board.setPiece(pieceB, new Position(5,3));
        board.setPiece(pieceB, new Position(5,5));
        
        List<Position> expected = List.of(
                new Position(5, 5),
                new Position(5, 3),
                new Position(5, 4),
                new Position(4, 4)
        );
        
        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
    @Test
    public void testGetPossibleMovesBlackMiddle() {
        Position position = new Position(4,4);
        Piece piece = new Pawn(Color.BLACK);
        Piece pieceB = new Pawn(Color.WHITE);
        board.setPiece(piece, position);
        board.setPiece(pieceB, new Position(3,3));
        board.setPiece(pieceB, new Position(3,5));
        
        List<Position> expected = List.of(
                new Position(3, 3),
                new Position(3, 5),
                new Position(3, 4)
        );
   
        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
    @Test
    public void testGetPossibleMovesBlackOnlyForward() {
        Position position = new Position(4,4);
        Piece piece = new Pawn(Color.BLACK);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(3, 4)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
    @Test 
    public void testGetPossibleMovesBlackCorner1() {
        Position position = new Position(0,0);
        Piece piece = new Pawn(Color.BLACK);
        board.setPiece(piece, position);

        List<Position> expected = List.of();
        
        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
    @Test 
    public void testGetPossibleMovesBlackCorner2() {
        Position position = new Position(0,7);
        Piece piece = new Pawn(Color.BLACK);
        board.setPiece(piece, position);

        List<Position> expected = List.of();
        
        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
    @Test 
    public void testGetPossibleMovesBlackCorner3() {
        Position position = new Position(7,7);
        Piece piece = new Pawn(Color.BLACK);
        Piece pieceW = new Pawn(Color.WHITE);
        board.setPiece(piece, position);
        board.setPiece(pieceW, new Position(6,6));

        List<Position> expected = List.of(
                new Position(6,6),
                new Position(6,7)
        );
        
        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    @Test 
    public void testGetPossibleMovesBlackCorner4() {
        Position position = new Position(7,0);
        Piece piece = new Pawn(Color.BLACK);
        Piece pieceW = new Pawn(Color.WHITE);
        board.setPiece(pieceW, new Position(6,1));
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(6,1),
                new Position(6,0)
        );
        
        List<Position> positions = piece.getPossibleMoves(position, board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
    /** Extra tests **/
    @Test
    public void testPawnCapture() {
        Piece pieceB = new Pawn(Color.BLACK);
        Piece pieceW = new Pawn(Color.WHITE);
        board.setPiece(pieceB, new Position(2,7));
        board.setPiece(pieceW, new Position(1,7));

        List<Position> expected = List.of();
       
        List<Position> positions = pieceW.getPossibleMoves(new Position(1,7), board);
        
        assertEqualsIgnoringOrder(expected, positions);
    }
    
    @Test
    public void testMove(){
        
        Piece pieceB = new Pawn(Color.BLACK);
        board.setPiece(new Pawn(Color.WHITE), new Position(3, 6));
        board.setPiece(new Pawn(Color.BLACK), new Position(4, 5));
        board.setPiece(new Pawn(Color.WHITE), new Position(3, 4));
        
        List<Position> expected = List.of(
                new Position(3, 5),
                new Position(3, 6),
                new Position(3, 4)
        );
        
        List<Position> positions = pieceB.getPossibleMoves(new Position(4,5), board);
        assertEqualsIgnoringOrder(expected, positions);
        
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

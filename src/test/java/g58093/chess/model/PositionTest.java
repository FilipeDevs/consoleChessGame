/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g58093.chess.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PositionTest {
    
    public PositionTest() {
    }

    // TEST NORTH //
     @Test
    public void testNextN() {
        Position p1 = new Position(4,4);
        Position p2 = p1.next(Direction.N);
        int col = p2.getColumn();
        int rw = p2.getRow();
        int col1 = 4;
        int rw1 = 5;
        
        assertEquals(col1, col);
        assertEquals(rw1, rw);
    }
    
    @Test
    public void testNextNE() {
        Position p1 = new Position(4,4);
        Position p2 = p1.next(Direction.NE);
        int col = p2.getColumn();
        int rw = p2.getRow();
        int col1 = 5;
        int rw1 = 5;
        
        assertEquals(col1, col);
        assertEquals(rw1, rw);
    }
    
    @Test
    public void testNextlNW() {
        Position p1 = new Position(4,4);
        Position p2 = p1.next(Direction.NW);
        int col = p2.getColumn();
        int rw = p2.getRow();
        int col1 = 3;
        int rw1 = 5;
        
        assertEquals(col, col1);
        assertEquals(rw, rw1);
    }
    
    // TEST SOUTH //
    
    @Test
    public void testNextlS() {
        Position p1 = new Position(4,4);
        Position p2 = p1.next(Direction.S);
        int col = p2.getColumn();
        int rw = p2.getRow();
        int col1 = 4;
        int rw1 = 3;
        
        assertEquals(col, col1);
        assertEquals(rw, rw1);
    }
    
    @Test
    public void testNextlSE() {
        Position p1 = new Position(4,4);
        Position p2 = p1.next(Direction.SE);
        int col = p2.getColumn();
        int rw = p2.getRow();
        int col1 = 5;
        int rw1 = 3;
        
        assertEquals(col, col1);
        assertEquals(rw, rw1);
    }
    
    @Test
    public void testNextlSW() {
        Position p1 = new Position(4,4);
        Position p2 = p1.next(Direction.SW);
        int col = p2.getColumn();
        int rw = p2.getRow();
        int col1 = 3;
        int rw1 = 3;
        
        assertEquals(col, col1);
        assertEquals(rw, rw1);
    }
    
    // TEST EASTH - WEST //
    
    @Test
    public void testNextE() {
        Position p1 = new Position(4,4);
        Position p2 = p1.next(Direction.E);
        int col = p2.getColumn();
        int rw = p2.getRow();
        int col1 = 5;
        int rw1 = 4;
        
        assertEquals(col, col1);
        assertEquals(rw, rw1);
    }
    
    @Test
    public void testNextW() {
        Position p1 = new Position(4,4);
        Position p2 = p1.next(Direction.W);
        int col = p2.getColumn();
        int rw = p2.getRow();
        int col1 = 3;
        int rw1 = 4;
        
        assertEquals(col, col1);
        assertEquals(rw, rw1);
    }
    
}

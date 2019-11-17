package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PieceTest {

    @Test
    public void testGivenPieceWhenIsAdvancedThenTrue(){
        assertTrue(new Pawn(Color.WHITE).isAdvanced(new Coordinate(5,0), new Coordinate(4,1)));
        assertTrue(new Pawn(Color.BLACK).isAdvanced(new Coordinate(2,1), new Coordinate(3,2)));
    }

    @Test
    public void testGivenPieceWhenNotIsAdvancedThenFalse(){
        assertFalse(new Pawn(Color.WHITE).isAdvanced(new Coordinate(5,0), new Coordinate(6,1)));
        assertFalse(new Pawn(Color.WHITE).isAdvanced(new Coordinate(5,0), new Coordinate(5,2)));
        assertFalse(new Pawn(Color.BLACK).isAdvanced(new Coordinate(2,1), new Coordinate(2,3)));
        assertFalse(new Pawn(Color.BLACK).isAdvanced(new Coordinate(2,1), new Coordinate(1,2)));
    }

    @Test
    public void testGivenPawnWhenGetPossibleMovementsThenArrayCoordinates(){
        Piece pawn = new Pawn(Color.BLACK);
        Coordinate[] possibleMovements = new Coordinate[] { new Coordinate(1, 0), new Coordinate(1, 2), new Coordinate(2, 3) };
        assertArrayEquals(possibleMovements, pawn.getPossibleMoves(new Coordinate(0, 1)).toArray());
        possibleMovements = new Coordinate[] { new Coordinate(1, 1), new Coordinate(2, 2) };
        assertArrayEquals(possibleMovements, pawn.getPossibleMoves(new Coordinate(0, 0)).toArray());
        pawn = new Pawn(Color.WHITE);
        possibleMovements = new Coordinate[] { new Coordinate(0, 0), new Coordinate(0, 2) };
        assertArrayEquals(possibleMovements, pawn.getPossibleMoves(new Coordinate(1, 1)).toArray());
        possibleMovements = new Coordinate[] { new Coordinate(0, 1) };
        assertArrayEquals(possibleMovements, pawn.getPossibleMoves(new Coordinate(1, 0)).toArray());
    }

    @Test
    public void testGivenDraughtWhenGetPossibleMovementsThenArrayCoordinates(){
        Piece pawn = new Draught(Color.WHITE);
        Coordinate[] possibleMovements = new Coordinate[] { new Coordinate(0, 7) };
        assertArrayEquals(possibleMovements, pawn.getPossibleMoves(new Coordinate(7, 0)).toArray());
        possibleMovements = new Coordinate[] { new Coordinate(7, 7) };
        assertArrayEquals(possibleMovements, pawn.getPossibleMoves(new Coordinate(0, 0)).toArray());
        possibleMovements = new Coordinate[] { new Coordinate(0, 0) };
        assertArrayEquals(possibleMovements, pawn.getPossibleMoves(new Coordinate(7, 7)).toArray());
        possibleMovements = new Coordinate[] { new Coordinate(3, 0), new Coordinate(5, 0), new Coordinate(7, 4), new Coordinate(0, 5) };
        assertArrayEquals(possibleMovements, pawn.getPossibleMoves(new Coordinate(4, 1)).toArray());
        possibleMovements = new Coordinate[] { new Coordinate(0, 1), new Coordinate(7, 4), new Coordinate(6, 7), new Coordinate(4, 7) };
        assertArrayEquals(possibleMovements, pawn.getPossibleMoves(new Coordinate(5, 6)).toArray());
        possibleMovements = new Coordinate[] { new Coordinate(0, 0), new Coordinate(6, 0), new Coordinate(7, 7), new Coordinate(0, 6) };
        assertArrayEquals(possibleMovements, pawn.getPossibleMoves(new Coordinate(3, 3)).toArray());
        possibleMovements = new Coordinate[] { new Coordinate(7, 0) };
        assertArrayEquals(possibleMovements, pawn.getPossibleMoves(new Coordinate(0, 7)).toArray());
    }
}
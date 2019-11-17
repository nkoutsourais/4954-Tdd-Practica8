package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoordinateTest {

    @Test
    public void testGivenTwoCoordinatesWhenBettweenDiagonalThenOk() {
        assertArrayEquals(new Coordinate[] { new Coordinate(1, 1) }, new Coordinate(2, 2).betweenDiagonal(new Coordinate(0, 0)));
        assertArrayEquals(new Coordinate[] { new Coordinate(3, 1) }, new Coordinate(2, 2).betweenDiagonal(new Coordinate(4, 0)));
        assertArrayEquals(new Coordinate[] { new Coordinate(3, 3) }, new Coordinate(2, 2).betweenDiagonal(new Coordinate(4, 4)));
        assertArrayEquals(new Coordinate[] { new Coordinate(1, 3) }, new Coordinate(2, 2).betweenDiagonal(new Coordinate(0, 4)));
        assertArrayEquals(new Coordinate[] { new Coordinate(4, 3), new Coordinate(3, 4), new Coordinate(2, 5) }, new Coordinate(5, 2).betweenDiagonal(new Coordinate(1, 6)));
    }

    @Test
    public void testGivenTwoCoordinatesWhenGetDistanceThenResult() {
        assertEquals(3, new Coordinate(3, 4).diagonalDistance(new Coordinate(0, 7)));
    }

}
package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class GameWithDraughtsTest {

    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenNewDraugts() {
        BoardBuilder boardBuilder = new BoardBuilder()
                                        .addRowEmpty()
                                        .addRow("b");
        Game game = new Game(boardBuilder.getBoard());
        Coordinate origin = new Coordinate(1, 0);
        Coordinate target = new Coordinate(0, 1);
        Piece pieceOrigin = game.getPiece(origin);
        assertNotNull(pieceOrigin);
        assertTrue(pieceOrigin instanceof Pawn);
        assertEquals(Color.WHITE, pieceOrigin.getColor());
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        Piece pieceTarget = game.getPiece(target);
        assertNotNull(pieceTarget);
        assertTrue(pieceTarget instanceof Draught);
        assertEquals(Color.WHITE, pieceTarget.getColor());
    }

    @Test
    public void testGivenGameWhenPawnAtLimitAndEatingThenNewDraugts() {
        BoardBuilder boardBuilder = new BoardBuilder()
                                        .addRowEmpty()
                                        .addRow("  n")
                                        .addRow(" b");
        Game game = new Game(boardBuilder.getBoard());
        Coordinate origin = new Coordinate(2, 1);
        Coordinate target = new Coordinate(0, 3);
        Coordinate coordinateEat = new Coordinate(1, 2);
        Piece pieceOrigin = game.getPiece(origin);
        assertNotNull(pieceOrigin);
        assertTrue(pieceOrigin instanceof Pawn);
        assertEquals(Color.WHITE, pieceOrigin.getColor());
        Piece pieceEat = game.getPiece(coordinateEat);
        assertNotNull(pieceEat);
        assertTrue(pieceEat instanceof Pawn);
        assertEquals(Color.BLACK, pieceEat.getColor());
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        assertNull(game.getPiece(coordinateEat));
        Piece pieceTarget = game.getPiece(target);
        assertNotNull(pieceTarget);
        assertTrue(pieceTarget instanceof Draught);
        assertEquals(Color.WHITE, pieceTarget.getColor());
    }

    @Test
    public void testGivenGameWhenBlackPawnAtLimitThenNewDraugts() {
        BoardBuilder boardBuilder = new BoardBuilder()
                                        .addRowEmpty()
                                        .addRowEmpty()
                                        .addRowEmpty()
                                        .addRowEmpty()
                                        .addRowEmpty()
                                        .addRowEmpty()
                                        .addRow("   n")
                                        .addRowEmpty();
        Game game = new Game(boardBuilder.getBoard(), new Turn(Color.BLACK));
        Coordinate origin = new Coordinate(6, 3);
        Coordinate target = new Coordinate(7, 2);
        Piece pieceOrigin = game.getPiece(origin);
        assertNotNull(pieceOrigin);
        assertTrue(pieceOrigin instanceof Pawn);
        assertEquals(Color.BLACK, pieceOrigin.getColor());
        game.move(origin, target);
        assertNull(game.getPiece(origin));
        Piece pieceTarget = game.getPiece(target);
        assertNotNull(pieceTarget);
        assertTrue(pieceTarget instanceof Draught);
        assertEquals(Color.BLACK, pieceTarget.getColor());
    }
}
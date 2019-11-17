package es.urjccode.mastercloudapps.adcs.draughts.checkers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import es.urjccode.mastercloudapps.adcs.draughts.models.BoardBuilder;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.Turn;

public class PieceCheckerTest {

    @Test
    public void givenPieceCheckerWhenPawnsNotAdvanceThenErrorNotAdvanced() {
        BoardBuilder boardBuilder = new BoardBuilder()
                                            .addRowEmpty()
                                            .addRow("bn");
        Game game = new Game(boardBuilder.getBoard());
        PieceChecker pieceChecker = new PieceChecker(game);
        assertEquals(Error.NOT_ADVANCED, pieceChecker.check(new Coordinate(1, 0), new Coordinate(2, 1)));
        assertEquals(Error.NOT_ADVANCED, pieceChecker.check(new Coordinate(1, 1), new Coordinate(0, 2)));
    }

    @Test
    public void givenPieceCheckerWhenDraughtsGoBackThenOk() {
        BoardBuilder boardBuilder = new BoardBuilder()
                                            .addRowEmpty()
                                            .addRow(" b")
                                            .addRow("B N")
                                            .addRow(" n");
        Game game = new Game(boardBuilder.getBoard());
        PieceChecker pieceChecker = new PieceChecker(game);
        assertNull(pieceChecker.check(new Coordinate(2, 0), new Coordinate(4, 2)));
        assertNull(pieceChecker.check(new Coordinate(2, 2), new Coordinate(0, 0)));
    }

    @Test
    public void givenPieceCheckerWhenDiagonalEatEmptyThenErrorEatingEmpty() {
        BoardBuilder boardBuilder = new BoardBuilder()
                                            .addRow("n");
        Game game = new Game(boardBuilder.getBoard());
        PieceChecker pieceChecker = new PieceChecker(game);
        assertEquals(Error.EATING_EMPTY, pieceChecker.check(new Coordinate(0, 0), new Coordinate(2, 2)));
    }

    @Test
    public void givenPieceCheckerWhenPawnCheckThenBadDistance() {
        BoardBuilder boardBuilder = new BoardBuilder()
                                        .addRowEmpty()
                                        .addRow(" n");
        Game game = new Game(boardBuilder.getBoard(), new Turn(Color.BLACK));
        PieceChecker pieceChecker = new PieceChecker(game);
        assertEquals(Error.BAD_DISTANCE, pieceChecker.check(new Coordinate(1, 1), new Coordinate(4, 4)));
    }

    @Test
    public void givenPieceCheckerWhenDraughtCheckThenOk() {
        BoardBuilder boardBuilder = new BoardBuilder()
                                        .addRowEmpty()
                                        .addRow(" N")
                                        .addRowEmpty()
                                        .addRow("   b");
        Game game = new Game(boardBuilder.getBoard(), new Turn(Color.BLACK));
        PieceChecker pieceChecker = new PieceChecker(game);
        assertNull(pieceChecker.check(new Coordinate(1, 1), new Coordinate(4, 4)));
    }

    @Test
    public void givenPieceCheckerWhenDraughtCheckThenManyOpposites() {
        BoardBuilder boardBuilder = new BoardBuilder()
                                        .addRowEmpty()
                                        .addRow(" N")
                                        .addRow("  b")
                                        .addRow("   b");
        Game game = new Game(boardBuilder.getBoard(), new Turn(Color.BLACK));
        PieceChecker pieceChecker = new PieceChecker(game);
        assertEquals(Error.MANY_OPPOSITES, pieceChecker.check(new Coordinate(1, 1), new Coordinate(4, 4)));
    }

    @Test
    public void givenPieceCheckerWhenGameIsOkThenOk() {
        BoardBuilder boardBuilder = new BoardBuilder()
                                        .addRow("n");
        Game game = new Game(boardBuilder.getBoard(), new Turn(Color.BLACK));
        PieceChecker pieceChecker = new PieceChecker(game);
        assertNull(pieceChecker.check(new Coordinate(0, 0), new Coordinate(1, 1)));
    }
}
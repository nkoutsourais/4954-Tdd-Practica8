package es.urjccode.mastercloudapps.adcs.draughts.checkers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import es.urjccode.mastercloudapps.adcs.draughts.models.BoardBuilder;
import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;
import es.urjccode.mastercloudapps.adcs.draughts.models.Pawn;
import es.urjccode.mastercloudapps.adcs.draughts.models.Turn;

@RunWith(MockitoJUnitRunner.class)
public class PieceCheckerTest {

    @Mock
    Game game;

    @InjectMocks
    PieceChecker checker;

    @Test
    public void givenPieceCheckerWhenPieceNotAdvanceThenErrorNotAdvanced() {
        when(game.getPiece(any()))
            .thenReturn(new Pawn(Color.BLACK))
            .thenReturn(new Pawn(Color.WHITE));
        assertEquals(Error.NOT_ADVANCED, checker.check(new Coordinate(5, 6), new Coordinate(4, 7)));
        assertEquals(Error.NOT_ADVANCED, checker.check(new Coordinate(4, 7), new Coordinate(5, 6)));
    }

    @Test
    public void givenPieceCheckerWhenDiagonalEatEmptyThenErrorEatingEmpty() {
        when(game.getDistanceMinEat()).thenReturn(2);
        when(game.getPiece(any()))
            .thenReturn(new Pawn(Color.WHITE))
            .thenReturn(null);
        assertEquals(Error.EATING_EMPTY, checker.check(new Coordinate(5, 4), new Coordinate(3, 2)));
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
        when(game.getDistanceMinEat()).thenReturn(2);
        when(game.getPiece(any())).thenReturn(new Pawn(Color.WHITE));
        assertNull(checker.check(new Coordinate(5, 4), new Coordinate(4, 3)));
    }
}
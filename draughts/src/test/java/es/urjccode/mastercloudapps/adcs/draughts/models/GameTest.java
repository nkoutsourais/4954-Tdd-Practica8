package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GameTest {

    private Game game;

    public GameTest() {
        game = new Game();
    }

    @Test
    public void testGivenNewBoardThenGoodLocations() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < game.getDimension(); j++) {
                Coordinate coordinate = new Coordinate(i,j);
                Color color = game.getColor(coordinate);
                if (coordinate.isBlack()){
                    assertEquals(Color.BLACK, color);
                } else {
                    assertNull(color);
                }
            }
        }
        for (int i = 5; i < game.getDimension(); i++) {
            for (int j = 0; j < game.getDimension(); j++) {
                Coordinate coordinate = new Coordinate(i,j);
                Color color = game.getColor(coordinate);
                if (coordinate.isBlack()){
                    assertEquals(Color.WHITE, color);
                } else {
                    assertNull(color);
                }
            }
        }
    }

    @Test
    public void testGivenGameWhenConstructThenInitialDistribution(){
        assertEquals(Color.WHITE, game.getColor(new Coordinate(5,0)));
        assertEquals(Color.BLACK, game.getColor(new Coordinate(2,1)));
    }

    @Test
    public void testGivenGameWhenMoveEmptySquaerThenEmptySquareError() {
        Coordinate origin = new Coordinate(4, 3);
        Coordinate target = new Coordinate(3, 4);
        assertEquals(Error.EMPTY_ORIGIN, game.move(origin, target));
    }

    @Test
    public void testGivenGameWhenMoveOppositePieceThenError() {
        Coordinate origin = new Coordinate(2, 7);
        Coordinate target = new Coordinate(3, 6);
        assertEquals(Error.OPPOSITE_PIECE, game.move(origin, target));
    }

    @Test
    public void testGivenGameWhenMoveWithNotAdvancedThenError() {
        BoardBuilder boardBuilder = new BoardBuilder()
                                        .addRowEmpty()
                                        .addRow("bn");
        Game game = new Game(boardBuilder.getBoard());
        Coordinate origin = new Coordinate(1, 0);
        Coordinate target = new Coordinate(2, 1);
        assertEquals(Error.NOT_ADVANCED, game.move(origin, target));
        game = new Game(boardBuilder.getBoard(), new Turn(Color.BLACK));
        origin = new Coordinate(1, 1);
        target = new Coordinate(0, 0);
        assertEquals(Error.NOT_ADVANCED, game.move(origin, target));        
    }

    @Test
    public void testGivenGameWhenNotEmptyTargeThenError() {
        BoardBuilder boardBuilder = new BoardBuilder()
                                        .addRowEmpty()
                                        .addRowEmpty()
                                        .addRowEmpty()
                                        .addRow("n")
                                        .addRow(" b");
        Game game = new Game(boardBuilder.getBoard(), new Turn(Color.BLACK));
        Coordinate origin = new Coordinate(3, 0);
        Coordinate target = new Coordinate(4, 1);
        assertEquals(Error.NOT_EMPTY_TARGET, game.move(origin, target)); 
    }

    @Test
    public void testGivenGameWhenCorrectMovementThenOk() {
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.WHITE, this.game.getColor(target));
        origin = new Coordinate(2, 3);
        target = new Coordinate(3, 4);
        this.game.move(origin, target);
        assertNull(this.game.getColor(origin));
        assertEquals(Color.BLACK, this.game.getColor(target));
    }

    @Test
    public void testGivenGameWhenPawnMovementThenEatPiece() {
        BoardBuilder boardBuilder = new BoardBuilder()
                                        .addRowEmpty()
                                        .addRowEmpty()
                                        .addRowEmpty()
                                        .addRow("n")
                                        .addRow(" b");
        Game game = new Game(boardBuilder.getBoard(), new Turn(Color.BLACK));
        Coordinate origin = new Coordinate(3, 0);
        Coordinate target = new Coordinate(5, 2);
        assertNull(game.move(origin, target));
        assertNull(game.getColor(origin));
        assertNull(game.getColor(new Coordinate(4, 1)));
        assertEquals(Color.BLACK, game.getColor(target));
    }

    @Test
    public void testGivenGameWhenDraughtMovementThenEatPiece() {
        BoardBuilder boardBuilder = new BoardBuilder()
                                        .addRowEmpty()
                                        .addRowEmpty()
                                        .addRow("     n")
                                        .addRowEmpty()
                                        .addRowEmpty()
                                        .addRow("  b");
        Game game = new Game(boardBuilder.getBoard());
        Coordinate origin = new Coordinate(5, 2);
        Coordinate target = new Coordinate(1, 6);
        assertNull(game.move(origin, target));
        assertNull(game.getColor(origin));
        assertNull(game.getColor(new Coordinate(2, 5)));
        assertEquals(Color.WHITE, game.getColor(target));
    }

    @Test
    public void testGivenGameWhenEatEmptyPieceThenError() {
        Coordinate origin = new Coordinate(5, 4);
        Coordinate target = new Coordinate(3, 2);
        assertEquals(Error.EATING_EMPTY, game.move(origin, target));
    }
}
package es.urjccode.mastercloudapps.adcs.draughts.models;

import es.urjccode.mastercloudapps.adcs.draughts.checkers.CheckerChain;
import es.urjccode.mastercloudapps.adcs.draughts.checkers.GameValidator;

public class Game {

	private static final int DISTANCE_EAT = 2;

	private Board board;

	private Turn turn;

	private CheckerChain checker;

	public Game() {
		this.turn = new Turn();
		this.board = new BoardInitialBuilder().getBoard();
		this.checker = GameValidator.getChecker(this);
	}

	public Error move(Coordinate origin, Coordinate target) {
		assert origin != null && target != null;
		Error error = this.checker.check(origin, target);
		if(error != null)
			return error;
		this.board.move(origin, target);
		if (origin.diagonalDistance(target) == DISTANCE_EAT) {
			Coordinate between = origin.betweenDiagonal(target);
			this.board.remove(between);
		}
		if (this.board.getPiece(target).isLimit(target)){
			this.board.remove(target);
			this.board.put(target, new Draught(Color.WHITE));
		}
		this.turn.change();
		return null;
	}

	public Color getColor(Coordinate coordinate) {
		return this.board.getColor(coordinate);
	}

	@Override
	public String toString() {
		return this.board + "\n" + this.turn;
	}

	public Color getColor() {
		return this.turn.getColor();
	}

	public Piece getPiece(Coordinate coordinate) {
		return this.board.getPiece(coordinate);
	}

	public boolean isBlocked() {
		return this.board.getPieces(this.turn.getColor()).isEmpty();
	}

	public int getDimension() {
		return this.board.getDimension();
	}

	public boolean isEmpty(Coordinate coordinate) {
        return this.board.isEmpty(coordinate);
    }
}
package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.HashMap;
import java.util.List;

import es.urjccode.mastercloudapps.adcs.draughts.checkers.CheckerChain;
import es.urjccode.mastercloudapps.adcs.draughts.checkers.GameValidator;

public class Game {

	private Board board;

	private Turn turn;

	private CheckerChain checker;

	public Game() {
		this.turn = new Turn();
		this.board = new BoardInitialBuilder().getBoard();
		this.checker = GameValidator.getChecker(this);
	}

	public Game(Board board) {
		this.turn = new Turn();
		this.board = board;
		this.checker = GameValidator.getChecker(this);
	}

	public Game(Board board, Turn turn) {
		this.board = board;
		this.turn = turn;
		this.checker = GameValidator.getChecker(this);
	}

	public Error move(Coordinate origin, Coordinate target) {
		Error error = simulateMove(origin, target);
		if(error != null)
			return error;
		this.board.move(origin, target);
		checkEatAnyPiece(origin, target);
		checkTransformPiece(target);
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
		HashMap<Coordinate, Piece> pieces = this.board.getPieces(this.turn.getColor());
		if(pieces.isEmpty())
			return true;
		for (Coordinate originPiece : pieces.keySet()) {
			List<Coordinate> targetList = pieces.get(originPiece).getPossibleMoves(originPiece);
			for (Coordinate possibleTarget : targetList) {
				if(this.simulateMove(originPiece, possibleTarget) == null) {
					return false;
				}
			}
		}
		return true;
	}

	public int getDimension() {
		return this.board.getDimension();
	}

	public boolean isEmpty(Coordinate coordinate) {
        return this.board.isEmpty(coordinate);
	}
	
	void checkTransformPiece(Coordinate target) {
		Piece piece = this.board.getPiece(target);
		if (piece.isConvertible() && this.isLimit(piece, target)){
			this.board.remove(target);
			this.board.put(target, new Draught(piece.getColor()));
		}
	}
	
	boolean isLimit(Piece piece, Coordinate coordinate){
		return coordinate.getRow() == 0 && piece.getColor() == Color.WHITE ||
		coordinate.getRow()== getDimension()-1 && piece.getColor() == Color.BLACK;
	}

	void checkEatAnyPiece(Coordinate origin, Coordinate target) {
		if (origin.diagonalDistance(target) >= Piece.getDistanceEat()) {
			Coordinate[] between = origin.betweenDiagonal(target);
			for (Coordinate coordinate : between) {
				Piece piece = this.getPiece(coordinate);
				if (piece != null && piece.getColor() != this.getColor(origin)) {
					this.board.remove(coordinate);
				}
			}
		}
	}

	public Error simulateMove(Coordinate origin, Coordinate target) {
		assert origin != null && target != null;
		return this.checker.check(origin, target);
	}
}
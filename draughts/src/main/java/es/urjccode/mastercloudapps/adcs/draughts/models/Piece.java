package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.List;

public abstract class Piece {

	private Color color;

	public Piece(Color color){
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	public boolean isAdvanced(Coordinate origin, Coordinate target) {
		int difference = origin.getRow() - target.getRow();
		return color == Color.WHITE ? difference > 0 : difference < 0;
	}

	public abstract boolean isConvertible();

	public abstract List<Coordinate> getPossibleMoves(Coordinate origin);
}
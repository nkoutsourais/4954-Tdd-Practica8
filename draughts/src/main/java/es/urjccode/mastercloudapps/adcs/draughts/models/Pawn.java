package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isConvertible() {
        return true;
    }

    @Override
    public List<Coordinate> getPossibleMoves(Coordinate origin) {
        List<Coordinate> coordinates = origin.getPossibleMoves();
        for (int i = coordinates.size() - 1; i >= 0; i--) {
            if(!coordinates.get(i).isValid() || !this.isAdvanced(origin, coordinates.get(i))) {
                coordinates.remove(coordinates.get(i));
            }
        }
        return coordinates;
    }
}
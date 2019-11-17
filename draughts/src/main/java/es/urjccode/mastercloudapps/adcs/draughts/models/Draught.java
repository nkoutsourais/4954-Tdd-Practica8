package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;

public class Draught extends Piece {

    public Draught(Color color) {
        super(color);
    }

    @Override
    public boolean isConvertible() {
        return false;
    }

    @Override
    public List<Coordinate> getPossibleMoves(Coordinate origin) {
        List<Coordinate> coordinates = origin.getMaximumDiagonalPossible();
        for (int i = coordinates.size() - 1; i >= 0; i--) {
            if(!coordinates.get(i).isValid() || coordinates.get(i).equals(origin)) {
                coordinates.remove(coordinates.get(i));
            }
        }
        return coordinates;
    }
}
package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    boolean isConvertible() {
        return true;
    }
}
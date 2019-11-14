package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Draught extends Piece {

    public Draught(Color color) {
        super(color);
    }

    @Override
    boolean isConvertible() {
        return false;
    }
}
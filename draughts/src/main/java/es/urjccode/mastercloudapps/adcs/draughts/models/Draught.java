package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Draught extends Piece {

    public Draught(Color color) {
        super(color);
    }

    @Override
    public boolean isConvertible() {
        return false;
    }
}
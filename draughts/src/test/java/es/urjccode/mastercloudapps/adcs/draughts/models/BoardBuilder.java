package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.List;

class BoardBuilder {

    private List<String> rows;
    private Board board;

    BoardBuilder() {
        this.rows = new ArrayList<>();
        this.board = new Board();
    }

    BoardBuilder addRow(String row) {
        assert row != null;
        rows.add(row);
        return this;
    }

    BoardBuilder addRowEmpty() {
        rows.add("        ");
        return this;
    }

    Board getBoard() {
        for (int i = 0; i < this.rows.size(); i++) {
            for (int j = 0; j < this.rows.get(i).length(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Piece piece = PieceFactory.getPiece(this.rows.get(i).charAt(j));
                if (piece != null)
                    this.board.put(coordinate, piece);
            }
        }
        return board;
    }
}

class PieceFactory {

    static Piece getPiece(char letter) {
        switch (letter) {
        case 'b':
            return new Pawn(Color.WHITE);
        case 'B':
            return new Draught(Color.WHITE);
        case 'n':
            return new Pawn(Color.BLACK);
        case 'N':
            return new Draught(Color.BLACK);
        default:
            return null;
        }
    }
}
package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Coordinate {

    private int row;
    private int column;
    private static final int LOWER_LIMIT = 0;
    private static final int UPPER_LIMIT = 7;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isValid() {
        return Coordinate.LOWER_LIMIT <= row && row <= Coordinate.UPPER_LIMIT && Coordinate.LOWER_LIMIT <= column
                && column <= Coordinate.UPPER_LIMIT;
    }

    public boolean isDiagonal(Coordinate coordinate) {
        assert coordinate != null && coordinate.isValid();
        assert this.isValid();
        return this.row + this.column == coordinate.row + coordinate.column
                || this.row - this.column == coordinate.row - coordinate.column;
    }

    public int diagonalDistance(Coordinate coordinate) {
        assert coordinate != null && coordinate.isValid();
        assert this.isValid() && this.isDiagonal(coordinate);
        return Math.abs(this.row - coordinate.row);
    }

    public Coordinate[] betweenDiagonal(Coordinate coordinate) {
        assert coordinate != null && coordinate.isValid();
        assert this.isValid() && this.diagonalDistance(coordinate) >= 2;
        Coordinate[] coordinates = new Coordinate[this.diagonalDistance(coordinate)-1];
        for (int i = 1; i < this.diagonalDistance(coordinate); i++)
        {
            int rowShift = i;
            if (coordinate.row - this.row < 0) {
                rowShift = i * -1;
            }
            int columnShift = i;
            if (coordinate.column - this.column < 0) {
                columnShift = i * -1;
            }
            coordinates[i-1] = new Coordinate(this.row + rowShift, this.column + columnShift);
        }
        return coordinates;
    }

    public boolean isBlack() {
        assert this.isValid();
        return (this.row + this.column) % 2 != 0;
    }

    int getRow() {
        return this.row;
    }

    int getColumn() {
        return this.column;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + column;
        result = prime * result + row;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinate other = (Coordinate) obj;
        if (column != other.column)
            return false;
        if (row != other.row)
            return false;
        return true;
    }

}
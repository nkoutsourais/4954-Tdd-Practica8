package es.urjccode.mastercloudapps.adcs.draughts.checkers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Color;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Piece;
import es.urjccode.mastercloudapps.adcs.draughts.models.Game;

class PieceChecker extends CheckerChain {

    Game game;

    PieceChecker(Game game) {
        this.game = game;
    }

    @Override
    public Error check(Coordinate origin, Coordinate target) {
        Piece piece = this.game.getPiece(origin);
		if (!piece.isAdvanced(origin, target)) {
			return Error.NOT_ADVANCED;
        }
        Error error;
        if (piece.isConvertible()) {
            error = CheckConvertible(origin, target) ;
        } else {
            error = CheckNoConvertible(origin, target) ;
        }
        return error != null ? error : checkNext(origin, target);
    }

    private Error CheckConvertible(Coordinate origin, Coordinate target) {
        if (origin.diagonalDistance(target) > game.getDistanceMinEat()) {
            return Error.BAD_DISTANCE;
        }
        if (origin.diagonalDistance(target) == game.getDistanceMinEat()) {
			Coordinate[] between = origin.betweenDiagonal(target);
            if (CountPieces(between, game.getColor(origin)) == 0) {
				return Error.EATING_EMPTY;
			}
        }
        return null;
    }

    private Error CheckNoConvertible(Coordinate origin, Coordinate target) {
        if (origin.diagonalDistance(target) < game.getDistanceMinEat()) {
            return Error.BAD_DISTANCE;
        }
        Coordinate[] between = origin.betweenDiagonal(target);
        int pieces = CountPieces(between, game.getColor(origin));
        if (pieces == 0)
            return Error.EATING_EMPTY;
        else if (pieces > 1)
            return Error.MANY_OPPOSITES;
        return null;
    }

    private int CountPieces(Coordinate[] between, Color originColor) {
        int pieces = 0;
        for (Coordinate coordinate : between) {
            Piece piece = this.game.getPiece(coordinate);
            if (piece != null && piece.getColor() != originColor) {
                pieces++;
            }
        }
        return pieces;
    }
}
package es.urjccode.mastercloudapps.adcs.draughts.checkers;

import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;

public class CoordinatesChecker extends CheckerChain {

    @Override
    public Error check(Coordinate origin, Coordinate target) {
        assert origin != null && target != null;
        if (!origin.isValid() || !target.isValid()) {
            return Error.OUT_COORDINATE;
        }
        if (!origin.isDiagonal(target)) {
            return Error.NOT_DIAGONAL;
        }
        return checkNext(origin, target);
    }
}
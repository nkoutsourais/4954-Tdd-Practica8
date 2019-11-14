package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.PlayController;
import es.urjccode.mastercloudapps.adcs.draughts.models.Error;
import es.urjccode.mastercloudapps.adcs.draughts.models.Coordinate;

public class PlayView extends ConsoleView {

    CoordinateView coordinateView;

    public PlayView() {
        super();
        coordinateView = new CoordinateView();
    }

    public void interact(PlayController playController) {
        GameView gameView = new GameView();
        Error error = null;
        do {
            Coordinate[] coordinates = coordinateView.getCoordinates(playController);
            error = playController.move(coordinates[0], coordinates[1]);
            if (error != null) {
                console.writeln(ErrorView.getMessage(error));
            }
            gameView.write(playController);
        } while (error != null);
        if (playController.isBlocked()) {
            this.console.write(MessageView.FINAL.getMessage());
        }
    }
}
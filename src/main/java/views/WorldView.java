package views;

import controllers.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Greatly simplified to just ~27 lines; controllers provide the interface components and the LevelController specifically provides the actors in the first level for use in the WorldView's constructor.
 * The interface components are returned by their controllers as static references so as not to ever create more than one of each even on subsequent levels.
 * SwampController and LevelController provide all intersectable components; Levels are garbage-collected as appropriate; the same Swamp objects are always used by the SwampController.
 */
public class WorldView extends Pane {

    public WorldView() {
        getChildren().add(new ImageView(new Image("file:src/main/resources/backgrounds/final.png")));

        getChildren().add(TimeController.getTimeView());
        getChildren().add(LivesController.getLivesView());
        getChildren().add(ScoreController.getScoreView());
        getChildren().add(LevelController.getLevelView());

        getChildren().addAll(SwampController.getSwamps());
        getChildren().addAll(LevelController.getFirstLevel());
    }

}

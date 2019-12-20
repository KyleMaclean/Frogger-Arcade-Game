package controllers;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Alert;
import models.Actor;
import models.Level;
import models.Player;
import views.LevelView;

/**
 * Maintains current level and passes level information between the Level model and WorldView.
 * LevelView binds to the currentLevelValue field and fetches boss level from this controller, thus the boss level can be altered in the future purely through this controller.
 * Handles finishing the game (when the boss level is finished) by calling the HomeController.gameOver(String).
 */
public class LevelController {

    private static final SimpleIntegerProperty currentLevelValue = new SimpleIntegerProperty();
    private static final int bossLevelValue = 4;

    public static String getBossLevelValue() {
        return Integer.toString(bossLevelValue);
    }

    /**
     * @return the changing value of the current level.
     * StringBinding is bound to the view which displays the level.
     * As the level changes, so can the displayed level because of this datatype.
     */
    public static StringBinding getCurrentLevelValue() {
        return currentLevelValue.asString();
    }

    public static LevelView getLevelView() {
        return new LevelView();
    }

    public static Actor[] getFirstLevel() {
        currentLevelValue.setValue(1);
        return getCurrentLevel();
    }

    public static Actor[] getCurrentLevel() {
        return Level.getLevel(currentLevelValue.get());
    }

    /**
     * @return
     *          an array of Actor which serves as the population for the next level.
     *          null if the game is finished, which is handled gracefully by callers.
     *          JFX tidying up is only performed if Player.isInitialised() returns true.
     *          If Player is not initialised, then this method is being used by its test class.
     */
    public static Actor[] getNextLevel() {
        currentLevelValue.setValue(currentLevelValue.get() + 1);
        if (currentLevelValue.get() > bossLevelValue) {
            if (Player.getName() != null) {
                HomeController.gameOver();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game Complete");
                alert.setHeaderText(null);
                alert.setContentText("Congratulations, you have finished the game!");
                alert.showAndWait();
            }
            return null;
        }
        return getCurrentLevel();
    }

    /**
     * @param level the desired level for the game to display
     * @return an array of actors for populating the level
     * Only used for debugging to explicitly set levels (without having to play through them all)
     */
    public static Actor[] getSpecificLevel(int level) {
        currentLevelValue.setValue(level);
        return getCurrentLevel();
    }
}

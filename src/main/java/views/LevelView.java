package views;

import controllers.LevelController;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * LevelView is unaware of what the boss level is; LevelController is relied upon to provide it.
 * Thus updates to levelling are all relegated to the LevelController.
 * Current level display is bound to the LevelController's SimpleIntegerProperty to update the level as the player progresses.
 */
public class LevelView extends Pane {

    public LevelView() {
        setPrefWidth(300);
        setPrefHeight(50);
        setLayoutY(850);
        setLayoutX(300);

        Label currentLevelText = new Label("Current Level: ");
        currentLevelText.setLayoutY(0);
        currentLevelText.setLayoutX(10);

        Label bossLevelText = new Label("Boss Level: ");
        bossLevelText.setLayoutY(25);
        bossLevelText.setLayoutX(10);

        Label currentLevelNumber = new Label();
        currentLevelNumber.textProperty().bind(LevelController.getCurrentLevelValue());
        currentLevelNumber.setLayoutY(0);
        currentLevelNumber.setLayoutX(280);

        Label bossLevelNumber = new Label(LevelController.getBossLevelValue());
        bossLevelNumber.setLayoutY(25);
        bossLevelNumber.setLayoutX(280);

        getChildren().addAll(new Label[]{
                currentLevelText,
                currentLevelNumber,
                bossLevelText,
                bossLevelNumber
        });

        setStyle("-fx-background-color: gold;" +
                "-fx-font-size: 20px;" +
                "-fx-font-family: \"monospaced\";" +
                "-fx-text-fill: black;");
    }

}

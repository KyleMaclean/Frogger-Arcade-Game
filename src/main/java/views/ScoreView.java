package views;

import controllers.ScoreController;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Simply displays scores in the formatting defined in the constructor.
 * ScoreController is relied upon to
 * <ul>
 *     <li>get the binding to the current score</li>
 *     <li>get the highest score</li>
 * </ul>
 */
public class ScoreView extends Pane {

    public ScoreView() {
        setPrefWidth(600);
        setPrefHeight(100);
        setLayoutY(0);

        Label currentScoreText = new Label("Current score");
        currentScoreText.setLayoutX(0);
        currentScoreText.setLayoutY(0);
        currentScoreText.setStyle("-fx-text-fill: aliceblue; -fx-font-weight: bold;");

        Label currentScoreNumber = new Label();
        currentScoreNumber.textProperty().bind(ScoreController.getBinding());
        currentScoreNumber.setLayoutX(0);
        currentScoreNumber.setLayoutY(40);
        currentScoreNumber.setStyle("-fx-text-fill: crimson; -fx-font-weight: bolder;");


        Label highestScoreText = new Label("Highest score");
        highestScoreText.setLayoutX(300);
        highestScoreText.setLayoutY(0);
        highestScoreText.setStyle("-fx-text-fill: aliceblue; -fx-font-weight: bold;");

        Label highestScoreNumber = new Label("" + ScoreController.getHighestScore());
        highestScoreNumber.setLayoutX(300);
        highestScoreNumber.setLayoutY(40);
        highestScoreNumber.setStyle("-fx-text-fill: crimson; -fx-font-weight: bolder;");


        getChildren().addAll(new Label[]{
                currentScoreText,
                currentScoreNumber,
                highestScoreText,
                highestScoreNumber
        });

        setStyle("-fx-font-size: 30px; -fx-font-family: \"monospaced\";");

    }

}

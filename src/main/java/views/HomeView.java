package views;

import controllers.MusicController;
import controllers.ScoreController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Procedural adding of all GUI components to the first screen seen in the game.
 * MusicController is called to play the appropriate music.
 * Provides access to the text entered in the name field, which is used by the HomeController to instantiate a Player.
 */
public class HomeView extends GridPane {
    private final TextField name;

    public HomeView() {

        MusicController.playHomeMusic();

        Label highScoresLabel = new Label(ScoreController.getHighScores());
        add(highScoresLabel, 0, 0, 1, 3);
        Label infoLabel = new Label("Welcome to Frogger");

        setHalignment(infoLabel, HPos.CENTER);
        name = new TextField();
        Label playLabel = new Label("Type name or leave blank\nPress <Enter> to play");

        add(infoLabel, 1, 0, 1, 1);
        add(name, 1, 1, 1, 1);
        add(playLabel, 1, 2, 1, 1);

        setHgap(20);
        setVgap(20);
        setPadding(new Insets(10, 10, 10, 10));

        setStyle("-fx-font-size: 30px; -fx-font-family: \"monospaced\";");
        infoLabel.setStyle("-fx-font-weight: bolder;");
        highScoresLabel.setStyle("-fx-text-alignment: left;" +
                "-fx-background-color: black;" +
                "-fx-text-fill: aliceblue;" +
                "-fx-padding: 20px;");
    }

    public String getNameText() {
        return name.getText();
    }
}

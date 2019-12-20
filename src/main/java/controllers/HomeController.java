package controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import models.ScoreBoard;
import models.Player;
import views.HomeView;
import views.WorldView;

/**
 * Controls the transitions between the HomeView and WorldView by changing scenes and setting stages.
 * Its methods populate() and setStage(Stage) accomplish this.
 * Both of them are called once by Main in its start(Stage) method.
 */
public class HomeController {

    private static Stage primaryStage;
    private static Scene scene;

    /**
     * Sets the stage to be the instance of HomeView.
     * Waits for a key press event to:
     * 1. set the scene as the game world
     * 2. submit the entered name to the instance of Player
     * The triggering key press event can only be [Enter] because the cursor starts in the name field
     */
    public static void populate() {
        HomeView homeView = new HomeView();
        homeView.setOnKeyPressed(keyEvent -> {
            WorldView worldView = WorldController.getWorld();
            worldView.getChildren().add(new Player(homeView.getNameText()));
            scene = new Scene(worldView,600,900);
            primaryStage.setTitle("Frogger Arcade");
            primaryStage.setScene(scene);
        });
        primaryStage.setTitle("Frogger Home");
        primaryStage.setScene(new Scene(homeView));
        primaryStage.show();
    }

    public static void setStage(Stage primaryStage) {
        HomeController.primaryStage = primaryStage;
    }

    /**
     * Writes the score to file and calls the populate() method to bring the user back to the starting screen.
     */
    public static void gameOver() {
        ScoreBoard.write(ScoreController.getScoresPath());
        populate();
    }

}

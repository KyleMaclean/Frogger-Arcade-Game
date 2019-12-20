package controllers;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Only used to initialise the program; it calls HomeController to handle the stage setting and view population.
 * All game restarts and state saves are handled by the HomeController until program termination.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HomeController.setStage(primaryStage);
        HomeController.populate();
    }
}

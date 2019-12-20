package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Maintains an array of sprites to indicate the number of lives the Player has left.
 * This array is manipulated by the LivesController, which even specifies how many to generate.
 * A static reference of a single instance of this class is controlled by the LivesController.
 * LivesController will simply get the children of the LivesView and remove the last sprite in the array upon the Player losing a life.
 */
public class LivesView extends Pane {
    private static final Image sprite = new Image("file:src/main/resources/frogger/up.png");
    private static ImageView[] sprites;

    public LivesView(int initialLives) throws ExceptionInInitializerError {
        if (initialLives < 0 || initialLives > 10) {
            throw new ExceptionInInitializerError("Cannot display this many lives in the GUI");
        }
        setPrefWidth(600);
        setPrefHeight(50);
        setLayoutY(850);
        sprites = new ImageView[initialLives];
        for (int i = 0; i < initialLives; i++) {
            sprites[i] = new ImageView(sprite);
            sprites[i].setX(i * 50);
            getChildren().add(sprites[i]);
        }
    }

    public static ImageView[] getSprites() {
        return sprites;
    }
}

package controllers;

import views.LivesView;

/**
 * Maintains a static reference to the LivesView and provides means to update the number of lives it displays.
 * Player calls loseLife() and the controller updates the views and state accordingly.
 */
public class LivesController {

    private static LivesView livesView;
    private static int lives;

    public static int getLives() {
        return lives;
    }

    public static LivesView getLivesView(){
        lives = 5;
        livesView = new LivesView(lives);
        return livesView;
    }

    public static void loseLife() {
        livesView.getChildren().remove(LivesView.getSprites()[lives - 1]);
        lives--;
    }
}

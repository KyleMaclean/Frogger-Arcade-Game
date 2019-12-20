package models;

import javafx.scene.image.Image;

/**
 * Gives bonus points to the Player for grabbing it.
 * The same random variable in the WorldController that determines the Crocodile's appearance also determines the Bug's appearance.
 * Extends Intersectable only because it does not move, its position is fixed once it spawns.
 * Provides methods to change its image to the number of points obtained once the Player intersects it.
 */
public class Bug extends Intersectable {

    private boolean grabbed = false;
    private final Image untouchedBug = new Image("file:src/main/resources/bonus/bug.png", 47, 47, false, true);
    private final Image points = new Image("file:src/main/resources/bonus/100.png", 40, 20, true, false);

    public Bug(int x, int y) {
        super(x, y);
        setImage(untouchedBug);
    }

    public boolean isGrabbed() {
        return grabbed;
    }

    public void grab() {
        setImage(points);
        grabbed = true;
    }
}

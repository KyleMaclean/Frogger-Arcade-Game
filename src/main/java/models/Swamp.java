package models;

import javafx.scene.image.Image;

/**
 * Fairly complex possible states controlled by SwampController
 * Used as elements in an array and manipulated by the SwampController.
 * If Player intersects, the intersectable is cast into a Swamp object and the SwampController contains the decision logic about which of these methods to use.
 * SwampController deactivates all swamps every new level.
 * endanger() method is used by SwampController's flipCrocodile() method to randomly place a crocodile <b>only</b> in a deactivated Swamp.
 */
public class Swamp extends Intersectable {
    private final Image empty = new Image("file:src/main/resources/swamp/empty.png", 47, 47, false, true);
    private final Image full = new Image("file:src/main/resources/swamp/full.png", 47, 47, false, true);
    private final Image crocodile = new Image("file:src/main/resources/swamp/crocodile-head.png", 47, 47, false, true);
    private boolean activated;
    private boolean dangerous;

    public Swamp(int x) {
        super(x, 100);
        setImage(empty);
        activated = dangerous = false;
    }

    public boolean isActivated() {
        return activated;
    }

    public boolean isDangerous() {
        return dangerous;
    }

    public void activate() {
        setImage(full);
        activated = true;
    }

    public void deactivate() {
        setImage(empty);
        activated = false;
    }

    public void endanger() {
        setImage(crocodile);
        dangerous = true;
    }

    public void free() {
        setImage(empty);
        dangerous = false;
    }
}

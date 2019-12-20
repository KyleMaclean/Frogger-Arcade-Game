package models;

import javafx.scene.image.Image;

/**
 * Some actors cycle through sprites to indicate their animation.
 * Extending this class allows passing of array of state images in the constructor.
 * The animate() method is a much more efficient approach to cycling the sates than previously.
 * All animated Actors use the animate() method and can adjust its speed parameter accordingly.
 */
public abstract class Animated extends Actor {
    final Image[] states;
    int ticks = 0;

    Animated(int x, int y, int width, int height, String[] spriteLocators) {
        super(x, y);
        this.states = new Image[spriteLocators.length];
        for (int i = 0; i < states.length; i++) {
            states[i] = new Image(spriteLocators[i], width, height, false, true);
        }
    }

    /**
     * @param states some Animated Actors use only a subset of their states to animate themselves; for example, Player has different death animations according to the cause of death.
     * @param speed the rate at which the images are cycled; for example, the Snake has a higher rate because it moves faster than the Turtle.
     *
     */
    void animate(Image[] states, int speed) {
        ticks++;
        if (ticks / speed > states.length) {
            ticks = -1;
        }
        if (ticks > speed - 1 && ticks % speed == 0) {
            setImage(states[(ticks / speed) - 1]);
        }
    }
}

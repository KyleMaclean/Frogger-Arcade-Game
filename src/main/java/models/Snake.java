package models;

import java.util.Random;

/**
 * Appears once at the beginning of each level. Appearance is chosen every time the x-co-ordinate is out of bounds.
 * A random variable determines whether it appears with a 0.2% chance every pulse.
 * Animation speed is set to 10 as the movement speed has magnitude of 5; this is the same ratio of animation to movement as the slower Turtles.
 */
public class Snake extends Animated {

    private final Random random = new Random();
    private boolean appear = true;

    public Snake(int x, int y) {
        super(x, y, 112, 44, new String[]{
                "file:src/main/resources/snake/1.png",
                "file:src/main/resources/snake/2.png",
                "file:src/main/resources/snake/3.png"
        });
        speed = -5;
        setImage(states[0]);
    }

    @Override
    public void act() {
        if (appear) {
            animate(states, 10);
            move(speed, 0);
        } else {
            double chance = random.nextDouble();
            if (chance < 0.002)
                appear = true;
        }
        if (getX() < -112) {
            setX(600);
            appear = false;
        }
    }
}

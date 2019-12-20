package models;

import javafx.scene.image.Image;

import java.util.Random;

/**
 * Special case of the Turtles class in which they can dive with a 15% chance per animation cycle.
 * Utilises the ticks field inherited from the Animated abstract class to determine when a potential dive could be.
 * Depending on the return value of the isDiving() predicate when Player intersects with DivingTurtles will either support the Player or cause waterDeath (see Player class).
 */
public class DivingTurtles extends Turtles {
    private final Random dive;
    private final Image[] divingStates;
    private boolean diving = false;
    private double chance;

    public DivingTurtles(int x, int y) {
        super(x, y);
        this.divingStates = new Image[]{
                new Image("file:src/main/resources/turtles/unsafe/1.png", 127, 47, false, true),
                new Image("file:src/main/resources/turtles/unsafe/2.png", 127, 47, false, true),
                new Image("file:src/main/resources/turtles/unsafe/3.png", 127, 47, false, true)
        };
        dive = new Random();
        chance = 1;
    }

    public boolean isDiving() {
        return diving;
    }

    @Override
    public void act() {
        if (ticks == 0)
            chance = dive.nextDouble();
        if (chance < 0.15) {
            animate(divingStates, 40);
            diving = true;
        } else {
            diving = false;
            animate(states, 40);
        }
        move(speed, 0);
        if (getX() < -75)
            setX(600);
    }
}

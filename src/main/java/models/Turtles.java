package models;

/**
 * Exemplar extension of the Animated abstract class.
 * Movement is defined by the speed setting in the constructor.
 * Simple cycling through animation images.
 * Both of the above are accomplished through the act() method, called every pulse.
 */
public class Turtles extends Animated {

    public Turtles(int x, int y) {
        super(x, y, 127, 47, new String[]{
                "file:src/main/resources/turtles/safe/1.png",
                "file:src/main/resources/turtles/safe/2.png",
                "file:src/main/resources/turtles/safe/3.png"
        });
        speed = -1;
        setImage(states[2]);
    }

    @Override
    public void act() {
        animate(states, 50);
        move(speed, 0);
        if (getX() < -75)
            setX(600);
    }

}

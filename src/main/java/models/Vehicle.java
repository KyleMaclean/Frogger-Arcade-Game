package models;

import javafx.scene.image.Image;

import java.util.Random;

/**
 * Exemplar extension of the Actor abstract class; clear use of speed in the move() method, which is called by act().
 * Type parameter also determines velocity (speed magnitude and direction).
 * Random aspect of choosing the colour of certain car types.
 */
public class Vehicle extends Actor {

    /**
     * @param x passed to the superclass for positioning
     * @param y passed to the superclass for positioning
     * @param type allows natural language specification of the type of Vehicle
     */
    public Vehicle(int x, int y, String type) {
        super(x, y);
        int height = 47;
        String[] cars = new String[]{
                "file:src/main/resources/vehicles/pink-car.png",
                "file:src/main/resources/vehicles/red-car.png"
        };
        Random random = new Random();
        switch (type) {
            case "fast car":
                speed = -3;
                setImage(new Image(cars[random.nextInt(cars.length)], 67, height, false, true));
                break;
            case "slow car":
                speed = -1;
                setImage(new Image(cars[random.nextInt(cars.length)], 67, height, false, true));
                break;
            case "long truck":
                speed = 1;
                setImage(new Image("file:src/main/resources/vehicles/long-truck.png", 197, height, false, true));
                break;
            case "short truck":
                setImage(new Image("file:src/main/resources/vehicles/short-truck.png", 117, height, false, true));
                speed = 1;
                break;
        }
    }

    @Override
    public void act() {
        move(speed, 0);
        if (getX() > 600 && speed > 0)
            setX(-200);
        if (getX() < -50 && speed < 0)
            setX(600);
    }

}

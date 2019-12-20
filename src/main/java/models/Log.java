package models;

import javafx.scene.image.Image;

/**
 * Constructor handles short and long logs based on the provided width to assign the appropriate image.
 * act() method moves the log by its speed, which is determined by width.
 * Repositioning at one end of the screen in handled by act method when X co-ordinates are out of range.
 */
public class Log extends Actor {

    public Log(int x, int y, int width) {
        super(x, y);
        switch (width) {
            case 147:
                setImage(new Image("file:src/main/resources/logs/short.png", width, 47, false, true));
                this.speed = 1;
                break;
            case 297:
                setImage(new Image("file:src/main/resources/logs/long.png", width, 47, false, true));
                this.speed = -2;
                break;
        }
    }

    @Override
    public void act() {
        move(speed, 0);
        if (getX() > 600 && speed > 0)
            setX(-180);
        if (getX() < -300 && speed < 0)
            setX(700);
    }

}

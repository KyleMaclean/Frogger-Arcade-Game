package models;

/**
 * Objects which extend Actor can:
 * <ul>
 * <li> be detected as intersecting with others</li>
 * <li>provide the number of x and y pixels to move by</li>
 * <li>implement act() method which is called by the WorldController every pulse</li>
 * </ul>
 */
public abstract class Actor extends Intersectable {
    int speed;

    public Actor(int x, int y) {
        super(x, y);
    }

    void move(int dx, int dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public abstract void act();

}

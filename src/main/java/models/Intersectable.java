package models;

import controllers.WorldController;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * One of the most basic properties of an Object in this game is to Intersectable.
 * Player uses the list returned by getIntersectingObjects() every pulse to determine its response.
 * Actor and Animated extend this Intersectable class and intersectable objects are cast to their concrete classes when encountered in the list returned by IntersectingObjects.
 */
public abstract class Intersectable extends ImageView {

    Intersectable(int x, int y) {
        setX(x);
        setY(y);
    }

    List<Intersectable> getIntersectingObjects() {
        ArrayList<Intersectable> objects = new ArrayList<>();
        for (Intersectable object : WorldController.getObjects(Intersectable.class)) {
            if (object != this && object.intersects(this.getBoundsInLocal())) {
                objects.add(object);
            }
        }
        return objects;
    }
}

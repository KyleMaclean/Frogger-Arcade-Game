package controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;
import models.Actor;
import models.Bug;
import models.Intersectable;
import views.WorldView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Sets up the animation timeline, which controls animations of all models.
 * Initialises the WorldView and attaches a listener to it for user interaction.
 * Its static instance of WorldView is accessible via getWorld() so that the HomeController can reliably place it in the Scene.
 * Provides a list of the WorldView's children for the Intersectable abstract class to determine which of its concrete descendants intersect for a given frame.
 */
public class WorldController {

    /* Docs: "The target framerate is the maximum framerate at which this Animation will run, in frames per second.
    This can be used, for example, to keep particularly complex Animations from over-consuming system resources." */
    static final double targetFramerate = 0.01;

    private static final Random random = new Random();
    private static WorldView worldView;
    private static int bonusBugs = 0;

    static final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(targetFramerate), event -> {
        if (SwampController.levelComplete()) {
            SwampController.newLevel();
            worldView.getChildren().removeAll(LevelController.getCurrentLevel());
            worldView.getChildren().addAll(LevelController.getNextLevel());
        }
        List<Intersectable> intersectables = getObjects(Intersectable.class);
        for (Intersectable intersectable : intersectables) {
            if (intersectable instanceof Actor) {
                ((Actor)intersectable).act();
            }
        }
        double chance = random.nextDouble();
        if (chance < 0.1) {
            SwampController.flipCrocodile();
        } else if (bonusBugs < 2 && chance > 0.5 && chance < 0.5005) {
            //noinspection UnusedAssignment
            chance = random.nextDouble();
            int componentY = random.nextInt(10);
            int componentX = random.nextInt(5);
            int y = (componentY + 3) * 50 + 1;
            int x = componentX * 100;
            worldView.getChildren().add(new Bug(x, y));
            bonusBugs++;
            //System.out.println(bonusBugs + " bugs have appeared.");
        }
    }));

    static WorldView getWorld() {
        worldView = new WorldView();
        worldView.sceneProperty().addListener((observableValue, scene, next) -> next.setOnKeyPressed(event -> {
            List<Intersectable> intersectables = WorldController.getObjects(Intersectable.class);
            for (Intersectable intersectable : intersectables) {
                if (intersectable.getOnKeyPressed() != null) {
                    intersectable.getOnKeyPressed().handle(event);
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        return worldView;
    }

    public static List<Intersectable> getObjects(Class<Intersectable> cls) {
        ArrayList<Intersectable> objects = new ArrayList<>();
        for (Node n : worldView.getChildren()) {
            if (cls.isInstance(n)) {
                objects.add((Intersectable) n);
            }
        }
        return objects;
    }
}

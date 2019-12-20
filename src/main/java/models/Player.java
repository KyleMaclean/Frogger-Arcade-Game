package models;

import controllers.*;
import javafx.animation.PauseTransition;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;


/**
 * Many more features than initially in only ~160 lines due to separation-of-concerns.
 * All states are stored in the single field inherited from Animated and used as sub-ranges.
 * onKeyPressed events have been optimised greatly by quartering the number of checks per pulse.
 * Changing score is performed to the ScoreController.
 * Losing lives is performed by the LivesController.
 * SwampController gives information on whether swamps are dangerous, activated or deactivated.
 * Greatly refactored act method logic to determine reactions based on Player's intersectables.
 */
public class Player extends Animated {
    private static final int DELTA = 25;
    private static String name = null;
    private final PauseTransition pause = new PauseTransition(Duration.millis(120));
    private boolean noMove, carDeath, waterDeath = false;
    /**
     * Used as a semaphore to ensure that only one gameOver() call is made when all lives are lost
     */
    private static boolean finished;

    /**
     * @param name passed in from whichever class creates the Player; HomeController passes it in from the Text Field on the HomeView when instantiating Player.
     */
    public Player(String name) {
        super(300, 700, 50, 50, new String[]{
                "file:src/main/resources/frogger/up.png",            // 00
                "file:src/main/resources/frogger/left.png",          // 01
                "file:src/main/resources/frogger/down.png",          // 02
                "file:src/main/resources/frogger/right.png",         // 03
                "file:src/main/resources/frogger/jump/up.png",       // 04
                "file:src/main/resources/frogger/jump/left.png",     // 05
                "file:src/main/resources/frogger/jump/down.png",     // 06
                "file:src/main/resources/frogger/jump/right.png",    // 07
                "file:src/main/resources/death/car/1.png",           // 08
                "file:src/main/resources/death/car/2.png",           // 09
                "file:src/main/resources/death/car/3.png",           // 10
                "file:src/main/resources/death/car/4.png",           // 11
                "file:src/main/resources/death/water/1.png",         // 12
                "file:src/main/resources/death/water/2.png",         // 13
                "file:src/main/resources/death/water/3.png",         // 14
                "file:src/main/resources/death/water/4.png"          // 15
        });

        setState(0);
        if (name.isEmpty())
            Player.name = "Frog";
        else
            Player.name = name;

        MusicController.playGameMusic();

        setOnKeyPressed(keyEvent -> {
            if (!noMove) {
                if (keyEvent.getCode() == KeyCode.W) {
                    setState(4);
                    move(0, -DELTA);
                    pause.setOnFinished(actionEvent -> {
                        move(0, -DELTA);
                        setState(0);
                    });
                }
                if (keyEvent.getCode() == KeyCode.A) {
                    setState(5);
                    move(-DELTA, 0);
                    pause.setOnFinished(actionEvent -> {
                        move(-DELTA, 0);
                        setState(1);
                    });
                }
                if (keyEvent.getCode() == KeyCode.S) {
                    setState(6);
                    move(0, DELTA);
                    pause.setOnFinished(actionEvent -> {
                        move(0, DELTA);
                        setState(2);
                    });
                }
                if (keyEvent.getCode() == KeyCode.D) {
                    setState(7);
                    move(DELTA, 0);
                    pause.setOnFinished(actionEvent -> {
                        move(DELTA, 0);
                        setState(3);
                    });
                }
                pause.play();
            }

        });
        finished = false;
    }

    public static String getName() { return name; }

    private void setState(int state) {
        setImage(this.states[state]);
    }

    private void respawn(int points) {
        pause.setOnFinished(actionEvent -> {
            setX(300);
            setY(700);
            carDeath = waterDeath = noMove = false;
            ticks = 0;
            setState(0);
            ScoreController.changeScore(points);
            TimeController.reset();
        });
        pause.play();
    }

    @Override
    public void act() {
        if (ticks == -1) {
            if (LivesController.getLives() > 0) {
                respawn(-50);
            } else if (!finished) {
                finished = true;
                HomeController.gameOver();
            }
        }
        if (getY() > 700 || getY() < 0) setY(700);
        if (getX() < 0) setX(600);
        else if (getX() > 600) setX(0);

        if (carDeath) animate(Arrays.copyOfRange(states, 8, 12), 20);
        else if (waterDeath) animate(Arrays.copyOfRange(states, 12, 16), 20);
        else if (!noMove) {
            List<Intersectable> intersectables = getIntersectingObjects();
            if (intersectables.isEmpty() && getY() < 400) {
                noMove = waterDeath = true;
                LivesController.loseLife();
            }
            for (Intersectable intersectable : intersectables) {
                if (intersectable instanceof Vehicle) {
                    carDeath = noMove = true;
                    LivesController.loseLife();
                } else if (intersectable instanceof Snake) {
                    carDeath = noMove = true;
                    LivesController.loseLife();
                } else if (intersectable instanceof Bug && !((Bug) intersectable).isGrabbed()) {
                    ((Bug) intersectable).grab();
                    ScoreController.changeScore(100);
                } else if (intersectable instanceof Log) move(((Log) (intersectable)).speed, 0);
                else if (intersectable instanceof DivingTurtles) {
                    if (((DivingTurtles) intersectable).isDiving()) waterDeath = noMove = true;
                    else move(((DivingTurtles) (intersectable)).speed, 0);
                } else if (intersectable instanceof Turtles) move(((Turtles) (intersectable)).speed, 0);
                else if (intersectable instanceof Swamp) {
                    if (SwampController.dangerous(intersectable)) {
                        noMove = carDeath = true;
                        LivesController.loseLife();
                    } else if (SwampController.notActivated(intersectable)) {
                        noMove = true;
                        SwampController.activate(intersectable);
                        respawn(100);
                    }
                }
            }

        }
    }

}

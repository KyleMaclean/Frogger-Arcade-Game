package controllers;

import models.Intersectable;
import models.Swamp;

import java.util.Random;

/**
 * Maintains a list of all 5 swamps and whether they have a random crocodile in them or are activated by the player.
 * The crocodile appearing is handled by a random variable every time WorldController calls it per frame in its Timeline.
 * It provides the levelComplete() predicate to the WorldController as true when all swamps are full.
 * The newLevel() static method is called to deactivate all the swamps so the player can fill them again.
 */
public class SwampController {

    private static final Intersectable[] swamps = new Intersectable[] {
            new Swamp(35),
            new Swamp(30 + 125),
            new Swamp(30 + 123 * 2),
            new Swamp(30 + 123 * 3),
            new Swamp(30 + 123 * 4)
    };
    private static final Random random = new Random();
    private static int swampsRemaining = swamps.length;
    private static int crocodile = -1;
    private static int crocodileTicks = 0;

    public static Intersectable[] getSwamps() {
        newLevel();
        return swamps;
    }

    public static boolean levelComplete() {
        return swampsRemaining == 0;
    }

    public static void newLevel() {
        swampsRemaining = swamps.length;
        for (Intersectable swamp : swamps) {
            ((Swamp) swamp).deactivate();
        }
    }

    public static void activate(Intersectable activatedSwamp) {
        for (Intersectable swamp : swamps) {
            if (activatedSwamp == swamp) {
                ((Swamp) swamp).activate();
                swampsRemaining--;
            }
        }
    }

    public static boolean notActivated(Intersectable intersectable) {
        return !((Swamp) intersectable).isActivated();
    }

    public static boolean activated(Intersectable intersectable) {
        return ((Swamp) intersectable).isActivated();
    }

    public static boolean dangerous(Intersectable intersectable) {
        return ((Swamp) intersectable).isDangerous();
    }

    public static void flipCrocodile() {
        if (crocodileTicks == 30) {
            if (crocodile < 0) {
                int position = random.nextInt(5);
                while (activated(swamps[position])) {
                    position = random.nextInt((5));
                }
                ((Swamp) swamps[position]).endanger();
                crocodile = position;
            } else {
                ((Swamp) swamps[crocodile]).free();
                crocodile = -1;
            }
            crocodileTicks = 0;
        } else {
            crocodileTicks++;
        }
    }
}

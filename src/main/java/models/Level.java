package models;

/**
 * Simple model of each of the four levels in the game. All are stored as arrays of Actors.
 * LevelController protects against accessing an invalid level number, thus getLevel() in this class just returns null in such a scenario, as is customary in MVC architectures.
 */
public class Level {

    private static final int spacing = 50;
    private static final Actor[] level1 = new Actor[]{
            // row 12
            new Log(220, spacing * 3 + 1, 147),
            new Log(440, spacing * 3 + 1, 147),
            new Log(0, spacing * 3 + 1, 147),

            // row 11
            new DivingTurtles(600, spacing * 4 + 1),
            new DivingTurtles(400, spacing * 4 + 1),
            new DivingTurtles(200, spacing * 4 + 1),

            // row 10
            new Log(0, spacing * 5 + 1, 297),
            new Log(400, spacing * 5 + 1, 297),

            // row 09
            new Log(50, spacing * 6 + 1, 147),
            new Log(270, spacing * 6 + 1, 147),
            new Log(490, spacing * 6 + 1, 147),

            // row 08
            new Turtles(500, spacing * 7 + 1),
            new Turtles(300, spacing * 7 + 1),
            new DivingTurtles(700, spacing * 7 + 1),

            // row 07
            // median
            new Snake(600, spacing * 8 + 1),

            //row 06
            new Vehicle(500, spacing * 9 + 1, "fast car"),

            //row 05
            new Vehicle(0, spacing * 10 + 1, "long truck"),

            // row 04
            new Vehicle(250, spacing * 11 + 1, "slow car"),
            new Vehicle(400, spacing * 11 + 1, "slow car"),

            // row 03
            new Vehicle(300, spacing * 12 + 1, "short truck"),
            new Vehicle(600, spacing * 12 + 1, "short truck"),

            // row 02
            new Vehicle(500, spacing * 13 + 1, "long truck")
            // y: spacing*13

            // row 01
            // median
            // y: spacing*14
    };
    private static final Actor[] level2 = new Actor[]{
            // row 12
            new Log(220, spacing * 3 + 1, 147),
            new Log(440, spacing * 3 + 1, 147),

            // row 11
            new DivingTurtles(600, spacing * 4 + 1),
            new DivingTurtles(400, spacing * 4 + 1),

            // row 10
            new Log(0, spacing * 5 + 1, 297),
            new Log(400, spacing * 5 + 1, 297),

            // row 09
            new Log(50, spacing * 6 + 1, 147),
            new Log(270, spacing * 6 + 1, 147),
            new Log(490, spacing * 6 + 1, 147),

            // row 08
            new Turtles(300, spacing * 7 + 1),
            new DivingTurtles(700, spacing * 7 + 1),

            // row 07
            // median
            new Snake(600, spacing * 8 + 1),

            //row 06
            new Vehicle(500, spacing * 9 + 1, "fast car"),
            new Vehicle(200, spacing * 9 + 1, "fast car"),

            //row 05
            new Vehicle(0, spacing * 10 + 1, "long truck"),

            // row 04
            new Vehicle(250, spacing * 11 + 1, "slow car"),
            new Vehicle(400, spacing * 11 + 1, "slow car"),

            // row 03
            new Vehicle(300, spacing * 12 + 1, "short truck"),
            new Vehicle(600, spacing * 12 + 1, "short truck"),

            // row 02
            new Vehicle(500, spacing * 13 + 1, "slow car")
            // y: spacing*13

            // row 01
            // median
            // y: spacing*14
    };
    private static final Actor[] level3 = new Actor[]{
            // row 12
            new Log(0, spacing * 3 + 1, 147),
            new Log(220, spacing * 3 + 1, 147),

            // row 11
            new DivingTurtles(600, spacing * 4 + 1),
            new DivingTurtles(400, spacing * 4 + 1),
            new DivingTurtles(200, spacing * 4 + 1),

            // row 10
            new Log(0, spacing * 5 + 1, 297),
            new Log(400, spacing * 5 + 1, 297),

            // row 09
            new Log(50, spacing * 6 + 1, 147),
            new Log(270, spacing * 6 + 1, 147),
            new Log(490, spacing * 6 + 1, 147),

            // row 08
            new DivingTurtles(300, spacing * 7 + 1),
            new DivingTurtles(700, spacing * 7 + 1),

            // row 07
            // median
            new Snake(600, spacing * 8 + 1),

            //row 06
            new Vehicle(500, spacing * 9 + 1, "fast car"),

            //row 05

            // row 04
            new Vehicle(400, spacing * 11 + 1, "fast car"),
            new Vehicle(600, spacing * 11 + 1, "fast car"),

            // row 03
            new Vehicle(0, spacing * 12 + 1, "fast car"),
            new Vehicle(100, spacing * 12 + 1, "fast car"),

            // row 02
            new Vehicle(300, spacing * 13 + 1, "fast car")
            // y: spacing*13

            // row 01
            // median
            // y: spacing*14
    };
    private static final Actor[] level4 = new Actor[]{
            // row 12
            new Log(0, spacing * 3 + 1, 147),
            new Log(220, spacing * 3 + 1, 147),

            // row 11
            new DivingTurtles(600, spacing * 4 + 1),
            new DivingTurtles(400, spacing * 4 + 1),

            // row 10
            new Log(0, spacing * 5 + 1, 297),
            new Log(400, spacing * 5 + 1, 297),

            // row 09
            new Log(50, spacing * 6 + 1, 147),
            new Log(490, spacing * 6 + 1, 147),

            // row 08
            new DivingTurtles(300, spacing * 7 + 1),
            new DivingTurtles(700, spacing * 7 + 1),

            // row 07
            // median
            new Snake(600, spacing * 8 + 1),

            //row 06
            new Snake(600, spacing * 9 + 1),

            //row 05
            new Snake(600, spacing * 10 + 1),

            // row 04
            new Snake(600, spacing * 11 + 1),

            // row 03
            new Snake(600, spacing * 12 + 1),

            // row 02
            new Snake(600, spacing * 13 + 1),
            // y: spacing*13

            // row 01
            // median
            // y: spacing*14
    };

    public static Actor[] getLevel(int level) {
        switch (level) {
            case 1:
                return level1;
            case 2:
                return level2;
            case 3:
                return level3;
            case 4:
                return level4;
            default:
                // LevelController handles finishing the game
                return null;
        }
    }
}

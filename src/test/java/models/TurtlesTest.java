package models;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurtlesTest {

    @Test
    void movement() {

        Turtles turtles = new Turtles(0,0);
        turtles.act();
        assertEquals(turtles.getX(), -1);
        for (int i = 0; i < 75; i++)
            turtles.act();
        assertEquals(turtles.getX(), 600);
    }
}
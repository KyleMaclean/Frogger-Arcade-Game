package models;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogTest {

    @BeforeEach
    void makePanel() {
        JFXPanel panel = new JFXPanel();
    }


    /**
     * checks that a long log moves at the appropriate speed and reappears at the correct side of the screen.
     */
    @Test
    void longAct() {
        Log log = new Log(0,0,297);
        for (int i = 0; i < 150; i++)
            log.act();
        assertEquals(-300,log.getX());
        log.act();
        assertEquals(700,log.getX());
    }

    /**
     * checks that a short log moves at the appropriate speed and reappears at the correct side of the screen.
     */
    @Test
    void shortAct() {
        Log log = new Log(0,0,147);
        for (int i = 0; i < 600; i++)
            log.act();
        assertEquals(600,log.getX());
        log.act();
        assertEquals(-180,log.getX());
    }
}
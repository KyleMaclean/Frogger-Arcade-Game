package controllers;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LevelControllerTest {

    @BeforeEach
    void makePanel() {
        JFXPanel panel = new JFXPanel();
    }

    @Test
    void firstLevelAssignment() {
        LevelController.getFirstLevel();
        assertEquals("1", LevelController.getCurrentLevelValue().get());
    }

    @Test
    void levelIncrementation() {
        LevelController.getFirstLevel();
        LevelController.getNextLevel();
        assertEquals("2",LevelController.getCurrentLevelValue().get());
        LevelController.getNextLevel();
        assertEquals("3",LevelController.getCurrentLevelValue().get());
    }

    /**
     * Ensures that the level returned is null in cases where the boss level is complete.
     * This test requires Player.isInitialised() to evaluate to false, thus bypassing the JFX housekeeping which would otherwise be performed.
     */
    @Test
    void win() {
        LevelController.getSpecificLevel(Integer.parseInt(LevelController.getBossLevelValue()));
        assertNull(LevelController.getNextLevel());
    }

}
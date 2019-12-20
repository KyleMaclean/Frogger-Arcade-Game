package controllers;

import javafx.embed.swing.JFXPanel;
import models.Intersectable;
import models.Swamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SwampControllerTest {

    Intersectable[] swamps;

    @BeforeEach
    void makePanelAndPopulateArray() {
        JFXPanel panel = new JFXPanel();
        swamps = SwampController.getSwamps();
    }

    @Test
    void getSwamps() {
        assertEquals(5,swamps.length);
    }

    @Test
    void activatingAllCausesLevelComplete() {
        assertFalse(SwampController.levelComplete());
        for (Intersectable swamp : swamps) {
            SwampController.activate(swamp);
        }
        assertTrue(SwampController.levelComplete());
    }

    @Test
    void dangerousSwampsAreAccountedFor() {
        assertFalse(SwampController.dangerous(swamps[0]));
        ((Swamp) swamps[0]).endanger();
        assertTrue(SwampController.dangerous(swamps[0]));
    }

    @Test
    void newLevelHasDeactivatedSwamps() {
        SwampController.newLevel();
        for (Intersectable swamp : swamps) {
            assertTrue(SwampController.notActivated(swamp));
        }
    }
}
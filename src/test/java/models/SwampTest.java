package models;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Comprehensive testing of all permutations of logical mutations of a Swamp's states; including the legitimacy of the order of mutation.
 */
public class SwampTest {

    Swamp swamp;

    @BeforeEach
    void setUp() {
        JFXPanel panel = new JFXPanel();
        swamp = new Swamp(0);
    }

    @Test
    void activation() {
        assertFalse(swamp.isActivated());
        swamp.activate();
        assertTrue(swamp.isActivated());
    }

    @Test
    void danger() {
        assertFalse(swamp.isDangerous());
        swamp.endanger();
        assertTrue(swamp.isDangerous());
    }

    @Test
    void deactivation() {
        swamp.activate();
        assertTrue(swamp.isActivated());
        swamp.deactivate();
        assertFalse(swamp.isActivated());
    }

    @Test
    void nonDanger() {
        swamp.endanger();
        assertTrue(swamp.isDangerous());
        swamp.free();
        assertFalse(swamp.isDangerous());
    }
}
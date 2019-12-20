package models;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BugTest {

    @Test
    void grabbing() {
        Bug bug = new Bug(0,0);
        assertFalse(bug.isGrabbed());
        bug.grab();
        assertTrue(bug.isGrabbed());
    }
}
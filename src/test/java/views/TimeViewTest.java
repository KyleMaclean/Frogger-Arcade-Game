package views;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeViewTest {

    @BeforeEach
    void makePanel() {
        JFXPanel panel = new JFXPanel();
    }

    /**
     * Only reasonable course of action is to throw an exception if the ProgressBar is expected to have a negative length.
     */
    @Test
    void negativeTimeIsIllegal() {
        assertThrows(IllegalArgumentException.class, () -> new TimeView(-1));
    }

    /**
     * Cycle count should not be more than 1 if the Timeline is stopped immediately after initialisation of the object
     */
    @Test
    void cycleCountIsReasonable() {
        TimeView timeView = new TimeView(20);
        timeView.getProgressTimeline().stop();
        assertEquals(1,timeView.getProgressTimeline().getCycleCount());
    }

    @Test
    void progressBarRespondsToSetting() {
        TimeView timeView = new TimeView(20);
        timeView.getProgressBar().progressProperty().setValue(99);
        assertEquals(99,timeView.getProgressBar().progressProperty().doubleValue());
    }
}
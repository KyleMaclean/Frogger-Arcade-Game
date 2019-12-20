package controllers;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.LivesView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LivesControllerTest {

    @BeforeEach
    void makePanel() {
        JFXPanel panel = new JFXPanel();
    }

    @Test
    void defaultLivesCorrect() {
        LivesController.getLivesView();
        assertEquals(5,LivesController.getLives());
    }

    /**
     * This test was created upon finding a bug that the livesView was displaying the same number of lives on each level.
     * Thus ensuring that the object returned by the LivesController is a distinct object is the purpose of this test.
     */
    @Test
    void novelLivesViews() {
        LivesView livesView1 = LivesController.getLivesView();
        LivesView livesView2 = LivesController.getLivesView();
        assertNotEquals(livesView1,livesView2);
    }

    @Test
    void loseLife() {
        LivesController.getLivesView();
        LivesController.loseLife();
        assertEquals(4,LivesController.getLives());
    }
}
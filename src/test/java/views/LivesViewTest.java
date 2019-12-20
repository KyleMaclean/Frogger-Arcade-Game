package views;

import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LivesViewTest {

    @BeforeEach
    void makePanel() {
        JFXPanel panel = new JFXPanel();
    }

    /**
     * We cannot allow a negative number of lives to be displayed; throwing is the only reasonable action.
     * If we coped with such a scenario, lots of handling code would have to be propagated throughout the codebase.
     */
    @Test
    void invalidInitialisation() {
        assertThrows(ExceptionInInitializerError.class, () -> new LivesView(-1));
    }

    /**
     * We expect the static array of sprites to be null before an instance of the view exists.
     */
    @Test
    void staticSpritesAreNullBeforeInstantiation() {
        assertNull(LivesView.getSprites());
    }

    /**
     * We expect the initialisation with a valid number of lives to create a static array of sprites of the same length.
     */
    @Test
    void staticSpritesHaveCorrectLengthAfterInstantiation() {
        LivesView livesView = new LivesView(5);
        assertEquals(5,LivesView.getSprites().length);
    }
}
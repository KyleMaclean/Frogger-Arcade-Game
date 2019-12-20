package models;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Injecting synthetic data sources to ensure robustness when encountering edge cases in the files to read
 */
class ScoreBoardTest {

    @Test
    void testValid() throws ParseException{
        ScoreBoard.read("src/test/resources/valid-high-scores.csv");
        assertEquals(400, ScoreBoard.getHighestScore());
    }

    @Test
    void testInvalidDashes() {
        assertThrows(ParseException.class, () -> {
            ScoreBoard.read("src/test/resources/invalid-high-scores1.csv");
        });
    }

    @Test
    void testInvalidEmpty() throws ParseException {
        assertEquals("NAME\tSCORE\tDATE\n", ScoreBoard.read("src/test/resources/empty-valid-high-scores.csv"));
    }

    @Test
    void testInvalidPath() throws ParseException {
        String path = "invalidpath.csv";
        assertEquals("Reading failed: " + path + " not found", ScoreBoard.read(path));
    }

    @Test
    void testInvalidScore() {
        assertThrows(NumberFormatException.class, () -> {
           ScoreBoard.read("src/test/resources/invalid-high-scores2.csv");
        });
    }
}
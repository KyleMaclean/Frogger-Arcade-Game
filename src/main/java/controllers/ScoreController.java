package controllers;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import models.ScoreBoard;
import views.ScoreView;

import java.text.ParseException;
import java.util.Objects;

/**
 * Communicates with the ScoreBoard model to read the CSV file of high scores and provides ScoreView with updates.
 * Maintains the actual score and makes it accessible when the game is over to the HomeController to save the score.
 * Thus the HomeController can request the score on demand from the ScoreController.
 */
public class ScoreController {

    public static String getScoresPath() {
        return scoresPath;
    }

    private static final String scoresPath = "src/main/resources/high-scores.csv";
    private static IntegerProperty score = new SimpleIntegerProperty(250);

    public static ScoreView getScoreView() {
        score = new SimpleIntegerProperty(250);
        return new ScoreView();
    }

    public static String getHighestScore() {
        return String.valueOf(ScoreBoard.getHighestScore());
    }

    public static int getScore() {
        return score.get();
    }

    public static StringBinding getBinding() {
        return score.asString();
    }

    public static void changeScore(int points) {
        int newScore = score.get();
        newScore += (points < 0) ? points : points * (1 + TimeController.getProgressBarValue());
        score.set(newScore);
    }

    public static String getHighScores() {
        try {
            return ScoreBoard.read(scoresPath);
        } catch (ParseException e) {
            System.out.println("Date in high scores file at path " + scoresPath + " is incorrectly formatted.");
            return null;
        }
    }
}

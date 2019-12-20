package views;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Defines the properties of the timer bar which resets every level/life.
 * Using a Timeline, it can count down whatever number of seconds the TimeController specifies (20 in every case).
 * The remaining time when the Player reaches a swamp is reported back and used as a bonus multiplier.
 * The progress bar reference is fetched by the TimeController, which then receives its doubleValue.
 * This doubleValue is then fetched by the ScoreController to apply the correct bonus after each swamp is activated.
 */
public class TimeView extends Pane {

    private final ProgressBar progressBar;
    private final Timeline progressTimeline;

    public TimeView(int secondsToCompleteLevel) {
        if (secondsToCompleteLevel < 0)
            throw new IllegalArgumentException("Positive time required.");
        progressBar = new ProgressBar();
        progressTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 1)),
                new KeyFrame(Duration.seconds(secondsToCompleteLevel), e -> {
                    //System.out.println("No extra points");
                }, new KeyValue(progressBar.progressProperty(), 0))
        );
        progressTimeline.playFromStart();
        progressBar.setPrefWidth(600);
        progressBar.setPrefHeight(50);
        setLayoutY(800);
        getChildren().add(progressBar);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public Timeline getProgressTimeline() {
        return progressTimeline;
    }
}

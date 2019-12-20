package controllers;

import views.TimeView;

/**
 * Provides access to the time remaining of the bar which decreases per life of the player.
 * The bar's seconds to complete the level for the bonus score can be changed as a parameter here for maintainability.
 * Also maintains a static single reference to the TimeView so that it has control over how this is provided to the WorldView.
 */
public class TimeController {

    private static TimeView timeView;

    public static TimeView getTimeView() {
        timeView = new TimeView(20);
        return timeView;
    }

    public static void reset() {
        timeView.getProgressTimeline().playFromStart();
    }

    public static double getProgressBarValue() {
        return timeView.getProgressBar().progressProperty().doubleValue();
    }
}

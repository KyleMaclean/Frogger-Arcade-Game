package controllers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Provides convenient static methods for the changing of game music.
 * Controls the stopping of one track and starting of another such that no two can be playing simultaneously.
 */
public class MusicController {

    private static final MediaPlayer homeMusic = new MediaPlayer(new Media(new File("src/main/resources/music/start.wav").toURI().toString()));
    private static final MediaPlayer gameMusic = new MediaPlayer(new Media(new File("src/main/resources/music/background.wav").toURI().toString()));
    public static void playHomeMusic() {
        gameMusic.stop();
        homeMusic.setCycleCount(MediaPlayer.INDEFINITE);
        homeMusic.play();
    }

    public static void playGameMusic() {
        homeMusic.stop();
        gameMusic.setCycleCount(MediaPlayer.INDEFINITE);
        gameMusic.play();
    }

}

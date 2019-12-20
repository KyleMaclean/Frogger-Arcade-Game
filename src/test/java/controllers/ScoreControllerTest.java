package controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreControllerTest {

    @Test
    void getBinding() {
        int currentScore = ScoreController.getScore();
        int bindingValue = Integer.parseInt(ScoreController.getBinding().get());
        assertEquals(currentScore,bindingValue);
    }

    @Test
    void changeScore() {
        ScoreController.changeScore(-1);
        assertEquals(249, ScoreController.getScore());
    }
}
package org.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    @Test
    public void testScoreBoardCreation() {
        ScoreBoard sb = new ScoreBoard();
        assertNotNull(sb);
        assertEquals(0, sb.scores.size());
    }

    @Test
    public void testGameStart() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        assertEquals(1, sb.scores.size());
    }

    @Test
    public void testStartTwoGames() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        sb.startGame("France", "Italy");
        assertEquals(2, sb.scores.size());
    }

    @Test
    public void testNotPossibleToStartGameWithTheSameTeamOnBothSides() {
        ScoreBoard sb = new ScoreBoard();
        assertThrows(IllegalArgumentException.class, () -> sb.startGame("Mexico", "Mexico"));
        assertEquals(0, sb.scores.size());
    }

    @Test
    public void testNotPossibleToStartTheSameGameTwice() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        assertThrows(IllegalArgumentException.class, () -> sb.startGame("Mexico", "Germany"));
        assertEquals(1, sb.scores.size());
    }

    @Test
    public void testNotPossibleToStartGameWithATeamAlreadyPlaying() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        assertThrows(IllegalArgumentException.class, () -> sb.startGame("Germany", "Brazil"));
    }

    @Test
    public void testFinishGame() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        // just to be sure that the game is actually started
        assertEquals(1, sb.scores.size());
        sb.finishGame("Mexico", "Germany");
        assertEquals(0, sb.scores.size());
    }


    @Test
    public void testIncorrectlyFinishGame() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        assertThrows( NoSuchElementException.class, () ->sb.finishGame("Germany", "Mexico"));
        assertEquals(1, sb.scores.size());
    }

    @Test
    public void testFinishNonExistingGame() {
        ScoreBoard sb = new ScoreBoard();
        assertThrows(NoSuchElementException.class, () -> sb.finishGame("Mexico", "Germany"));
    }

    @Test
    public void testUpdateScore() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        sb.updateScore("Mexico", "Germany", 1, 0);
        assertEquals(1, sb.scores.get(0).getHomeScore());
    }

    @Test
    public void testUpdateNonExistingGame() {
        ScoreBoard sb = new ScoreBoard();
        assertThrows(NoSuchElementException.class, () -> sb.updateScore("Mexico", "Germany", 1, 0));
    }

    @Test
    public void testUpdateScoreWithNegativeValue() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        assertThrows(IllegalArgumentException.class, () -> sb.updateScore("Mexico", "Germany", -1, 0));
    }


}
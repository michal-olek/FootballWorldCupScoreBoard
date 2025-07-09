package org.scoreboard;

import org.junit.jupiter.api.Test;

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



}
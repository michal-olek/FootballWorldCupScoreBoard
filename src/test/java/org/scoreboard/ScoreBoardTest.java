package org.scoreboard;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    @Test
    public void testScoreBoardCreation() {
        ScoreBoard sb = new ScoreBoard();
        assertNotNull(sb);
        assertEquals(0, sb.getNumberOfGames());
    }

    @Test
    public void testGameStart() {
        ScoreBoard sb = new ScoreBoard();
        assertDoesNotThrow(() -> sb.startGame("Mexico", "Germany"));
        assertEquals(1, sb.getNumberOfGames());
    }

    @Test
    public void testNotPossibleToStartTheSameGameTwice() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        Exception e = assertThrows(IllegalArgumentException.class, () -> sb.startGame("Mexico", "Germany"));
        assertEquals(ScoreBoard.TEAM_ALREADY_PLAYING, e.getMessage());
        assertEquals(1, sb.getNumberOfGames());
    }

    @Test
    public void testStartTwoGames() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        sb.startGame("France", "Italy");
        assertEquals(2, sb.getNumberOfGames());
    }

    @Test
    public void testNotPossibleToStartGameWithTheSameTeamOnBothSides() {
        ScoreBoard sb = new ScoreBoard();
        Exception e = assertThrows(IllegalArgumentException.class, () -> sb.startGame("Mexico", "Mexico"));
        assertEquals(Score.TEAM_NAMES_MUST_BE_DIFFERENT, e.getMessage());
        assertEquals(0, sb.getNumberOfGames());
    }

    @Test
    public void testNotPossibleToStartGameWithATeamAlreadyPlaying() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        Exception e = assertThrows(IllegalArgumentException.class, () -> sb.startGame("Germany", "Brazil"));
        assertEquals(ScoreBoard.TEAM_ALREADY_PLAYING, e.getMessage());
        assertEquals(1, sb.getNumberOfGames());
    }

    @Test
    public void testFinishGame() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        // just to be sure that the game is actually started
        assertEquals(1, sb.getNumberOfGames());
        assertDoesNotThrow( ()->sb.finishGame("Mexico", "Germany"));
        assertEquals(0, sb.getNumberOfGames());
    }


    @Test
    public void testIncorrectlyFinishGame() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        Exception e = assertThrows( NoSuchElementException.class, () ->sb.finishGame("Germany", "Mexico"));
        assertEquals(ScoreBoard.GAME_NOT_STARTED, e.getMessage());
        assertEquals(1, sb.getNumberOfGames());
    }

    @Test
    public void testFinishNonExistingGame() {
        ScoreBoard sb = new ScoreBoard();
        Exception e = assertThrows(NoSuchElementException.class, () -> sb.finishGame("Mexico", "Germany"));
        assertEquals(ScoreBoard.GAME_NOT_STARTED, e.getMessage());
    }


    @Test
    public void testUpdateNonExistingGame() {
        ScoreBoard sb = new ScoreBoard();
        Exception e = assertThrows(NoSuchElementException.class, () -> sb.updateScore("Mexico", "Germany", 1, 0));
        assertEquals(ScoreBoard.GAME_NOT_STARTED, e.getMessage());
    }

    @Test
    public void testUpdateScoreWithNegativeValue() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        Exception e = assertThrows(IllegalArgumentException.class, () -> sb.updateScore("Mexico", "Germany", -1, 0));
        assertEquals(ScoreBoard.SCORE_NOT_NON_NEGATIVE, e.getMessage());
    }

    @Test
    public void testGetSummary() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Austria", "Greece");
        sb.updateScore("Austria", "Greece", 0, 1);
        sb.startGame("Spain", "Argentina");
        sb.updateScore("Spain", "Argentina", 5, 0);
        sb.startGame("Mexico", "Germany");
        sb.updateScore("Mexico", "Germany", 1, 0);
        sb.startGame("Brazil", "USA");
        sb.updateScore("Brazil", "USA", 3, 2);
        sb.startGame("Poland", "France");
        sb.updateScore("Poland", "France", 4, 1);

        List<Score> scores = sb.getSummary();

        assertNotNull(scores);
        assertEquals(5, scores.size());
        assertEquals("Poland", scores.get(0).getHomeTeam());
        assertEquals("Brazil", scores.get(1).getHomeTeam());
        assertEquals("Spain", scores.get(2).getHomeTeam());
        assertEquals("Mexico", scores.get(3).getHomeTeam());
        assertEquals("Austria", scores.get(4).getHomeTeam());
    }


    @Test void testGetSummaryFromExample() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Canada");
        sb.updateScore("Mexico", "Canada", 0, 5);
        sb.startGame("Spain", "Brazil");
        sb.updateScore("Spain", "Brazil", 10, 2);
        sb.startGame("Germany", "France");
        sb.updateScore("Germany", "France", 2, 2);
        sb.startGame("Uruguay", "Italy");
        sb.updateScore("Uruguay", "Italy", 6, 6);
        sb.startGame("Argentina", "Australia");
        sb.updateScore("Argentina", "Australia", 3, 1);

        List<Score> scores = sb.getSummary();

        assertNotNull(scores);
        assertEquals(5, scores.size());
        assertEquals("Uruguay", scores.get(0).getHomeTeam());
        assertEquals("Spain", scores.get(1).getHomeTeam());
        assertEquals("Mexico", scores.get(2).getHomeTeam());
        assertEquals("Argentina", scores.get(3).getHomeTeam());
        assertEquals("Germany", scores.get(4).getHomeTeam());
    }

    @Test
    public void testSummaryWithNoGames() {
        ScoreBoard sb = new ScoreBoard();
        List<Score> scores = sb.getSummary();
        assertNotNull(scores);
        assertEquals(0, scores.size());
    }

    @Test
    public void testUpdateScore() {
        ScoreBoard sb = new ScoreBoard();
        sb.startGame("Mexico", "Germany");
        assertDoesNotThrow( () -> sb.updateScore("Mexico", "Germany", 1, 0));

        List<Score> scores = sb.getSummary();
        assertNotNull(scores);
        assertEquals(1, scores.size());
        assertEquals(1, scores.get(0).getHomeScore());
        assertEquals(0, scores.get(0).getAwayScore());
    }




}
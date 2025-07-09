package org.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    public void testScoreCreation() {
        Score s = new Score("Mexico", "Germany");
        assertNotNull(s);
        assertEquals("Mexico", s.getHomeTeam());
        assertEquals("Germany", s.getAwayTeam());
        assertEquals(0, s.getHomeScore());
        assertEquals(0, s.getAwayScore());
    }

    @Test
    public void testNotPossibleToCreateScoreWithSameTeamOnBothSides() {
        assertThrows(IllegalArgumentException.class, () -> new Score("Mexico", "Mexico"));
    }


}
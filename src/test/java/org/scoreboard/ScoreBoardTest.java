package org.scoreboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    @Test
    public void testScoreBoardCreation() {
        ScoreBoard sb = new ScoreBoard();
        assertNotNull(sb);
    }



}
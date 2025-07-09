package org.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {

    List<Score> scores;

    public ScoreBoard() {
        scores = new ArrayList<>();
    }

    public void startGame(String homeTeam, String awayTeam) {
        Score score = new Score(homeTeam, awayTeam);
        if(checkIfTeamAlreadyPlaying(score)) {
            throw new IllegalArgumentException("One of the teams is already playing");
        }
        scores.add(score);
    }

    private boolean checkIfTeamAlreadyPlaying(Score s) {
        return scores.stream().anyMatch(score -> score.hasAnyOfTheTeamsIn(s));
    }

    public void finishGame(String homeTeam, String awayTeam) {
        boolean wasGameFinished = scores.removeIf(score -> score.getHomeTeam().equals(homeTeam) && score.getAwayTeam().equals(awayTeam));
        if(!wasGameFinished) {
            throw new IllegalArgumentException("The game was not started");
        }
    }

}
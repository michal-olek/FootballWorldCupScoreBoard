package org.scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
            throw new NoSuchElementException("The game was not started");
        }
    }


    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {

        if(homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("The score must be a non negative number");
        }

        Score score = scores.stream()
                .filter(s -> s.getHomeTeam().equals(homeTeam) && s.getAwayTeam().equals(awayTeam))
                .findFirst().orElseThrow( () -> new NoSuchElementException("There is no such a game"));

        score.setHomeScore(homeScore);
        score.setAwayScore(awayScore);
    }



}
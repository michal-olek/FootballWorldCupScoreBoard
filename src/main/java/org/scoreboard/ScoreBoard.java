package org.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;


public class ScoreBoard {

    public static String TEAM_ALREADY_PLAYING = "One of the teams is already playing";
    public static String GAME_NOT_STARTED = "The game was not started";
    public static String SCORE_NOT_NON_NEGATIVE = "The score must be a non negative number";

    private final List<Score> scores;

    public ScoreBoard() {
        scores = new ArrayList<>();
    }

    public void startGame(String homeTeam, String awayTeam) {

        Score score = new Score(homeTeam, awayTeam);
        if(checkIfTeamAlreadyPlaying(score)) {
            throw new IllegalArgumentException(TEAM_ALREADY_PLAYING);
        }
        scores.add(score);
    }

    private boolean checkIfTeamAlreadyPlaying(Score s) {
        return scores.stream().anyMatch(score -> score.hasAnyOfTheTeamsIn(s));
    }

    public void finishGame(String homeTeam, String awayTeam) {
        boolean wasGameFinished = scores.removeIf(score -> score.getHomeTeam().equals(homeTeam) && score.getAwayTeam().equals(awayTeam));
        if(!wasGameFinished) {
            throw new NoSuchElementException(GAME_NOT_STARTED);
        }
    }


    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {

        if(homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException(SCORE_NOT_NON_NEGATIVE);
        }

        Score score = scores.stream()
                .filter(s -> s.getHomeTeam().equals(homeTeam) && s.getAwayTeam().equals(awayTeam))
                .findFirst().orElseThrow( () -> new NoSuchElementException(GAME_NOT_STARTED));

        score.setHomeScore(homeScore);
        score.setAwayScore(awayScore);
    }

    public List<Score> getSummary() {

        record IndexedScore(int index, Score score) {}
        var indexedStream = IntStream.range(0, scores.size());
        
        return  indexedStream.mapToObj(index -> new IndexedScore(index, scores.get(index)))
                .sorted(Comparator
                        .comparingInt((IndexedScore is1) -> (is1.score.getHomeScore()+is1.score.getAwayScore())).reversed()
                        .thenComparing(Comparator.comparingInt(IndexedScore::index).reversed())
                )
                .map(IndexedScore::score)
                .toList();

    }

    public int getNumberOfGames() {
        return scores.size();
    }

}






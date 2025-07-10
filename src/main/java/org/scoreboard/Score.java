package org.scoreboard;

public class Score {

    public static final String TEAM_NAME_NOT_PROVIDED = "Team name not provided";
    public static final String TEAM_NAMES_MUST_BE_DIFFERENT = "Team names must be different";

    private final String homeTeam;
    private final String awayTeam;

    private int homeScore;
    private int awayScore;

    public Score(String homeTeam, String awayTeam) {

        if(homeTeam.isBlank() || awayTeam.isBlank()) {
            throw new IllegalArgumentException(TEAM_NAME_NOT_PROVIDED);
        }

        if(homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException(TEAM_NAMES_MUST_BE_DIFFERENT);
        }

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.setHomeScore(0);
        this.setAwayScore(0);
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }



    public boolean hasAnyOfTheTeamsIn(Score s) {
        return this.homeTeam.equals(s.getHomeTeam())
                || this.homeTeam.equals(s.getAwayTeam())
                || this.awayTeam.equals(s.getAwayTeam())
                || this.awayTeam.equals(s.getHomeTeam());
    }

}

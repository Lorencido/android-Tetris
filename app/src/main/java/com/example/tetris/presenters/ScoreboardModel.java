package com.example.tetris.presenters;

public class ScoreboardModel {
    private String playerName;
    private int score;

    public ScoreboardModel(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }
}

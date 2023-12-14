package com.example.tetris.presenters;

public interface GameView {
    void init(int gameSize, int gameSize2);
    void draw(Point[][] points);
    void setScore(int score);
    void setStatus(GameStatus status);
}

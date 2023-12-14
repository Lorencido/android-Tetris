package com.example.tetris.presenters;

public interface GameModel {
    int FPS = 60;
    int SPEED = 25;
    void init();
    int getGameSize();
    int getGameSize2();
    void newGame();
    void startGame(Observer<Point[][]> onGameDrawnListener);
    void pauseGame();
    void turn(GameTurn turn);
    void setGameOverListener(CompletableObserver onGameOverListener);
    void setScoreUpdatedListener(Observer<Integer> onScoreUpdatedListener);
}

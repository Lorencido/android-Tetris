package com.example.tetris.presenters;

import com.example.tetris.LeaderboardActivity;
import com.example.tetris.models.TetrisGameModel;

public class GamePresenter {
    private GameView mGameView;
    private GameModel mGameModel;
    private GameStatus mStatus;
    private LeaderboardActivity mLeaderboard;
    private TetrisGameModel mTetrisModel;

    public void setGameView(GameView gameView) {
        mGameView = gameView;
    }

    public void setGameModel(GameModel gameModel) {
        mGameModel = gameModel;
    }

    public void init() {
        mGameModel.init();
        mGameView.init(mGameModel.getGameSize(), mGameModel.getGameSize2());
        mGameModel.setGameOverListener(() -> setStatus(GameStatus.OVER));
        mGameModel.setScoreUpdatedListener(mGameView::setScore);
        setStatus(GameStatus.START);
    }

    public void turn(GameTurn turn) {
        mGameModel.turn(turn);
    }

    public void changeStatus() {
        if (mStatus == GameStatus.PLAYING) {
            pauseGame();
        } else {
            startGame();
        }
    }

    private void pauseGame() {
        setStatus(GameStatus.PAUSED);
        mGameModel.pauseGame();
    }

    private void startGame() {
        setStatus(GameStatus.PLAYING);
        mGameModel.startGame(mGameView::draw);
    }

    private void setStatus(GameStatus status) {
        if (mStatus == GameStatus.OVER) {
            mLeaderboard.saveScoresToSharedPreferences(mTetrisModel.lScores);
            mGameModel.newGame();
        } else if(status == GameStatus.START) {
            mGameModel.newGame();
        }
        mStatus = status;
        mGameView.setStatus(status);
    }
}

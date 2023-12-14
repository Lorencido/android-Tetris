package com.example.tetris.views;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tetris.presenters.GameStatus;
import com.example.tetris.presenters.GameView;
import com.example.tetris.presenters.Point;

public class GameViewRunnable implements GameView {
    private final GameWindow mGameFrame;
    private final TextView mGameScoreText;
    private final TextView mGameStatusText;
    private final Button mGameCtlBtn;
    GameViewRunnable(GameWindow gameFrame, TextView gameScoreText, TextView gameStatusText, Button gameCtlBtn) {
        mGameFrame = gameFrame;
        mGameScoreText = gameScoreText;
        mGameStatusText = gameStatusText;
        mGameCtlBtn = gameCtlBtn;
    }

    @Override
    public void init(int gameSize, int gameSize2) {
        mGameFrame.init(gameSize, gameSize2);
    }

    @Override
    public void draw(Point[][] points) {
        mGameFrame.setPoints(points);
        mGameFrame.invalidate();
    }

    @Override
    public void setScore(int score) {
        mGameScoreText.setText("Score: " + score);
    }

    @Override
    public void setStatus(GameStatus status) {
        mGameStatusText.setText(status.getValue());
        mGameStatusText.setVisibility(status == GameStatus.PLAYING ? View.INVISIBLE : View.VISIBLE);
        mGameCtlBtn.setText(status == GameStatus.PLAYING ? "Pause" : "Start");
    }
}

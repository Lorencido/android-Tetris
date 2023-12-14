package com.example.tetris.views;

import android.widget.Button;
import android.widget.TextView;

import com.example.tetris.presenters.GameView;

public class GameViewInit {
    private GameViewInit() {
    }

    public static GameView newGameView(GameWindow gameFrame, TextView gameScoreText, TextView gameStatusText, Button gameCtlBtn) {
        return new GameViewRunnable(gameFrame, gameScoreText, gameStatusText, gameCtlBtn);
    }
}

package com.example.tetris.models;

import com.example.tetris.presenters.GameModel;

public class GameModelInit {
    private GameModelInit() {
    }

    public static GameModel newGameModel(GameType gameType) {
        switch (gameType) {
            case TETRIS:
                return new TetrisGameModel();
            default:
                return null;
        }
    }
}

package com.example.tetris.presenters;

import com.example.tetris.models.TetrisGameModel;

import java.util.Random;

public enum BrickColor {
    RED(0), GREEN(1), BLUE(2), PURPLE(3);
    final int value;

    BrickColor(int value) {
        this.value = value;
    }

    static BrickColor fromValue(int value) {
        switch (value) {
            case 1:
                return GREEN;
            case 2:
                return BLUE;
            case 3:
                return PURPLE;
            case 0:
                return RED;
            default:
                return RED;
        }
    }

    public static BrickColor random() {
        Random random = new Random();
        return fromValue(random.nextInt(4));
    }
}

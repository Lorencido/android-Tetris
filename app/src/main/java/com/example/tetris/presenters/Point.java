package com.example.tetris.presenters;

public class Point {
    public final int x, y;
    public boolean isFallingPoint;
    public PointType type;

    public BrickColor color;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = PointType.EMPTY;
        this.isFallingPoint = false;
        this.color = BrickColor.RED;
    }

    public Point(int x, int y, PointType type, boolean isFallingPoint, BrickColor color) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.isFallingPoint = isFallingPoint;
        this.color = color;
    }

    public boolean isStablePoint() {
        return !isFallingPoint && type == PointType.BOX;
    }
}

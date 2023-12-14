package com.example.tetris.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.tetris.models.TetrisGameModel;
import com.example.tetris.presenters.BrickColor;
import com.example.tetris.presenters.Point;

public class GameWindow extends View {
    public GameWindow(Context context) {
        super(context);
    }

    public GameWindow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GameWindow(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameWindow(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private Point[][] mPoints;
    private int mBoxSize;
    private int mBoxPadding;
    private int mGameSize;
    private int mGameSize2;

    private final Paint mPaint = new Paint();

    public void init(int gameSize, int gameSize2) {
        mGameSize = gameSize;
        mGameSize2 = gameSize2;
        getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            mBoxSize = Math.min(getWidth(), getHeight()) / mGameSize;
            mBoxPadding = mBoxSize / 10;
        });
    }

    void setPoints(Point[][] points) {
        mPoints = points;
    }

    private Point getPoint(int x, int y) {
        return mPoints[y][x];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, mGameSize, mGameSize, mPaint);
        if (mPoints == null) {
            return;
        }

        for (int i = 0; i < mGameSize; i++) {
            for (int j = 0; j < mGameSize2; j++) {
                Point point = getPoint(i, j);
                int left, right, top, bottom;

                switch (point.color) {
                    case RED:
                        mPaint.setColor(Color.RED);
                        break;
                    case BLUE:
                        mPaint.setColor(Color.BLUE);
                        break;
                    case PURPLE:
                        mPaint.setColor(Color.MAGENTA);
                        break;
                    case GREEN:
                        mPaint.setColor(Color.GREEN);
                        break;
                    default:
                        mPaint.setColor(Color.RED);
                }

                switch (point.type) {
                    case BOX:
                        left = mBoxSize * point.x + mBoxPadding;
                        right = left + mBoxSize - mBoxPadding;
                        top = mBoxSize * point.y + mBoxPadding;
                        bottom = top + mBoxSize - mBoxPadding;
                        break;
                    case VERTICAL_LINE:
                        mPaint.setColor(Color.parseColor("#336699"));
                        left = mBoxSize * point.x;
                        right = left + mBoxPadding;
                        top = mBoxSize * point.y;
                        bottom = top + mBoxSize;
                        break;
                    case HORIZONTAL_LINE:
                        left = mBoxSize * point.y;
                        right = left + mBoxSize;
                        top = mBoxSize * point.y;
                        bottom = top + mBoxPadding;
                        break;
                    case EMPTY:
                    default:
                        left = mBoxSize * point.x;
                        right = left + mBoxSize;
                        top = mBoxSize * point.y;
                        bottom = top + mBoxSize;
                        mPaint.setColor(Color.BLACK);
                        break;
                }
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }
}

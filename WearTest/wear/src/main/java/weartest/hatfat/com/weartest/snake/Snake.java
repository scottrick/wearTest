package weartest.hatfat.com.weartest.snake;

import android.graphics.Canvas;
import android.provider.Contacts;

import weartest.hatfat.com.weartest.game.GameObject;

/**
 * Created by scottrick on 7/2/14.
 */
public class Snake implements GameObject {

    private static final int DIR_LEFT = 0;
    private static final int DIR_UP = 1;
    private static final int DIR_RIGHT = 2;
    private static final int DIR_DOWN = 3;
    private static final int DIR_NUM = 4;

    private int snakeDirection = DIR_RIGHT;
    private int nextDirection = snakeDirection;

    private final float timeBeforeMove = 1.0f / 8.0f;

    private float timeCounter = 0.0f;

    @Override
    public void update(float deltaTime) {
        timeCounter += deltaTime;

        while (timeCounter >= timeBeforeMove) {
            timeCounter -= timeBeforeMove;

            advance();
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }

    public void turnRight() {
        nextDirection = (snakeDirection + 1) % DIR_NUM;
    }

    public void turnLeft() {
        nextDirection = snakeDirection - 1;

        if (nextDirection < 0) {
            nextDirection += DIR_NUM;
        }
    }

    private void advance() {
        snakeDirection = nextDirection;
    }
}

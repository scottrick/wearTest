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

    private int snakeDirection = DIR_LEFT;

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void draw(Canvas canvas) {

    }

    public void changeDirection() {
        snakeDirection = (snakeDirection + 1) % DIR_NUM;
    }
}

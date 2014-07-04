package weartest.hatfat.com.weartest.snake;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

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

    private Paint headPaint;
    private Paint bodyPaint;

    private List<BlockObject> snakeBody;

    private int numToGrow = 5;

    public Snake() {
        snakeBody = new LinkedList<BlockObject>();

        headPaint = new Paint();
        headPaint.setStyle(Paint.Style.FILL);
        headPaint.setColor(0xff00aa00);

        bodyPaint = new Paint();
        bodyPaint.setStyle(Paint.Style.FILL);
        bodyPaint.setColor(0xff008f00);

        int startX = BlockObject.NUM_BLOCKS / 5;
        int startY = BlockObject.NUM_BLOCKS / 2 + 1;

        BlockObject startingHead = new BlockObject(startX, startY, headPaint);
        snakeBody.add(startingHead);
    }

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
        for (BlockObject blockObject : snakeBody) {
            blockObject.draw(canvas);
        }
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

        BlockObject oldHead = snakeBody.get(snakeBody.size() - 1);
        BlockObject newHead;

        if (numToGrow > 0) {
            newHead = new BlockObject(0, 0, null);
            snakeBody.add(newHead);

            numToGrow--;
        }
        else {
            newHead = snakeBody.remove(0);
            snakeBody.add(newHead);
        }

        oldHead.setPaint(bodyPaint);
        newHead.setPaint(headPaint);

        int newX = oldHead.getX();
        int newY = oldHead.getY();

        switch (snakeDirection) {
            case DIR_LEFT:
                newX--;
                break;
            case DIR_UP:
                newY--;
                break;
            case DIR_RIGHT:
                newX++;
                break;
            case DIR_DOWN:
                newY++;
                break;
            default:
                throw new RuntimeException();
        }

        newHead.setX(newX);
        newHead.setY(newY);
    }
}

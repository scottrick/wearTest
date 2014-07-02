package weartest.hatfat.com.weartest.snake;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import weartest.hatfat.com.weartest.game.GameObject;

/**
 * Created by scottrick on 7/2/14.
 */
public class BlockObject implements GameObject {

    public static final int NUM_BLOCKS = 24;

    private int x;
    private int y;
    private Paint paint;

    public BlockObject(int x, int y, Paint paint) {
        this.x = x;
        this.y = y;
        this.paint = paint;
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void draw(Canvas canvas) {
        //default draw implementation for a block object.

        Rect rect = canvas.getClipBounds();
        float blockSize = rect.width() / NUM_BLOCKS;

        canvas.drawRect(blockSize * getX(), blockSize * getY(), blockSize * (getX() + 1), blockSize * (getY() + 1), paint);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

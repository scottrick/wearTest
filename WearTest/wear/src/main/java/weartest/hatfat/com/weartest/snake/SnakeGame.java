package weartest.hatfat.com.weartest.snake;

import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import weartest.hatfat.com.weartest.game.Game;
import weartest.hatfat.com.weartest.game.GameObject;

/**
 * Created by scottrick on 7/2/14.
 */
public class SnakeGame extends Game {

    private Paint applePaint;

    private Snake snake;
    private List<GameObject> apples;

    public SnakeGame() {
        applePaint = new Paint();
        applePaint.setStyle(Paint.Style.FILL);
        applePaint.setColor(0xffaa0000);

        snake = new Snake();
        addObject(snake);

        apples = new LinkedList<GameObject>();

        generateApples();
    }

    private void generateApples() {
        removeObjects(apples);
        apples.clear();

        Random random = new Random();

        while (apples.size() < 12) {
            Apple newApple = new Apple(random.nextInt(BlockObject.NUM_BLOCKS), random.nextInt(BlockObject.NUM_BLOCKS), applePaint);

            if (!isSpaceOccupied(newApple.getX(), newApple.getY())) {
                apples.add(newApple);
            }
        }

        addObjects(apples);
    }

    private boolean isSpaceOccupied(int x, int y) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            snake.changeDirection();
            generateApples();
            return true;
        }

        return false;
    }
}

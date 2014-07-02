package weartest.hatfat.com.weartest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by scottrick on 7/2/14.
 */
public class SnakeSurface extends SurfaceView implements Runnable {

    private static final int DIR_LEFT = 0;
    private static final int DIR_UP = 1;
    private static final int DIR_RIGHT = 2;
    private static final int DIR_DOWN = 3;
    private static final int DIR_NUM = 4;

    private int snakeDirection = DIR_LEFT;

    private static final int TICKS_PER_UPDATE = 1000 / 20;
    private static final int TICKS_PER_REDRAW = 1000 / 10;

    private boolean isPaused;

    private long previousTime;
    private long currentTime;

    private long currentGameTick;
    private long nextUpdateTick;
    private long nextDrawTick;

    private Paint backgroundPaint;
    private Paint snakeBodyPaint;
    private Paint snakeHeadPaint;
    private Paint applePaint;

    private int screenheight;
    private int screenwidth;
    private float density;

    private volatile int cx;
    private volatile int cy;

    private volatile boolean running = false;

    private SurfaceHolder holder;
    private Thread renderThread = null;

    public SnakeSurface(Context context) {
        super(context);
        init();
    }

    public SnakeSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SnakeSurface(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        holder = getHolder();

        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        screenheight = dm.heightPixels;
        screenwidth = dm.widthPixels;
        density = dm.density;

        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(0xff00aa00);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            changeDirection();
            Log.e("hatfat", "snakeDirection " + snakeDirection);
            return true;
        }

        return false;
    }

    public void resume() {
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }

    @Override
    public void run() {
        while(running) {
            previousTime = currentTime;
            currentTime = System.currentTimeMillis();

            long timeChange = currentTime - previousTime;

            if (!isPaused) {
                currentGameTick += timeChange;
            }

            //update the game state
            while (currentGameTick >= nextUpdateTick) {
                float gameTime = (float)currentGameTick / 1000.0f;

                update(gameTime);
                nextUpdateTick += TICKS_PER_UPDATE;
            }

            if(!holder.getSurface().isValid()) {
                continue; //wait till it becomes valid
            }

            Log.e("hatfat", "here " + currentGameTick + ", " + nextDrawTick);

            if (currentGameTick < nextDrawTick) {
                //don't draw yet
                continue;
            }

            while (currentGameTick >= nextDrawTick) {
                nextDrawTick += TICKS_PER_REDRAW;
            }

            draw();
        }
    }

    private void update(float gameTime) {
        Log.e("hatfat", "update: " + gameTime);
//        cx +=opx;
//        cy +=opy;
//        if(cy>screenheight) {
//            opy = -1;
//        } else if(cy<0) {
//            opy = +1;
//        }
//
//        if(cx>screenwidth) {
//            opx = -1;
//        } else if(cx<0) {
//            opx = +1;
//        }
    }

    private void draw() {
        Log.e("hatfat", "draw");

        Canvas canvas = holder.lockCanvas();

        //paint background
        canvas.drawPaint(backgroundPaint);

        //paint snake

        //paint snake head

        //paint apples

        holder.unlockCanvasAndPost(canvas);
    }

    public void pause() {
        running = false;
        boolean retry = true;
        while(retry) {
            try {
                renderThread.join();
                retry = false;
            }
            catch( InterruptedException e) {
                //retry
            }
        }
    }

    private void changeDirection() {
        snakeDirection = (snakeDirection + 1) % DIR_NUM;
    }
}

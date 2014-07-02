package weartest.hatfat.com.weartest.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by scottrick on 7/2/14.
 */
public class GameSurface extends SurfaceView implements Runnable {

    private static final int TICKS_PER_UPDATE = 1000 / 50;
    private static final int TICKS_PER_REDRAW = 1000 / 25;

    private boolean isPaused;

    private long previousTime;
    private long currentTime;

    private long currentGameTick;
    private long nextUpdateTick;
    private long nextDrawTick;

    private Paint backgroundPaint;

    private int screenheight;
    private int screenwidth;
    private float density;

    private volatile boolean running = false;

    private SurfaceHolder holder;
    private Thread renderThread = null;

    private Game game;

    public GameSurface(Context context) {
        super(context);
        init();
    }

    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameSurface(Context context, AttributeSet attrs, int defStyle) {
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
        backgroundPaint.setColor(0xff000000);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (game != null) {
            return game.onTouchEvent(event);
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
        currentTime = System.currentTimeMillis();

        while (running) {
            previousTime = currentTime;
            currentTime = System.currentTimeMillis();

            long timeChange = currentTime - previousTime;

            if (!isPaused) {
                currentGameTick += timeChange;
            }

            //update the game state
            while (currentGameTick >= nextUpdateTick) {
                float deltaTime = (float)TICKS_PER_UPDATE / 1000.0f;
                update(deltaTime);

                nextUpdateTick += TICKS_PER_UPDATE;
            }

            if (!holder.getSurface().isValid()) {
                continue; //wait till it becomes valid
            }

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

    private void update(float deltaTime) {
        if (game == null) {
            return;
        }

        game.update(deltaTime);
    }

    private void draw() {
        if (game == null) {
            return;
        }

        Canvas canvas = holder.lockCanvas();

        //paint background
        canvas.drawPaint(backgroundPaint);

        game.draw(canvas);

        holder.unlockCanvasAndPost(canvas);
    }

    public void pause() {
        running = false;
        boolean retry = true;

        while (retry) {
            try {
                renderThread.join();
                retry = false;
            }
            catch (InterruptedException e) {
                //retry
            }
        }
    }
}

package weartest.hatfat.com.weartest.game;

import android.graphics.Canvas;

/**
 * Created by scottrick on 7/2/14.
 */
public interface GameObject {
    public void update(float deltaTime);
    public void draw(Canvas canvas);
}

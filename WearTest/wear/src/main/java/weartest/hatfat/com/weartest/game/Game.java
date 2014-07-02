package weartest.hatfat.com.weartest.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.LinkedList;
import java.util.List;

import weartest.hatfat.com.weartest.game.GameObject;

/**
 * Created by scottrick on 7/2/14.
 */
public class Game implements GameObject {
    private List<GameObject> objects;

    public Game() {
        objects = new LinkedList<GameObject>();
    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void update(float deltaTime) {
        for (GameObject object : objects) {
            object.update(deltaTime);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (GameObject object : objects) {
            object.draw(canvas);
        }
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void addObjects(List<GameObject> objects) {
        this.objects.addAll(objects);
    }

    public void removeObject(GameObject object) {
        objects.remove(object);
    }

    public void removeObjects(List<GameObject> objects) {
        this.objects.removeAll(objects);
    }

    public void removeAllObjects() {
        objects.clear();
    }
}

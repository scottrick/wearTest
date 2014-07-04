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
    private List<GameObject> objectsToAdd;
    private List<GameObject> objectsToRemove;

    private GameSurface surface;

    public Game(GameSurface surface) {
        this.surface = surface;
        this.objects = new LinkedList<GameObject>();
        this.objectsToAdd = new LinkedList<GameObject>();
        this.objectsToRemove = new LinkedList<GameObject>();
    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void update(float deltaTime) {
        for (GameObject object : objects) {
            object.update(deltaTime);
        }

        //add all that are queued to be added
        objects.removeAll(objectsToRemove);

        //remove all that should be removed
        objects.addAll(objectsToAdd);

        objectsToRemove.clear();
        objectsToAdd.clear();
    }

    @Override
    public void draw(Canvas canvas) {
        for (GameObject object : objects) {
            object.draw(canvas);
        }
    }

    public void addObject(GameObject object) {
        objectsToAdd.add(object);
    }

    public void addObjects(List<GameObject> objects) {
        objectsToAdd.addAll(objects);
    }

    public void removeObject(GameObject object) {
        objectsToRemove.add(object);
    }

    public void removeObjects(List<GameObject> objects) {
        objectsToRemove.addAll(objects);
    }

    public void removeAllObjects() {
        objectsToRemove.addAll(objects);
    }

    public GameSurface getSurface() {
        return surface;
    }
}

package weartest.hatfat.com.weartest;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import weartest.hatfat.com.weartest.game.GameSurface;
import weartest.hatfat.com.weartest.snake.SnakeGame;

public class WearActivity extends Activity {

    private GameSurface gameSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_snake);
        gameSurface = (GameSurface) findViewById(R.id.view_surface);
        gameSurface.setGame(new SnakeGame(gameSurface));

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onPause() {
        super.onPause();

        gameSurface.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        gameSurface.resume();
    }
}

package weartest.hatfat.com.weartest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

public class WearActivity extends Activity {

    private SnakeSurface snakeSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_snake);
        snakeSurface = (SnakeSurface) findViewById(R.id.view_surface);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void onPause() {
        super.onPause();

        snakeSurface.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        snakeSurface.resume();
    }
}

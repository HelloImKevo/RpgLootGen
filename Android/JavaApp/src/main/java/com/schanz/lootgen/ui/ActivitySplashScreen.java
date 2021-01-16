package com.schanz.lootgen.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.LinearLayout;

import com.schanz.lootgen.R;

public class ActivitySplashScreen extends Activity implements OnTouchListener {

    private static final long SPLASH_TIME = 4 * 1000;
    private LinearLayout splashLayout;
    private AsyncTask<Void, Void, Void> mSplashScreenTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        splashLayout = (LinearLayout)findViewById(R.id.splash_layout);
        splashLayout.setOnTouchListener(this);

        mSplashScreenTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... arg0) {
                try {
                    Thread.sleep(SPLASH_TIME);
                } catch (InterruptedException e) {
                    // Do nothing
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void nothing) {
                endSplashScreen();
            }
        }.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mSplashScreenTask.cancel(true);
        endSplashScreen();
        return false;
    }

    private void endSplashScreen() {
        startActivity(new Intent(this, ActivityHome.class));
        finish();
    }
}

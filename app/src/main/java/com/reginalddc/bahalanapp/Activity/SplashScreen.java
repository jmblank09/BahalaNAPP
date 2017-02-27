package com.reginalddc.bahalanapp.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.reginalddc.bahalanapp.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TextView appName = (TextView) findViewById(R.id.appname_textView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/arial_rounded.ttf");
        appName.setTypeface(typeface);

        Thread loadingThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(2000);
                } catch (Exception e) {

                } finally {
                    Intent intent = new Intent(SplashScreen.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        loadingThread.start();
    }
}

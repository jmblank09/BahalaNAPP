package com.reginalddc.bahalanapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView signup_textView, tapmona_textView, top10_textView, allResto_textView;
    Button bahalana_button;
    ImageView signup_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup_textView = (TextView) findViewById(R.id.signup_textView);
        tapmona_textView = (TextView) findViewById(R.id.tapmona_textView);
        top10_textView = (TextView) findViewById(R.id.top10_textView);
        allResto_textView = (TextView) findViewById(R.id.viewallresto_textView);
        bahalana_button = (Button) findViewById(R.id.bahalana_button);
        signup_imageView = (ImageView) findViewById(R.id.signup_imageView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/arial_rounded.ttf");

        signup_textView.setTypeface(typeface);
        tapmona_textView.setTypeface(typeface);
        top10_textView.setTypeface(typeface);
        allResto_textView.setTypeface(typeface);
        bahalana_button.setTypeface(typeface);


    }
}

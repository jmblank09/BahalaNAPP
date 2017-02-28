package com.reginalddc.bahalanapp.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;

import com.reginalddc.bahalanapp.Fragment.Fragment.RestaurantDetailFragment;
import com.reginalddc.bahalanapp.R;

//fragments

public class SpecificRestaurantActivity extends AppCompatActivity {

    TextView back_textView, user_textView, resto_textView, resto_title_textView,
            profile_description_textView, map_location_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_restaurant);

        back_textView = (TextView) findViewById(R.id.back_textView);
        user_textView = (TextView) findViewById(R.id.user_textView);
        resto_title_textView = (TextView) findViewById(R.id.resto_title_textView);
        profile_description_textView = (TextView) findViewById(R.id.profile_description);
        map_location_textView = (TextView) findViewById(R.id.map_location);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/arial_rounded.ttf");

        back_textView.setTypeface(typeface);
        user_textView.setTypeface(typeface);
        resto_title_textView.setTypeface(typeface);
        profile_description_textView.setTypeface(typeface);
        map_location_textView.setTypeface(typeface);
        user_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpecificRestaurantActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        back_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpecificRestaurantActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        willView();
    }

    private void willView(){
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_layout, new RestaurantDetailFragment()).commit();

        profile_description_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.fragment_layout, new RestaurantDetailFragment()).commit();
                }
        });

        map_location_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fragmentManager.beginTransaction().replace(R.id.fragment_layout, new MapsActivity()).commit();
                Intent intent = new Intent(SpecificRestaurantActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}

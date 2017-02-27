package com.reginalddc.bahalanapp.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.reginalddc.bahalanapp.Model.Resto;
import com.reginalddc.bahalanapp.Model.RestoAdapter;
import com.reginalddc.bahalanapp.R;

import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity {

    TextView back_textView, user_textView, resto_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        back_textView = (TextView) findViewById(R.id.back_textView);
        user_textView = (TextView) findViewById(R.id.user_textView);
        resto_textView = (TextView) findViewById(R.id.allresto_textView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/arial_rounded.ttf");

        back_textView.setTypeface(typeface);
        user_textView.setTypeface(typeface);
        resto_textView.setTypeface(typeface);

        ArrayList<Resto> arrayOfResto = new ArrayList<Resto>();
        final RestoAdapter adapter = new RestoAdapter(getApplicationContext(), arrayOfResto);
        ListView listView = (ListView) findViewById(R.id.resto_listView);
        listView.setAdapter(adapter);
        Resto firstResto = new Resto(1, "Dimsum Treats");
        Resto secondResto = new Resto(2, "Mcdo");
        Resto thirdResto = new Resto(3, "KFC");
        Resto fourthResto = new Resto(4, "Perikoko");
        Resto fifthResto = new Resto(5, "Big B Burgers");
        Resto sixthResto = new Resto(6, "LoveLite");
        Resto seventhResto = new Resto(7, "Sisig Express");
        Resto eightResto = new Resto(8, "Extreme");
        Resto ninthResto = new Resto(9, "Green Box");
        Resto tenthResto = new Resto(10, "Gayuma ni Maria");
        Resto eleventhResto = new Resto(11, "Ate Rita's");
        Resto twelveResto = new Resto(12, "Chow King");
        Resto thirteenResto = new Resto(13, "Chezron");
        Resto fourteenResto = new Resto(14, "Happy n' Healthy");
        Resto fifteenResto = new Resto(15, "Tapa King");
        Resto sixteenResto = new Resto(16, "Chowpoint");
        Resto seventeenResto = new Resto(17, "Extreme");
        Resto eighteenResto = new Resto(18, "Yellow Cab");
        Resto ninteenResto = new Resto(19, "Jollibee");
        Resto twentiethResto = new Resto(20, "D Cream");
        adapter.add(firstResto);
        adapter.add(secondResto);
        adapter.add(thirdResto);
        adapter.add(fourthResto);
        adapter.add(fifthResto);
        adapter.add(sixthResto);
        adapter.add(seventhResto);
        adapter.add(eightResto);
        adapter.add(ninthResto);
        adapter.add(tenthResto);
        adapter.add(eleventhResto);
        adapter.add(twelveResto);
        adapter.add(thirteenResto);
        adapter.add(fourteenResto);
        adapter.add(fifteenResto);
        adapter.add(sixteenResto);
        adapter.add(seventeenResto);
        adapter.add(eighteenResto);
        adapter.add(ninteenResto);
        adapter.add(twentiethResto);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), adapter.getItem(position).name, Toast.LENGTH_LONG).show();
            }
        });

        user_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        back_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestaurantsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

package com.reginalddc.bahalanapp.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.reginalddc.bahalanapp.Model.Resto;
import com.reginalddc.bahalanapp.Model.RestoAdapter;
import com.reginalddc.bahalanapp.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView signup_textView, tapmona_textView, top10_textView, allResto_textView;
    Button bahalana_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup_textView = (TextView) findViewById(R.id.signup_textView);
        tapmona_textView = (TextView) findViewById(R.id.tapmona_textView);
        top10_textView = (TextView) findViewById(R.id.top10_textView);
        allResto_textView = (TextView) findViewById(R.id.viewallresto_textView);
        bahalana_button = (Button) findViewById(R.id.bahalana_button);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/arial_rounded.ttf");

        signup_textView.setTypeface(typeface);
        tapmona_textView.setTypeface(typeface);
        top10_textView.setTypeface(typeface);
        allResto_textView.setTypeface(typeface);
        bahalana_button.setTypeface(typeface);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), adapter.getItem(position).name, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, SpecificRestaurantActivity.class);
                startActivity(intent);
            }
        });

        signup_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        allResto_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RestaurantsActivity.class);
                startActivity(intent);
            }
        });
    }
}

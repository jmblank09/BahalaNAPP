package com.reginalddc.bahalanapp.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.reginalddc.bahalanapp.Model.Resto;
import com.reginalddc.bahalanapp.Model.RestoBaseAdapter;
import com.reginalddc.bahalanapp.Model.RestoAdapter;
import com.reginalddc.bahalanapp.Model.User;
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

        final User user = new User();

        if(user.getUser_ID() != 0) {
            user_textView.setText("Hi, " + user.getFirstName() + "!");
        } else {
            user_textView.setText(getText(R.string.login));
        }

        ArrayList<RestoBaseAdapter> arrayOfRestoBaseAdapter = new ArrayList<RestoBaseAdapter>();
        final RestoAdapter adapter = new RestoAdapter(getApplicationContext(), arrayOfRestoBaseAdapter);
        ListView listView = (ListView) findViewById(R.id.resto_listView);
        listView.setAdapter(adapter);

        String arrayName[] = Resto.getRestoName();
        int arrayId[] = Resto.getRestoId();
        if (arrayName.length > 0) {
            for (int i = 0; i < arrayName.length; i++){
                int rank = i + 1;
                RestoBaseAdapter addResto = new RestoBaseAdapter(rank, arrayName[i], arrayId[i]);
                adapter.add(addResto);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), adapter.getItem(position).name, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(RestaurantsActivity.this, SpecificRestaurantActivity.class);
                startActivity(intent);
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

package com.reginalddc.bahalanapp.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.reginalddc.bahalanapp.Fragment.Fragment.RestaurantDetailFragment;
import com.reginalddc.bahalanapp.Model.Resto;
import com.reginalddc.bahalanapp.Model.User;
import com.reginalddc.bahalanapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

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
        final User user = new User();

        if(user.getUser_ID() != 0) {
            user_textView.setText("Hi, " + user.getFirstName() + "!");
        } else {
            user_textView.setText(getText(R.string.login));
        }

        user_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getUser_ID() != 0) {
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(SpecificRestaurantActivity.this);
                    dlgAlert.setCancelable(false);
                    dlgAlert.setMessage("Are you sure you want to Logout?");
                    dlgAlert.setPositiveButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //do nothing
                                }
                            });

                    dlgAlert.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            user.setUser_ID(0);
                            user.setFirstName("");
                            user.setToken("");
                            user.setUsername("");
                            Intent intent = new Intent(SpecificRestaurantActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    final AlertDialog alert = dlgAlert.create();
                    alert.show();
                } else {
                    Intent intent = new Intent(SpecificRestaurantActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        back_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SpecificRestaurantActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        invokeWS();
    }

    private void invokeWS(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://107.170.61.180/BahalaNAPP_API/resto/" + Resto.getSelectedRestoId() , new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String response = new String(responseBody, "UTF-8");
                    JSONObject obj = new JSONObject(response);
                    Resto resto = new Resto(obj);
                    resto.specificRestoDataRetrieval();
                    willView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                try {
                    String response = new String(responseBody, "UTF-8");
                    JSONObject obj = new JSONObject(response);
                    JSONObject err = new JSONObject("error");
                    String error_response = err.getString("detail");

                    Toast.makeText(getApplicationContext(), error_response, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void willView(){
        resto_title_textView.setText(Resto.getSelectedRestoName());
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
//                fragmentManager.beginTransaction().replace(R.id.fragment_layout, new MapFragment()).commit();
                Intent intent = new Intent(SpecificRestaurantActivity.this, MapsActivity.class);
                intent.putExtra("NameOfResto", Resto.getSelectedRestoName());
                intent.putExtra("LatOfResto", Resto.getSelectedRestoLatitude());
                intent.putExtra("LongOfResto", Resto.getSelectedRestoLongitude());
                startActivity(intent);
            }
        });
    }
}

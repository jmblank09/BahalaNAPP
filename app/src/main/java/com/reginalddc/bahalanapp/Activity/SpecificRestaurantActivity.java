package com.reginalddc.bahalanapp.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reginalddc.bahalanapp.Fragment.Fragment.RestaurantDetailFragment;
import com.reginalddc.bahalanapp.Model.Resto;
import com.reginalddc.bahalanapp.Model.User;
import com.reginalddc.bahalanapp.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

//fragments

public class SpecificRestaurantActivity extends AppCompatActivity {

    TextView back_textView, user_textView, resto_title_textView,
            profile_description_textView, map_location_textView, title_rate;
    ProgressBar progressBar;
    ImageView imageview;
    RelativeLayout rate_us;
    Dialog rankDialog;
    RatingBar ratingBar;

    String rating = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_restaurant);

        back_textView = (TextView) findViewById(R.id.back_textView);
        user_textView = (TextView) findViewById(R.id.user_textView);
        resto_title_textView = (TextView) findViewById(R.id.resto_title_textView);
        profile_description_textView = (TextView) findViewById(R.id.profile_description);
        map_location_textView = (TextView) findViewById(R.id.map_location);
        imageview = (ImageView)findViewById(R.id.specificResto_imageView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        title_rate = (TextView) findViewById(R.id.title_rate);
        rate_us = (RelativeLayout) findViewById(R.id.rate);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/arial_rounded.ttf");

        back_textView.setTypeface(typeface);
        user_textView.setTypeface(typeface);
        resto_title_textView.setTypeface(typeface);
        profile_description_textView.setTypeface(typeface);
        map_location_textView.setTypeface(typeface);
        final User user = new User();

        if(user.getUser_ID() != 0) {
            user_textView.setText("Hi, " + user.getFirstName() + "!");
            rate_us.setVisibility(View.VISIBLE);
        } else {
            user_textView.setText(getText(R.string.login));
        }

        title_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rankDialog = new Dialog(SpecificRestaurantActivity.this, R.style.FullHeightDialog);
                rankDialog.setContentView(R.layout.rank_dialog);
                rankDialog.setCancelable(true);
                ratingBar = (RatingBar)rankDialog.findViewById(R.id.dialog_ratingbar);

                TextView text = (TextView) rankDialog.findViewById(R.id.rank_dialog_text1);
                Button updateButton = (Button) rankDialog.findViewById(R.id.rank_dialog_button);

                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
                        rating = Float.toString(arg1);
                    }
                });

                //now that the dialog is set up, it's time to show it
                rankDialog.show();
            }
        });

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

    public void addRating(View view) throws UnsupportedEncodingException, JSONException {
        RequestParams params = new RequestParams();

        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject jsonParams = new JSONObject();

        jsonParams.put("rating", rating);

        StringEntity entity = new StringEntity(jsonParams.toString());

        client.addHeader("Authorization", User.getToken());

        client.put(null, "http://107.170.61.180/BahalaNAPP_API/resto/" + Resto.getSelectedRestoId(), entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String response = new String(responseBody, "UTF-8");
                    JSONObject obj = new JSONObject(response);
                    JSONObject success = obj.getJSONObject("success");
                    String success_response = success.getString("detail");

                    Toast.makeText(getApplicationContext(), success_response, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable error){
                try {
                    String response = new String(errorResponse, "UTF-8");
                    JSONObject obj = new JSONObject(response);
                    JSONObject err = obj.getJSONObject("error");
                    String error_response = err.getString("detail");

                    Toast.makeText(getApplicationContext(), obj.toString(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        rankDialog.dismiss();
    }

    private void invokeWS(){
        progressBar.setVisibility(View.VISIBLE);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://107.170.61.180/BahalaNAPP_API/resto/" + Resto.getSelectedRestoId(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String response = new String(responseBody, "UTF-8");
                    JSONObject obj = new JSONObject(response);
                    Resto resto = new Resto(obj);
                    resto.specificRestoDataRetrieval();
                    willView();
                    progressBar.setVisibility(View.GONE);
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

        //set the image
        if(!Resto.getSelectedRestoImage().equals("null"))
            Picasso.with(this).load("http://" + Resto.getSelectedRestoImage()).into(imageview);
        else
            Picasso.with(this).load("http://107.170.61.180/bahalanapp_images/unknown.png").into(imageview);

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

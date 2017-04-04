package com.reginalddc.bahalanapp.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.reginalddc.bahalanapp.Model.Resto;
import com.reginalddc.bahalanapp.Model.RestoBaseAdapter;
import com.reginalddc.bahalanapp.Model.RestoAdapter;
import com.reginalddc.bahalanapp.Model.User;
import com.reginalddc.bahalanapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    TextView signup_textView, tapmona_textView, top10_textView, allResto_textView;
    Button bahalana_button;
    ProgressBar progressBar;
    int RandomResto = 0;

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
        bahalana_button.setEnabled(false);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        invokeWS();
    }

    private void invokeWS(){
        progressBar.setVisibility(View.VISIBLE);
        AsyncHttpClient client1 = new AsyncHttpClient();
        client1.get("http://107.170.61.180/BahalaNAPP_API/resto" , new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    progressBar.setVisibility(View.GONE);
                    String response = new String(responseBody, "UTF-8");
                    JSONArray obj = new JSONArray(response);
                    Resto resto = new Resto(obj);
                    resto.dataRetrieval();
                    willView();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                try {
                    progressBar.setVisibility(View.GONE);
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

    private void willView() {

        bahalana_button.setEnabled(true);

        final User user = new User();

        if(user.getUser_ID() != 0) {
            signup_textView.setText("Hi, " + user.getFirstName() + "!");
        } else {
            signup_textView.setText(getText(R.string.login));
        }

        ArrayList<RestoBaseAdapter> arrayOfRestoBaseAdapter = new ArrayList<RestoBaseAdapter>();
        final RestoAdapter adapter = new RestoAdapter(getApplicationContext(), arrayOfRestoBaseAdapter);
        ListView listView = (ListView) findViewById(R.id.resto_listView);
        listView.setAdapter(adapter);
        String arrayName[] = Resto.getRestoName();
        int arrayId[] = Resto.getRestoId();
        if (arrayName.length > 0) {
            for (int i = 0; i < 10; i++){
                int rank = i + 1;
                RestoBaseAdapter addResto = new RestoBaseAdapter(rank, arrayName[i], arrayId[i]);
                adapter.add(addResto);
            }
        }

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
                if(user.getUser_ID() != 0) {
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(MainActivity.this);
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
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    final AlertDialog alert = dlgAlert.create();
                    alert.show();
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }


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

    public void randomizeResto(View view) {
        int randomNumber = randInt();

        int arrayID[] = Resto.getRestoId();
        String arrayName[] = Resto.getRestoName();

        Resto.setSelectedRestoId(arrayID[randomNumber]);
        if (arrayName[randomNumber].length() > 15) {
            tapmona_textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_greater_10));
        } else {
            tapmona_textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_lesser_10));
        }
        tapmona_textView.setText(arrayName[randomNumber]);
        tapmona_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SpecificRestaurantActivity.class);
                startActivity(intent);
            }
        });
    }

    private static int randInt() {

        Random rand = new Random();

        int randomNum = rand.nextInt((49 - 0) + 1) + 0;

        return randomNum;
    }
}

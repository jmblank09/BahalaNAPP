package com.reginalddc.bahalanapp.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

import org.json.JSONException;
import org.json.JSONObject;

import com.reginalddc.bahalanapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText username_editText, password_editText;
    Button login_button, signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_button = (Button) findViewById(R.id.login_button);
        signup_button = (Button) findViewById(R.id.signup_button);
        username_editText = (EditText) findViewById(R.id.username_editText);
        password_editText = (EditText) findViewById(R.id.editText_password);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/arial_rounded.ttf");
        username_editText.setTypeface(typeface);
        password_editText.setTypeface(typeface);
        login_button.setTypeface(typeface);
        signup_button.setTypeface(typeface);

    }

    public void loginAccount(View view) throws UnsupportedEncodingException, JSONException {


        RequestParams params = new RequestParams();

        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject jsonParams = new JSONObject();

        jsonParams.put("username", username_editText.getText().toString());
        jsonParams.put("password", password_editText.getText().toString());

        StringEntity entity = new StringEntity(jsonParams.toString());

        client.post(null, "http://107.170.61.180/BahalaNAPP_API/auth/login", entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String response = new String(responseBody, "UTF-8");
                    JSONObject obj = new JSONObject(response);
                    JSONObject meta = obj.getJSONObject("meta");
                    String username = obj.getString("username");
                    int userId = Integer.parseInt(obj.getString("id"));
                    String token = meta.getString("token");

                    //add to model
//                    Model model = new Model();
//                    model.setToken(token);
//                    model.setUserame(username);
//                    model.setUserId(userId);
                    Toast.makeText(getApplicationContext(), "Successful Login!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable error) {
                try {
                    String response = new String(errorResponse, "UTF-8");
                    JSONObject obj = new JSONObject(response);
                    JSONObject err = obj.getJSONObject("error");
                    String error_response = err.getString("detail");

                    Toast.makeText(getApplicationContext(), error_response, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_LONG).show();
//                error.printStackTrace();
//                error.getCause();
            }
        });
    }

    public void signupAccount(View view) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}

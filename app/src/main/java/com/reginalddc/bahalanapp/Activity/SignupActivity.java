package com.reginalddc.bahalanapp.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reginalddc.bahalanapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class SignupActivity extends AppCompatActivity {

    TextView title_textView, alreadyhave_textView, login_textView;
    EditText firstname_editText, lastname_editText, username_editText, password_editText, repassword_editText;
    Button create_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        title_textView = (TextView) findViewById(R.id.titlesignup_textView);
        alreadyhave_textView = (TextView) findViewById(R.id.alreadyhave_textView);
        login_textView = (TextView) findViewById(R.id.login_textView);
        firstname_editText = (EditText) findViewById(R.id.firstname_editText);
        lastname_editText = (EditText) findViewById(R.id.lastname_editText);
        username_editText = (EditText) findViewById(R.id.username_editText);
        password_editText = (EditText) findViewById(R.id.editText_password);
        repassword_editText = (EditText) findViewById(R.id.editText_repassword);
        create_button = (Button) findViewById(R.id.create_button);


        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/arial_rounded.ttf");
        title_textView.setTypeface(typeface);
        alreadyhave_textView.setTypeface(typeface);
        login_textView.setTypeface(typeface);
        firstname_editText.setTypeface(typeface);
        lastname_editText.setTypeface(typeface);
        username_editText.setTypeface(typeface);
        password_editText.setTypeface(typeface);
        repassword_editText.setTypeface(typeface);
        create_button.setTypeface(typeface);

        login_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void createAccount(View view) throws UnsupportedEncodingException, JSONException {
        RequestParams params = new RequestParams();

        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject jsonParams = new JSONObject();


        jsonParams.put("first_name", firstname_editText.getText().toString());
        jsonParams.put("last_name", lastname_editText.getText().toString());
        jsonParams.put("username", username_editText.getText().toString());
        jsonParams.put("password", password_editText.getText().toString());
        jsonParams.put("re-password", repassword_editText.getText().toString());

        StringEntity entity = new StringEntity(jsonParams.toString());

        client.post(null, "http://107.170.61.180/BahalaNAPP_API/users", entity, "application/json", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String response = new String(responseBody, "UTF-8");
                    JSONObject obj = new JSONObject(response);
                    JSONObject success = obj.getJSONObject("success");
                    String success_response = success.getString("detail");

                    Toast.makeText(getApplicationContext(), success_response, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
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

                    Toast.makeText(getApplicationContext(), error_response, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

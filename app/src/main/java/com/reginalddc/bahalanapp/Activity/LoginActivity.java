package com.reginalddc.bahalanapp.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.reginalddc.bahalanapp.R;

public class LoginActivity extends AppCompatActivity {

    EditText username_editText, password_editText;
    Button login_button, signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_editText = (EditText) findViewById(R.id.username_editText);
        password_editText = (EditText) findViewById(R.id.editText_password);
        login_button = (Button) findViewById(R.id.login_button);
        signup_button = (Button) findViewById(R.id.signup_button);


        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/arial_rounded.ttf");
        username_editText.setTypeface(typeface);
        password_editText.setTypeface(typeface);
        login_button.setTypeface(typeface);
        signup_button.setTypeface(typeface);

    }

    public void loginAccount(View view) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void signupAccount(View view) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}

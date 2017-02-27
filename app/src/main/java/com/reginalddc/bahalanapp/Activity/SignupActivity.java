package com.reginalddc.bahalanapp.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.reginalddc.bahalanapp.R;

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

    public void createAccount(View view) {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}

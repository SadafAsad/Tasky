package com.example.sadafx.tasky_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private Button create;
    private Button login;
    private EditText name;
    private EditText email;
    private EditText password;
    DBManager dbmanager;
    Variables variables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbmanager = new DBManager(this);
        findViews();
        onClicks();

    }

    public void onClicks(){
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String in_name = name.getText().toString();
                String in_email = email.getText().toString();
                String in_password = password.getText().toString();

                variables.logged_in_email = in_email;
                dbmanager.onSignupInsert(in_name,in_email,in_password);

                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void findViews(){
        create = (Button) findViewById(R.id.create);
        login = (Button) findViewById(R.id.login);
        name = (EditText) findViewById(R.id.first_name);
        email = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

}


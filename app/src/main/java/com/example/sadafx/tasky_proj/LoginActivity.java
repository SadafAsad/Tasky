package com.example.sadafx.tasky_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private Button signup;
    private EditText email;
    private EditText password;
    DBManager dbmanager;
    Variables variables = new Variables();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        dbmanager = new DBManager(this);
        findviews();
        onClicks();

    }

    public void onClicks(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String in_email = email.getText().toString();
                String in_password = password.getText().toString();
                boolean flag = dbmanager.findUser(in_email,in_password);
                if ( flag ){
                    variables.loged_in_email = in_email;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "user not find",Toast.LENGTH_LONG).show();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    public void findviews(){
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }

}

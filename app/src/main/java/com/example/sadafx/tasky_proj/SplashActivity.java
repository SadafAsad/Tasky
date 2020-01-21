package com.example.sadafx.tasky_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    Variables variables;
    DBManager dbmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        dbmanager = new DBManager(this);
        String last_token = dbmanager.getLastToken();
//        if valid bood token boro bebin kiye va boro mainactivity agar valid nabood va ya kolln khali bood table...
//        alan n khatte paeiniye mire ke login kone va token begire ...
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}

package com.example.sadafx.tasky_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    Variables variables;
    DBManager dbmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        dbmanager = new DBManager(this);
        String last_token = dbmanager.getLastToken();

        if (last_token.matches("")) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            Log.i("correct ", String.valueOf(1));
            ArrayList<String> user = dbmanager.getUser(last_token);
            variables.logged_in_email = user.get(0);
            variables.first_name = user.get(1);
            variables.last_name = user.get(2);
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }

}

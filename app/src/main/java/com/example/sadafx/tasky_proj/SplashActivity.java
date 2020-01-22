package com.example.sadafx.tasky_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

        if (last_token.equals("")) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            getUser(this, last_token);
        }

    }

    public void getUser(Context mContext, String token){
        Ion.with(mContext)
                .load("http://192.241.136.152:3000/api/user/")
                .setHeader("Authorization", "Bearer "+token.replaceAll("^\"|\"$",""))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        Log.i("splash result ", String.valueOf(result));
                        String email = String.valueOf((result.get("email")));
                        ArrayList<String> user = dbmanager.getUser(email);

                        variables.logged_in_email = user.get(0);
                        Log.i("splash email ", variables.logged_in_email);
                        variables.first_name = user.get(1);
                        variables.last_name = user.get(2);

                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
                });
    }

}

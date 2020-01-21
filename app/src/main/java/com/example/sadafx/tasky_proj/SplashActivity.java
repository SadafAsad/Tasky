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

        if (last_token == "") {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            Log.i("correct ", String.valueOf(1));
            ArrayList<String> user = dbmanager.getUser(last_token);
            if (user==null){
                getUser(this, last_token);
            }
            variables.logged_in_email = user.get(0);
            variables.first_name = user.get(1);
            variables.last_name = user.get(2);
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }

    public void getUser(Context mContext, String token){
        JsonObject json = new JsonObject();
        json.addProperty("Authorization", "Bearer "+token);
        Ion.with(mContext)
                .load("http://192.241.136.152:3000/api/user/")
                .setHeader("Authorization", "Bearer "+token.replaceAll("^\"|\"$",""))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        String first_name = String.valueOf(result.get("first_name"));
                        String last_name = String.valueOf(result.get("last_name"));
                        String email = String.valueOf(result.get("email"));
                        String username = String.valueOf(result.get("username"));
                        String password = String.valueOf(result.get("password"));

                        dbmanager.onSignupInsert(token,first_name,last_name,username,email,password);
                    }
                });
    }

}

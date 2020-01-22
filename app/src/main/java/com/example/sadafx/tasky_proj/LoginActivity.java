package com.example.sadafx.tasky_proj;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private Button signup;
    private EditText username;
    private EditText password;
    DBManager dbmanager;
    Variables variables;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        dbmanager = new DBManager(this);
        findViews();
        onClicks(this);

    }

    public void onClicks(final Context mContext){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String in_username = username.getText().toString();
                String in_password = password.getText().toString();

                getUsersToken(mContext, in_username, in_password);

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

    public void getUsersToken(Context mContext, String username, String password){
        JsonObject json = new JsonObject();
        json.addProperty("username", username);
        json.addProperty("password", password);
        Ion.with(mContext)
                .load("http://192.241.136.152:3000/api/token/")
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (result!=null && result.has("access")){
                            token = String.valueOf(result.get("access"));
                            dbmanager.updateLastToken(token);
                            getUser(mContext, token);
                        } else {
                            Toast.makeText(mContext, "User not found", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void getUser(Context mContext, String token){
        Ion.with(mContext)
                .load("http://192.241.136.152:3000/api/user/")
                .setHeader("Authorization", "Bearer "+token.replaceAll("^\"|\"$",""))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        String email = String.valueOf((result.get("email")));
                        ArrayList<String> user = dbmanager.getUser(email);

                        if(user==null){
                            variables.logged_in_email = email;
                            variables.first_name = String.valueOf(result.get("first_name"));
                            variables.last_name = String.valueOf(result.get("last_name"));
                            String username = String.valueOf(result.get("username"));
                            String password = String.valueOf(result.get("password"));

                            dbmanager.onSignupInsert(variables.first_name,variables.last_name,username,email,password);
                        }
                        else {
                            variables.logged_in_email = user.get(0);
                            variables.first_name = user.get(1);
                            variables.last_name = user.get(2);
                        }

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
    }

    public void findViews(){
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

}

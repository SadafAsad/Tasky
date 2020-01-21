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
        findViews();
        onClicks(this);

    }

    public void onClicks(final Context mContext){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String in_email = email.getText().toString();
                String in_password = password.getText().toString();

                JsonObject json = new JsonObject();
                json.addProperty("username", in_email);
                json.addProperty("password", in_password);
                Ion.with(mContext)
                        .load("http://192.241.136.152:3000/api/token/")
                        .setJsonObjectBody(json)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                if (result!=null && result.has("access")){
                                    String token = String.valueOf(result.get("access"))
                                } else {
                                    Toast.makeText(mContext, "Try again", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

//                boolean flag = dbmanager.findUser(in_email,in_password);
//                if ( flag ){
//                    variables.loged_in_email = in_email;
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                }
//                else {
//                    Toast.makeText(LoginActivity.this, "user not find",Toast.LENGTH_LONG).show();
//                }
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

    public void findViews(){
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }

}

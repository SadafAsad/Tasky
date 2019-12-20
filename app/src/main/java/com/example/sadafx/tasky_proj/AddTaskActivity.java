package com.example.sadafx.tasky_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    RelativeLayout set_time;
    RelativeLayout set_alarm;
    Button add;
    Button cancel;
    EditText title;
    EditText context;

    Variables variables;
    DBManager dbmanager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        findViews();

    }

    public void findViews(){
        set_time = (RelativeLayout) findViewById(R.id.time);
        set_alarm = (RelativeLayout) findViewById(R.id.alarm);
        add = (Button) findViewById(R.id.add);
        cancel = (Button) findViewById(R.id.cancel);
        title = (EditText) findViewById(R.id.tile);
        context = (EditText) findViewById(R.id.context);
    }

    public void onClicks(){
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dbmanager = new DBManager(getApplicationContext());
//                final String in_title = title.getText().toString();
//                final String in_context = context.getText().toString();
//                //time and alarm ...
//                int id = dbmanager.maxTaskID(variables.loged_in_email);
//                id = id + 1;
//                String str_id = Integer.toString(id);
//
//                dbmanager.onNewTask(str_id,in_title,in_context,time,alarm,"0",day,variables.loged_in_email);
//
//                Toast.makeText(AddTaskActivity.this, "Added",Toast.LENGTH_LONG).show();
//
//                Intent intent = new Intent(AddTaskActivity.this, today_or_next_day.class);
//                startActivity(intent);
//            }
//        });
    }

}

package com.example.sadafx.tasky_proj;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
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
    TextView choose_time;
    TextView choose_alarm;

    Variables variables;
    DBManager dbmanager;

    TimePickerDialog time_picker_dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        findViews();
        onClicks();

    }

    public void findViews(){
        set_time = (RelativeLayout) findViewById(R.id.time);
        set_alarm = (RelativeLayout) findViewById(R.id.alarm);
        add = (Button) findViewById(R.id.add);
        cancel = (Button) findViewById(R.id.cancel);
        title = (EditText) findViewById(R.id.tile);
        context = (EditText) findViewById(R.id.context);
        choose_alarm = (TextView) findViewById(R.id.choose_alarm);
        choose_time = (TextView) findViewById(R.id.choose_time);
    }

    public void onClicks(){
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dbmanager = new DBManager(getApplicationContext());
//
//                final String in_title = title.getText().toString();
//                final String in_context = context.getText().toString();
//                final String in_time = choose_time.getText().toString();
//                final String in_alarm = choose_alarm.getText().toString();
//
//                int id = dbmanager.maxTaskID(variables.loged_in_email);
//                id = id + 1;
//                String str_id = Integer.toString(id);
//
//                dbmanager.onNewTask(str_id,in_title,in_context,in_time,in_alarm,"0",day,variables.loged_in_email);
//
//                Toast.makeText(AddTaskActivity.this, "Added",Toast.LENGTH_LONG).show();
//
//                Intent intent = new Intent(AddTaskActivity.this, today_or_next_day.class);
//                startActivity(intent);
//            }
//        });
        set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time_picker_dialog = new TimePickerDialog(AddTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        choose_time.setText(hourOfDay + ":" + minute);
                    }
                }, 0, 0, true);
                time_picker_dialog.show();
            }
        });
        set_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time_picker_dialog = new TimePickerDialog(AddTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        choose_alarm.setText(hourOfDay + ":" + minute);
                    }
                }, 0, 0, true);
                time_picker_dialog.show();
            }
        });
    }

}

package com.example.sadafx.tasky_proj;

public class Task {

    public String id;
    public String title;
    public String context;
    public String time;
    public String alarm;

    public Task(String id, String title, String context, String time, String alarm) {
        this.id = id;
        this.title = title;
        this.context = context;
        this.time = time;
        this.alarm = alarm;
    }

}
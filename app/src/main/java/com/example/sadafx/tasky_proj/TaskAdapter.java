package com.example.sadafx.tasky_proj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    List<Task> taskList;
    MainActivity mainActivity;

    public TaskAdapter(List<Task> taskList, MainActivity mainActivity){
        this.taskList = taskList;
        this.mainActivity = mainActivity;
        addTask();
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_card_alarm_set, parent, false);
        TaskViewHolder pvh = new TaskViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        TaskViewHolder(View itemView) { super(itemView); }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void addTask(){
        taskList = new ArrayList<>();
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd"));
    }

}

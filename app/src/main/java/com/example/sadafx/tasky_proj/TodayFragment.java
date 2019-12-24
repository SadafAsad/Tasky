package com.example.sadafx.tasky_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TodayFragment extends Fragment {

    RecyclerView rv;
    ArrayList<Task> taskList;

    RecyclerView rv2;
    List<Done> doneList;

    ImageView add_task_0;
    ImageView add_task_1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        String day = getArguments().getString("DAY");

        findViews(view);
        onClicks();

        MainActivity mainActivity = (MainActivity) getActivity();

        addTask();
        initRecyclerView(view);

        DoneAdapter adapter2 = new DoneAdapter(doneList, mainActivity);
        LinearLayoutManager llm2 = new LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false);
        rv2.setLayoutManager(llm2);
        rv2.setAdapter(adapter2);

        return view;
    }

    public void findViews(View v){
        rv = (RecyclerView) v.findViewById(R.id.to_do_container);
        rv2 = (RecyclerView) v.findViewById(R.id.done_container);
        add_task_0 = (ImageView) v.findViewById(R.id.add_task_0);
        add_task_1 = (ImageView) v.findViewById(R.id.add_task_1);
    }

    public void onClicks(){
        add_task_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTaskActivity.class);
                startActivity(intent);
            }
        });
        add_task_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initRecyclerView(View v){
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.to_do_container);
        TaskAdapter adapter = new TaskAdapter(taskList,getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void addTask(){
        taskList = new ArrayList<>();
        taskList.add(new Task("dxwafceferf", "egfregergvsd","00:00","00:00"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd","00:00","00:00"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd","00:00","00:00"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd","00:00","00:00"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd","00:00","00:00"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd","00:00","00:00"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd","00:00","00:00"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd","00:00","00:00"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd","00:00","00:00"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd","00:00","00:00"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd","00:00","00:00"));
        taskList.add(new Task("dxwafceferf", "egfregergvsd","00:00","00:00"));

    }

}

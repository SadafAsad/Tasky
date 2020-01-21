package com.example.sadafx.tasky_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NextDayFragment extends Fragment {


    ArrayList<Task> taskList;

    ConstraintLayout add_task;
    String day;

    Variables variables;
    DBManager dbmanager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.fragment_next_day, container, false);

        day = getArguments().getString("DAY");

        dbmanager = new DBManager(getContext());
        taskList = dbmanager.getDayTasks_todo(variables.logged_in_email,day);

        findViews(view);
        onClicks();

        initRecyclerView(view);

        return view;
    }

    public void findViews(View v){
        add_task = (ConstraintLayout) v.findViewById(R.id.add_task);
    }

    public void onClicks(){
        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("DAY",day);

                Intent intent = new Intent(getActivity(), AddTaskActivity.class);
                intent.putExtras(bundle);
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

}
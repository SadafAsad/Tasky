package com.example.sadafx.tasky_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class NextDayFragment extends Fragment {

    ImageView add_task_0;
    ImageView add_task_1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.fragment_next_day, container, false);

        String day = getArguments().getString("DAY");

        findViews(view);
        onClicks();

        return view;
    }

    public void findViews(View v){
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

}
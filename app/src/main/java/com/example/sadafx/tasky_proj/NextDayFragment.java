package com.example.sadafx.tasky_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class NextDayFragment extends Fragment {

    ConstraintLayout add_task;
    String day;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.fragment_next_day, container, false);

        day = getArguments().getString("DAY");

        findViews(view);
        onClicks();

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
                startActivity(intent);
            }
        });
    }

}
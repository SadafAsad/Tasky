package com.example.sadafx.tasky_proj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PreviousDayFragment extends Fragment {

    ArrayList<Done> doneList;
    ArrayList<Overdue> overdueList;

    String day;

    Variables variables;
    DBManager dbmanager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View view = inflater.inflate(R.layout.fragment_previous_day, container, false);

        day = getArguments().getString("DAY");

        dbmanager = new DBManager(getContext());
        doneList = dbmanager.getDayTasks_done(variables.loged_in_email,day);
        overdueList = dbmanager.getDayTasks_overdue(variables.loged_in_email,day);

        initRecyclerView_overdue(view);

        initRecyclerView_done(view);

        return view;
    }

    public void initRecyclerView_done(View v){
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.done_container);
        DoneAdapter adapter = new DoneAdapter(doneList,getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void initRecyclerView_overdue(View v){
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.overdue_container);
        OverdueAdapter adapter = new OverdueAdapter(overdueList,getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
package com.example.sadafx.tasky_proj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DoneAdapter extends RecyclerView.Adapter<DoneAdapter.DoneViewHolder> {

    List<Done> doneList;
    MainActivity mainActivity;

    public DoneAdapter(List<Done> doneList, MainActivity mainActivity){
        this.doneList = doneList;
        this.mainActivity = mainActivity;
        addDone();
    }

    @NonNull
    @Override
    public DoneAdapter.DoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_done, parent, false);
        DoneViewHolder pvh = new DoneViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull DoneAdapter.DoneViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return doneList.size();
    }

    public static class DoneViewHolder extends RecyclerView.ViewHolder{
        DoneViewHolder(View itemView) { super(itemView); }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void addDone(){
        doneList = new ArrayList<>();
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
        doneList.add(new Done("dxwafceferf"));
    }

}


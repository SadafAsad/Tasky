package com.example.sadafx.tasky_proj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoneAdapter extends RecyclerView.Adapter<DoneAdapter.ViewHolder> {

    private ArrayList<Done> doneList;
    private Context mContext;

    public DoneAdapter(ArrayList<Done> task_list, Context mContext) {
        this.doneList = task_list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DoneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_done, parent, false);
        DoneAdapter.ViewHolder holder = new DoneAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoneAdapter.ViewHolder holder, int position) {
        holder.title.setText(doneList.get(position).title);
    }

    @Override
    public int getItemCount() {
        return doneList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        FrameLayout parent_layout;
        TextView title;

        public ViewHolder(View itemView){
            super(itemView);
            parent_layout = (FrameLayout) itemView.findViewById(R.id.parent_layout);
            title = (TextView) itemView.findViewById(R.id.title);
        }

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


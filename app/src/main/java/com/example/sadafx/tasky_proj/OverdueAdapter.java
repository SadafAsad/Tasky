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

public class OverdueAdapter extends RecyclerView.Adapter<OverdueAdapter.ViewHolder> {

    private ArrayList<Overdue> overdueList;
    private Context mContext;

    public OverdueAdapter(ArrayList<Overdue> overdueList, Context mContext) {
        this.overdueList = overdueList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public OverdueAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_overdue, parent, false);
        OverdueAdapter.ViewHolder holder = new OverdueAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OverdueAdapter.ViewHolder holder, int position) {
        holder.title.setText(overdueList.get(position).title);
        holder.context.setText(overdueList.get(position).context);
    }

    @Override
    public int getItemCount() {
        return overdueList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        FrameLayout parent_layout;
        TextView title;
        TextView context;

        public ViewHolder(View itemView){
            super(itemView);
            parent_layout = (FrameLayout) itemView.findViewById(R.id.parent_layout);
            title = (TextView) itemView.findViewById(R.id.title);
            context = (TextView) itemView.findViewById(R.id.context);
        }

    }

}

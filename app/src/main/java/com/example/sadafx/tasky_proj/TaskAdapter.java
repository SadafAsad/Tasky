package com.example.sadafx.tasky_proj;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{

    private ArrayList<Task> task_list = new ArrayList<>();
    private Context mContext;

    public TaskAdapter(ArrayList<Task> task_list, Context mContext) {
        this.task_list = task_list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_card_alarm_set, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.parent_layout.setOnTouchListener(new OnSwipeTouchListener(mContext){
            public void onSwipeRight() {
                Toast.makeText(mContext, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Are you sure you want to delete this task?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Alert");
                alert.show();
            }
        });
        holder.title.setText(task_list.get(position).title);
        holder.context.setText(task_list.get(position).context);
    }

    @Override
    public int getItemCount() {
        return task_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout parent_layout;
        TextView title;
        TextView context;

        public ViewHolder(View itemView){
            super(itemView);
            parent_layout = (LinearLayout) itemView.findViewById(R.id.parent_layout);
            title = (TextView) itemView.findViewById(R.id.title);
            context = (TextView) itemView.findViewById(R.id.context);
        }

    }

}
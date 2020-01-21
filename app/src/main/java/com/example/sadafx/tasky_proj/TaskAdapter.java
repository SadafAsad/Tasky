package com.example.sadafx.tasky_proj;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
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

    private ArrayList<Task> task_list;
    private Context mContext;

    Variables variables;
    DBManager dbmanager;

    public TaskAdapter(ArrayList<Task> task_list, Context mContext) {
        this.task_list = task_list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_card_alarm_set, parent, false);
        ViewHolder holder = new ViewHolder(view);

        dbmanager = new DBManager(mContext);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.parent_layout.setOnTouchListener(new OnSwipeTouchListener(mContext){
            public void onSwipeRight() {
                Toast.makeText(mContext, "Done", Toast.LENGTH_LONG).show();
                dbmanager.doneTask(variables.loged_in_email, task_list.get(position).id);
            }
            public void onSwipeLeft() {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Are you sure you want to delete this task?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dbmanager.deleteTask(variables.loged_in_email, task_list.get(position).id);
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
        holder.time.setText(task_list.get(position).time);
        holder.alarm_time.setText(task_list.get(position).alarm);
    }

    @Override
    public int getItemCount() {
        return task_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout parent_layout;
        TextView title;
        TextView context;
        TextView time;
        TextView alarm_time;

        public ViewHolder(View itemView){
            super(itemView);
            parent_layout = (LinearLayout) itemView.findViewById(R.id.parent_layout);
            title = (TextView) itemView.findViewById(R.id.title);
            context = (TextView) itemView.findViewById(R.id.context);
            time = (TextView) itemView.findViewById(R.id.time);
            alarm_time = (TextView) itemView.findViewById(R.id.alarm_time);
        }

    }

}
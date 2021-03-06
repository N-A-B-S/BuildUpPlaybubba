package com.example.buildupplaybubba.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildupplaybubba.DataModels.ActivityDataModel;
import com.example.buildupplaybubba.R;
import com.example.buildupplaybubba.RecyclerViewInterface;

import java.util.ArrayList;

public class ActivityRecyclerAdapter extends RecyclerView.Adapter<ActivityRecyclerAdapter.activityViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private ArrayList<ActivityDataModel> modelList;

    public ActivityRecyclerAdapter(ArrayList<ActivityDataModel> list, RecyclerViewInterface recyclerViewInterface){
        this.modelList = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public class activityViewHolder extends RecyclerView.ViewHolder{
        private TextView date;
        //private TextView stepCount;
        private TextView activityTitle;
        private TextView caloriesBurnt;

        public activityViewHolder(final View view, RecyclerViewInterface recyclerViewInterface){
            //Akin to an onCreate method but not quite.
            super(view);
            date = view.findViewById(R.id.tv_date);
            //stepCount = view.findViewById(R.id.tv_stepCount);
            activityTitle = view.findViewById(R.id.tv_activityTitle);
            caloriesBurnt = view.findViewById(R.id.tv_caloriesBurnt);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ActivityRecyclerAdapter.activityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View activityView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_activity_items, parent, false);
        return new activityViewHolder(activityView, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityRecyclerAdapter.activityViewHolder holder, int position) {
        //Assigning values to the views.
        String date = modelList.get(position).getDate();
        //String stepCount = modelList.get(position).getStepCount();
        String activityTitle = modelList.get(position).getActivityTitle();
        String calories = modelList.get(position).getCaloriesBurnt();

        holder.date.setText(date);
        //holder.stepCount.setText(stepCount);
        holder.activityTitle.setText(activityTitle);
        holder.caloriesBurnt.setText(calories+" cals");
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}

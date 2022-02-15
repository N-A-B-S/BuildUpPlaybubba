package com.example.buildupplaybubba.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildupplaybubba.DataModels.SleepDataModel;
import com.example.buildupplaybubba.R;
import com.example.buildupplaybubba.RecyclerViewInterface;

import java.text.ParseException;
import java.util.ArrayList;

public class SleepRecyclerAdapter extends RecyclerView.Adapter<SleepRecyclerAdapter.sleepViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private ArrayList<SleepDataModel> modelList;

    public SleepRecyclerAdapter(ArrayList<SleepDataModel> modelList, RecyclerViewInterface recyclerViewInterface) {
        this.modelList = modelList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public class sleepViewHolder extends RecyclerView.ViewHolder{
        private TextView date;
        private TextView sleepDuration;
        private TextView deepSleepDuration;

        public sleepViewHolder(final View view, RecyclerViewInterface recyclerViewInterface){
            super(view);
            date = view.findViewById(R.id.tv_date);
            sleepDuration = view.findViewById(R.id.tv_sleepDuration);
            deepSleepDuration = view.findViewById(R.id.tv_deepSleepDuration);

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

    public SleepRecyclerAdapter.sleepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View activityView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sleep_items, parent, false);
        return new sleepViewHolder(activityView, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull sleepViewHolder holder, int position) {
        String date = modelList.get(position).getStartDate();
        String sleepDuration = null;
        try {
            sleepDuration = modelList.get(position).getSleepDuration();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String deepSleepDuration = modelList.get(position).getDeepSleepDuration();

        holder.date.setText(date);
        holder.sleepDuration.setText(sleepDuration);
        holder.deepSleepDuration.setText(deepSleepDuration);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}

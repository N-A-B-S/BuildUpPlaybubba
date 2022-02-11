package com.example.buildupplaybubba.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildupplaybubba.R;
import com.example.buildupplaybubba.RecyclerViewInterface;

import java.util.ArrayList;

public class MealRecyclerAdapter extends RecyclerView.Adapter<MealRecyclerAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private ArrayList<MealDataModel> modelList;

    public MealRecyclerAdapter(ArrayList<MealDataModel> list, RecyclerViewInterface recyclerViewInterface){
        this.modelList = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView date;
        private TextView mealTitle;
        private TextView mealCalories;

        public MyViewHolder(final View view, RecyclerViewInterface recyclerViewInterface){
            //Akin to an onCreate method but not quite.
            super(view);
            date = view.findViewById(R.id.tv_date);
            mealTitle = view.findViewById(R.id.tv_mealTitle);
            mealCalories = view.findViewById(R.id.tv_mealCalories);

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
    public MealRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View activityView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_meal_items, parent, false);
        return new MyViewHolder(activityView, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MealRecyclerAdapter.MyViewHolder holder, int position) {
        //Assigning values to the views.
        String date = modelList.get(position).getDate();
        String mealsTitle = modelList.get(position).getMealTitle();
        String calories = modelList.get(position).getCalories();

        holder.date.setText(date);
        holder.mealTitle.setText(mealsTitle);
        holder.mealCalories.setText(calories+" cals");
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}

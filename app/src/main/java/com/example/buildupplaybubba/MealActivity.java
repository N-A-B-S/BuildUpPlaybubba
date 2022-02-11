package com.example.buildupplaybubba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.buildupplaybubba.DB.MealDatabaseHelper;
import com.example.buildupplaybubba.DataModels.MealDataModel;
import com.example.buildupplaybubba.RecyclerView.MealRecyclerAdapter;
import com.example.buildupplaybubba.RecyclerView.ActivityRecyclerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MealActivity extends AppCompatActivity implements RecyclerViewInterface{

    private ArrayList<MealDataModel> mealList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        recyclerView = findViewById(R.id.rv_mealRecycler);

        mealList = new ArrayList<>();
        setMealModel();
        setAdapter();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        mealList.clear();
        setMealModel();
        setAdapter();
    }

    private void setMealModel(){
        Log.d("SetMealModel", "accessing method");
        MealDatabaseHelper dbHelper = new MealDatabaseHelper(getApplicationContext());
        ArrayList<String> valuesFromDB = new ArrayList<>();
        ArrayList<HashMap<String, String>> databaseData = new ArrayList<>();
        databaseData = dbHelper.getMealData();
        for (HashMap<String, String> map : databaseData){
            valuesFromDB.clear();
            for (Map.Entry<String, String> mapEntries : map.entrySet()){
                String key = mapEntries.getKey();
                String value = mapEntries.getValue();
                valuesFromDB.add(value);
                Log.d(key, value);
            }
            mealList.add(new MealDataModel(valuesFromDB.get(12),valuesFromDB.get(0), valuesFromDB.get(5), valuesFromDB.get(4),
                    valuesFromDB.get(3), valuesFromDB.get(8), valuesFromDB.get(10), valuesFromDB.get(13), valuesFromDB.get(14),
                    valuesFromDB.get(6), valuesFromDB.get(1), valuesFromDB.get(9), valuesFromDB.get(7), valuesFromDB.get(2),
                    valuesFromDB.get(11)));
        }

    }

    private void setAdapter(){
        MealRecyclerAdapter recyclerAdapter = new MealRecyclerAdapter(mealList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onItemClick(int position) {
//        Toast.makeText(JournalActivity.this, "Card onClick", Toast.LENGTH_SHORT).show();
//        String id = activityList.get(position).getDbID();
//        String activityTitle = activityList.get(position).getActivityTitle();
//        String activity = activityList.get(position).getActivity();
//        String date = activityList.get(position).getDate();
//        String calories = activityList.get(position).getCaloriesBurnt();
//        Log.d("ID", id);
//        Intent intent = new Intent(this, JournalEntryActivity.class);
//        intent.putExtra("id", id);
//        intent.putExtra("activityTitle", activityTitle);
//        intent.putExtra("activity", activity);
//        intent.putExtra("date", date);
//        intent.putExtra("calories", calories);
//        startActivity(intent);
    }
}
package com.example.buildupplaybubba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.buildupplaybubba.DB.SleepDatabaseHelper;
import com.example.buildupplaybubba.DataModels.SleepDataModel;
import com.example.buildupplaybubba.RecyclerView.SleepRecyclerAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SleepActivity extends AppCompatActivity implements RecyclerViewInterface{

    private ArrayList<SleepDataModel> sleepList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        recyclerView = findViewById(R.id.rv_sleepRecycler);

        sleepList = new ArrayList<>();
        setSleepModel();
        setAdapter();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        sleepList.clear();
        setSleepModel();
        setAdapter();
    }

    private void setSleepModel() {
        Log.d("SetSleepModel", "accessing method");
        SleepDatabaseHelper dbHelper = new SleepDatabaseHelper(getApplicationContext());
        ArrayList<String> valuesFromDB = new ArrayList<>();
        ArrayList<HashMap<String, String>> databaseData = new ArrayList<>();
        databaseData = dbHelper.getSleepData();
        for (HashMap<String, String> map : databaseData) {
            valuesFromDB.clear();
            for (Map.Entry<String, String> mapEntries : map.entrySet()) {
                String key = mapEntries.getKey();
                String value = mapEntries.getValue();
                valuesFromDB.add(value);
                Log.d(key, value);
            }
            sleepList.add(new SleepDataModel(valuesFromDB.get(2), valuesFromDB.get(4), valuesFromDB.get(0),
                    valuesFromDB.get(1), valuesFromDB.get(3)));
        }
    }

    private void setAdapter(){
        SleepRecyclerAdapter recyclerAdapter = new SleepRecyclerAdapter(sleepList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(SleepActivity.this, "Card onClick", Toast.LENGTH_SHORT).show();
        String id = sleepList.get(position).getDbID();
        String startDate = sleepList.get(position).getStartDate();
        String endDate = sleepList.get(position).getEndDate();
        String startTime = sleepList.get(position).getStartTime();
        String endTime = sleepList.get(position).getEndTime();
        String sleepDuration = "";
        try {
            sleepDuration = sleepList.get(position).getSleepDuration();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String deepSleepDuration = sleepList.get(position).getDeepSleepDuration();

        Intent intent = new Intent(this, SleepEntryActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("startDate", startDate);
        intent.putExtra("endDate", endDate);
        intent.putExtra("startTime", startTime);
        intent.putExtra("endTime", endTime);
        intent.putExtra("sleepDuration", sleepDuration);
        intent.putExtra("deepSleepDuration", deepSleepDuration);
        startActivity(intent);
    }
}
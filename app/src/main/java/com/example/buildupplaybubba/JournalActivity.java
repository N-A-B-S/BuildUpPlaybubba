package com.example.buildupplaybubba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.buildupplaybubba.DB.ActivityDatabaseHelper;
import com.example.buildupplaybubba.RecyclerView.ActivityRecyclerAdapter;
import com.example.buildupplaybubba.DataModels.ActivityDataModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JournalActivity extends AppCompatActivity implements RecyclerViewInterface{

    private ArrayList<ActivityDataModel> activityList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        recyclerView = findViewById(R.id.rv_activityRecycler);

        activityList = new ArrayList<>();
        setActivityModel();
        setAdapter();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.journal);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.journal:
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.browse:
                        startActivity(new Intent(getApplicationContext(), BrowseActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        activityList.clear();
        setActivityModel();
        setAdapter();
    }

    private void setActivityModel() {
        Log.d("SetActivityModel", "accessing method");
        ActivityDatabaseHelper dbHelper = new ActivityDatabaseHelper(getApplicationContext());
        ArrayList<String> valuesFromDB = new ArrayList<>();
        ArrayList<HashMap<String, String>> databaseData = new ArrayList<>();
        databaseData = dbHelper.getActivityData();
        for (HashMap<String, String> map : databaseData){
            valuesFromDB.clear();
            for (Map.Entry<String, String> mapEntries : map.entrySet()) {
                String key = mapEntries.getKey();
                String value = mapEntries.getValue();
                valuesFromDB.add(value);
                Log.d("values", value);
            }
            activityList.add(new ActivityDataModel(valuesFromDB.get(0), valuesFromDB.get(4), valuesFromDB.get(3), valuesFromDB.get(2), valuesFromDB.get(1)));
        }
    }

    private void setAdapter(){
        ActivityRecyclerAdapter activityRecyclerAdapter = new ActivityRecyclerAdapter(activityList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(activityRecyclerAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(JournalActivity.this, "Card onClick", Toast.LENGTH_SHORT).show();
        String id = activityList.get(position).getDbID();
        String activityTitle = activityList.get(position).getActivityTitle();
        String activity = activityList.get(position).getActivity();
        String date = activityList.get(position).getDate();
        String calories = activityList.get(position).getCaloriesBurnt();
        Log.d("ID", id);
        Intent intent = new Intent(this, JournalEntryActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("activityTitle", activityTitle);
        intent.putExtra("activity", activity);
        intent.putExtra("date", date);
        intent.putExtra("calories", calories);
        startActivity(intent);
    }

//    private String loadPreviousDaysSteps(String date){
//        SharedPreferences sharedPreferences = getSharedPreferences("stepPreferences", Context.MODE_PRIVATE);
//        int previousStepCount = sharedPreferences.getInt(date+"0", 0);
//        return String.valueOf(previousStepCount);
//    }
}
package com.example.buildupplaybubba;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buildupplaybubba.DB.ActivityDatabaseHelper;

public class JournalEntryActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    ActivityDatabaseHelper dbHelper;

    ImageButton editButton, deleteButton;
    TextView title, date, caloriesBurnt;

    private String id;
    private String activity;
    private String activityTitle;
    private String activityDate;
    private String activityCalories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entry);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            id = bundle.getString("id");
            Log.d("ID retrieved", id);
            activityTitle = bundle.getString("activityTitle");
            Log.d("Title retrieved", activityTitle);
            activity = bundle.getString("activity");
            Log.d("Activity retrieved", activity);
            activityDate = bundle.getString("date");
            Log.d("Date retrieved", activityDate);
            activityCalories = bundle.getString("calories");
            Log.d("Calories Retrieved", activityCalories);
        }

        editButton = (ImageButton) findViewById(R.id.editButton);
        deleteButton = (ImageButton) findViewById(R.id.deleteButton);

        title = (TextView) findViewById(R.id.activity_title);
        date = (TextView) findViewById(R.id.date);
        caloriesBurnt = (TextView) findViewById(R.id.calorieValue);

        title.setText(activityTitle);
        date.setText(activityDate);
        caloriesBurnt.setText(activityCalories + " cals");

        dbHelper = new ActivityDatabaseHelper(this);



        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editActivity(v);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteActivity(v);
            }
        });

    }

    public void editActivity(View view){
        Intent intent = new Intent(this, EditJournalActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("activityTitle", activityTitle);
        intent.putExtra("activity", activity);
        intent.putExtra("date", activityDate);
        intent.putExtra("calories", activityCalories);
        //AdditionalExtras needed for Activity Title, Calories Burnt and Date
        startActivity(intent);
    }

    public void deleteActivity(View view){
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Delete Activity?");
        dialogBuilder.setMessage("Are you sure you want to delete?");
        dialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteActivityRecord(id);
                finish();
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        Toast.makeText(this, "Deleted activity", Toast.LENGTH_SHORT).show();
        dialogBuilder.show();
    }
}
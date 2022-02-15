package com.example.buildupplaybubba;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buildupplaybubba.DB.SleepDatabaseHelper;

public class SleepEntryActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    SleepDatabaseHelper dbHelper;

    ImageButton editButton, deleteButton;
    TextView date, sleepDuration, deepSleepDuration;

    private String id;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String durationOfSleep;
    private String durationOfDeepSleep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_entry);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            id = bundle.getString("id");
            startDate = bundle.getString("startDate");
            startTime = bundle.getString("startTime");
            endDate = bundle.getString("endDate");
            endTime = bundle.getString("endTime");
            durationOfSleep = bundle.getString("sleepDuration");
            durationOfDeepSleep = bundle.getString("deepSleepDuration");
        }

        editButton = (ImageButton) findViewById(R.id.editButton);
        deleteButton = (ImageButton) findViewById(R.id.deleteButton);

        date = (TextView) findViewById(R.id.date);
        sleepDuration = (TextView) findViewById(R.id.sleepValue);
        deepSleepDuration = (TextView) findViewById(R.id.deepSleepValue);

        date.setText(startDate);
        sleepDuration.setText(durationOfSleep);
        deepSleepDuration.setText(durationOfDeepSleep);

        dbHelper = new SleepDatabaseHelper(this);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editSleep(v);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSleep(v);
            }
        });
    }

    public void editSleep(View view){
        Intent intent = new Intent(this, EditSleepActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("startDate", startDate);
        intent.putExtra("endDate", endDate);
        intent.putExtra("startTime", startTime);
        intent.putExtra("endTime", endTime);
        startActivity(intent);
    }

    public void deleteSleep(View view){
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Delete Sleep?");
        dialogBuilder.setMessage("Are you sure you want to delete?");
        dialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteSleep(id);
                finish();
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        Toast.makeText(this, "Deleted sleep", Toast.LENGTH_SHORT).show();
        dialogBuilder.show();
    }
}
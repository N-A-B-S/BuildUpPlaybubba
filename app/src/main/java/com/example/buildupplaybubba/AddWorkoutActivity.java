package com.example.buildupplaybubba;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildupplaybubba.DB.SQLiteOpenHelper;

public class AddWorkoutActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button saveButton;
    EditText activityTitle, caloriesBurnt;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        spinner = (Spinner) findViewById(R.id.activities_spinner);
        saveButton = (Button) findViewById(R.id.btn_saveActivity);
        activityTitle = (EditText) findViewById(R.id.editText_activityTitle);
        caloriesBurnt = (EditText) findViewById(R.id.editNumber_caloriesBurnt);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.activities_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveActivity(v);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String activity = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + activity, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(), "Please select activity", Toast.LENGTH_SHORT).show();
    }

    public void saveActivity(View view) {
        SQLiteOpenHelper sqLiteOpenHelper = new SQLiteOpenHelper(getApplicationContext());
        if (activityTitle.getText().toString().isEmpty() || spinner.getSelectedItem().toString().isEmpty() || caloriesBurnt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
        } else {
            boolean result = sqLiteOpenHelper.addActivity(activityTitle.getText().toString(), spinner.getSelectedItem().toString(), caloriesBurnt.getText().toString());
            if (result){
                Toast.makeText(this, "Activity saved", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to save activity", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
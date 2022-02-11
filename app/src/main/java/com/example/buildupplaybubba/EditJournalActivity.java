package com.example.buildupplaybubba;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.buildupplaybubba.DB.ActivityDatabaseHelper;

import java.util.Calendar;

public class EditJournalActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button editButton, cancelButton;
    EditText editTitle;
    Spinner spinner;
    EditText date, calories;

    DatePickerDialog picker;

    ActivityDatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_journal);

        dbhelper = new ActivityDatabaseHelper(this);

        editButton = (Button) findViewById(R.id.editBtn);
        cancelButton = (Button) findViewById(R.id.cancelBtn);
        editTitle = (EditText) findViewById(R.id.editJournalTitle);
        spinner = (Spinner) findViewById(R.id.edit_activities_spinner);
        date = (EditText) findViewById(R.id.editTextDate);
        calories = (EditText) findViewById(R.id.editTextCalories);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.activities_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        date.setInputType(InputType.TYPE_NULL);
        date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
               final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month =  calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                picker = new DatePickerDialog(EditJournalActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            editTitle.setText(bundle.getString("activityTitle"));
            spinner.setSelection(adapter.getPosition(bundle.getString("activity")));
            date.setText(bundle.getString("date"));
            calories.setText(bundle.getString("calories"));
        }


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper.updateActivity(bundle.getString("id"), editTitle.getText().toString(),
                        spinner.getSelectedItem().toString(), calories.getText().toString(), date.getText().toString());
                Toast.makeText(v.getContext(), "Updating record", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String activity = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(), "Please select activity", Toast.LENGTH_SHORT).show();
    }
}
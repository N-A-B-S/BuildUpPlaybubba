package com.example.buildupplaybubba;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.buildupplaybubba.DB.SleepDatabaseHelper;

import java.util.Calendar;

public class EditSleepActivity extends AppCompatActivity {

    Button saveButton, cancelButton;
    EditText bedDate, bedTime, wakeDate, wakeTime;

    DatePickerDialog datePicker;
    TimePickerDialog timePicker;

    SleepDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sleep);

        dbHelper = new SleepDatabaseHelper(this);

        bedDate = (EditText) findViewById(R.id.bedtimeDate);
        bedTime = (EditText) findViewById(R.id.bedtimeTime);
        wakeDate = (EditText) findViewById(R.id.wakeupDate);
        wakeTime = (EditText) findViewById(R.id.wakeupTime);

        saveButton = (Button) findViewById(R.id.editSleep);
        cancelButton = (Button) findViewById(R.id.cancelBtn);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            bedDate.setText(bundle.getString("startDate"));
            wakeDate.setText(bundle.getString("endDate"));
            bedTime.setText(bundle.getString("startTime"));
            wakeTime.setText(bundle.getString("endTime"));
        }

        bedDate.setInputType(InputType.TYPE_NULL);
        bedTime.setInputType(InputType.TYPE_NULL);
        wakeDate.setInputType(InputType.TYPE_NULL);
        wakeTime.setInputType(InputType.TYPE_NULL);

        bedDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month =  calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                datePicker = new DatePickerDialog(EditSleepActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        bedDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        wakeDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month =  calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                datePicker = new DatePickerDialog(EditSleepActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        wakeDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePicker.show();
            }
        });

        bedTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);
                timePicker = new TimePickerDialog(EditSleepActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        bedTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, mins, true);
                timePicker.show();
            }
        });

        wakeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);
                timePicker = new TimePickerDialog(EditSleepActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        wakeTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, mins, true);
                timePicker.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String startDate, String endDate, String startTime, String endTime
                dbHelper.updateSleep(bundle.getString("id"), bedDate.getText().toString(), wakeDate.getText().toString(),
                        bedTime.getText().toString(), wakeTime.getText().toString());
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
}
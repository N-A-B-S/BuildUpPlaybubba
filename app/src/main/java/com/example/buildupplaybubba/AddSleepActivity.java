package com.example.buildupplaybubba;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.buildupplaybubba.DB.SleepDatabaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddSleepActivity extends AppCompatActivity {

    Button saveButton;
    EditText bedDate, bedTime, wakeDate, wakeTime;

    DatePickerDialog datePicker;
    TimePickerDialog timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sleep);
        Calendar today = Calendar.getInstance();

        bedDate = (EditText) findViewById(R.id.bedtimeDate);
        bedTime = (EditText) findViewById(R.id.bedtimeTime);
        wakeDate = (EditText) findViewById(R.id.wakeupDate);
        wakeTime = (EditText) findViewById(R.id.wakeupTime);
        saveButton = (Button) findViewById(R.id.saveSleep);

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
                datePicker = new DatePickerDialog(AddSleepActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                datePicker = new DatePickerDialog(AddSleepActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                timePicker = new TimePickerDialog(AddSleepActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                timePicker = new TimePickerDialog(AddSleepActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                saveSleep(v);
            }
        });


    }

    public void saveSleep(View view){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.UK);

        String startDateTime = bedDate.getText().toString() + " " + bedTime.getText().toString();
        String endDateTime =  wakeDate.getText().toString() + " " + wakeTime.getText().toString();
        Date d1 = new Date();
        Date d2 = new Date();

        try {
            d1 = dateFormat.parse(startDateTime);
            d2 = dateFormat.parse(endDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SleepDatabaseHelper sbh = new SleepDatabaseHelper(getApplicationContext());

        if (d2.compareTo(d1) > 0){
            if ((bedDate.getText().toString().isEmpty() || bedTime.getText().toString().isEmpty()
                    || wakeDate.getText().toString().isEmpty() || wakeTime.getText().toString().isEmpty())){
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean result = sbh.addSleep(bedDate.getText().toString(), wakeDate.getText().toString(),
                        bedTime.getText().toString(), wakeTime.getText().toString());
                if (result){
                    Toast.makeText(this, "Sleep saved", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to save activity", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "Ensure wake date and time are after sleep date and time", Toast.LENGTH_SHORT).show();
        }
    }
}
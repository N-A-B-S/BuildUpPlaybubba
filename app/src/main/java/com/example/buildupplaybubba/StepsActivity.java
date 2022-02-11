package com.example.buildupplaybubba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.buildupplaybubba.DateHelper.DateHelper;

import java.util.ArrayList;

public class StepsActivity extends AppCompatActivity{
    //Retrieve dates
    //Retrieve steps for those dates from shared prefs
    //Set textView to the date
    //Set other textView to the steps

    TextView date, date2, date3, date4, date5, date6;
    TextView steps, steps2, steps3, steps4, steps5, steps6;
    ArrayList<String> previousDates;
    ArrayList<Integer> previousSteps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        date = (TextView) findViewById(R.id.tv_date);
        date2 = (TextView) findViewById(R.id.tv_date2);
        date3 = (TextView) findViewById(R.id.tv_date3);
        date4 = (TextView) findViewById(R.id.tv_date4);
        date5 = (TextView) findViewById(R.id.tv_date5);
        date6 = (TextView) findViewById(R.id.tv_date6);

        steps = (TextView) findViewById(R.id.tv_steps);
        steps2 = (TextView) findViewById(R.id.tv_steps2);
        steps3 = (TextView) findViewById(R.id.tv_steps3);
        steps4 = (TextView) findViewById(R.id.tv_steps4);
        steps5 = (TextView) findViewById(R.id.tv_steps5);
        steps6 = (TextView) findViewById(R.id.tv_steps6);

        retrieveStepCounts();

        date.setText(previousDates.get(0));
        steps.setText(String.valueOf(previousSteps.get(0)));
        date2.setText(previousDates.get(1));
        steps2.setText(String.valueOf(previousSteps.get(1)));
        date3.setText(previousDates.get(2));
        steps3.setText(String.valueOf(previousSteps.get(2)));
        date4.setText(previousDates.get(3));
        steps4.setText(String.valueOf(previousSteps.get(3)));
        date5.setText(previousDates.get(4));
        steps5.setText(String.valueOf(previousSteps.get(4)));
        date6.setText(previousDates.get(5));
        steps6.setText(String.valueOf(previousSteps.get(5)));

    }

    private void retrieveStepCounts(){
        DateHelper dh = new DateHelper();
        previousDates = dh.getLast7Dates();
        previousSteps = new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences("stepPreferences", Context.MODE_PRIVATE);
        for (int i = 0; i < previousDates.size(); i++) {
            int previousStepCount = sharedPreferences.getInt(previousDates.get(i)+"0", 0);
            previousSteps.add(previousStepCount);
        }
    }

}
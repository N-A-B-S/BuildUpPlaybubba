package com.example.buildupplaybubba;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buildupplaybubba.DateHelper.DateHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    FloatingActionButton fab, workoutFab, sleepFab, mealsFab;
    TextView addWorkoutText, addSleepText, addMealText;
    Animation fabOpen, fabClose;

    boolean isOpen = false;

    private TextView textView_stepCounter;
    private SensorManager sensorManager;
    private Sensor mStepCounter;
    private boolean isSensorPresent;
    private DateHelper dateHelper;

    private int stepCount;
    private int sensorValue;

    private final boolean running = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Forces permissions screen to be shown.
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){ //ask for permission
            requestPermissions(new String[]{
                    Manifest.permission.ACTIVITY_RECOGNITION
            }, 0);
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        workoutFab = (FloatingActionButton) findViewById(R.id.fab_workout);
        sleepFab = (FloatingActionButton) findViewById(R.id.fab_sleep);
        mealsFab = (FloatingActionButton) findViewById(R.id.fab_meals);

        addWorkoutText = findViewById(R.id.workout_text);
        addSleepText = findViewById(R.id.sleep_text);
        addMealText = findViewById(R.id.meals_text);
        textView_stepCounter = findViewById(R.id.tv_stepsTaken);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        dateHelper = new DateHelper();

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorPresent = true;
        } else {
            textView_stepCounter.setText("Counter sensor is not present");
            isSensorPresent = false;
        }

        textView_stepCounter.setText(String.valueOf(loadPreviousSteps()));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.journal:
                        startActivity(new Intent(getApplicationContext(), JournalActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        return true;

                    case R.id.browse:
                        startActivity(new Intent(getApplicationContext(), BrowseActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen){
                    fab.startAnimation(fabOpen);
                    workoutFab.show();
                    addWorkoutText.setVisibility(View.VISIBLE);
                    sleepFab.show();
                    addSleepText.setVisibility(View.VISIBLE);
                    mealsFab.show();
                    addMealText.setVisibility(View.VISIBLE);
                    isOpen = true;
                } else {
                    fab.startAnimation(fabClose);
                    workoutFab.hide();
                    addWorkoutText.setVisibility(View.GONE);
                    sleepFab.hide();
                    addSleepText.setVisibility(View.GONE);
                    mealsFab.hide();
                    addMealText.setVisibility(View.GONE);
                    isOpen = false;
                }
            }
        });

        workoutFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Add workout functionality here", Toast.LENGTH_SHORT).show();
                goToWorkoutActivity();
            }
        });

        sleepFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Add sleep functionality here", Toast.LENGTH_SHORT).show();
                goToSleepActivity();
            }
        });

        mealsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Add meal functionality here", Toast.LENGTH_SHORT).show();
                goToMealActivity();
            }

        });
    }

    public void goToWorkoutActivity(){
        Intent intent = new Intent(this, AddWorkoutActivity.class);
        startActivity(intent);
    }

    public void goToMealActivity(){
        Intent intent = new Intent(this, AddMealActivity.class);
        startActivity(intent);
    }

    public void goToSleepActivity(){
        Intent intent = new Intent(this, AddSleepActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_UI);
        }

        textView_stepCounter.setText(String.valueOf(loadPreviousSteps()));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensorManager.unregisterListener(this, mStepCounter);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == mStepCounter) {
            sensorValue = (int) event.values[0];
            if (!doesSensorRecordingExist()) {
                Log.d("StepSensor", "Previous value doesn't exists.");
                saveInitialSensorValue(sensorValue);
            }
            if (loadInitialSensorSteps() > sensorValue){
                stepCount = (int) sensorValue;
                checkCorrectTextView();
            } else {
                stepCount = (int) sensorValue - loadInitialSensorSteps();
                checkCorrectTextView();
            }
        }
    }

    public void checkCorrectTextView(){
        if  (loadInitialSensorSteps() == 0 || sensorValue == 0) {
            textView_stepCounter.setText(String.valueOf(0));
        } else {
            textView_stepCounter.setText(String.valueOf(stepCount));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            sensorManager.unregisterListener(this, mStepCounter);
        }

        saveSteps(stepCount);
//
//        saveSteps(stepCount);
//        Log.d("onPause: ", "Saved Steps");
//
//        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
//            sensorManager.unregisterListener(this, mStepCounter);
//        }
    }

    public void saveSteps(int steps){
        SharedPreferences sharedPreferences = getSharedPreferences("stepPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(dateHelper.getDate()+"0", steps);
        editor.apply();
        Log.d("Saving steps: ", String.valueOf(steps));
    }

    public void saveInitialSensorValue(int steps){
        SharedPreferences sharedPreferences = getSharedPreferences("stepPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(dateHelper.getDate(), steps);
        editor.apply();
        Log.d("Saving initial steps: ", String.valueOf(steps));
    }

    public boolean doesSensorRecordingExist(){
        SharedPreferences sharedPreferences = getSharedPreferences("stepPreferences", Context.MODE_PRIVATE);
        return sharedPreferences.contains(dateHelper.getDate());
    }

    public int loadInitialSensorSteps(){
        //Returns 0 if value does not exist.
        SharedPreferences sharedPreferences = getSharedPreferences("stepPreferences", Context.MODE_PRIVATE);
        int initialDailySensorValue = sharedPreferences.getInt(dateHelper.getDate(), 0);
        Log.d("Initial Value: ", String.valueOf(initialDailySensorValue));
        return initialDailySensorValue;
    }

    public int loadPreviousSteps(){
        //Returns 0 if value does not exist.
        SharedPreferences sharedPreferences = getSharedPreferences("stepPreferences", Context.MODE_PRIVATE);
        int previousStepCount = sharedPreferences.getInt(dateHelper.getDate()+"0", 0);
        Log.d("Previous Steps: ", String.valueOf(previousStepCount));
        return previousStepCount;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
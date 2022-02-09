package com.example.buildupplaybubba;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buildupplaybubba.DB.MealDatabaseHelper;

public class AddMealActivity extends AppCompatActivity {

    EditText mealTitle, calories, carbs, protein, fat, fibre, sugar, sodium, K, vitA, vitC, calcium, iron;
    Button saveMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        mealTitle = (EditText) findViewById(R.id.mealTitle);
        calories = (EditText) findViewById(R.id.mealCalories);
        carbs = (EditText) findViewById(R.id.mealCarbs);
        protein = (EditText) findViewById(R.id.mealPro);
        fat = (EditText) findViewById(R.id.mealFat);
        fibre = (EditText) findViewById(R.id.mealFibre);
        sugar = (EditText) findViewById(R.id.mealSugar);
        sodium = (EditText) findViewById(R.id.mealSodium);
        K = (EditText) findViewById(R.id.mealPotassium);
        vitA = (EditText) findViewById(R.id.mealVitA);
        vitC = (EditText) findViewById(R.id.mealVitC);
        calcium = (EditText) findViewById(R.id.mealCalcium);
        iron = (EditText) findViewById(R.id.mealIron);

        saveMeal = (Button) findViewById(R.id.saveMeal);
        saveMeal.setOnClickListener(v -> {
            saveActivity(v);
        });


    }

    public void saveActivity(View view){
        MealDatabaseHelper mdh = new MealDatabaseHelper(getApplicationContext());
        if (mealTitle.getText().toString().isEmpty() || calories.getText().toString().isEmpty() || carbs.getText().toString().isEmpty() ||
                protein.getText().toString().isEmpty() || fat.getText().toString().isEmpty() || fibre.getText().toString().isEmpty() ||
                sugar.getText().toString().isEmpty() || sodium.getText().toString().isEmpty() || K.getText().toString().isEmpty() || vitA.getText().toString().isEmpty()
                || vitC.getText().toString().isEmpty() || calcium.getText().toString().isEmpty() || iron.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
        } else {
            boolean result = mdh.addMeal(mealTitle.getText().toString(), calories.getText().toString(),protein.getText().toString(),
                    carbs.getText().toString(), fibre.getText().toString(), fat.getText().toString(), sugar.getText().toString(),
                     sodium.getText().toString(), K.getText().toString(), vitA.getText().toString(), vitC.getText().toString(), calcium.getText().toString(), iron.getText().toString());
            if (result){
                Toast.makeText(this, "Meal saved", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to save meal", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
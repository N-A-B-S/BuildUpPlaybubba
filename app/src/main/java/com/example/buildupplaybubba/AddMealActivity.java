package com.example.buildupplaybubba;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
           //Activities
       });


    }
}
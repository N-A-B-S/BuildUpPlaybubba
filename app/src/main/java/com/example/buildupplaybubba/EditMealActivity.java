package com.example.buildupplaybubba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.buildupplaybubba.DB.MealDatabaseHelper;

public class EditMealActivity extends AppCompatActivity {

    Button editButton, cancelButton;
    EditText mealTitle, mealCalories, mealCarbs, mealProtein, mealFat, mealFibre, mealSugar, mealSodium,
            mealPotassium, mealVitaminA, mealVitaminC, mealCalcium, mealIron;

    MealDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meal);

        dbHelper = new MealDatabaseHelper(this);

        editButton = (Button) findViewById(R.id.editMeal);
        cancelButton = (Button) findViewById(R.id.cancelMeal);
        mealTitle = (EditText) findViewById(R.id.mealTitle);
        mealCalories = (EditText) findViewById(R.id.mealCalories);
        mealCarbs = (EditText) findViewById(R.id.mealCarbs);
        mealProtein = (EditText) findViewById(R.id.mealPro);
        mealFat = (EditText) findViewById(R.id.mealFat);
        mealFibre = (EditText) findViewById(R.id.mealFibre);
        mealSugar = (EditText) findViewById(R.id.mealSugar);
        mealSodium = (EditText) findViewById(R.id.mealSodium);
        mealPotassium = (EditText) findViewById(R.id.mealPotassium);
        mealVitaminA = (EditText) findViewById(R.id.mealVitA);
        mealVitaminC = (EditText) findViewById(R.id.mealVitC);
        mealCalcium = (EditText) findViewById(R.id.mealCalcium);
        mealIron = (EditText) findViewById(R.id.mealIron);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            mealTitle.setText(bundle.getString("title"));
            mealCalories.setText(bundle.getString("calories"));
            mealCarbs.setText(bundle.getString("carbs"));
            mealProtein.setText(bundle.getString("protein"));
            mealFat.setText(bundle.getString("fat"));
            mealFibre.setText(bundle.getString("fibre"));
            mealSugar.setText(bundle.getString("sugar"));
            mealSodium.setText(bundle.getString("sodium"));
            mealPotassium.setText(bundle.getString("potassium"));
            mealVitaminA.setText(bundle.getString("vitaminA"));
            mealVitaminC.setText(bundle.getString("vitaminC"));
            mealCalcium.setText(bundle.getString("calcium"));
            mealIron.setText(bundle.getString("iron"));
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateMeal(bundle.getString("id"), mealTitle.getText().toString(), mealCalories.getText().toString(),
                        mealProtein.getText().toString(), mealCarbs.getText().toString(), mealFibre.getText().toString(),
                        mealFat.getText().toString(), mealSugar.getText().toString(), mealSodium.getText().toString(),
                        mealPotassium.getText().toString(), mealVitaminA.getText().toString(),
                        mealVitaminC.getText().toString(), mealCalcium.getText().toString(), mealIron.getText().toString());
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
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

import com.example.buildupplaybubba.DB.MealDatabaseHelper;

public class MealEntryActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    MealDatabaseHelper dbHelper;

    ImageButton editButton, deleteButton;
    TextView title, date, caloriesEaten, carbs, protein, fat, fibre, sugar, sodium, potassium,
    vitaminA, vitaminC, calcium, iron;

    private String id;
    private String mealTitle;
    private String mealDate;
    private String mealCalories;
    private String mealCarbs;
    private String mealProtein;
    private String mealFat;
    private String mealFibre;
    private String mealSugar;
    private String mealSodium;
    private String mealPotassium;
    private String mealVitaminA;
    private String mealVitaminC;
    private String mealCalcium;
    private String mealIron;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_entry);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            id = bundle.getString("id");
            mealTitle = bundle.getString("title");
            mealDate = bundle.getString("date");
            mealCalories = bundle.getString("calories");
            mealCarbs = bundle.getString("carbs");
            mealProtein = bundle.getString("protein");
            mealFat = bundle.getString("fat");
            mealFibre = bundle.getString("fibre");
            mealSugar = bundle.getString("sugar");
            mealSodium = bundle.getString("sodium");
            mealPotassium = bundle.getString("potassium");
            mealVitaminA = bundle.getString("vitaminA");
            mealVitaminC = bundle.getString("vitaminC");
            mealCalcium = bundle.getString("calcium");
            mealIron = bundle.getString("iron");
        }

        editButton = (ImageButton) findViewById(R.id.editButton);
        deleteButton = (ImageButton) findViewById(R.id.deleteButton);

        title = (TextView) findViewById(R.id.meal_title);
        date = (TextView) findViewById(R.id.date);
        caloriesEaten = (TextView) findViewById(R.id.calorieValue);
        carbs = (TextView) findViewById(R.id.carbsValuetv);
        protein = (TextView) findViewById(R.id.proteinValuetv);
        fat = (TextView) findViewById(R.id.fatValuetv);
        fibre = (TextView) findViewById(R.id.fibreValuetv);
        sugar = (TextView) findViewById(R.id.sugarValuetv);
        sodium = (TextView) findViewById(R.id.sodiumValuetv);
        potassium = (TextView) findViewById(R.id.potassiumValuetv);
        vitaminA = (TextView) findViewById(R.id.VitaminAValuetv);
        vitaminC = (TextView) findViewById(R.id.VitaminCValuetv);
        calcium = (TextView) findViewById(R.id.calciumValuetv);
        iron = (TextView) findViewById(R.id.ironValuetv);

        title.setText(mealTitle);
        date.setText(mealDate);
        caloriesEaten.setText(mealCalories + " cals");
        carbs.setText(mealCarbs + " g");
        protein.setText(mealProtein + " g");
        fat.setText(mealFat + " g");
        fibre.setText(mealFibre + " g");
        sugar.setText(mealSugar + " g");
        sodium.setText(mealSodium + " g");
        potassium.setText(mealPotassium + " mg");
        vitaminA.setText(mealVitaminA + " mg");
        vitaminC.setText(mealVitaminC + " mg");
        calcium.setText(mealCalcium + " mg");
        iron.setText(mealIron + " mg");

        dbHelper = new MealDatabaseHelper(this);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editMeal(v);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMeal(v);
            }
        });
    }
    public void editMeal(View view){
        Intent intent = new Intent(this, EditMealActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", mealTitle);
        intent.putExtra("date", mealDate);
        intent.putExtra("calories", mealCalories);
        intent.putExtra("carbs", mealCarbs);
        intent.putExtra("protein", mealProtein);
        intent.putExtra("fat", mealFat);
        intent.putExtra("fibre", mealFibre);
        intent.putExtra("sugar", mealSugar);
        intent.putExtra("sodium", mealSodium);
        intent.putExtra("potassium", mealPotassium);
        intent.putExtra("vitaminA", mealVitaminA);
        intent.putExtra("vitaminC", mealVitaminC);
        intent.putExtra("calcium", mealCalcium);
        intent.putExtra("iron", mealIron);
        startActivity(intent);
    }

    public void deleteMeal(View view){
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Delete Meal?");
        dialogBuilder.setMessage("Are you sure you want to delete?");
        dialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.deleteMealRecord(id);
                finish();
            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        Toast.makeText(this, "Deleted meal", Toast.LENGTH_SHORT).show();
        dialogBuilder.show();
    }

}
package com.example.buildupplaybubba.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.buildupplaybubba.DateHelper.DateHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class MealDatabaseHelper extends android.database.sqlite.SQLiteOpenHelper {
    public static final String DB_NAME = "FitnessApp";
    public static final int DB_VERSION = 2;
    public static final String TABLE_NAME = "UserMeals";

    public static final String ID = "id";
    private static final String DATE = "date";
    public static final String TITLE = "title";
    public static final String CALORIES = "calories";
    public static final String PROTEIN = "protein";
    public static final String CARBS = "carbs";
    public static final String FIBRE = "fibre";
    public static final String FAT = "fat";
    public static final String SUGAR = "sugar";
    public static final String SODIUM = "sodium";
    public static final String POTASSIUM = "potassium";
    public static final String VITAMIN_A = "vitA";
    public static final String VITAMIN_C = "vitC";
    public static final String CALCIUM = "calcium";
    public static final String IRON = "iron";

    DateHelper dateHelper;


    public MealDatabaseHelper(@Nullable Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " TEXT NOT NULL, " +
                TITLE + " TEXT NOT NULL, " + CALORIES + " TEXT NOT NULL, " + PROTEIN  + " TEXT NOT NULL, " +
                CARBS + " TEXT NOT NULL, " + FIBRE + " TEXT NOT NULL, " + FAT  + " TEXT NOT NULL, " + SUGAR  + " TEXT NOT NULL, " +
                SODIUM  + " TEXT NOT NULL, " + POTASSIUM  + " TEXT NOT NULL, " + VITAMIN_A  + " TEXT NOT NULL, " +
                VITAMIN_C  + " TEXT NOT NULL, " + CALCIUM  + " TEXT NOT NULL, " + IRON  + " TEXT NOT NULL)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS UserMeals");
        onCreate(db);
    }

    public boolean addMeal(String title, String calories, String protein, String carbs, String fibre, String fat, String sugar,
    String sodium, String potassium, String vitaminA, String vitaminC, String calcium, String iron){
        dateHelper = new DateHelper();
        Log.d("AddMeal in SQLite", "Method called");
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DATE, dateHelper.getDate());
            values.put(TITLE, title);
            values.put(CALORIES, calories);
            values.put(PROTEIN, protein);
            values.put(CARBS, carbs);
            values.put(FIBRE, fibre);
            values.put(FAT, fat);
            values.put(SUGAR, sugar);
            values.put(SODIUM, sodium);
            values.put(POTASSIUM, potassium);
            values.put(VITAMIN_A, vitaminA);
            values.put(VITAMIN_C, vitaminC);
            values.put(CALCIUM, calcium);
            values.put(IRON, iron);
            db.insert(TABLE_NAME, null, values);
            db.close();
            return true;
        } catch (Exception e) {
            Log.d("AddMeal error", e.toString());
            return false;
        }
    }

    public ArrayList<HashMap<String, String>> getMealData(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> mealList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM UserMeals", null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("date", cursor.getString(1));
                map.put("title", cursor.getString(2));
                map.put("calories", cursor.getString(3));
                map.put("protein", cursor.getString(4));
                map.put("carbs", cursor.getString(5));
                map.put("fibre", cursor.getString(6));
                map.put("fat", cursor.getString(7));
                map.put("sugar", cursor.getString(8));
                map.put("sodium", cursor.getString(9));
                map.put("potassium", cursor.getString(10));
                map.put("vitA", cursor.getString(11));
                map.put("vitC", cursor.getString(12));
                map.put("calcium", cursor.getString(13));
                map.put("iron", cursor.getString(14));
                mealList.add(map);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();

        return mealList;
    }

    public void deleteMealRecord(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{id});
    }

    public void updateMeal(String id, String title, String calories, String protein, String carbs, String fibre, String fat,
                           String sugar, String sodium, String potassium, String vitaminA, String vitaminC,
                           String calcium, String iron){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, title);
        values.put(CALORIES, calories);
        values.put(PROTEIN, protein);
        values.put(CARBS, carbs);
        values.put(FIBRE, fibre);
        values.put(FAT, fat);
        values.put(SUGAR, sugar);
        values.put(SODIUM, sodium);
        values.put(POTASSIUM, potassium);
        values.put(VITAMIN_A, vitaminA);
        values.put(VITAMIN_C, vitaminC);
        values.put(CALCIUM, calcium);
        values.put(IRON, iron);
        db.update(TABLE_NAME, values, "id=?", new String[]{id});
        db.close();
    }


}

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

public class ActivityDatabaseHelper extends android.database.sqlite.SQLiteOpenHelper {
        public static final String DB_NAME = "FitnessApp";
        public static final int DB_VERSION = 2;
        public static final String TABLE_NAME = "UserWorkouts";

        public static final String ID = "id";
        public static final String TITLE = "title";
        private static final String DATE = "date";
        public static final String ACTIVITY = "activity";
        public static final String CALORIES = "calories";

        DateHelper dateHelper;

        public ActivityDatabaseHelper(@Nullable Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " TEXT NOT NULL, " + TITLE + " TEXT NOT NULL, " + ACTIVITY + " TEXT NOT NULL, " + CALORIES + " TEXT NOT NULL)";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS UserWorkouts");
            onCreate(db);
        }

        public boolean addActivity(String title, String activity, String calories){
            dateHelper = new DateHelper();
        Log.d("AddActivity in SQLite", "Method called");
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TITLE, title);
            values.put(DATE, dateHelper.getDate());
            values.put(ACTIVITY, activity);
            values.put(CALORIES, calories);
            db.insert(TABLE_NAME, null, values);
            db.close();
            return true;
        } catch (Exception e) {
            Log.d("AddActivity Error", e.toString());
            return false;
        }
    }

    public ArrayList<HashMap<String, String>> getActivityData(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> activityList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM UserWorkouts", null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("date", cursor.getString(1));
                map.put("title", cursor.getString(2));
                map.put("activity", cursor.getString(3));
                map.put("calories", cursor.getString(4));
                activityList.add(map);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();

        return activityList;
    }

    public void deleteActivityRecord(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{id});
    }

    public void updateActivity(String id, String title, String activity, String calories, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, title);
        values.put(ACTIVITY, activity);
        values.put(CALORIES, calories);
        values.put(DATE, date);
        db.update(TABLE_NAME, values, "id=?", new String[]{id});
        db.close();
    }


}

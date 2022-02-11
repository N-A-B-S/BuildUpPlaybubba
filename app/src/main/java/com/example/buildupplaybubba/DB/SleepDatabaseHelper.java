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

public class SleepDatabaseHelper extends android.database.sqlite.SQLiteOpenHelper {
    public static final String DB_NAME = "FitnessApp";
    public static final int DB_VERSION = 2;
    public static final String TABLE_NAME = "UserSleep";

    public static final String ID = "id";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTime";


    public SleepDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + START_DATE + " TEXT NOT NULL, " + END_DATE + " TEXT NOT NULL, " + START_TIME + " TEXT NOT NULL, " + END_TIME + " TEXT NOT NULL)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS UserSleep");
        onCreate(db);
    }

    public boolean addSleep(String startDate, String endDate, String startTime, String endTime){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(START_DATE, startDate);
            values.put(END_DATE, endDate);
            values.put(START_TIME, startTime);
            values.put(END_TIME, endTime);
            db.insert(TABLE_NAME, null, values);
            db.close();
            return true;
        } catch (Exception e) {
            Log.d("AddActivity Error", e.toString());
            return false;
        }
    }

    public ArrayList<HashMap<String, String>> getSleepData(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<HashMap<String, String>> sleepList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM UserSleep", null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("startDate", cursor.getString(1));
                map.put("endDate", cursor.getString(2));
                map.put("startTime", cursor.getString(3));
                map.put("endTime", cursor.getString(4));
                sleepList.add(map);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return sleepList;
    }

    public void deleteSleep(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{id});
    }

    public void updateSleep(String id, String startDate, String endDate, String startTime, String endTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(START_DATE, startDate);
        values.put(END_DATE, endDate);
        values.put(START_TIME, startTime);
        values.put(END_TIME, endTime);
        db.update(TABLE_NAME, values, "id=?", new String[]{id});
        db.close();

    }

}

package com.example.buildupplaybubba.DateHelper;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

    public String getDate(){
        //Return String of date in DD/MM/YYYY format. Using this as the key for the SharedPreferences, so I can get the steps for the day.
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        Date todaysDate = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(todaysDate);
        String date = sdf.format(todaysDate);
//        Calendar calendar = Calendar.getInstance();
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int month =  calendar.get(Calendar.MONTH); //Calendar.MONTH starts at 0. E.g. 0 = Jan, 1 = Feb etc.
//        int year = calendar.get(Calendar.YEAR);
//        String date = day + "/" + (month + 1) + "/" + year;
        Log.d("Date: ", date);
        return date;
    }

    public ArrayList<String> getLast7Dates(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
        Date currentDate = Calendar.getInstance().getTime();
        ArrayList<String> lastSevenDates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();


        for (int i = 1; i < 7; i++) {
            calendar.setTime(currentDate);
            calendar.add(Calendar.DAY_OF_YEAR, -i);
            Date newDate = calendar.getTime();
            String date = sdf.format(newDate);

            lastSevenDates.add(date);
        }
        return lastSevenDates;
    }
}

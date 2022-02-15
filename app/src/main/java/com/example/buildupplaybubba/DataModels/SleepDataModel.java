package com.example.buildupplaybubba.DataModels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class SleepDataModel {
    private String dbID;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String sleepDuration;
    private String deepSleepDuration;

    public SleepDataModel(String dbID, String startDate, String endDate, String startTime, String endTime) {
        this.dbID = dbID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDbID() {
        return dbID;
    }

    public void setDbID(String dbID) {
        this.dbID = dbID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSleepDuration() throws ParseException {
        String sleepDuration = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.UK);

        String startDateTime = startDate + " " + startTime;
        String endDateTime = endDate + " " + endTime;

        Date firstDate = dateFormat.parse(startDateTime);
        Date secondDate = dateFormat.parse(endDateTime);

        long diff = secondDate.getTime() - firstDate.getTime();
        long secondInMillis = 1000;
        long minutesInMillis = secondInMillis * 60;
        long hourInMillis = minutesInMillis * 60;
        long dayInMillis = hourInMillis * 24;

        long elapsedDays = diff / dayInMillis;
        int elapsedDaysInt = (int) elapsedDays;
        diff = diff % dayInMillis;
        int day = (int) (elapsedDaysInt * 0.17);

        long elapsedHours = diff / hourInMillis;
        int elapsedHoursInt = (int) elapsedHours;
        diff = diff % hourInMillis;
        int hour = (int) (elapsedHoursInt * 0.17);

        long elapsedMinutes = diff / minutesInMillis;
        int elapsedMinutesInt = (int) elapsedMinutes;
        diff = diff % minutesInMillis;
        int minutes = (int) (elapsedMinutesInt * 0.17);

        if (elapsedDaysInt == 0){
            sleepDuration = String.format("%d hours, %d minutes%n", elapsedHoursInt, elapsedMinutesInt);
        } else {
            sleepDuration = String.format("%d days, %d hours, %d minutes%n", elapsedDaysInt, elapsedHoursInt, elapsedMinutesInt);
        }

        String deepSleepDuration;

        if (day == 0){
            deepSleepDuration = String.format("%d hours, %d minutes%n", hour, minutes);
        } else {
            deepSleepDuration = String.format("%d days, %d hours, %d minutes%n", day, hour, minutes);
        }

        setDeepSleepDuration(deepSleepDuration);

        return sleepDuration;
    }

    public String getDeepSleepDuration() {
        return deepSleepDuration;
    }

    public void setDeepSleepDuration(String deepSleepDuration) {
        this.deepSleepDuration = deepSleepDuration;
    }
}

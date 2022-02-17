package com.example.buildupplaybubba.DataModels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import android.util.Log;

import junit.framework.TestCase;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SleepDataModelTest extends TestCase {

    @Test
    public void testStringDateConcatenation(){
        String startDate = "16/02/2022";
        String startTime = "11:09";

        String startDateTime = startDate + " " + startTime;

        assertEquals(startDateTime, "16/02/2022 11:09");
    }

    @Test
    public void testStringToDateParse() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.UK);

        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, 2022);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 16);
        cal.set(Calendar.HOUR_OF_DAY, 11);
        cal.set(Calendar.MINUTE, 9);
        cal.set(Calendar.SECOND, 0);

        Date d = cal.getTime();

        String startDate = "16/02/2022";
        String startTime = "11:09";

        String startDateTime = startDate + " " + startTime;

        Date firstDate = dateFormat.parse(startDateTime);

        //assertEquals(expected, actual);
        assertEquals(d.getTime(), firstDate.getTime());
        assertEquals(d.toString(), firstDate.toString());

        String secondDateTime = startDate + " " + "11";
        Exception exception = assertThrows(ParseException.class, () -> {
            Date secondDate = dateFormat.parse(secondDateTime);
        });

        String expectedMessage = "Unparseable date";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetSleepDurationHoursAndMins() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.UK);
        String startDate = "16/02/2022";
        String endDate = "16/02/2022";
        String startTime = "11:00";
        String endTime = "13:00";

        String startDateTime = startDate + " " + startTime;
        String endDateTime = endDate + " " + endTime;

        Date firstDate = dateFormat.parse(startDateTime);
        Date secondDate = dateFormat.parse(endDateTime);

        long diff = secondDate.getTime() - firstDate.getTime();
        assertEquals(diff, 7200000);

        long secondInMillis = 1000;
        long minutesInMillis = secondInMillis * 60;
        long hourInMillis = minutesInMillis * 60;
        long dayInMillis = hourInMillis * 24;

        long elapsedDays = diff / dayInMillis;
        assertEquals(elapsedDays, 0);
        int elapsedDaysInt = (int) elapsedDays;
        assertEquals(elapsedDaysInt, 0);
        diff = diff % dayInMillis;
        assertEquals(diff, 7200000);

        long elapsedHours = diff / hourInMillis;
        assertEquals(elapsedHours, 2);
        int elapsedHoursInt = (int) elapsedHours;
        assertEquals(elapsedHoursInt, 2);
        diff = diff % hourInMillis;
        assertEquals(diff, 0);

        long elapsedMinutes = diff / minutesInMillis;
        assertEquals(elapsedMinutes, 0);
        int elapsedMinutesInt = (int) elapsedMinutes;
        assertEquals(elapsedMinutesInt, 0);
        diff = diff % hourInMillis;
        assertEquals(diff, 0);

        String duration = "2 hours, 0 minutes";
        String sleepDuration = String.format("%d hours, %d minutes%n", elapsedHoursInt, elapsedMinutesInt);
        assertTrue(sleepDuration.contains(duration));
    }

    @Test
    public void testGetSleepDurationDaysHoursMins() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.UK);
        String startDate = "14/02/2022";
        String endDate = "16/02/2022";
        String startTime = "11:00";
        String endTime = "13:00";

        String startDateTime = startDate + " " + startTime;
        String endDateTime = endDate + " " + endTime;

        Date firstDate = dateFormat.parse(startDateTime);
        Date secondDate = dateFormat.parse(endDateTime);

        long diff = secondDate.getTime() - firstDate.getTime();
        assertEquals(diff, 180000000);

        long secondInMillis = 1000;
        long minutesInMillis = secondInMillis * 60;
        long hourInMillis = minutesInMillis * 60;
        long dayInMillis = hourInMillis * 24;

        long elapsedDays = diff / dayInMillis;
        assertEquals(elapsedDays, 2);
        int elapsedDaysInt = (int) elapsedDays;
        assertEquals(elapsedDaysInt, 2);
        diff = diff % dayInMillis;
        assertEquals(diff, 7200000);

        long elapsedHours = diff / hourInMillis;
        assertEquals(elapsedHours, 2);
        int elapsedHoursInt = (int) elapsedHours;
        assertEquals(elapsedHoursInt, 2);
        diff = diff % hourInMillis;
        assertEquals(diff, 0);

        long elapsedMinutes = diff / minutesInMillis;
        assertEquals(elapsedMinutes, 0);
        int elapsedMinutesInt = (int) elapsedMinutes;
        assertEquals(elapsedMinutesInt, 0);
        diff = diff % hourInMillis;
        assertEquals(diff, 0);

        String duration = "2 days, 2 hours, 0 minutes";
        String sleepDuration = String.format("%d days, %d hours, %d minutes%n", elapsedDaysInt, elapsedHoursInt, elapsedMinutesInt);
        assertTrue(sleepDuration.contains(duration));
    }

    @Test
    public void testEdgeCases(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.UK);
        String startDate = "17/02/2022";
        String endDate = "16/02/2022";
        String startTime = "11:00";
        String endTime = "13:00";

        String startDateTime = startDate + " " + startTime;
        String endDateTime = endDate + " " + endTime;

        Date firstDate = new Date();
        Date secondDate = new Date();

        try {
            firstDate = dateFormat.parse(startDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            secondDate = dateFormat.parse(endDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals(firstDate.getTime(), 1645095600000L);
        assertEquals(secondDate.getTime(), 1645016400000L);

        assertFalse(secondDate.compareTo(firstDate) > 0);

        String startDate2 = "17/02/2022";
        String endDate2 = "18/02/2022";
        String startTime2 = "11:00";
        String endTime2 = "13:00";

        String startDateTime2 = startDate2 + " " + startTime2;
        String endDateTime2 = endDate2 + " " + endTime2;

        Date firstDate2 = new Date();
        Date secondDate2 = new Date();

        try {
            firstDate2 = dateFormat.parse(startDateTime2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            secondDate2 = dateFormat.parse(endDateTime2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        assertEquals(firstDate2.getTime(), 1645095600000L);
        assertEquals(secondDate2.getTime(), 1645189200000L);

        assertTrue(secondDate2.compareTo(firstDate2) > 0);

    }

}
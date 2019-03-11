package com.rj.eventapp;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String getFormattedDateUsingDateAndTime(int dd, int mm, int yyyy, int hh, int min) {

        /*int year = Integer.parseInt(yyyy);
        int month = Integer.parseInt(mm);
        int date = Integer.parseInt(dd);
        int hours = Integer.parseInt(hh);
        int minutes = Integer.parseInt(min);*/

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yyyy);
        cal.set(Calendar.MONTH, mm);
        cal.set(Calendar.DAY_OF_MONTH, dd);
        cal.set(Calendar.HOUR_OF_DAY, hh);
        cal.set(Calendar.MINUTE, min);

        return getFormattedDateUsingTimestamp(cal.getTime().getTime());
    }

    public static String getFormattedTimeUsingDateAndTime(int dd, int mm, int yyyy, int hh, int min) {

        /*int year = Integer.parseInt(yyyy);
        int month = Integer.parseInt(mm);
        int date = Integer.parseInt(dd);
        int hours = Integer.parseInt(hh);
        int minutes = Integer.parseInt(min);*/

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yyyy);
        cal.set(Calendar.MONTH, mm);
        cal.set(Calendar.DAY_OF_MONTH, dd);
        cal.set(Calendar.HOUR_OF_DAY, hh);
        cal.set(Calendar.MINUTE, min);

        return getFormattedTimeUsingTimestamp(cal.getTime().getTime());
    }

    public static String getFormattedDateUsingTimestamp(Long timestamp) {
        DateFormat format = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
        Date date = new Date(timestamp);
        return format.format(date);
    }

    public static String getFormattedTimeUsingTimestamp(Long timestamp) {
        DateFormat format = DateFormat.getTimeInstance(DateFormat.DEFAULT, Locale.getDefault());
        Date date = new Date(timestamp);
        return format.format(date);
    }

}

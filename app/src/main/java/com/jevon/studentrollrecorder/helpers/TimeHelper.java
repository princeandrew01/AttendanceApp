package com.jevon.studentrollrecorder.helpers;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;



/**
 * Created by jevon on 20-Apr-16.
 *
 * Helper class that provides some useful time related functions
 * Updated and Extended by Richard on 6-Dec-18
 */

public class TimeHelper {

    //formats 24hr time to am/pm
    public String formatTime(int hour, int minute){
        String am_pm = "am";
        if(hour>=12 && hour < 24){
            am_pm = "pm";
            hour = hour % 12;
        }
        if(hour == 0) hour = 12;
        DecimalFormat df = new DecimalFormat("00");
        return df.format(hour)+":"+df.format(minute)+" "+am_pm;
    }

    //generate an ID for a session by concatenating the current date and the time the session is scheduled to start
    public String getIDTimeStamp(int startHr){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EE dd-MM-yy", Locale.ENGLISH);
        return df.format(c.getTime())+" "+startHr;
    }

    public int getCurrentHour(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public int getCurrentMinute(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MINUTE);
    }

    public String getCurrDay(){
        Calendar c = Calendar.getInstance();
        return c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    public int getMilitaryTime(int hour, int min){
        return hour*100 + min;
    }

    //Function added from ViewCourseAnalytics Class
    public Date toDate(String dateStr){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd-MM-yy H", Locale.ENGLISH);
        Date sessionDate = new Date();

        try{
            sessionDate = dateFormat.parse(dateStr);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return sessionDate;
    }
    //Function added from ViewCourseAnalytics Class
    public String shortenDate(String longDate){
        String date = "";
        String token=null;
        int tokenNum=1;

        StringTokenizer stringTokenizer = new StringTokenizer(longDate,"-, ");

        while(stringTokenizer.hasMoreTokens()){
            token = stringTokenizer.nextToken();

            if(tokenNum == 2){
                date += token + "-";
            }

            else if(tokenNum == 3){
                date += token + " ";
            }

            else if(tokenNum == 5) {
                date += token;
            }

            tokenNum++;
        }
        return date;
    }

    //Function added from ViewCourseAnalytics Class
    public int getStartHour(String date){
        // consider replacing with String.split
        StringTokenizer stringTokenizer = new StringTokenizer(date);
        String token=null;

        while(stringTokenizer.hasMoreTokens()){
            token = stringTokenizer.nextToken();
        }

        return Integer.valueOf(token);
    }
}

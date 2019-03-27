package com.example.jake.university.timetable.scheduleServClasses;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeController {

    public static int getStudyYearByDate()
    {
        Calendar calendar = Calendar.getInstance();
        int current_study_year = 0;
        if(calendar.get(Calendar.MONTH) > 6 && calendar.get(Calendar.MONTH)<=12)
            current_study_year = calendar.get(Calendar.YEAR);
        else
            current_study_year = calendar.get(Calendar.YEAR) - 1;
        return current_study_year;
    }

    public static int getSemesterByDate()
    {
        Calendar calendar = Calendar.getInstance();
        int current_semestr = 0;
        if((calendar.get(Calendar.MONTH) > 6) && (calendar.get(Calendar.MONTH) <= 12)) { current_semestr = 1; }
        else {current_semestr = 0;}
        return current_semestr;
    }

    public static int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static ArrayList<String> getWeekDate(){
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("d MMM yyyy");
        ArrayList<String> week = new ArrayList<>();
        int j = 0;
        for (int i = 2; i < 9 ; i++)
        {
            int weekday = calendar.get(Calendar.DAY_OF_WEEK);
            if(weekday == 1)
                weekday = 8;
            int days = i-weekday;
            calendar.add(Calendar.DAY_OF_YEAR, days);
            Date date = calendar.getTime();
            week.add(j, df.format(date));
            j++;
        }
        return week;
    }
    public static int getDayOfWeekNumber()
    {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK)-2;
    }
    public static int getWeekTypeByDate()
    {
        Calendar calendar = Calendar.getInstance();
        GregorianCalendar date_first = new GregorianCalendar(getStudyYearByDate(), Calendar.SEPTEMBER, 1);
        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {date_first.add(Calendar.DATE, 2);}
        else if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {date_first.add(Calendar.DATE, 1);}
        int res;
        res = daysBetween(calendar.getTime(), date_first.getTime()) * (-1);
        res = res / 7 % 2 + 1;
        return res;
    }
}

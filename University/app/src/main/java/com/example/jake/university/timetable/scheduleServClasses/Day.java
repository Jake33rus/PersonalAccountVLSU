package com.example.jake.university.timetable.scheduleServClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day
{
    private String dayFullName;
    private String dayShortName;

    public String getDayFullName() {
        return dayFullName;
    }

    public void setDayFullName(String dayFullName) {
        this.dayFullName = dayFullName;
    }

    public String getDayShortName() {
        return dayShortName;
    }

    public void setDayShortName(String dayShortName) {
        this.dayShortName = dayShortName;
    }

    private ArrayList<Lesson> oddWeekPairs;
    private ArrayList<Lesson> evenWeekPairs;

    private static HashMap<Integer, String> timeForLessons = new HashMap()
    {{
        put(1, "8:30-10:00");
        put(2, "10:20-11:50");
        put(3, "12:10-13:40");
        put(4, "14:00-15:30");
        put(5, "15:50-17:20");
        put(6, "17:40-19:10");
        put(7, "19:30-21:00");
    }};

    public ArrayList<Lesson> getEven()
    {return evenWeekPairs;}

    public ArrayList<Lesson> getParsedEven()
    {
        ArrayList<Lesson> parsed = new ArrayList<>();
        for(Lesson l: evenWeekPairs)
        {
            if(l.isPair()==true)parsed.add(l);
        }
        return parsed;
    }

    public ArrayList<Lesson> getOdd()
    {return oddWeekPairs;}

    public ArrayList<Lesson> getParsedOdd()
    {
        ArrayList<Lesson> parsed = new ArrayList<>();
        for(Lesson l: oddWeekPairs)
        {
            if(l.isPair()==true)parsed.add(l);
        }
        return parsed;
    }

    public void addEven(Lesson L)
    {
        evenWeekPairs.add(L);
    }

    public void addOdd(Lesson L)
    {
        oddWeekPairs.add(L);
    }

    public Day()
    {
        oddWeekPairs = new ArrayList<>();
        evenWeekPairs = new ArrayList<>();
    }


    public static String getTime(Integer i)
    {
        return timeForLessons.get(i);
    }
}

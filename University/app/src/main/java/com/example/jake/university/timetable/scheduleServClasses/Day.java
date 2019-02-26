package com.example.jake.university.timetable.scheduleServClasses;

import java.util.ArrayList;

public class Day
{
    private String dayFullName;
    private String dayShortName;

    private ArrayList<Lesson> oddWeekPairs;
    private ArrayList<Lesson> evenWeekPairs;

    public ArrayList<Lesson> getEven()
    {return evenWeekPairs;}

    public ArrayList<Lesson> getOdd()
    {return oddWeekPairs;}

    public void addEven(Lesson L)
    {
        evenWeekPairs.add(L);
    }

    public void addOdd(Lesson L)
    {
        oddWeekPairs.add(L);
    }
}

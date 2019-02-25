package com.example.jake.university.timetable;

import com.example.jake.university.timetable.scheduleServClasses.*;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class WeekSchedule
{
    private ArrayList<Day> weekSchedule;

    public void addDay(Day D)
    {
        weekSchedule.add(D);
    }

    public ArrayList<Day> getSchedule()
    {
        return weekSchedule;
    }

    public WeekSchedule(JSONObject unparsedWeek)
    {}
}

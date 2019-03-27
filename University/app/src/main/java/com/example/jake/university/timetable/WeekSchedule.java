package com.example.jake.university.timetable;

import com.example.jake.university.timetable.scheduleServClasses.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeekSchedule
{
    private ArrayList<Day> weekSchedule;

    public ArrayList<Day> getSchedule()
    {
        return weekSchedule;
    }

    public WeekSchedule(JSONArray unparsedWeek) throws JSONException
    {
        weekSchedule = new ArrayList<>();
        JSONObject jbuf;
        Day dBuf;
        Lesson lBuf;
        String buf;
        String delimetrFirst = ",";
        String delimetrSecond = "\n";
        String subStrFirst[], subbStrSecond[];
        int ix = unparsedWeek.length();

        for(int i =0; i<unparsedWeek.length(); i++)
        {
            dBuf = new Day();
            jbuf = unparsedWeek.getJSONObject(i);
            dBuf.setDayFullName(jbuf.getString("name"));
            dBuf.setDayShortName(jbuf.getString("name_short"));

for(int j = 0;j<=6;j++)
            {
                if(!jbuf.getString("n"+j).equals("null") && !jbuf.getString("n"+j).equals(""))
                {
                    buf = jbuf.getString("n"+j);
                    subStrFirst = buf.split(delimetrFirst);

                    if(subStrFirst.length>=3)
                    {
                        subbStrSecond = subStrFirst[2].split(delimetrSecond);
                        lBuf = new Lesson(subbStrSecond[2], subbStrSecond[1],subStrFirst[1],subbStrSecond[0], Day.getTime(j));

                    }
                    else
                    {
                        subbStrSecond = subStrFirst[1].split(delimetrSecond);
                        lBuf = new Lesson(subbStrSecond[1], "",subbStrSecond[0],"", Day.getTime(j));
                    }
                }
                else
                {
                    lBuf = new Lesson(false);
                }
                dBuf.addEven(lBuf);

                if(!jbuf.getString("z"+j).equals("null") && !jbuf.getString("z"+j).equals(""))
                {
                    buf = jbuf.getString("z"+j);
                    subStrFirst = buf.split(delimetrFirst);
                    if(subStrFirst.length>=3)
                    {
                        subbStrSecond = subStrFirst[2].split(delimetrSecond);
                        lBuf = new Lesson(subbStrSecond[2], subbStrSecond[1],subStrFirst[1],subbStrSecond[0], Day.getTime(j));
                    }
                    else
                    {
                        subbStrSecond = subStrFirst[1].split(delimetrSecond);
                        lBuf = new Lesson(subbStrSecond[1], "",subbStrSecond[0],"", Day.getTime(j));
                    }
                }
                else
                {
                    lBuf = new Lesson(false);
                }
                dBuf.addOdd(lBuf);
            }
             weekSchedule.add(dBuf);
        }
    }

    static public ArrayList<Day> teacherSchedule(JSONArray unparsedWeek) throws JSONException
    {
        ArrayList<Day>  tSchedule = new ArrayList<>();
        JSONObject jbuf;
        Day dBuf;
        Lesson lBuf = null;
        String buf;
        String delimetrFirst = ",";
        String delimetrSecond = "\n";
        String delimetrThird = "\n--------------------------\n";
        String subStrFirst[], subbStrSecond[], subStrThird[];
        int ix = unparsedWeek.length();

        for(int i =0; i<unparsedWeek.length(); i++)
        {
            dBuf = new Day();
            jbuf = unparsedWeek.getJSONObject(i);
            dBuf.setDayFullName(jbuf.getString("name"));
            dBuf.setDayShortName(jbuf.getString("name_short"));


            for(int j = 0;j<=6;j++)
            {
                if(!jbuf.getString("n"+j).equals("null") && !jbuf.getString("n"+j).equals(""))
                {
                    buf = jbuf.getString("n"+j);
                    subStrThird= buf.split(delimetrThird);
                    for (String s:subStrThird)
                    {
                        subStrFirst = s.split(delimetrFirst);

                        if (subStrFirst.length >= 3)
                        {
                            subbStrSecond = subStrFirst[2].split(delimetrSecond);
                            lBuf = new Lesson(subbStrSecond[2], subbStrSecond[1], subStrFirst[1], subbStrSecond[0], Day.getTime(j));

                        }
                        else
                            {
                            subbStrSecond = subStrFirst[1].split(delimetrSecond);
                            lBuf = new Lesson(subbStrSecond[1], "", subbStrSecond[0], "", Day.getTime(j));
                            }
                    }
                }
                else
                {
                    lBuf = new Lesson(false);
                }
                dBuf.addEven(lBuf);

                if(!jbuf.getString("z"+j).equals("null") && !jbuf.getString("z"+j).equals(""))
                {
                    buf = jbuf.getString("z"+j);
                    subStrThird= buf.split(delimetrThird);
                    for (String s:subStrThird)
                    {
                    subStrFirst = s.split(delimetrFirst);
                    if(subStrFirst.length>=3)
                    {
                        subbStrSecond = subStrFirst[2].split(delimetrSecond);
                        lBuf = new Lesson(subbStrSecond[2], subbStrSecond[1],subStrFirst[1],subbStrSecond[0], Day.getTime(j));
                    }
                    else
                    {
                        subbStrSecond = subStrFirst[1].split(delimetrSecond);
                        lBuf = new Lesson(subbStrSecond[1], "",subbStrSecond[0],"", Day.getTime(j));
                    }
                    }
                }
                else
                {
                    lBuf = new Lesson(false);
                }
                dBuf.addOdd(lBuf);
            }
            tSchedule.add(dBuf);
        }
        return  tSchedule;
    }
}

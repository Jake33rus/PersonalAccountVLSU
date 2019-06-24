package com.example.jake.university.profile;

import com.example.jake.university.API.postReq;
import com.example.jake.university.timetable.WeekSchedule;
import com.example.jake.university.timetable.scheduleServClasses.Day;
import com.example.jake.university.timetable.scheduleServClasses.Lesson;
import com.example.jake.university.timetable.scheduleServClasses.TimeController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;

public class Singleton {

    /*Поля класса*/
    String nrec;
    String studentID;
    BigInteger longNrec;
    String bigNrec;
    JSONArray arrExams;
    JSONObject jobj;
    postReq comand;
    ProfileInfo profileInfo;
    WeekSchedule schedule;
    HashSet<String> lecturersList;
    int studentCurse;

    boolean parity;

    /* Конструктор */
    private Singleton(String nrec) throws ExecutionException, InterruptedException, JSONException {
        this.nrec = nrec;
            longNrec = new BigInteger(nrec.replaceFirst("0x8", ""), 16);
        bigNrec = longNrec.toString();
       // setTimetable();
        setProfileInfo();
    }

//TODO: Изменить хранимку с студентов на преподов, проверить входящие данные
    private void setProfileInfo() throws JSONException, ExecutionException, InterruptedException {
        JSONObject obj = new JSONObject();
        postReq comand = new postReq("getData");
        comand.execute("15","vlsu_lk_SotrList",nrec).get();
        JSONArray arr = comand.getjARRAY();
        obj = arr.getJSONObject(0);
        profileInfo = new ProfileInfo(obj.getString("FIO"), obj.getString("UCHST"),
                obj.getString("UCHZV"),
                obj.getString("DOLJ_FULL"), obj.getString("PODR"),
                obj.getString("PODPODR"), obj.getString("PED_STAG"),
                obj.getString("STRAH_STAG"), "email", "tel_num");
    }


    private void setTimetable() throws JSONException, ExecutionException, InterruptedException
    {
     /*   postReq comand = new postReq("getData");
        comand.execute("10", "[dbo].[_A_SCD_StudSchedule]", "2," +bigNrec+ ",0,0,-1,"+
                TimeController.getStudyYearByDate() +","
                +TimeController.getSemesterByDate()+",-1").get();
        JSONArray arr = comand.getjARRAY();
        schedule = new WeekSchedule(arr);*/
    }

    public ProfileInfo getProfileInfo() {return profileInfo;}

    public WeekSchedule getTimetable() {return schedule;}

    public boolean isParity() {
        return parity;
    }

    private static Singleton instance;

    /*Реализация Singleton*/
    public static Singleton getInstance(String nrec) throws ExecutionException, InterruptedException, JSONException {
        if(instance == null)
        {
            instance = new Singleton(nrec);
        }
        return instance;
    }
    public static Singleton getter() {return instance;}

    public ArrayList<Lesson> getTodayPairs()
    {
        ArrayList<Lesson> todayLesson = new ArrayList<>();
        if(TimeController.getWeekTypeByDate() == 1)
           todayLesson = getTimetable().getSchedule().get(TimeController.getDayOfWeekNumber()).getEven();
        else if (TimeController.getWeekTypeByDate() == 2)
            todayLesson = getTimetable().getSchedule().get(TimeController.getDayOfWeekNumber()).getOdd();
        return todayLesson;
    }

    public String getNrec()
    {
        return nrec;
    }
    public String getBigNrec()
    {
        return bigNrec;
    }
}

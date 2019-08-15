package com.example.jake.university.profile;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.jake.university.API.Retrofit.IRetroF;
import com.example.jake.university.API.Retrofit.RetroFController;
import com.example.jake.university.API.Retrofit.pojoes.Request;
import com.example.jake.university.API.Retrofit.pojoes.profileInfo;
import com.example.jake.university.API.postReq;
import com.example.jake.university.Docs.DocWorker;
import com.example.jake.university.Docs.Document;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.jake.university.API.Login.APP_PREFERENCES;

public class Singleton {

    /*Поля класса*/
    String parusID;
    String bigNrec;
    JSONArray arrExams;
    JSONObject jobj;
    postReq comand;
    ProfileInfo profileInfo;
    WeekSchedule schedule;
    profileInfo profInf;
    HashSet<String> lecturersList;

    public void setLogin(String login) {
        this.login = login;
    }

    String login;
    int studentCurse;


    boolean parity;

    /* Конструктор */
    private Singleton() throws ExecutionException, InterruptedException, JSONException {
    }

    public void setParusID(String parusID){
        this.parusID = parusID;
    }

    public void startApplication() throws InterruptedException, ExecutionException, JSONException {
        setProfileInfo();
        setTimetable();
    }

//TODO: проверить вторую хранимку
    private void setProfileInfo() throws JSONException, ExecutionException, InterruptedException {
        JSONObject obj = new JSONObject();
        postReq comand = new postReq("getData");
        comand.execute("15","vlsu_lk_SotrList","Id:"+parusID+",fio:empty,kafId:empty,podrId:empty,TabNum:empty,uslId:empty,kateg:empty").get();
        JSONArray arr = comand.getjARRAY();
        obj = arr.getJSONObject(0);
        profileInfo = new ProfileInfo(obj.getString("FIO"), obj.getString("UCHST"),
                obj.getString("UCHZV"),
                obj.getString("DOLJ_FULL"), obj.getString("PODR"),
                obj.getString("PODPODR"), obj.getString("PED_STAG"),
                obj.getString("STRAH_STAG"), "email", "tel_num");
        postReq comand1 = new postReq("getData");


        comand1.execute("20","AuthData_GetData","0,0,'"+login+"','','','',0,'','',0").get();
        JSONArray arr1 = comand1.getjARRAY();
        JSONObject object = arr1.getJSONObject(0);
        profileInfo.setEmail(object.getString("Email"));
        profileInfo.setTel_numb(object.getString("PhoneNumb"));

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
    public static Singleton getInstance() throws ExecutionException, InterruptedException, JSONException {
        if(instance == null)
        {
            instance = new Singleton();
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

    public String getParusID()
    {
        return parusID;
    }
}

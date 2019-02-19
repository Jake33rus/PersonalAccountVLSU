package com.example.jake.university;

import com.example.jake.university.API.postReq;
import com.example.jake.university.profile.ProfileInfo;
import com.example.jake.university.scholarships.Scholarships;
import com.example.jake.university.timetable.Timetable;
import com.example.jake.university.exams.ExamItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Singleton {

    /*Поля класса*/
    String nrec;
    JSONArray arrExams;
    JSONObject jobj;
    postReq comand;
    ArrayList<ExamItem> arrears;
    ArrayList<ExamItem> upcomingExams;
    ArrayList<ExamItem> passedExams;
    ArrayList<Scholarships> scholarships;
    ProfileInfo profileInfo;
    ArrayList<Timetable> timetables;

    private  static Singleton instance;

    /* Конструктор */
    private Singleton(String nrec) throws ExecutionException, InterruptedException, JSONException {
        this.nrec = nrec;
        setExamsList();
        setArrears();
        setPassedExams();
        setUpcomingExams();
        setProfileInfo();
        setScholarships();
        setTimetable();
    }

    private void setTimetable() {
        timetables = new ArrayList();
    }

    private void setArrears() throws JSONException, ExecutionException, InterruptedException {
        arrears = new ArrayList();
        for (int i=0; i<arrExams.length(); i++) {
            jobj = arrExams.getJSONObject(i);
            if (jobj.getString("Положит").equals("0")) {
                arrears.add(new ExamItem(jobj.getString("Наименование дисциплины"),
                        jobj.getString("Тип"), jobj.getString("Семестр")));
            }
        }
    }

    private void setExamsList() throws ExecutionException, InterruptedException {
        jobj = new JSONObject();
        comand = new postReq();
        comand.execute("10", "A_LKS_GetMarks", nrec).get();
        arrExams = comand.getjARRAY();
    }

    private void setUpcomingExams() throws JSONException {
        upcomingExams = new ArrayList();
        for (int i=0; i<arrExams.length(); i++) {
            jobj = arrExams.getJSONObject(i);
            if (jobj.getString("Оценка").equals("нет оценки") || jobj.getString("Оценка").equals("")) {
                upcomingExams.add(new ExamItem(jobj.getString("Наименование дисциплины"),
                        jobj.getString("Тип"),  jobj.getString("Семестр")));
            }
        }
    }

    private void setPassedExams() throws JSONException {
        passedExams = new ArrayList();
        for (int i=0; i<arrExams.length(); i++) {
            jobj = arrExams.getJSONObject(i);
            if (jobj.getString("Оценка") != "" && jobj.getString("Оценка") != "нет оценки") {
                passedExams.add(new ExamItem(jobj.getString("Наименование дисциплины"),
                        jobj.getString("Тип"),  jobj.getString("Семестр"),
                        jobj.getString("Оценка")));
            }
        }
    }

    private void setScholarships() throws ExecutionException, InterruptedException, JSONException {
        JSONObject jobj = new JSONObject();
        postReq comand = new postReq();
        comand.execute("10","A_LKS_GetBonuses",nrec).get();
        JSONArray arr = comand.getjARRAY();
        scholarships = new ArrayList<>();
        for (int i=0; i<arr.length(); i++)
        {
            jobj=arr.getJSONObject(i);
            scholarships.add(new Scholarships(jobj.getString("Тип"), jobj.getString("Дата начала"),
                    jobj.getString("Дата окончания"), jobj.getString("Сумма")));
        }
    }

    private void setProfileInfo() throws JSONException, ExecutionException, InterruptedException {
        JSONObject obj = new JSONObject();
        postReq comand = new postReq();
        comand.execute("10","A_LKS_GetStudentInfo",nrec).get();
        JSONArray arr = comand.getjARRAY();
        obj = arr.getJSONObject(0);
        profileInfo = new ProfileInfo(obj.getString("ФИО"), obj.getString("Факультет"),
                obj.getString("Источник финансирования обучения"),
                obj.getString("Форма обучения"), obj.getString("Группа"),
                obj.getString("Специальность"), "fdsf", "01.01.2018", obj.getString("Номер контактного телефона"));
    }

    public ArrayList<ExamItem> getArrears(){return arrears;}

    public ArrayList<ExamItem> getUpcomingExams(){return upcomingExams;}

    public ArrayList<ExamItem> getPassedExams() {return passedExams;}

    public ArrayList<Scholarships> getScholarships() {return scholarships;}

    public ProfileInfo getProfileInfo() {return profileInfo;}

    public ArrayList<Timetable> getTimetable() {return timetables;}

    /*Реализация Singleton*/
    public static Singleton getInstance(String nrec) throws ExecutionException, InterruptedException, JSONException {
        if(instance == null){
            instance = new Singleton(nrec);
        }
        return instance;
    }
}

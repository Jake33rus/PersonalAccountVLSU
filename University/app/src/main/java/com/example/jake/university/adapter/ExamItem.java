package com.example.jake.university.adapter;

public class ExamItem {
    String discipline, grade, type, semestr;
    public ExamItem(String discipline, String type, String semestr, String grade){
        this.discipline = discipline;
        this.grade = grade;
        this.type = type;
        this.semestr = semestr;
    }
    public ExamItem(String discipline, String type, String semestr){
        this.discipline = discipline;
        this.type = type;
        this.semestr = semestr;
        this.grade = null;
    }
    public String getDiscipline(){return discipline;}
    public String getGrade(){return grade;}
    public String getType(){return type;}
    public String getSemestr() {return semestr;}
}

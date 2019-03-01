package com.example.jake.university.timetable.scheduleServClasses;

public class Lesson
{
    private String time;
    private String pairName;
    private String teacherName;
    private String pairType;
    private String cabNum;
    private boolean isPair = true;

    public boolean isPair() {
        return isPair;
    }

    public void setPair() {
        isPair =true;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPairName() {
        return pairName;
    }

    public void setPairName(String pairName) {
        this.pairName = pairName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPairType() {
        return pairType;
    }

    public void setPairType(String pairType) {
        this.pairType = pairType;
    }

    public String getCabNum() {
        return cabNum;
    }

    public void setCabNum(String cabNum) {
        this.cabNum = cabNum;
    }

    public Lesson(String pairName, String teacherName, String pairType, String cabNum)
    {
        this.pairName = pairName;
        this.teacherName = teacherName;
        this.pairType = pairType;
        this.cabNum = cabNum;
    }

    public Lesson(boolean isPair)
    {
        this.isPair=isPair;
        this.pairName = "";
        this.teacherName = "";
        this.pairType = "";
        this.cabNum = "";
    }
}

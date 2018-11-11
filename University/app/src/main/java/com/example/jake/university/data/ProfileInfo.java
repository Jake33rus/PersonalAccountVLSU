package com.example.jake.university.data;

public class ProfileInfo
{
    public ProfileInfo(String FIO, String faculty, String finType, String educForm, String group, String speciality, String photo, String startDate, String phoneNum) {
        this.FIO = FIO;
        Faculty = faculty;
        FinType = finType;
        EducForm = educForm;
        Group = group;
        Speciality = speciality;
        Photo = photo;
        StartDate = startDate;
        PhoneNum = phoneNum;
    }

    public ProfileInfo() {
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }

    public String getFinType() {
        return FinType;
    }

    public void setFinType(String finType) {
        FinType = finType;
    }

    public String getEducForm() {
        return EducForm;
    }

    public void setEducForm(String educForm) {
        EducForm = educForm;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    private String FIO;
    private String Faculty;
    private String FinType;
    private String EducForm;
    private String Group;
    private String Speciality;
    private String Photo;
    private String StartDate;
    private String PhoneNum;


}

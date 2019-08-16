package com.example.jake.university.individualPlans;

public class Plan
{
    private String institute;
    private String cafedra;
    private String cafConsDate;
    private String charge;
    private String EKP;
    private String status;
    private String confirmDate;
    private String postType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Plan(String institute, String cafedra, String cafConsDate, String charge, String EKP,
                String status, String confirmDate, String postType, String name) {
        this.institute = institute;
        this.cafedra = cafedra;
        this.cafConsDate = cafConsDate;
        this.charge = charge;
        this.EKP = EKP;
        this.status = status;
        this.confirmDate = confirmDate;
        this.postType = postType;
        this.name = name;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getCafedra() {
        return cafedra;
    }

    public void setCafedra(String cafedra) {
        this.cafedra = cafedra;
    }

    public String getCafConsDate() {
        return cafConsDate;
    }

    public void setCafConsDate(String cafConsDate) {
        this.cafConsDate = cafConsDate;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getEKP() {
        return EKP;
    }

    public void setEKP(String EKP) {
        this.EKP = EKP;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }
}

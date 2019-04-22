package com.example.jake.university.payment;

public class PaymentItem {


    private String id;
    private String summ;
    private String dateCreate;
    private String datePay;
    private String payment;
    private String peny;
    private String studyYear;
    private String semestr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;

    public String getSumm() {
        return summ;
    }

    public void setSumm(String summ) {
        this.summ = summ;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDatePay() {
        return datePay;
    }

    public void setDatePay(String datePay) {
        this.datePay = datePay;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPeny() {
        return peny;
    }

    public void setPeny(String peny) {
        this.peny = peny;
    }

    public String getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(String studyYear) {
        this.studyYear = studyYear;
    }

    public String getSemestr() {
        return semestr;
    }

    public void setSemestr(String semestr) {
        this.semestr = semestr;
    }

    public PaymentItem(String id, String summ, String dateCreate, String datePay, String payment, String peny, String studyYear) {
        this.summ = summ;
        this.id = id;
        this.dateCreate = dateCreate;
        this.datePay = datePay;
        this.payment = payment;
        this.peny = peny;
        this.studyYear = studyYear;
    }
    public PaymentItem(String id, String summ, String dateCreate, String datePay, String payment, String studyYear) {
        this.id = id;
        this.summ = summ;
        this.dateCreate = dateCreate;
        this.datePay = datePay;
        this.payment = payment;
        this.studyYear = studyYear;
    }
}

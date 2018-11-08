package com.example.jake.university.data;

public class Exams {

    String disciplineName;
    String mark;
    String dateSurrender;
    String term;

    public Exams(String disciplineName, String mark, String dateSurrender, String term) {
        this.disciplineName = disciplineName;
        this.mark = mark;
        this.dateSurrender = dateSurrender;
        this.term = term;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDateSurrender() {
        return dateSurrender;
    }

    public void setDateSurrender(String dateSurrender) {
        this.dateSurrender = dateSurrender;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }


}

package com.example.jake.university.ranked;

public class RankedItem {
    String discipline;
    String rating1;
    String rating2;

    public RankedItem() {
    }

    public RankedItem(String discipline, String rating1, String rating2, String rating3) {

        this.discipline = discipline;
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.rating3 = rating3;
    }

    public String getDiscipline() {

        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getRating1() {
        return rating1;
    }

    public void setRating1(String rating1) {
        this.rating1 = rating1;
    }

    public String getRating2() {
        return rating2;
    }

    public void setRating2(String rating2) {
        this.rating2 = rating2;
    }

    public String getRating3() {
        return rating3;
    }

    public void setRating3(String rating3) {
        this.rating3 = rating3;
    }

    String rating3;
}

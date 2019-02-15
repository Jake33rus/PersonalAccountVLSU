package com.example.jake.university.data;

public class Scholarships {
    private String type, summ, preDate, postDate;

    public Scholarships(String type,  String preDate, String postDate, String summ) {
        this.type = type;
        this.summ = summ;
        this.preDate = preDate;
        this.postDate = postDate;
    }

    public String getType() {
        return type;
    }

    public String getSumm() {
        return summ;
    }

    public String getPreDate() {
        return preDate;
    }

    public String getPostDate() {
        return postDate;
    }
}

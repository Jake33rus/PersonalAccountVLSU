package com.example.jake.university;

public class NewsItem
{
    private int id;
    private String title;
    private String date;
    private String imgUrl;

    public NewsItem(int id, String title, String date, String imgUrl)
    {
        this.id = id;
        this.title = title;
        this.date = date;
        this.imgUrl = imgUrl;
    }

    public int getId() { return id; }

    public String getTitle()
    {
        return  title;
    }

    public String getDate()
    {
        return  date;
    }

    public String getImgUrl()
    {
        return  imgUrl;
    }
}

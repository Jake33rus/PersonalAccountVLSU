package com.example.jake.university;

public class NewsItem
{
    private String title;
    private String date;
    private String imgUrl;

    public NewsItem(String title, String date, String imgUrl)
    {
        this.title = title;
        this.date = date;
        this.imgUrl = imgUrl;
    }

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

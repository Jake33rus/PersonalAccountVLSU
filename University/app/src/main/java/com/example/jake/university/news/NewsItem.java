package com.example.jake.university.news;

public class NewsItem
{
    private String title;
    private String date;
    private String imgUrl;
    private String URL;

    public NewsItem(String title, String date, String imgUrl, String URL)
    {
        this.title = title;
        this.date = date;
        this.imgUrl = imgUrl;
        this.URL = URL;
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

    public String getURL()
    {
        return  URL;
    }
}

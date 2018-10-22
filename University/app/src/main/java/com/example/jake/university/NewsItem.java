package com.example.jake.university;

public class NewsItem
{
    private String Title;
    private String Date;
    private String imgUrl;

    public NewsItem(String a, String b, String c)
    {
        Title = a;
        Date = b;
        imgUrl = c;
    }

    public String getTitle()
    {
        return  Title;
    }

    public String getDate()
    {
        return  Date;
    }

    public String getImgUrl()
    {
        return  imgUrl;
    }
}

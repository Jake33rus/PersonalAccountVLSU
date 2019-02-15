package com.example.jake.university.API;

public class Hash
{
    private static int UserID;

    public static void SetID(int n)
    {
        UserID = n;
    }

    public static int GetId()
    {
        return UserID;
    }
}

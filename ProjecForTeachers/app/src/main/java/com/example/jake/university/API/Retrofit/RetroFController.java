package com.example.jake.university.API.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFController
{
    private static RetroFController mInstance;
    private static String BASE_URL ;
    private Retrofit mRetrofit;

    public static RetroFController getInstance(String baseUrl) {
        if (mInstance == null)
        {
            BASE_URL = baseUrl;
            mInstance = new RetroFController();
        }
        return mInstance;
    }


    private RetroFController() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public IRetroF getJSONApi()
    {
        return mRetrofit.create(IRetroF.class);
    }
}

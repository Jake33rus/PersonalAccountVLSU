package com.example.jake.university.API.Retrofit;

import com.example.jake.university.API.Retrofit.pojoes.Request;
import com.example.jake.university.API.Retrofit.pojoes.profileInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRetroF
{
    @POST("GetTable")
    public Call<profileInfo> profInfo(@Body Request request);
}

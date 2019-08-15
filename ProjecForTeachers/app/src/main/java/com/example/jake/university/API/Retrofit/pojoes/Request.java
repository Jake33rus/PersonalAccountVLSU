package com.example.jake.university.API.Retrofit.pojoes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("idDb")
    @Expose
    private String idDb;
    @SerializedName("nameExec")
    @Expose
    private String nameExec;
    @SerializedName("paramsList")
    @Expose
    private String paramsList;

    public String getIdDb() {
        return idDb;
    }

    public void setIdDb(String idDb) {
        this.idDb = idDb;
    }

    public String getNameExec() {
        return nameExec;
    }

    public void setNameExec(String nameExec) {
        this.nameExec = nameExec;
    }

    public String getParamsList() {
        return paramsList;
    }

    public void setParamsList(String paramsList) {
        this.paramsList = paramsList;
    }

    public Request (String iddb, String nameexec, String params)
    {
        idDb = iddb;
        nameExec = nameexec;
        paramsList = params;
    }

}
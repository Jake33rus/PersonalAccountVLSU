package com.example.jake.university.individualPlans;

import com.example.jake.university.API.postReq;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Plan
{
    private String institute;
    private String cafedra;
    private String cafConsDate;
    private String charge;
    private String EKP;
    private String status;
    private String confirmDate;
    private String postType;
    private String ID;
    private boolean visibleFlag;
    private HashMap<String,Character> symbToNumb;

    public boolean isVisibleFlag() {
        return visibleFlag;
    }

    public void setVisibleFlag(boolean visibleFlag) {
        this.visibleFlag = visibleFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Plan(String institute, String cafedra, String cafConsDate, String charge, String EKP,
                String status, String confirmDate, String postType, String name, String ID) {
        this.institute = institute;
        this.cafedra = cafedra;
        this.cafConsDate = cafConsDate;
        this.charge = charge;
        this.EKP = EKP;
        this.status = status;
        this.confirmDate = confirmDate;
        this.postType = postType;
        this.name = name;
        this.visibleFlag = false;

        symbToNumb = new HashMap<String, Character>();

        char c = 'A';
        for (int i=0;i<25;i++)
        {
            symbToNumb.put(Integer.toString(i),c);
            c++;
        }
    }

    //TODO Доделать скачивание плана
    static public String decodeId(String ID)
    {
        String result = "";
       for(int i=0;i<ID.length();i++)
       {
           int symBuder = Character.getNumericValue(ID.charAt(i));
           int numBuder = 'A'+symBuder;
           result+=(char)numBuder;
       }
       return result;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getCafedra() {
        return cafedra;
    }

    public void setCafedra(String cafedra) {
        this.cafedra = cafedra;
    }

    public String getCafConsDate() {
        return cafConsDate;
    }

    public void setCafConsDate(String cafConsDate) {
        this.cafConsDate = cafConsDate;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getEKP() {
        return EKP;
    }

    public void setEKP(String EKP) {
        this.EKP = EKP;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public static ArrayList<Plan> planParser (String lecID)
    {
        JSONObject obj = new JSONObject();
        postReq comand = new postReq("getData");
        ArrayList<Plan> plans = new ArrayList();
        try {
            comand.execute("20","[dbo].[PPS_PlanTitle_GetDataGal]","0,'', 0, '', 0, 0, 0, '', '',0,281474976716985").get();
            JSONArray arr = comand.getjARRAY();
            for(int i = 0; i< arr.length();i++)
            {
                obj = arr.getJSONObject(i);
                plans.add(new Plan(obj.getString("Inst_L_Name"),obj.getString("Kaf_L_Name"),obj.getString("ZasKafDate"),
                        obj.getString("Bet"),obj.getString("EkpName"),obj.getString("StatusName"),"-",
                        obj.getString("RangType"),"Индивидуальный план на "+obj.getString("StudyYears")+", кафедра "+obj.getString("KafName"),
                        obj.getString("ID")));
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return plans;
    }


}

package com.example.jake.university.payment;

import com.example.jake.university.API.postReq;
import com.example.jake.university.profile.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class PaymentGetter
{
    public PaymentGetter()
    {}

    public JSONArray hostelGetter()
    {
        JSONObject jobj = new JSONObject();
        postReq comand = new postReq("getData");

        try {
            comand.execute("5", "get_kvit_listnew", Singleton.getInstance("").getNrec());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray paymentArr = comand.getjARRAY();

        return paymentArr;
    }

    public JSONArray receiptGetter()
    {
        JSONObject jobj = new JSONObject();
        postReq comand = new postReq("getData");
        String kek ="";
        try {
            kek = Singleton.getInstance("").getBigNrec();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            comand.execute("10","0","select * from _getReceiptsPaymentsList(2,"+kek+",0)").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONArray paymentArr = comand.getjARRAY();
       return paymentArr;
    }

    public String[] idGetter(JSONArray jar)
    {
        JSONObject obj;
        String[] idBuf = new String[jar.length()];
        for (int i = 0; i < jar.length(); i++) {
            try {
                obj = jar.getJSONObject(i);
                idBuf[i] = obj.getString("nrec");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return idBuf;
    }

    public String pdfGetter(String kvitId)
    {
        JSONObject jobj = new JSONObject();
        postReq comand = new postReq("getReceipt", "kvitID","studID","type");

        try {
            comand.execute(kvitId, Singleton.getInstance("").getNrec(), "1");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray paymentArr = comand.getjARRAY();
        byte[] buff=comand.
        String str="";
        try {
            str = paymentArr.getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return str;

    }

}

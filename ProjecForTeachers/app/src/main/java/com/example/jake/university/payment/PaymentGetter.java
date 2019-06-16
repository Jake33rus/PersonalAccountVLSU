package com.example.jake.university.payment;

import android.content.pm.PackageManager;
import android.os.Environment;

import com.example.jake.university.API.postReq;
import com.example.jake.university.profile.Singleton;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.ExecutionException;

public class PaymentGetter
{
    public PaymentGetter()
    {}

    public JSONArray hostelGetter(String nrec)
    {
        JSONObject jobj = new JSONObject();
        postReq comand = new postReq("getData");

        comand.execute("5", "get_kvit_listnew", nrec);

        JSONArray paymentArr = new JSONArray();
        paymentArr = comand.getjARRAY();

        if(paymentArr==null)return null;
        return paymentArr;
    }

    public JSONArray receiptGetter(String nrec)
    {
        JSONObject jobj = new JSONObject();
        postReq comand = new postReq("getData");
        String kek ="";

        try {
            comand.execute("10","0","select * from _getReceiptsPaymentsList(2,"+nrec+",0)").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONArray paymentArr = new JSONArray();
        paymentArr = comand.getjARRAY();
        if(paymentArr==null)return null;
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

    public File pdfGetter(String kvitId, String name)
    {
        JSONObject jobj = new JSONObject();
        postReq comand = new postReq("getReceipt", "kvitID","studID","type");

        try {
            comand.execute(kvitId, Singleton.getInstance("").getNrec(), "0");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        byte[] buffer = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            try {
                buffer = Base64.getDecoder().decode(comand.getString().getBytes());
            }catch (Exception e)
            {e.printStackTrace();}
        }


        File docsFolder = new File(Environment.getExternalStorageDirectory()+"/Documents");
        if(!docsFolder.exists())
        {
            docsFolder.mkdir();
        }
        File pdfFile = new File(docsFolder.getAbsolutePath(), name+".pdf");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pdfFile);
            fos.write(buffer);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return pdfFile;

    }

}

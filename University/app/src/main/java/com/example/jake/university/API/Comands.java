package com.example.jake.university.API;


import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class Comands {

    public static String urlApi()
    {   return "http://hqvla7327aw01:8000//api/";   }
    //вызов процедуры или функции из бд
    //результат в виде массива json
    public static JSONObject GetTableArray(String idDb, String nameExec, String paramsList) throws NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        JSONObject jObject = new JSONObject();
        TripleDES crypter = new TripleDES();
        idDb = "xХСУ?┼5";
        paramsList = "5 ЬЪп\u0002п9бЬ?є)\u0015yЛMчy<<i\u0013З";
        nameExec = "\u0016\"}Ь\"Tg,СТ8'':\u0015Ф-+P{л\"?┐";
        /*idDb = crypter.Encr(idDb);
        paramsList = crypter.Encr(paramsList);
        nameExec = crypter.Encr(nameExec);*/


        try {

            Map<String,String> info = new LinkedHashMap<>();

            info.put("idDb", idDb);
            info.put("nameExec", nameExec);
            info.put("paramsList", paramsList);

            StringBuilder postData = new StringBuilder();

            for (Map.Entry<String,String> inf : info.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(inf.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(inf.getValue()), "UTF-8"));
            }

            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            URL url = new URL(urlApi()+"GetTable");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty( "charset", "utf-8");
            connection.setDoOutput(true);
            connection.getOutputStream().write(postDataBytes);

            String result = new String(postDataBytes);

            JSONParser helper = new JSONParser();
            jObject = (JSONObject) helper.parse(result);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jObject;
    }
}
//http://qaru.site/questions/22859/java-sending-http-parameters-via-post-method-easily
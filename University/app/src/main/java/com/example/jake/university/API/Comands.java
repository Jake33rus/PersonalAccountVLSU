package com.example.jake.university.API;

import android.net.Uri;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


class Comands {

    //вызов процедуры или функции из бд
    //результат в виде массива json
    public static JSONObject GetTableArray(String idDb, String nameExec, String paramsList) {
        JSONObject jObject = new JSONObject();
        TripleDES crypter = new TripleDES();
        idDb = crypter.Encr(idDb);
        paramsList = crypter.Encr(paramsList);
        nameExec = crypter.Encr(nameExec);

        /*WebClient myWebClient = new WebClient();*/
        HttpClient myWebClient;
        HttpPost post = new HttpPost(MvcApplication.urlApi + "GetTable");
        List<NameValuePair> info;


            info = new ArrayList<NameValuePair>();
            info.add(new BasicNameValuePair("idDb", idDb));
            info.add(new BasicNameValuePair("nameExec", nameExec));
            info.add(new BasicNameValuePair("paramsList", paramsList));


            RequestConfig requestConfig = RequestConfig.custom()
                    .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                    .build();

            CredentialsProvider cc = new BasicCredentialsProvider();
            myWebClient = HttpClients.custom()
                    .setDefaultCredentialsProvider(cc)
                    .setDefaultRequestConfig(requestConfig)
                    .build();

            try {
                post.setEntity(new UrlEncodedFormEntity(info));

                byte[] buff = myWebClient.execute(post).toString().getBytes(StandardCharsets.UTF_8);
                String result = new String(buff);

                try {
                    JSONParser helper = new JSONParser();
                    jObject = (JSONObject) helper.parse(result);
                    }catch (ParseException parse) {}
                    // Invalid syntax



            } catch (IOException enc) {
                enc.printStackTrace();


            }

        return jObject;
    }
}

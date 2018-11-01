package com.example.jake.university.API;

import org.json.JSONArray;
import org.json.JSONObject;

class Comands {

    //вызов процедуры или функции из бд
    //результат в виде массива json
    public static JSONObject GetTableArray(String idDb, String nameExec, String paramsList)
    {
        JSONObject jObject = new JSONObject();
        TripleDES crypter = new TripleDES();
        idDb = crypter.Encr(idDb);
        paramsList = crypter.Encr(paramsList);
        nameExec = crypter.Encr(nameExec);

        WebClient myWebClient = new WebClient();
        var info = new NameValueCollection
        {
            { "idDb", idDb },
            { "nameExec",  nameExec},
            { "paramsList",  paramsList},
        };
        CredentialCache cc = new CredentialCache();
        cc.Add(
                new Uri(MvcApplication.urlApi + "GetTable"),
                "NTLM",
                CredentialCache.DefaultNetworkCredentials);
        myWebClient.Credentials = cc;


        String result = Encoding.UTF8.GetString(myWebClient.UploadValues(MvcApplication.urlApi + "GetTable", "POST", info));
        jObject = JSONArray.Parse(result);

        return jObject;
    }
}

package com.example.jake.university.API;

/*public class Comands {
    public static void RegisterMail(String login, String password, String email, String type)
    {
        //JObject - в шарпе - библиотека для работы с JSON надо найти аналог на java и прикрутить
        JObject jObject;
        try
        {


            using (var webClient = new WebClient())
            {
                //Аналога для var в java нет, хз че делать!
                var info = new NameValueCollection
                {
                    { "Email", Encryption.Encr(email)},
                    { "Password", Encryption.Encr(password)},
                    { "Name",Encryption.Encr( login)},
                    { "Type",Encryption.Encr( type)}
                };
                //Че это за класс тоже хз, подозреваю что то тоже из JSON
                CredentialCache cc = new CredentialCache();
                cc.Add(
                        new Uri(MvcApplication.urlApi + "Mail/GetS/"),
                        "NTLM",

                        CredentialCache.DefaultNetworkCredentials);
                webClient.Credentials = cc;


                // Посылаем параметры на сервер
                // Может быть ответ в виде массива байт
                byte[] responseArray = webClient.UploadValues(MvcApplication.urlApi +
                        "Mail/GetS/", "POST", info);
                String resul = Encoding.UTF8.GetString(responseArray);
                jObject = JObject.Parse("{ 'result':" + resul + "}");

            }
        }
        catch (WebException e)
        {
            //хз что делать с гребаными потоками
            System.IO.Stream stream = e.Response.GetResponseStream();
            System.IO.StreamReader sr = new System.IO.StreamReader(stream);
            String resul = sr.ReadToEnd();
            throw new Exception(resul);
        }


    }



    //вызов процедуры или функции из бд
    //результат в виде массива json
    public static JArray GetTableArray(String idDb, String nameExec, String paramsList)
    {
        JArray jObject = new JArray();
        try
        {
            idDb = Encryption.Encr(idDb);
            paramsList = Encryption.Encr(paramsList);
            nameExec = Encryption.Encr(nameExec);

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
            jObject = JArray.Parse(result);
        }
        catch (WebException e)
        {
            System.IO.Stream stream = e.Response.GetResponseStream();
            System.IO.StreamReader sr = new System.IO.StreamReader(stream);
            String resul = sr.ReadToEnd();
            throw new Exception(resul);
        }
        return jObject;
    }
}*/

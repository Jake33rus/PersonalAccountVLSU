package com.example.jake.university.API;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class postReq extends AsyncTask<String, Void, Void>
{
    private JSONArray jARRAY = null;
    private String type = "";
    private String firstArgName="idDb", secArgName="nameExec", thirdArgName = "paramsList";
    private int responseCode;
    private byte[] bytes;
    private String string;
    ProgressDialog progressDialog;
    int progressIncr = 1;
    public JSONArray getjARRAY() {
        return jARRAY;
    }

    public postReq(String type)
    {
        this.type=type;
    }

    public postReq(String type, String firstArgName, String secArgName, String thirdArgName)
    {
        this.type=type;
        this.firstArgName=firstArgName;
        this.secArgName=secArgName;
        this.thirdArgName=thirdArgName;
        responseCode = 0;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected Void doInBackground(String... params)
    {
        try {
            jARRAY = createReq(params[0], params[1], params[2]);
        } catch ( Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);
    }

    protected JSONArray createReq(String idDb, String nameExec, String paramsList) throws IOException, ParseException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException
    {
        JSONObject jObj;
        JSONArray jArr = new JSONArray();
        JSONParser jsonParser=new JSONParser();
        TripleDES coder = new TripleDES();
        String myURL = "http://172.18.14.137:3000/"+type;
        byte[] data = null;
        InputStream is = null;

        try {

            /*idDb = coder.Encr(idDb);
            nameExec = coder.Encr(nameExec);
            paramsList = coder.Encr(paramsList);*/

            Map<String,String> info = new LinkedHashMap<>();

            info.put(firstArgName, idDb);
            info.put(secArgName, nameExec);
            info.put(thirdArgName, paramsList);


//            "idDb", idDb);
//            info.put("nameExec", nameExec);
//            info.put("paramsList"

            StringBuilder postData = new StringBuilder();

            for (Map.Entry<String,String> inf : info.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(inf.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(inf.getValue()), "UTF-8"));
            }

            URL url = new URL(myURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Content-Length", "" + Integer.toString(postData.toString().getBytes().length));
            OutputStream os = null;
            try {
                os = conn.getOutputStream();
                data = postData.toString().getBytes("UTF-8");
            }
            catch (Exception e){
                e.printStackTrace();
            }
            os.write(data);
            data = null;

            conn.connect();
            responseCode= conn.getResponseCode();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            is = conn.getInputStream();

            byte[] buffer = new byte[8192];
            int bytesRead;
            try {
                while ((bytesRead = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesRead);
                }
            }catch(Exception e)
            {e.printStackTrace();}

            data = baos.toByteArray();
            bytes = data;

            String finalRes =  new String(data, StandardCharsets.UTF_8);
            string=finalRes;
            //String[] parts = finalRes.split();
            jArr = new JSONArray(finalRes);


        }catch(Exception e)
        {e.printStackTrace();}
       /*catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }*/

        return jArr;
    }

    public int getResponseCode()
    {return responseCode;}

    static public String[] getLogin(String login, String password)
    {
        TripleDES tde = new TripleDES();
        JSONArray arr;
        JSONObject obj = new JSONObject();
        postReq comand = new postReq("getData");
        String[] arrg = {"0", login, Integer.toString(comand.getResponseCode())};

        try {
            comand.execute("20","AuthData_GetData",
                    "0, 0,'"+login+"', '"+MD5.hash(tde.Encr(password))+"','','', 0,'','', 0").get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        arr = comand.getjARRAY();
        int i = comand.getResponseCode();
        arrg[2]=Integer.toString(i);
        if (arr.length()!=0)
        {
            try {
                obj = arr.getJSONObject(0);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String CPerson = new String();
            try {
                CPerson =  obj.getString("CPerson");
            } catch (Exception e) {
                e.printStackTrace();
            }
//Этап 2. Получаем PerID

            postReq comand2 = new postReq("getData");

            try {
                comand2.execute("20","dbo.UserProfiles_GetData","0,'',0,"+CPerson+",0,0,0,0").get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            arr = comand2.getjARRAY();

            try {
                obj = arr.getJSONObject(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String PerID = new String();

            try {
                PerID =  obj.getString("PerID");
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Этап 3. Получаем nrec

            postReq comand3 = new postReq("getData");
            try {
                comand3.execute("10","A_LKS_GetUserIdsByTabNumb",CPerson).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            arr = comand3.getjARRAY();

            try {
                obj = arr.getJSONObject(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String parusID = new String();

            try {
                parusID =  obj.getString("ParusID");
            } catch (Exception e) {
                e.printStackTrace();
            }

            arrg[0] = parusID;
            return arrg;

        }
        else return arrg;
    }

    public static String getAttachments(String docID)
    {
        String filename = "_";
        postReq comand = new postReq("getData");
        try {
            comand.execute("35","[dbo].[WorkDocFiles_GetList]","0,"+docID+",0,0,'',0").get();
            JSONArray jar = comand.getjARRAY();
            JSONObject job = jar.getJSONObject(0);
            filename = job.getString("FileName");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return filename;
    }

    public byte[] getBytes()
    {return bytes;}
    public String getString()
    {return string;}
}

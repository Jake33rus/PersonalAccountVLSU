package com.example.jake.university.API;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class postReq extends AsyncTask<String, Void, Void>
{
    private JSONArray jARRAY = null;

    public JSONArray getjARRAY() {
        return jARRAY;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... params)
    {
        try {
            jARRAY = createReq(params[0], params[1], params[2]);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);
    }

    protected JSONArray createReq(String idDb, String nameExec, String paramsList) throws IOException, ParseException {
        JSONArray jObj = new JSONArray();
        JSONParser jsonParser=new JSONParser();
        TripleDES coder = new TripleDES();
        String myURL = "http://localhost:3001/api";
        byte[] data = null;
        InputStream is = null;

        try {

           /* idDb = coder.Encr(idDb);
            nameExec = coder.Encr(nameExec);
            paramsList = coder.Encr(paramsList);*/

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
            int responseCode= conn.getResponseCode();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            is = conn.getInputStream();

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            data = baos.toByteArray();

            String finalRes =  new String(data, StandardCharsets.UTF_8);
            jObj = (JSONArray)jsonParser.parse(finalRes);

            jARRAY = jObj;

            }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } /*catch (NoSuchPaddingException e) {
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

        return jObj;
    }


}

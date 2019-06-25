package com.example.jake.university.Docs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DocWorker

{
    public DocWorker()
    {}

    public ArrayList<Document> getDocList(JSONArray ja)
    {
        ArrayList<Document> docs = new ArrayList<>();
        Document buf;
        for (int i = 0; i < ja.length(); i++)
        {
            try {
                JSONObject jo = ja.getJSONObject(i);
                buf = new Document(jo.getString("DocDate"), jo.getString("DocNumb"), jo.getString("DocTitle"),
                        jo.getString("DocBeginDate"), jo.getString("DocEndDate"), jo.getString("DocTypeID"),
                        jo.getString("DocType_S_Name"), jo.getString("DocStatusID"), jo.getString("DocStatus_S_Name"),
                        jo.getString("Descr"), jo.getString("CreatorFIO"), jo.getString("TitleAndNumber"),
                        jo.getString("Templates"), jo.getString("Files"), jo.getString("Authors"),
                        jo.getString("IsViewed"));
                docs.add(buf);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return docs;
    }
}

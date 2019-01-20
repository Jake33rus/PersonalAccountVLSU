package com.example.jake.university.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jake.university.API.postReq;
import com.example.jake.university.R;
import com.example.jake.university.adapter.ExamItem;
import com.example.jake.university.adapter.ScholarshipsAdapter;
import com.example.jake.university.data.Scholarships;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class FragmentScholarships extends Fragment {
    private static final int LAYOUT = R.layout.fragment_scholarships;
    private View view;
    JSONArray arr;
    JSONObject jobj;
    postReq comand;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(LAYOUT, container, false);
        ScholarshipsAdapter adapter = null;
        try {
            ArrayList<Scholarships> list = ReadInDB();
            adapter = new ScholarshipsAdapter(view.getContext(), R.layout.item_sholarships, list);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListView lv = (ListView) view.findViewById(R.id.lv_scholarship);
        lv.setAdapter(adapter);
        return view;
    }


    protected ArrayList<Scholarships> ReadInDB() throws ExecutionException, InterruptedException, JSONException {
       /* jobj = new JSONObject();
        comand = new postReq();
        comand.execute("10","A_LKS_GetBonuses","0x8001000000027C02").get();
        arr = comand.getjARRAY();
        ArrayList<Scholarships> list = new ArrayList<>();
        for (int i=0; i<arr.length(); i++)
        {
            jobj=arr.getJSONObject(i);
            list.add(new Scholarships(jobj.getString("Тип"), jobj.getString("Дата начала"),
                    jobj.getString("Дата окончания"), jobj.getString("Сумма")));
        }*/
       ArrayList<Scholarships> list = new ArrayList<>();
       list.add(new Scholarships("Академическая стипендия","01.09.2017","31.01.2018","1653"));
       list.add(new Scholarships("Академическая стипендия","01.02.2018","31.07.2018","2066"));
       list.add(new Scholarships("Академическая стипендия","01.08.2018","31.01.2019","2066"));
        return list;
    }
}

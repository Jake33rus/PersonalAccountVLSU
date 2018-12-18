package com.example.jake.university.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jake.university.API.postReq;
import com.example.jake.university.R;
import com.example.jake.university.adapter.ArrearsAdapter;
import com.example.jake.university.adapter.ExamItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentUpcomingExams extends Fragment {
    private static final int LAYOUT = R.layout.fragment_upcoming_exams;
    private View view;
    private ListView lv;
    JSONArray arr;
    JSONObject jobj;
    postReq comand;
    public static FragmentUpcomingExams getInstance() {
        Bundle args = new Bundle();
        FragmentUpcomingExams fragment = new FragmentUpcomingExams();
        fragment.setArguments(args);

        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        lv = (ListView) view.findViewById(R.id.lv_upcom_exams);
        ArrearsAdapter adapter = null;
        try {
            adapter = new ArrearsAdapter(view.getContext(), R.layout.item_arrears, ReadInDB());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lv.setAdapter(adapter);
        return view;
    }
    protected ArrayList<ExamItem> ReadInDB() throws ExecutionException, InterruptedException, JSONException {
        jobj = new JSONObject();
        comand = new postReq();
        comand.execute("10","A_LKS_GetMarks","0x8001000000027C02").get();
        arr = comand.getjARRAY();
        ArrayList<ExamItem> list = new ArrayList();
        for (int i=0; i<arr.length(); i++) {
            jobj = arr.getJSONObject(i);
            if (jobj.getString("Оценка") == "нет оценки" || jobj.getString("Оценка") == "") {
                list.add(new ExamItem(jobj.getString("Наименование дисциплины"),
                        jobj.getString("Тип"),  jobj.getString("Семестр")));
            }
        }
        return list;
    }
}
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

public class FragmentArrears extends Fragment {

    private static final int LAYOUT = R.layout.fragment_arrears;
    private View view;
    JSONArray arr;
    JSONObject jobj;
    postReq comand;
    public static FragmentArrears getInstance() {
        Bundle args = new Bundle();
        FragmentArrears fragment = new FragmentArrears();
        fragment.setArguments(args);

        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        ArrearsAdapter adapter = null;
        try {
            ArrayList<ExamItem> list = ReadInDB();
            adapter = new ArrearsAdapter(view.getContext(), R.layout.item_arrears, list);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListView lv = (ListView) view.findViewById(R.id.lv_arrears);
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
            String result = jobj.getString("Оценка");
            if (result == result || result == "не явка") {
                list.add(new ExamItem(jobj.getString("Наименование дисциплины"),
                        jobj.getString("Семестр"),
                        jobj.getString("Тип")));
            }
        }
        return list;
    }
}

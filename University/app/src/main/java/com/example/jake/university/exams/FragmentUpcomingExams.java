package com.example.jake.university.exams;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jake.university.R;
import com.example.jake.university.profile.Singleton;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentUpcomingExams extends Fragment {
    private static final int LAYOUT = R.layout.fragment_upcoming_exams;
    private View view;
    private ListView lv;
    Singleton singleton = Singleton.getInstance("0");

    public FragmentUpcomingExams() throws InterruptedException, ExecutionException, JSONException {
    }

    /* JSONArray arr;
     JSONObject jobj;
     postReq comand;*/
    public static FragmentUpcomingExams getInstance() throws InterruptedException, ExecutionException, JSONException {
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
        ArrearsAdapter adapter = new ArrearsAdapter(view.getContext(), R.layout.item_arrears, singleton.getUpcomingExams());
        lv.setAdapter(adapter);
        return view;
    }
   /* protected ArrayList<ExamItem> ReadInDB() throws ExecutionException, InterruptedException, JSONException {
       /* jobj = new JSONObject();
        comand = new postReq();
        comand.execute("10","A_LKS_GetMarks","0x8001000000027C02").get();
        arr = comand.getjARRAY();
        ArrayList<ExamItem> list = new ArrayList();
        for (int i=0; i<arr.length(); i++) {
            jobj = arr.getJSONObject(i);
            if (jobj.getString("Оценка").equals("нет оценки") || jobj.getString("Оценка").equals("")) {
                list.add(new ExamItem(jobj.getString("Наименование дисциплины"),
                        jobj.getString("Тип"),  jobj.getString("Семестр")));
            }
        }*/
      /*  ArrayList<ExamItem> list = new ArrayList();
        list.add(new ExamItem("Ядерная физика, теория квантования квантовых квантов", "Лиффириентированный зачет", "2", "Неудволитворительно"));
        list.add(new ExamItem("Ядерная физика, теория квантования квантовых квантов", "Лиффириентированный зачет", "2", "Неудволитворительно"));
        list.add(new ExamItem("Ядерная физика, теория квантования квантовых квантов", "Лиффириентированный зачет", "2", "Неудволитворительно"));
        return list;
    }*/
}
package com.example.jake.university.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jake.university.R;
import com.example.jake.university.adapter.ArrearsAdapter;
import com.example.jake.university.adapter.ExamItem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentUpcomingExams extends Fragment {
    private static final int LAYOUT = R.layout.fragment_upcoming_exams;
    private View view;
    private ListView lv;
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
        lv = (ListView) view.findViewById(R.id.lv_arrears);
        ArrearsAdapter adapter = new ArrearsAdapter(view.getContext(), R.layout.item_arrears, ReadInDB());
        lv.setAdapter(adapter);
        return view;
    }
    protected ArrayList<ExamItem> ReadInDB()
    {
        ArrayList<ExamItem> list = new ArrayList();
        return list;
    }
}
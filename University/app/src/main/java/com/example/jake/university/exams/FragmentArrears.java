package com.example.jake.university.exams;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jake.university.R;
import com.example.jake.university.profile.Singleton;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentArrears extends Fragment {

    private static final int LAYOUT = R.layout.fragment_arrears;
    private View view;
    Singleton singleton = Singleton.getInstance("0");

    public FragmentArrears() throws InterruptedException, ExecutionException, JSONException {
    }

    public static FragmentArrears getInstance() throws InterruptedException, ExecutionException, JSONException {
        Bundle args = new Bundle();
        FragmentArrears fragment = new FragmentArrears();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        ArrayList<ExamItem> list = singleton.getArrears();
        ArrearsAdapter adapter = new ArrearsAdapter(view.getContext(), R.layout.item_arrears, list);
        ListView lv = (ListView) view.findViewById(R.id.lv_arrears);
        lv.setAdapter(adapter);
        return view;
    }
}



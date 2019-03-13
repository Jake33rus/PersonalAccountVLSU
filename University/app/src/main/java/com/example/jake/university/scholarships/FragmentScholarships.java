package com.example.jake.university.scholarships;

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
import com.example.jake.university.profile.Singleton;

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
    Singleton singleton = Singleton.getInstance("0");

    public FragmentScholarships() throws InterruptedException, ExecutionException, JSONException {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(LAYOUT, container, false);
        ScholarshipsAdapter adapter = null;
        ArrayList<Scholarships> list = singleton.getScholarships();
        adapter = new ScholarshipsAdapter(view.getContext(), R.layout.item_sholarships, list);
        ListView lv = (ListView) view.findViewById(R.id.lv_scholarship);
        lv.setAdapter(adapter);
        return view;
    }
}

package com.example.jake.university.timetable;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.jake.university.R;
import com.example.jake.university.exams.TabsPagerFragmentAdapter;
import com.example.jake.university.profile.Singleton;
import com.example.jake.university.timetable.scheduleServClasses.Day;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

public class FragmentTimetable extends Fragment {
    private static final int LAYOUT = R.layout.fragment_time_table;
    private View view;
    private ViewPager viewPager;
    private FragmentActivity myContext;
    Singleton singleton = Singleton.getInstance("0");

    public FragmentTimetable() throws InterruptedException, ExecutionException, JSONException {
    }

    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        TimetableAdapter adapter = null;
        ArrayList<Day> daysArr = singleton.getTimetable().getSchedule();
        adapter = new TimetableAdapter(view.getContext(), R.layout.item_timetable_day, daysArr);
        ListView lv = (ListView) view.findViewById(R.id.daysLV);
        lv.setAdapter(adapter);
        return view;
    }
}

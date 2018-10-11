package com.example.jake.university.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jake.university.R;
import com.example.jake.university.adapter.TabsPagerFragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

public class FragmentExamsAndArrears extends Fragment {
    private static final int LAYOUT = R.layout.fragment_exams_arrears;
    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentActivity myContext;

    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        initTabs();
        return view;
    }

    private void initTabs() {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        FragmentManager fragmentManager = myContext.getSupportFragmentManager();
        TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}

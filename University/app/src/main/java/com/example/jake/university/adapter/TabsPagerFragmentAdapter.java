package com.example.jake.university.adapter;


import com.example.jake.university.fragments.FragmentArrears;
import com.example.jake.university.fragments.FragmentPassedExams;
import com.example.jake.university.fragments.FragmentUpcomingExams;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabsPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[] {
                "Задолженности",
                "Сданные экзамены",
                "Предстоящие экзамены"
        };
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public androidx.fragment.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentArrears.getInstance();
            case 1:
                return FragmentPassedExams.getInstance();
            case 2:
                return FragmentUpcomingExams.getInstance();
        }
        return null;
    }
    @Override
    public int getCount() {
        return tabs.length;
    }
}

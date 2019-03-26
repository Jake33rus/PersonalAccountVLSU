package com.example.jake.university.timetable;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jake.university.R;
import com.example.jake.university.adapter.UIUtiles;
import com.example.jake.university.profile.Singleton;
import com.example.jake.university.timetable.scheduleServClasses.Day;
import com.example.jake.university.timetable.scheduleServClasses.Lesson;
import com.example.jake.university.timetable.scheduleServClasses.TimeController;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

public class FragmentTimetable extends Fragment {
    private static final int LAYOUT = R.layout.fragment_time_table;
    private View view;
    private ViewPager viewPager;
    private FragmentActivity myContext;
    private boolean parity;
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
        CardView cv = (CardView) view.findViewById(R.id.cardView6);
        TimetableAdapter adapter = null;
        ArrayList<Day> days = singleton.getTimetable().getSchedule();
        ArrayList<Lesson> lessons= null;
        TextView numberOfPairs = (TextView) view.findViewById(R.id.chooseTeacher);

        int size = 0;
        for (Lesson lesson: singleton.getTodayPairs()) {
            if(lesson.isPair())
                size++;
        }
        String pair;
        if(size == 1)
            pair = " пара";
        else if(size >= 5)
            pair = " пар";
        else
            pair = " пары";
        numberOfPairs.setText("Сегодня " + size + pair);
        TextView numbersOfWeek = (TextView) view.findViewById(R.id.numbersOfWeekTV);
        TextView parityOfWeek = (TextView) view.findViewById(R.id.parityOfWeekTV);
        if(TimeController.getWeekTypeByDate() == 1)
            parity = true;
        if(TimeController.getWeekTypeByDate() == 2)
            parity = false;
        TimetableAdapter adapter1 = null;
        ListView lv1 = (ListView) view.findViewById(R.id.pairLV1);
        TextView date1 = (TextView) view.findViewById(R.id.dateTV1);
        TextView dayOfWeek1 = (TextView) view.findViewById(R.id.dayOfWeek1);
        TimetableAdapter adapter2 = null;
        ListView lv2 = (ListView) view.findViewById(R.id.pairLV2);
        TextView date2 = (TextView) view.findViewById(R.id.dateTV2);
        TextView dayOfWeek2 = (TextView) view.findViewById(R.id.dayOfWeek2);
        TimetableAdapter adapter3 = null;
        ListView lv3 = (ListView) view.findViewById(R.id.pairLV3);
        TextView date3 = (TextView) view.findViewById(R.id.dateTV3);
        TextView dayOfWeek3 = (TextView) view.findViewById(R.id.dayOfWeek3);
        TimetableAdapter adapter4 = null;
        ListView lv4 = (ListView) view.findViewById(R.id.pairLV4);
        TextView date4 = (TextView) view.findViewById(R.id.dateTV4);
        TextView dayOfWeek4 = (TextView) view.findViewById(R.id.dayOfWeek4);
        TimetableAdapter adapter5 = null;
        ListView lv5 = (ListView) view.findViewById(R.id.pairLV5);
        TextView date5 = (TextView) view.findViewById(R.id.dateTV5);
        TextView dayOfWeek5 = (TextView) view.findViewById(R.id.dayOfWeek5);
        TimetableAdapter adapter6 = null;
        ListView lv6 = (ListView) view.findViewById(R.id.pairLV6);
        TextView date6 = (TextView) view.findViewById(R.id.dateTV6);
        TextView dayOfWeek6 = (TextView) view.findViewById(R.id.dayOfWeek6);
        if(parity) {
            parityOfWeek.setText("Эта неделя - числитель");
            //numberOfPairs.setText(days.get(TimeController.getDayOfWeekNumber()).getEven().size());
            adapter1 = new TimetableAdapter(view.getContext(), R.layout.item_pair, days.get(0).getParsedEven());
            lv1.setAdapter(adapter1);
            adapter2 = new TimetableAdapter(view.getContext(), R.layout.item_pair, days.get(1).getParsedEven());
            lv2.setAdapter(adapter2);
            adapter3 = new TimetableAdapter(view.getContext(), R.layout.item_pair, days.get(2).getParsedEven());
            lv3.setAdapter(adapter3);
            adapter4 = new TimetableAdapter(view.getContext(), R.layout.item_pair, days.get(3).getParsedEven());
            lv4.setAdapter(adapter4);
            adapter5 = new TimetableAdapter(view.getContext(), R.layout.item_pair, days.get(4).getParsedEven());
            lv5.setAdapter(adapter5);
            if(days.get(5).getParsedEven().size()==0)
                cv.setVisibility(View.GONE);
            adapter6 = new TimetableAdapter(view.getContext(), R.layout.item_pair, days.get(5).getParsedEven());
            lv6.setAdapter(adapter6);
        }
        else {
            parityOfWeek.setText("Эта неделя - знаменатель");
            //umberOfPairs.setText(days.get(TimeController.getDayOfWeekNumber()).getOdd().size());
            adapter1 = new TimetableAdapter(view.getContext(), R.layout.item_pair, days.get(0).getParsedOdd());
            lv1.setAdapter(adapter1);
            adapter2 = new TimetableAdapter(view.getContext(), R.layout.item_pair, days.get(1).getParsedOdd());
            lv2.setAdapter(adapter2);
            adapter3 = new TimetableAdapter(view.getContext(), R.layout.item_pair, days.get(2).getParsedOdd());
            lv3.setAdapter(adapter3);
            adapter4 = new TimetableAdapter(view.getContext(), R.layout.item_pair, days.get(3).getParsedOdd());
            lv4.setAdapter(adapter4);
            adapter5 = new TimetableAdapter(view.getContext(), R.layout.item_pair, days.get(4).getParsedOdd());
            lv5.setAdapter(adapter5);
            if(days.get(5).getParsedOdd().size()==0)
                cv.setVisibility(View.GONE);
            adapter6 = new TimetableAdapter(view.getContext(), R.layout.item_pair, days.get(5).getParsedOdd());
            lv6.setAdapter(adapter6);
        }
        UIUtiles.setListViewHeightBasedOnItems(lv1);
        UIUtiles.setListViewHeightBasedOnItems(lv2);
        UIUtiles.setListViewHeightBasedOnItems(lv3);
        UIUtiles.setListViewHeightBasedOnItems(lv4);
        UIUtiles.setListViewHeightBasedOnItems(lv5);
        UIUtiles.setListViewHeightBasedOnItems(lv6);
        ArrayList<String> weekDates = TimeController.getWeekDate();
        numbersOfWeek.setText(weekDates.get(0) + " - " + weekDates.get(6));
        dayOfWeek1.setText("Понедельник");
        date1.setText(weekDates.get(0));
        dayOfWeek2.setText("Вторник");
        date2.setText(weekDates.get(1));
        dayOfWeek3.setText("Среда");
        date3.setText(weekDates.get(2));
        dayOfWeek4.setText("Четверг");
        date4.setText(weekDates.get(3));
        dayOfWeek5.setText("Пятница");
        date5.setText(weekDates.get(4));
                dayOfWeek6.setText("Суббота");
        date6.setText(weekDates.get(5));
        return view;
    }

}

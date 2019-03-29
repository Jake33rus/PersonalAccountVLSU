package com.example.jake.university.timetable;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jake.university.API.postReq;
import com.example.jake.university.R;
import com.example.jake.university.adapter.UIUtiles;
import com.example.jake.university.timetable.scheduleServClasses.Day;
import com.example.jake.university.timetable.scheduleServClasses.TimeController;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

public class FragmentLecturerSchedule extends Fragment {
    private static final int LAYOUT = R.layout.fragment_teacher_timetable;
    private View view;
    private ViewPager viewPager;
    private FragmentActivity myContext;
    JSONArray schedule;
    private CardView cv1, cv2, cv3, cv4, cv5, cv6;
    private ListView lv1,lv2,lv3,lv4,lv5,lv6;
    private TextView date1, date2,date3, date4, date5, date6, dayOfWeek1, dayOfWeek2, dayOfWeek3,
            dayOfWeek4, dayOfWeek5, dayOfWeek6;
    private TimetableAdapter adapter1, adapter2, adapter3, adapter4, adapter5, adapter6;
    @Override
    public void onAttach(Context context) {
        myContext = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        cv1 = (CardView) view.findViewById(R.id.lecCardView1);
        cv2 = (CardView) view.findViewById(R.id.lecCardView2);
        cv3 = (CardView) view.findViewById(R.id.lecCardView3);
        cv4 = (CardView) view.findViewById(R.id.lecCardView4);
        cv5 = (CardView) view.findViewById(R.id.lecCardView5);
        cv6 = (CardView) view.findViewById(R.id.lecCardView6);
        lv1 = (ListView) view.findViewById(R.id.lecturePairLV1);
        date1 = (TextView) view.findViewById(R.id.lectureDateTV1);
        dayOfWeek1 = (TextView) view.findViewById(R.id.lectureDayOfWeek1);
        adapter2 = null;
        lv2 = (ListView) view.findViewById(R.id.lecturePairLV2);
        date2 = (TextView) view.findViewById(R.id.lectureDateTV2);
        dayOfWeek2 = (TextView) view.findViewById(R.id.lectureDayOfWeek2);
        adapter3 = null;
        lv3 = (ListView) view.findViewById(R.id.lecturePairLV3);
        date3 = (TextView) view.findViewById(R.id.lectureDateTV3);
        dayOfWeek3 = (TextView) view.findViewById(R.id.lectureDayOfWeek3);
        adapter4 = null;
        lv4 = (ListView) view.findViewById(R.id.lecturePairLV4);
        date4 = (TextView) view.findViewById(R.id.lectureDateTV4);
        dayOfWeek4 = (TextView) view.findViewById(R.id.lectureDayOfWeek4);
        adapter5 = null;
        lv5 = (ListView) view.findViewById(R.id.lecturePairLV5);
        date5 = (TextView) view.findViewById(R.id.lectureDateTV5);
        dayOfWeek5 = (TextView) view.findViewById(R.id.lectureDayOfWeek5);
        adapter6 = null;
        lv6 = (ListView) view.findViewById(R.id.lecturePairLV6);
        date6 = (TextView) view.findViewById(R.id.lectureDateTV6);
        dayOfWeek6 = (TextView) view.findViewById(R.id.lectureDayOfWeek6);
        final TextView lecFIO = (TextView) view.findViewById(R.id.lecFioTV);
        Button seachLec = (Button) view.findViewById(R.id.butDisplayList);
        seachLec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lectureFio = lecFIO.getText().toString();
                TimeController controller = new TimeController();
                if(lectureFio.equals(""))
                {
                    Toast toast = Toast.makeText(myContext, "Введите ФИО преподавателя",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
                else {
                    postReq comand = new postReq();
                    try {
                        comand.execute("10", "[dbo].[A_LKS_GetLecturesListGal]", "0,0,0,'','" + lectureFio + "','','',0,0").get();
                    } catch (ExecutionException | InterruptedException e) {
                        Toast toast = Toast.makeText(myContext, "Такого преподавателя нет!",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                    JSONArray arr = comand.getjARRAY();
                    try {
                        String lecNrec = arr.getJSONObject(0).getString("nrec_lec");
                        postReq comand2 = new postReq();
                        comand2.execute("10", "[dbo].[_A_SCD_StudSchedule]", "2,'" + lecNrec +
                                "',0,0,-1,"+TimeController.getStudyYearByDate()+","+
                                TimeController.getSemesterByDate()+",-1").get();
                        schedule = comand2.getjARRAY();

                    } catch (JSONException | InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    try {
                        initSchedule();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return view;
    }
    private void initSchedule() throws JSONException {
        ArrayList<Day> days = WeekSchedule.teacherSchedule(schedule);
        if(TimeController.getWeekTypeByDate() == 1) {
            if(days.get(0).getParsedEven().size()>0)
                cv1.setVisibility(View.VISIBLE);
            if(days.get(1).getParsedEven().size()>0)
                cv2.setVisibility(View.VISIBLE);
            if(days.get(2).getParsedEven().size()>0)
                cv3.setVisibility(View.VISIBLE);
            if(days.get(3).getParsedEven().size()>0)
                cv4.setVisibility(View.VISIBLE);
            if(days.get(4).getParsedEven().size()>0)
                cv5.setVisibility(View.VISIBLE);
            if(days.get(5).getParsedEven().size()>0)
                cv6.setVisibility(View.VISIBLE);
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
            adapter6 = new TimetableAdapter(view.getContext(), R.layout.item_pair, days.get(5).getParsedEven());
            lv6.setAdapter(adapter6);
        }
        if(TimeController.getWeekTypeByDate() == 2) {
            if(days.get(0).getParsedOdd().size()>0)
                cv1.setVisibility(View.VISIBLE);
            if(days.get(1).getParsedOdd().size()>0)
                cv2.setVisibility(View.VISIBLE);
            if(days.get(2).getParsedOdd().size()>0)
                cv3.setVisibility(View.VISIBLE);
            if(days.get(3).getParsedOdd().size()>0)
                cv4.setVisibility(View.VISIBLE);
            if(days.get(4).getParsedOdd().size()>0)
                cv5.setVisibility(View.VISIBLE);
            if(days.get(5).getParsedOdd().size()>0)
                cv6.setVisibility(View.VISIBLE);
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
    }
}

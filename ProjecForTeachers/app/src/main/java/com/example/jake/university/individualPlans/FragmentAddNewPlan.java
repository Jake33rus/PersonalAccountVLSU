package com.example.jake.university.individualPlans;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.jake.university.R;

public class FragmentAddNewPlan extends Fragment {
    Button addPlan, close;
    EditText dateAssigment, dateConsideration, studyYear, rate;
    Spinner institute, kafedra, ekp, typePost;

    public FragmentAddNewPlan() {
        //TODO добавить формирование списков для спинера
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_add_plan, container, false);
        addPlan =(Button) view.findViewById(R.id.buttonAdd);
        close = (Button) view.findViewById(R.id.buttonClose);
        addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO добавить отправку данных на сервер
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO добавить возвращение назад
            }
        });
        return view;
    }
}

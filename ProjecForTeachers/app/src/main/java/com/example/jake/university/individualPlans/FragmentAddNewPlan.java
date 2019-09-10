package com.example.jake.university.individualPlans;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.jake.university.API.postReq;
import com.example.jake.university.R;

import java.util.ArrayList;

public class FragmentAddNewPlan extends Fragment {
    Button addPlan, close;
    EditText dateAssigment, dateConsideration, studyYear, rate;
    Spinner institute, kafedra, ekp, typePost;
    FragmentTransaction ftrans;

    public FragmentAddNewPlan()
    {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        String[] ekps = {"ЭКП-1", "ЭКП-2", "-"};
        String[] rang_types = {"Основная", "Внутреннеее совмещение", "Внешнее совмещение"};
        View view = inflater.inflate(R.layout.fragment_add_plan, container, false);
        FragmentManager fm;

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
            public void onClick(View v)
            {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, new PersonalPlansFragment());
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        institute = (Spinner) view.findViewById(R.id.spinnerInstitute);
        ArrayAdapter<String> adapterInstitute = new ArrayAdapter<String>(this.getActivity(), R.layout.support_simple_spinner_dropdown_item, postReq.getAllInsts());
        institute.setAdapter(adapterInstitute);
        kafedra = (Spinner) view.findViewById(R.id.spinnerKafedra);
        ArrayAdapter<String> adapterKafedra = new ArrayAdapter<String>(this.getActivity(), R.layout.support_simple_spinner_dropdown_item, postReq.getAllFaks());
        kafedra.setAdapter(adapterKafedra);
        ekp = (Spinner) view.findViewById(R.id.spinnerEKP);
        ArrayAdapter<String> adapterEKP = new ArrayAdapter<String>(this.getActivity(), R.layout.support_simple_spinner_dropdown_item, ekps);
        ekp.setAdapter(adapterEKP);
        typePost = (Spinner) view.findViewById(R.id.spinnerType);
        ArrayAdapter<String> adapterRang = new ArrayAdapter<String>(this.getActivity(), R.layout.support_simple_spinner_dropdown_item, rang_types);
        typePost.setAdapter(adapterRang);
        return view;
    }
}

package com.example.jake.university.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jake.university.API.postReq;
import com.example.jake.university.R;
import com.example.jake.university.data.ProfileInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FragmentProfile extends androidx.fragment.app.Fragment {
    View view;
    TextView tvFIO, tvGroup, tvInstitut, tvKafedra, tvStartStudy, tvStudyForm, tvFinans, tvMobile, tvEmail;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        JSONArray arr = new JSONArray();
        JSONObject obj = new JSONObject();
        postReq comand = new postReq();
        comand.execute("","","");
        arr = comand.getjARRAY();
        try {
            obj = arr.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ProfileInfo info = null;
        try {
            info = new ProfileInfo(obj.getString("ФИО"), obj.getString("Факультет"),
                    obj.getString("Источник финансирования обучения"),
                    obj.getString("Форма обучения"), obj.getString("Группа"),
                    obj.getString("Специальность"), obj.getString("Фото"),
                    obj.getString("Дата поступления"),
                    obj.getString("Номер контактного телефона"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initViews();
        tvFIO.setText(info.getFIO());
        tvGroup.setText(info.getGroup());
        tvInstitut.setText(info.getFaculty());//
        tvKafedra.setText(info.getGroup());//
        tvStartStudy.setText(info.getStartDate());
        tvStudyForm.setText(info.getEducForm());
        tvFinans.setText(info.getFinType());
        tvMobile.setText(info.getPhoneNum());
        return view;
    }
    void initViews() {
        tvFIO = (TextView) view.findViewById(R.id.text_view_fio);
        tvGroup = (TextView) view.findViewById(R.id.text_view_group);
        tvInstitut = (TextView) view.findViewById(R.id.text_view_institute);
        tvKafedra = (TextView) view.findViewById(R.id.text_view_kafedra);
        tvStartStudy = (TextView) view.findViewById(R.id.text_view_startValue);
        tvStudyForm = (TextView) view.findViewById(R.id.text_view_studyFormValue);
        tvFinans = (TextView) view.findViewById(R.id.text_view_finansValue);
        tvMobile = (TextView) view.findViewById(R.id.text_view_mobile2);
        tvEmail = (TextView) view.findViewById(R.id.text_view_email2);

    }
}

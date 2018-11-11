package com.example.jake.university.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jake.university.API.Comands;
import com.example.jake.university.R;
import com.example.jake.university.data.ProfileInfo;

import org.json.JSONObject;
import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentProfile extends Fragment {
    View view;
    TextView tvFIO, tvGroup, tvInstitut, tvKafedra, tvStartStudy, tvStudyForm, tvFinans, tvMobile, tvEmail;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        JSONObject obj = new JSONObject();
        Comands comand = new Comands();
        obj = comand.GetTableArray("10","A_LKS_GetStudentInfo –gal", "0x8001000000027C02");
        ProfileInfo info = new ProfileInfo(obj.getString(), obj.getString(), obj.getString(), obj.getString(), obj.getString(), obj.getString(), obj.getString(), obj.getString(), obj.getString());
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

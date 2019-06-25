package com.example.jake.university.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jake.university.R;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FragmentProfile extends androidx.fragment.app.Fragment {
    View view;
    TextView tvFIO, tvGroup, tvInstitut, tvKafedra, tvStartStudy, tvStudyForm, tvFinans, tvMobile, tvEmail;;
    Singleton singleton;
    public FragmentProfile() throws InterruptedException, ExecutionException, JSONException {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        singleton = Singleton.getter();
        ProfileInfo info = singleton.getProfileInfo();
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initViews();
//        tvFIO.setText(info.getFIO());
//        tvGroup.setText(info.getGroup());
//        tvInstitut.setText(info.getFaculty());//
//        tvKafedra.setText(info.getGroup());//
//        tvStartStudy.setText(info.getStartDate());
//        tvStudyForm.setText(info.getEducForm());
//        tvFinans.setText(info.getFinType());
//        tvMobile.setText(info.getPhoneNum());
        tvEmail.setText(info.getEmail());
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

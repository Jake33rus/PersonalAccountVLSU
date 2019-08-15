package com.example.jake.university.profile;

import android.annotation.SuppressLint;
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
    TextView tvFIO, tvUchStep, tvUchZv, tvDolzh, tvInstitut, tvCathdra, tvPedStazh, tvCommonStazh, tvMobile, tvEmail;
    Singleton singleton;
    ProfileInfo info;
    public FragmentProfile() throws InterruptedException, ExecutionException, JSONException {
        singleton = Singleton.getInstance();
        info = singleton.getProfileInfo();
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.profile, container, false);
        initViews();
        tvFIO.setText(info.getFio());
        tvUchStep.setText(info.getUch_step());
        tvUchZv.setText(info.getUch_zv());
        tvDolzh.setText(info.getPost());
        tvInstitut.setText(info.getInstitute());
        tvCathdra.setText(info.getCathedra());
        String temp = (String) tvPedStazh.getText();
        tvPedStazh.setText(temp + " " + info.getPed_stazh());
        temp = (String) tvCommonStazh.getText();
        tvCommonStazh.setText(temp + " " + info.getGeneral_stazh());
        tvMobile.setText(info.getTel_numb());
        tvEmail.setText(info.getEmail());
        return view;
    }
    void initViews() {
        tvFIO = (TextView) view.findViewById(R.id.text_view_fio);
        tvUchStep = (TextView) view.findViewById(R.id.tv_uch_step);
        tvUchZv = (TextView) view.findViewById(R.id.tv_uch_zv);
        tvDolzh = (TextView) view.findViewById(R.id.tv_dolzhnost);
        tvInstitut = (TextView) view.findViewById(R.id.tv_institute);
        tvCathdra = (TextView) view.findViewById(R.id.tv_cathedra);
        tvPedStazh = (TextView) view.findViewById(R.id.tv_ped_stazh);
        tvCommonStazh = (TextView) view.findViewById(R.id.tv_common_stazh);
        tvMobile = (TextView) view.findViewById(R.id.text_view_mobile2);
        tvEmail = (TextView) view.findViewById(R.id.text_view_email2);
    }
}

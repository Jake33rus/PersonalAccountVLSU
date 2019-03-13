package com.example.jake.university;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.jake.university.profile.Singleton;
import com.victor.loading.newton.NewtonCradleLoading;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LockScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);
        NewtonCradleLoading newtonCradleLoading;
        newtonCradleLoading = (NewtonCradleLoading) findViewById(R.id.newton_cradle_loading);
        newtonCradleLoading.start();
        newtonCradleLoading.setLoadingColor(R.color.colorPrimary);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle arguments = getIntent().getExtras();
        String nrec = arguments.get("nrec").toString();
        try {
            Singleton.getInstance(nrec);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
    }
}

package com.example.jake.university;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void onButtonClick (View v)
    {
        /*android.support.v7.widget.AppCompatEditText logField = (android.support.v7.widget.AppCompatEditText)findViewById(R.id.loginField);
        android.support.v7.widget.AppCompatEditText pasField = (android.support.v7.widget.AppCompatEditText)findViewById(R.id.passField);

        String login = logField.getText().toString();
        String passsword = pasField.getText().toString();

        if(login == "admin" && passsword == "admin")*/
        setContentView(R.layout.activity_main);
    }
}

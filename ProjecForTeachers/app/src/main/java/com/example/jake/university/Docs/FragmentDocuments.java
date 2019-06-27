package com.example.jake.university.Docs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.jake.university.R;

import java.util.ArrayList;

class FragmentDocuments extends Fragment {
    Button butSearch, button1, button2, button3, button4, button5;
    TextView tvName, tvData;
    ArrayList<Document> docs;
    Bundle bundle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_documents_main, container, false);
        butSearch = (Button) view.findViewById(R.id.butSearch);
        button5 = (Button) view.findViewById(R.id.butSearchDate);
        button1 = (Button) view.findViewById(R.id.but1);
        button2 = (Button) view.findViewById(R.id.but2);
        button3 = (Button) view.findViewById(R.id.but3);
        button4 = (Button) view.findViewById(R.id.but4);
        tvName = (TextView) view.findViewById(R.id.tvNumb);
        tvData = (EditText) view.findViewById(R.id.tvSearchDateTo);
        butSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentDocumentsList fdl = new FragmentDocumentsList();
                bundle = new Bundle();
                bundle.putSerializable("list", docs);
                fdl.setArguments(bundle);
                fm.beginTransaction().replace(R.id.fragment_container,
                        fdl).addToBackStack(null).commit();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentDocumentsList fdl = new FragmentDocumentsList();
                bundle = new Bundle();
                bundle.putSerializable("list", docs);
                fdl.setArguments(bundle);
                fm.beginTransaction().replace(R.id.fragment_container,
                        fdl).addToBackStack(null).commit();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentDocumentsList fdl = new FragmentDocumentsList();
                bundle = new Bundle();
                bundle.putSerializable("list", docs);
                fdl.setArguments(bundle);
                fm.beginTransaction().replace(R.id.fragment_container,
                        fdl).addToBackStack(null).commit();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentDocumentsList fdl = new FragmentDocumentsList();
                bundle = new Bundle();
                bundle.putSerializable("list", docs);
                fdl.setArguments(bundle);
                fm.beginTransaction().replace(R.id.fragment_container,
                        fdl).addToBackStack(null).commit();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentDocumentsList fdl = new FragmentDocumentsList();
                bundle = new Bundle();
                bundle.putSerializable("list", docs);
                fdl.setArguments(bundle);
                fm.beginTransaction().replace(R.id.fragment_container,
                        fdl).addToBackStack(null).commit();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentDocumentsList fdl = new FragmentDocumentsList();
                bundle = new Bundle();
                bundle.putSerializable("list", docs);
                fdl.setArguments(bundle);
                fm.beginTransaction().replace(R.id.fragment_container,
                        fdl).addToBackStack(null).commit();
            }
        });
        return view;
    }
}

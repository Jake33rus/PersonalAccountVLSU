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

import com.example.jake.university.API.postReq;
import com.example.jake.university.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FragmentDocuments extends Fragment {
    Button butSearch, button1, button2, button3, button4, button5;
    TextView tvName, tvDataFrom, tvDataTo, tvNumb;
    ArrayList<Document> docs;
    Bundle bundle;
    private String ORDERS = "281474976904107",
            DISPOSALS = "281474976904108",
            SMK_DOCS = "281474976904113",
            BLANKS = "281474976904111";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_documents_main, container, false);
        butSearch = (Button) view.findViewById(R.id.butSearch); //Кнопка поиска
        button1 = (Button) view.findViewById(R.id.but1); // Приказы
        button2 = (Button) view.findViewById(R.id.but2); //Распоряжения
        button3 = (Button) view.findViewById(R.id.but3); //Документы СМК
        button4 = (Button) view.findViewById(R.id.but4); //Бланки
        tvName = (TextInputEditText) view.findViewById(R.id.name_edit_text);
        tvNumb = (TextInputEditText) view.findViewById(R.id.code_edit_text);
        tvDataFrom = (EditText) view.findViewById(R.id.tvSearchDateFrom);
        tvDataTo = (EditText) view.findViewById(R.id.tvSearchDateTo);

        docs = new ArrayList<>();

        butSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postReq comand = new postReq("getData");
                DocWorker dw = new DocWorker();
                JSONObject obj = new JSONObject();
                comand.execute("35","[dbo].[WorkDocument_Fast_GetList]",
                        "0,0,0,0,'','',False,"+ORDERS+",0,281474976904130,0,0,11549,1,'','',0");
//                comand.execute("35","[dbo].[WorkDocument_Fast_GetList]",
//                        "0,0,0,0,'"+tvName.getText().toString()+"','"+tvNumb.getText().toString()+"',False,"+ORDERS+",0,281474976904130,0,0,11549,1,'"+tvDataFrom.getText().toString()+"','"+tvDataTo.getText().toString()+"',0");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!comand.getjARRAY().isNull(0))
                {
                    docs.addAll(dw.getDocList(comand.getjARRAY()));
                }

                comand = new postReq("getData");
                comand.execute("35","[dbo].[WorkDocument_Fast_GetList]",
                        "0,0,0,0,'','',False,"+DISPOSALS+",0,281474976904130,0,0,11549,1,'','',0");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!comand.getjARRAY().isNull(0))
                {
                    docs.addAll(dw.getDocList(comand.getjARRAY()));
                }

                comand = new postReq("getData");
                comand.execute("35","[dbo].[WorkDocument_Fast_GetList]",
                        "0,0,0,0,'','',False,"+SMK_DOCS+",0,281474976904130,0,0,11549,1,'','',0");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!comand.getjARRAY().isNull(0))
                {
                    docs.addAll(dw.getDocList(comand.getjARRAY()));
                }

                comand = new postReq("getData");
                comand.execute("35","[dbo].[WorkDocument_Fast_GetList]",
                        "0,0,0,0,'','',False,"+BLANKS+",0,281474976904130,0,0,11549,1,'','',0");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!comand.getjARRAY().isNull(0))
                {
                    docs.addAll(dw.getDocList(comand.getjARRAY()));
                }

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

                DocWorker dw = new DocWorker();
                JSONObject obj = new JSONObject();
                postReq comand = new postReq("getData");
                comand.execute("35","[dbo].[WorkDocument_Fast_GetList]",
                        "0,0,0,0,'','',False,"+ORDERS+",0,281474976904130,0,0,11549,1,'','',0");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                docs = dw.getDocList(comand.getjARRAY());

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

                DocWorker dw = new DocWorker();
                JSONObject obj = new JSONObject();
                postReq comand = new postReq("getData");
                comand.execute("35","[dbo].[WorkDocument_Fast_GetList]",
                        "0, 0, 0, 0, '', '', False, "+DISPOSALS+", 0, 281474976904130, 0, 0, 12729, 1, '', '', 0");
                docs = dw.getDocList(comand.getjARRAY());

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

                DocWorker dw = new DocWorker();
                JSONObject obj = new JSONObject();
                postReq comand = new postReq("getData");
                comand.execute("35","[dbo].[WorkDocument_Fast_GetList]",
                        "0, 0, 0, 0, '', '', False, "+SMK_DOCS+", 0, 281474976904130, 0, 0, 12729, 1, '', '', 0");
                docs = dw.getDocList(comand.getjARRAY());

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

                DocWorker dw = new DocWorker();
                JSONObject obj = new JSONObject();
                postReq comand = new postReq("getData");
                comand.execute("35","[dbo].[WorkDocument_Fast_GetList]",
                        "0, 0, 0, 0, '', '', False, "+BLANKS+", 0, 281474976904130, 0, 0, 12729, 1, '', '', 0");
                docs = dw.getDocList(comand.getjARRAY());

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

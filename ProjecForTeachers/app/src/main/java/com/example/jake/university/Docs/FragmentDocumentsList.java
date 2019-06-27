package com.example.jake.university.Docs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jake.university.R;

import java.util.ArrayList;

public class FragmentDocumentsList extends Fragment {
    ListView list;
    ArrayList<Document> documents = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_documents_list, container, false);
        list = (ListView) view.findViewById(R.id.documentsList);
        Bundle bundle = this.getArguments();
        documents = (ArrayList<Document>) bundle.getSerializable("list");
        DocumentsAdapter adapter = new DocumentsAdapter(view.getContext(), R.layout.document_item, documents);
        list.setAdapter(adapter);
        return view;
    }
}

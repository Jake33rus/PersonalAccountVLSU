package com.example.jake.university.ranked;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jake.university.profile.Singleton;
import com.example.jake.university.scholarships.ScholarshipsAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentRanked extends Fragment {
   /* private static final int LAYOUT = R.layout.fragment_ranked;
    private View view;
    Singleton singleton = Singleton.getInstance("0");

    public FragmentRanked() throws InterruptedException, ExecutionException, JSONException {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        ScholarshipsAdapter adapter = null;
        ArrayList<RankedItem> list = singleton.getRankedItems();
        return view;
    }*/
}

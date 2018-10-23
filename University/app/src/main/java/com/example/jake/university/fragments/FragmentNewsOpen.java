package com.example.jake.university.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jake.university.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentNewsOpen extends Fragment {

    private static final int LAYOUT = R.layout.fragment_news_open;
    private View view;
    private String newsURL, newsTitle, newsImg;
    private String Text, imgURL;
    private TextView TV, TITV;
    private ImageView IV;

    public static FragmentNewsOpen getInstance() {
        Bundle args = new Bundle();
        FragmentNewsOpen fragment = new FragmentNewsOpen();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            newsURL = bundle.getString("URL");
            newsTitle = bundle.getString("Title");
        }

        TV = (TextView) view.findViewById(R.id.newsText);
        TITV = (TextView) view.findViewById(R.id.newsTitle);
        IV = (ImageView) view.findViewById(R.id.newsImage);

        TITV.setText(newsTitle);

        new NewThread().execute();

        return view;
    }

    public class NewThread extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... arg)
        {
            Document doc;
            try {

                Text="";
                doc = Jsoup.connect(newsURL).get();
                Elements News = doc.select(".news-single-item p");
                Elements contentImg = doc.select("img[src~=(?i)\\.(jpe?g)]");

                for (Element el : News)
                {
                    Text += el.text();
                }

                for(Element el:contentImg)
                {
                    imgURL = el.absUrl("src");
                    break;
                }



            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(String result)
        {
            TV.setText(Text);
            Picasso.get().load(imgURL).into(IV);
        }

    }
}


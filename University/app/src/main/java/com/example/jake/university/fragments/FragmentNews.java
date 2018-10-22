package com.example.jake.university.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jake.university.NewsItem;
import com.example.jake.university.R;
import com.example.jake.university.adapter.NewsAdapter;
import com.example.jake.university.adapter.SimpleDividerItemDecoration;
import com.google.android.material.tabs.TabLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class FragmentNews extends Fragment {
    private static final int LAYOUT = R.layout.fragment_news;
    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentActivity myContext;

    public Elements contentL, contentR, contentImg;
    public ArrayList<String> titleList = new ArrayList<String>();

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private List<NewsItem> newsItems;

    @Override
    public void onAttach(Context context) {
        myContext = (FragmentActivity) context;
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        rv = (RecyclerView) view.findViewById(R.id.NewsView);
        rv.setHasFixedSize(true);
        new NewThread().execute();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        return view;
    }
    public class NewThread extends AsyncTask<String, Void, String> {

        @Override
        protected  String doInBackground(String... arg)
        {

            Document doc;
            try
            {
                doc = Jsoup.connect("http://www.vlsu.ru/index.php?id=1412").get();
                contentL = doc.select(".hdr-left");
                contentR = doc.select(".hdr-right");
                contentImg = doc.select("img[src~=(?i)\\.(jpe?g)]");

                newsItems = new ArrayList<>();

                ArrayList<ContextContainer> cc = new ArrayList<ContextContainer>();

                for(int i = 0; i < contentL.size(); i++)
                {
                    ContextContainer temp;

                        temp = new ContextContainer(contentL.get(i), contentR.get(i), contentImg.get(i*2), contentImg.get(i*2 + 1));


                    cc.add(temp);
                }

                titleList.clear();

                for (ContextContainer contents: cc )
                {
                    String urlL = contents.get_lSide().child(2).attr("href");
                    String titleL = contents.get_lSide().child(1).text();
                    String textL = contents.get_lSide().child(3).text();
                    textL.replace("[подробнее]","");
                    String imgSrcL = contents.get_lImg().absUrl("src");
                    String dateL = contents.get_lSide().child(0).text();

                    String urlR = contents.get_rSide().child(2).attr("href");
                    String titleR = contents.get_rSide().child(1).text();
                    String textR = contents.get_rSide().child(3).text();
                    textR.replace("[подробнее]","");
                    String imgSrcR = contents.get_rImg().absUrl("src");
                    String dateR = contents.get_rSide().child(0).text();



                    NewsItem ni = new NewsItem(titleL, imgSrcL, imgSrcL);
                    newsItems.add(ni);
                    ni = new NewsItem(titleR, imgSrcR, imgSrcR);
                    newsItems.add(ni);


                }
                adapter = new NewsAdapter(this, newsItems);

            } catch (IOException e){
                e.printStackTrace();
            }return null;
        }
        @Override
        protected void onPostExecute(String result)
        {
            rv.setAdapter(adapter);
        }
    }

    public class ContextContainer
    {
        private Element lSide;
        private Element rSide;
        private Element lImg;
        private Element rImg;

        ContextContainer(Element l, Element r, Element il, Element ir)
        {
            lSide =l;
            rSide = r;
            lImg = il;
            rImg = ir;
        }

        public Element get_lSide()
        {
            return lSide;
        }
        public Element get_rSide()
        {
            return rSide;
        }
        public Element get_lImg()
        {
            return lImg;
        }
        public Element get_rImg()
        {
            return rImg;
        }
    }
}

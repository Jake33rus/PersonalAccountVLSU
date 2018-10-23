
package com.example.jake.university.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jake.university.NewsItem;
import com.example.jake.university.R;
import com.example.jake.university.RecyclerItemClickListener;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
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

        rv.addOnItemTouchListener(new RecyclerItemClickListener(myContext, rv ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position)
                    {
                        FragmentNewsOpen fno = new FragmentNewsOpen();
                        Bundle bundle = new Bundle();
                        bundle.putString("URL", newsItems.get(position).getURL());
                        bundle.putString("Title", newsItems.get(position).getTitle());
                        fno.setArguments(bundle);
                        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                        ftrans.replace(R.id.fragment_container, fno).commit();

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

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

                    temp = new ContextContainer(contentL.get(i), contentR.get(i));


                    cc.add(temp);
                }

                titleList.clear();

                for (ContextContainer contents: cc )
                {
                    String urlL = contents.get_lSide().child(2).attr("abs:href");
                    String titleL = contents.get_lSide().child(1).text();
                    String textL = contents.get_lSide().child(3).text();
                    textL.replace("[подробнее]","");

                    String imgSrcL = "https://i.ytimg.com/vi/iEjtJROdPVI/hqdefault.jpg";
                    Elements eh = contents.get_lSide().select("img[src~=(?i)\\.(jpe?g)]");
                    if(eh.size()>0)
                    {
                        imgSrcL = eh.first().absUrl("src");
                    }

                    String dateL = contents.get_lSide().child(0).text();

                    String urlR = contents.get_rSide().child(2).attr("abs:href");
                    String titleR = contents.get_rSide().child(1).text();
                    String textR = contents.get_rSide().child(3).text();
                    textR.replace("[подробнее]","");

                    String imgSrcR = "https://i.ytimg.com/vi/iEjtJROdPVI/hqdefault.jpg";
                    eh = contents.get_rSide().select("img[src~=(?i)\\.(jpe?g)]");
                    if(eh.size()>0)
                    {
                        imgSrcR = eh.first().absUrl("src");
                    }
                    String dateR = contents.get_rSide().child(0).text();


                    NewsItem ni = new NewsItem(titleL, dateL, imgSrcL, urlL);
                    newsItems.add(ni);
                    ni = new NewsItem(titleR, dateR, imgSrcR, urlR);
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


        ContextContainer(Element l, Element r)
        {
            lSide =l;
            rSide = r;;
        }

        public Element get_lSide()
        {
            return lSide;
        }
        public Element get_rSide()
        {
            return rSide;
        }
    }
}

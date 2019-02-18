package com.example.jake.university.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jake.university.API.JSOUP_parser;
import com.example.jake.university.R;
import com.example.jake.university.adapter.RecyclerItemClickListener;
import com.example.jake.university.adapter.SimpleDividerItemDecoration;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private String nextPageLink;

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private List<NewsItem> newsItems;
    private boolean isLoading = false;

    private RecyclerView.LayoutManager layoutManager;
    private int currentNews;

    @Override
    public void onAttach(Context context) {
        myContext = (FragmentActivity) context;
        super.onAttach(context);

    }

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        currentNews = -1;
        rv = (RecyclerView) view.findViewById(R.id.NewsView);
        rv.setHasFixedSize(true);
        newsItems = new ArrayList<>();
        nextPageLink = "http://www.vlsu.ru/index.php?id=1412";
        new NewThread().execute();
        layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        rv.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = ((LinearLayoutManager)rv.getLayoutManager()).findFirstVisibleItemPosition();

if(!isLoading)
{
    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount)
    {
        isLoading = true;
        new NewThread().execute();
    }
}
                }
            }
        });

        rv.addOnItemTouchListener(new RecyclerItemClickListener(myContext, rv ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position)
                    {
                        if(newsItems.get(position).getURL().contains("http://www.vlsu.ru")==true)
                        {
                            FragmentNewsOpen fno = new FragmentNewsOpen();
                            Bundle bundle = new Bundle();
                            bundle.putString("URL", newsItems.get(position).getURL());
                            bundle.putString("Title", newsItems.get(position).getTitle());
                            fno.setArguments(bundle);
                            FragmentTransaction ftrans = getFragmentManager().beginTransaction();
                            ftrans.replace(R.id.fragment_container, fno).addToBackStack(null).commit();
                        }
                        else
                            {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsItems.get(position).getURL()));
                                startActivity(browserIntent);
                            }

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
                nextPageLink = JSOUP_parser.parseNewsBunch(nextPageLink, newsItems);
                adapter = new NewsAdapter(this, newsItems);

                return null;
        }
        @Override
        protected void onPostExecute(String result )
        {
            rv.setAdapter(adapter);
            isLoading=false;
            if(currentNews==-1)currentNews=0;
            else
                {
                    layoutManager.scrollToPosition(pastVisiblesItems);
                }

        }


    }

}
package com.example.jake.university;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class News extends Activity {

    public Elements contentL, contentR, contentImg;
    public ArrayList<String> titleList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private ListView newsLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);

        newsLV = (ListView) findViewById(R.id.NewsView);
        new NewThread().execute();
        adapter = new ArrayAdapter<String>(this, R.layout.news_item, R.id.news, titleList);
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
                contentImg = doc.getElementsByTag("img");

                ArrayList<ContextContainer> cc = new ArrayList<ContextContainer>();

                for(int i = 0; i < contentL.size(); i++)
                {
                    ContextContainer temp;
                    temp = new ContextContainer(contentL.get(i), contentR.get(i), contentImg.get(i+6), contentImg.get(i+7));

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

                    String urlR = contents.get_rSide().child(2).attr("href");
                    String titleR = contents.get_rSide().child(1).text();
                    String textR = contents.get_rSide().child(3).text();
                    textR.replace("[подробнее]","");
                    String imgSrcR = contents.get_rImg().absUrl("src");

                    titleList.add(titleL);
                    titleList.add(titleR);

                }


            } catch (IOException e){
                e.printStackTrace();
            }return null;
        }
        @Override
        protected void onPostExecute(String result)
        {
            newsLV.setAdapter(adapter);
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



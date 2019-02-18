package com.example.jake.university.API;

import com.example.jake.university.news.NewsItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSOUP_parser
{

    public static String parseNewsBunch (String siteAdress, List<NewsItem> newsItems) {
        Document doc;
        String toNextPg = new String();
        try {
            doc = Jsoup.connect(siteAdress).get();
            Elements contentL = doc.select(".hdr-left");
            Elements contentR = doc.select(".hdr-right");
            Elements toNextPage = doc.select(".browseLinksWrap");
            Elements contentImg = doc.select("img[src~=(?i)\\.(jpe?g)]");

            Element toNext = toNextPage.get(0);
            toNextPg = toNext.child(9).attr("abs:href");

            ArrayList<ContextContainer> cc = new ArrayList<ContextContainer>();

            for (int i = 0; i < contentL.size(); i++) {
                ContextContainer temp;

                temp = new ContextContainer(contentL.get(i), contentR.get(i));

                cc.add(temp);
            }

            ArrayList<String> titleList;

            for (ContextContainer contents : cc) {
                String urlL = contents.get_lSide().child(2).attr("abs:href");
                String titleL = contents.get_lSide().child(1).text();
                String textL = contents.get_lSide().child(3).text();
                textL.replace("[подробнее]", "");

                String imgSrcL = "https://i.ytimg.com/vi/iEjtJROdPVI/hqdefault.jpg";
                Elements eh = contents.get_lSide().select("img[src~=(?i)\\.(jpe?g)]");
                if (eh.size() > 0) {
                    imgSrcL = eh.first().absUrl("src");
                }

                String dateL = contents.get_lSide().child(0).text();

                String urlR = contents.get_rSide().child(2).attr("abs:href");
                String titleR = contents.get_rSide().child(1).text();
                String textR = contents.get_rSide().child(3).text();
                textR.replace("[подробнее]", "");

                String imgSrcR = "https://i.ytimg.com/vi/iEjtJROdPVI/hqdefault.jpg";
                eh = contents.get_rSide().select("img[src~=(?i)\\.(jpe?g)]");
                if (eh.size() > 0) {
                    imgSrcR = eh.first().absUrl("src");
                }
                String dateR = contents.get_rSide().child(0).text();


                NewsItem ni = new NewsItem(titleL, dateL, imgSrcL, urlL);
                newsItems.add(ni);
                ni = new NewsItem(titleR, dateR, imgSrcR, urlR);
                newsItems.add(ni);

            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return toNextPg;
    }
}
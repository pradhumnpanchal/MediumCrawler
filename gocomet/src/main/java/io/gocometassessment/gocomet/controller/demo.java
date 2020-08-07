package io.gocometassessment.gocomet.controller;

import io.gocometassessment.gocomet.domain.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
@CrossOrigin
@RestController
public class demo {
    @RequestMapping("/title")
    String title() throws IOException {
        Document doc = Jsoup.connect("https://medium.com/swlh/the-one-and-only-factor-that-will-make-you-a-senior-developer-4fdd9d21b8c4").get();
        Element body = doc.body();
        return body.text();
    }

    @RequestMapping("/search/{id}")
    ArrayList<Article> search(@PathVariable String id) throws IOException {

        String url = "https://medium.com/swlh/search?q=" + id;

        Document doc = Jsoup.connect(url).get();

        Elements titles = doc.body().select("h3");
        Elements links = doc.getElementsByClass("postArticle-content").select("a[href]");
        Elements author_name = doc.getElementsByClass("ds-link ds-link--styleSubtle link link--darken link--accent u-accentColor--textNormal u-accentColor--textDarken");
        //Elements tags = doc.body().getElementsByClass("link u-baseColor--link");
        Elements read_time = doc.getElementsByClass("readingTime").select("span[title]");
        Elements responses = doc.body().getElementsByClass("button button--chromeless u-baseColor--buttonNormal");

        ArrayList<String> title_list = new ArrayList<>();
        ArrayList<String> link_list = new ArrayList<>();
        ArrayList<String> author_list = new ArrayList<>();
        //ArrayList<String> tags_list = new ArrayList<>();
        ArrayList<String> read_list = new ArrayList<>();
        ArrayList<String> response_count_list = new ArrayList<>();

        ArrayList<Article> result = new ArrayList<>();

        for(Element i: read_time) {
            String[] time_extract = i.attr("abs:title").split("/");
            read_list.add(time_extract[time_extract.length-1]);
        }

        for(Element i: responses)
            response_count_list.add(i.text());

//        for(Element i: tags)
//            tags_list.add(i.text());

        for(Element i: titles)
            title_list.add(i.text());

        for(Element i: author_name)
            author_list.add(i.text());

        for(Element i: links)
            link_list.add(i.attr("abs:href"));

        int total_count = Math.min(title_list.size(), Math.min(link_list.size(), Math.min(read_list.size(),Math.min(response_count_list.size(),author_list.size()))));

        for(int i = 0; i < total_count ; i++){
            Article new_article = new Article();
            new_article.setTitle(title_list.get(i));
            new_article.setUrl(link_list.get(i));
            new_article.setAuthor(author_list.get(i));
            //new_article.setTags(tags_list);
            new_article.setDuration(read_list.get(i));
            new_article.setResponse(response_count_list.get(i));
            result.add(new_article);
        }

        return result;
    }
}
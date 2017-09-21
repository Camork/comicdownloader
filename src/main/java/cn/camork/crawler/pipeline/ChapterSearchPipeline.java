package cn.camork.crawler.pipeline;

import cn.camork.controller.IndexAction;
import cn.camork.crawler.spider.ChapterSearchSpider;
import cn.camork.model.ChapterBean;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Camork on 2017/9/19.
 */

@Service("chapterSearchPipeline")
public class ChapterSearchPipeline implements Pipeline<ChapterSearchSpider> {

    @Override
    public void process(ChapterSearchSpider bean) {

        String comicUrl = "";
        for (ChapterBean chapterBean : bean.getChapters()) {
            if (chapterBean.getTitle().equals(bean.getRequest().getParameter("chapter"))) {
                comicUrl = chapterBean.getUrl();
            }
        }

        //处理不存在的章节,返回现有的第一个
        if (comicUrl.equals("")) {
            comicUrl = bean.getChapters().get(0).getUrl();
        }

        int pageNum = getPageNums(comicUrl);
        IndexAction.images = new ArrayList<>(pageNum);
        System.out.println(comicUrl);
        for (int i = 1; i <= pageNum; i++) {
            DeriveSchedulerContext.into(bean.getRequest().subRequest(comicUrl + "&p=" + i));
        }

    }

    private int getPageNums(String chapterUrl) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(chapterUrl);
        CloseableHttpResponse response;
        String content = "";
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity, "utf-8");
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Document doc = Jsoup.parse(content);
        Elements selectElements = doc.select("option:last-child");

        return Integer.parseInt(selectElements.attr("value"));
    }
}

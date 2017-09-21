package cn.camork.crawler.spider;

import cn.camork.model.ChapterBean;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * Created by Camork on 2017/9/19.
 */

@Gecco(matchUrl = "http://www.verydm.com/manhua/{comicName}?chapter={chapter}", pipelines = {"consolePipeline", "chapterSearchPipeline"})
public class ChapterSearchSpider implements HtmlBean{

    @Request
    private HttpRequest request;

    @Href
    @HtmlField(cssPath = "div.chapters > ul> li")
    private List<ChapterBean> chapters;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public List<ChapterBean> getChapters() {
        return chapters;
    }

    public void setChapters(List<ChapterBean> chapters) {
        this.chapters = chapters;
    }
}

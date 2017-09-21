package cn.camork.crawler.spider;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * Created by Camork on 2017/9/19.
 */

@Gecco(matchUrl = "http://www.verydm.com/index.php?r=comic%2Fsearch&keyword={keyword}&chapter={chapter}", pipelines = {"consolePipeline", "comicSearchPipeline"})
public class ComicSearchSpider implements HtmlBean {

    @Request
    private HttpRequest request;

    @Href
    @HtmlField(cssPath = "div.main-container li.first > a")
    private String resultUrl;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public String getResultUrl() {
        return resultUrl;
    }

    public void setResultUrl(String resultUrl) {
        this.resultUrl = resultUrl;
    }
}

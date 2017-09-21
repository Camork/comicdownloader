package cn.camork.model;

import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * Created by Camork on 2017/9/19.
 */
public class ChapterBean implements HtmlBean{

    @Text
    @Attr("title")
    @HtmlField(cssPath = "a")
    private String title;

    @Href
    @HtmlField(cssPath = "a")
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ChapterBean{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

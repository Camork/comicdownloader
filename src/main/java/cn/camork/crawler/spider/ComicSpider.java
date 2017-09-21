package cn.camork.crawler.spider;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * Created by Camork on 2017/9/19.
 */

@Gecco(matchUrl = "http://www.verydm.com/chapter.php?id={id}&p={p}", pipelines = {"consolePipeline", "comicPipeline"})
public class ComicSpider implements HtmlBean {

    @Href("src")
    @HtmlField(cssPath = "#mainImage2")
    String imageUrl;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

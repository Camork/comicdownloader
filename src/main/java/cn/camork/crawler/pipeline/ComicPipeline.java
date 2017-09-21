package cn.camork.crawler.pipeline;

import cn.camork.controller.IndexAction;
import cn.camork.crawler.spider.ComicSpider;
import com.geccocrawler.gecco.pipeline.Pipeline;
import org.springframework.stereotype.Service;

/**
 * Created by Camork on 2017/9/19.
 */

@Service("comicPipeline")
public class ComicPipeline implements Pipeline<ComicSpider> {

    @Override
    public void process(ComicSpider bean) {
        IndexAction.images.add(bean.getImageUrl());
    }
}

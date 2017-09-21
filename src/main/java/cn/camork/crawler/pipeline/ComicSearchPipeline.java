package cn.camork.crawler.pipeline;

import cn.camork.crawler.spider.ComicSearchSpider;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext;
import org.springframework.stereotype.Service;

/**
 * Created by Camork on 2017/9/19.
 */

@Service("comicSearchPipeline")
public class ComicSearchPipeline implements Pipeline<ComicSearchSpider> {

    @Override
    public void process(ComicSearchSpider bean) {

        String url=bean.getResultUrl()+"?chapter="+bean.getRequest().getParameter("chapter");

        DeriveSchedulerContext.into(bean.getRequest().subRequest(url));
    }
}

package cn.camork.controller;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.geccocrawler.gecco.request.HttpGetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Camork on 2017/9/19.
 */

@Controller
public class IndexAction {

    public static List<String> images;
    private PipelineFactory springPipelineFactory;

    @Autowired
    public IndexAction(PipelineFactory springPipelineFactory) {
        this.springPipelineFactory = springPipelineFactory;
    }

    @RequestMapping("/getComic")
    public String index(HttpServletResponse response, @RequestParam String cartoonName, String chapter) {

        String url = "http://www.verydm.com/index.php?r=comic%2Fsearch&keyword=" + cartoonName + "&chapter=" + chapter;
        HttpGetRequest start = new HttpGetRequest(url);
        GeccoEngine.create()
                .classpath("cn.camork.crawler")
                .pipelineFactory(springPipelineFactory)
                .thread(3)
                .start(start)
                .run();
        download(response, cartoonName + "-" + chapter+".zip");
        return "../index";
    }

    private void download(HttpServletResponse response, String zipName) {

        try {
            String downloadFilename = zipName;
            downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            for (int i = 0; i < images.size(); i++) {
                URL url = new URL(images.get(i));
                zos.putNextEntry(new ZipEntry(i + ".jpg"));
                InputStream fis = url.openConnection().getInputStream();
                byte[] buffer = new byte[1024];
                int r = 0;
                while ((r = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
                fis.close();
            }
            zos.flush();
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

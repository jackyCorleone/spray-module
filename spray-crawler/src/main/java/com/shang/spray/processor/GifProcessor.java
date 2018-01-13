package com.shang.spray.processor;

import com.shang.spray.entity.BaoZouPicTx;
import com.shang.spray.entity.Sources;
import com.shang.spray.pipeline.NewsPipeline;
import com.shang.spray.utils.Constant;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by jacky on 2018/1/12.
 */
public class GifProcessor implements PageProcessor {

    private Site site = Site.me()
            .setDomain("http://baozoumanhua.com/catalogs/gif")
            .setRetryTimes(3).setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");


    public static final String list = "http://baozoumanhua.com/catalogs/gif";

    @Override
    public void process(Page page) {

        page.addTargetRequests(page.getHtml().css("div.pager").links().all());
        BaoZouPicTx baoZouPicTx = new BaoZouPicTx();
        baoZouPicTx.setAuthorAvatar(page.getHtml().xpath("//div[@class='user-avator pull-left']/a/img/@src").get());
        baoZouPicTx.setAuthor(page.getHtml().xpath("//div[@class='article-author-field']/a/text()").toString());
        baoZouPicTx.setTitle(page.getHtml().xpath("//h4[@class='article-title']/a/text()").toString());
        baoZouPicTx.setLabel(page.getHtml().xpath("//h4[@class='article-title']/a").links().toString());
        String content = page.getHtml().xpath("//div[@class='article-content']/a/img/@data-original-image-url").get();
        if(content == null){
            content = page.getHtml().xpath("//video[@class='vjs-tech']/@data-original-image-url").get();
        }
        if(content != null){
            baoZouPicTx.setContentType(2);
            baoZouPicTx.setContent(content);

            baoZouPicTx.setSources(new Sources(Constant.Sources_DuanZiGe));
            page.putField("gif", baoZouPicTx);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider=Spider.create(new JianShuProcessor());
        spider.addUrl("http://www.jianshu.com");
        spider.addPipeline(new NewsPipeline());
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
    }
}

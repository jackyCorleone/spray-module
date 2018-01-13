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
public class QiuShiBaiKeProcessor implements PageProcessor {

    private Site site = Site.me()
            .setDomain("https://www.qiushibaike.com")
            .setRetryTimes(3).setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");




    @Override
    public void process(Page page) {

        page.addTargetRequests(page.getHtml().css("ul.pagination").links().all());
        BaoZouPicTx baoZouPicTx = new BaoZouPicTx();
        baoZouPicTx.setAuthorAvatar("https:"+page.getHtml().xpath("//div[@class='author clearfix']/a/img/@src").get());
        baoZouPicTx.setAuthor(page.getHtml().xpath("//div[@class='author clearfix']/a/img/@alt").get());
        baoZouPicTx.setTitle(page.getHtml().xpath("//div[@class='content']/span/text()").toString());
        baoZouPicTx.setLabel(page.getHtml().xpath("//div[@class='article']/a/@href").get());
        String links = page.getHtml().xpath("//div[@class='thumb']").links().toString();
        if(links == null){
            baoZouPicTx.setContentType(1);
        }else {
            String content = page.getHtml().xpath("//div[@class='thumb']/a/img/@src").toString();
            baoZouPicTx.setContentType(2);
            baoZouPicTx.setContent("https:"+content);
        }


        baoZouPicTx.setSources(new Sources(Constant.SOURCES_QIUSHIBAIKE));
        page.putField("qiushibaike", baoZouPicTx);

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
       /* Spider spider=Spider.create(new JianShuProcessor());
        spider.addUrl("http://www.jianshu.com");
        spider.addPipeline(new NewsPipeline());
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();*/
    }
}

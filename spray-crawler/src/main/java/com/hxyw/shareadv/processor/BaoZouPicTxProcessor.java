package com.hxyw.shareadv.processor;

import com.hxyw.shareadv.entity.BaoZouPicTx;
import com.hxyw.shareadv.entity.Sources;
import com.hxyw.shareadv.utils.Constant;
import com.hxyw.shareadv.pipeline.NewsPipeline;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * Created by jacky on 2018/1/11.
 */
public class BaoZouPicTxProcessor implements PageProcessor {
    private Site site = Site.me()
            .setDomain("http://baozoumanhua.com/catalogs/text")
            .setRetryTimes(3).setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");


    public static final String list = "http://baozoumanhua.com/catalogs/text";

    @Override
    public void process(Page page) {

        page.addTargetRequests(page.getHtml().css("div.pager").links().all());
        List<Selectable> list=page.getHtml().xpath("//div[@class='articles']/div").nodes();
        for (Selectable s : list) {
            BaoZouPicTx baoZouPicTx = new BaoZouPicTx();
            baoZouPicTx.setAuthorAvatar(s.xpath("//div[@class='user-avator pull-left']/a/img/@src").get());
            baoZouPicTx.setAuthor(s.xpath("//div[@class='article-author-field']/a/text()").toString());
            baoZouPicTx.setTitle(s.xpath("//h4[@class='article-title']/a/text()").toString());
            baoZouPicTx.setLabel(s.xpath("//h4[@class='article-title']/a").links().toString());
            String content = s.xpath("//div[@class='article-content']/a/img/@data-original-image-url").get();
            if(content != null){
                baoZouPicTx.setContentType(2);
                baoZouPicTx.setContent(content);
            }else {
                baoZouPicTx.setContentType(1);

            }
            baoZouPicTx.setSources(new Sources(Constant.Sources_DuanZiGe));

            if (StringUtils.isNotEmpty(baoZouPicTx.getTitle())) {
                page.putField("baoZouPicTx"+baoZouPicTx.getLabel(), baoZouPicTx);
            }


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

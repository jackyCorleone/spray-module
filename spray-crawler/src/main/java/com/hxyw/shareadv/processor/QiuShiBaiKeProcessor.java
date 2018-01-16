package com.hxyw.shareadv.processor;

import com.hxyw.shareadv.entity.BaoZouPicTx;
import com.hxyw.shareadv.entity.Sources;
import com.hxyw.shareadv.utils.Constant;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * Created by jacky on 2018/1/12.
 */
public class QiuShiBaiKeProcessor implements PageProcessor {

    private Site site = Site.me()
            .setDomain("https://www.qiushibaike.com")
            .setRetryTimes(3).setSleepTime(2000)
            .setDisableCookieManagement(true)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");




    @Override
    public void process(Page page) {

        page.addTargetRequests(page.getHtml().css("ul.pagination").links().all());

        List<Selectable> list=page.getHtml().xpath("//div[@class='col1']/div").nodes();
        for (Selectable s : list) {
            BaoZouPicTx baoZouPicTx = new BaoZouPicTx();
            baoZouPicTx.setAuthorAvatar("https:"+s.xpath("//div[@class='author clearfix']/a/img/@src").get());
            baoZouPicTx.setAuthor(s.xpath("//div[@class='author clearfix']/a/img/@alt").get());
            baoZouPicTx.setTitle(s.xpath("//div[@class='content']/span/text()").toString());
            baoZouPicTx.setLabel("https://www.qiushibaike.com"+s.xpath("//div[@class='article']/a/@href").get());
            String content = s.xpath("//div[@class='thumb']/a/img/@src").toString();
            if(content == null){
                baoZouPicTx.setContentType(1);
            }else {
                baoZouPicTx.setContentType(2);
                baoZouPicTx.setContent("https:"+content);
            }


            baoZouPicTx.setSources(new Sources(Constant.SOURCES_QIUSHIBAIKE));

            if (StringUtils.isNotEmpty(baoZouPicTx.getTitle())) {
                page.putField("qiushibaike"+baoZouPicTx.getLabel(), baoZouPicTx);
            }


        }


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

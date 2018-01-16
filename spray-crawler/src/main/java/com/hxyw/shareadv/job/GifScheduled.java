package com.hxyw.shareadv.job;

import com.hxyw.shareadv.pipeline.GifPipeline;
import com.hxyw.shareadv.processor.GifProcessor;
import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * Created by jacky on 2018/1/12.
 */
@Component
public class GifScheduled {

    @Resource
    private GifPipeline gifPipeline;

    @Scheduled(cron = "0 0/50 *  * * ? ")//从0点开始,每2个小时执行一次
    public void baoZouPicTxScheduled() {
        System.out.println("----开始执行暴走gif定时任务");
        Spider spider = Spider.create(new GifProcessor());
        spider.addUrl("http://baozoumanhua.com/catalogs/gif");
        spider.addPipeline(gifPipeline);
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
        spider.stop();
    }
}

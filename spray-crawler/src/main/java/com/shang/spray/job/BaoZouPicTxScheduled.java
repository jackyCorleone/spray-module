package com.shang.spray.job;

import com.shang.spray.pipeline.BaoZouPicTxPipeline;
import com.shang.spray.processor.BaoZouPicTxProcessor;
import com.shang.spray.processor.JianShuProcessor;
import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * Created by jacky on 2018/1/12.
 */
@Component
public class BaoZouPicTxScheduled {
    @Resource
    private BaoZouPicTxPipeline baoZouPicTxPipeline;

   // @Scheduled(cron = "0 0/5 *  * * ? ")//从0点开始,每2个小时执行一次
    public void baoZouPicTxScheduled() {
        System.out.println("----开始执行简书定时任务");
        Spider spider = Spider.create(new BaoZouPicTxProcessor());
        spider.addUrl("http://baozoumanhua.com/catalogs/text");
        spider.addPipeline(baoZouPicTxPipeline);
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
        spider.stop();
    }
}

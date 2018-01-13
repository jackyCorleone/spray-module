package com.shang.spray.job;

import com.shang.spray.pipeline.QiuShiBaiKePipeline;
import com.shang.spray.processor.QiuShiBaiKeProcessor;
import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * Created by jacky on 2018/1/12.
 */
@Component
public class QiuShiBaiKeScheduled {
    @Resource
    private QiuShiBaiKePipeline duanZiPipeline;

    @Scheduled(cron = "0 0/3 *  * * ? ")//从0点开始,每2个小时执行一次
    public void baoZouPicTxScheduled() {
        System.out.println("----开始执行挖段子任务");
        Spider spider = Spider.create(new QiuShiBaiKeProcessor());
        spider.addUrl("https://www.qiushibaike.com");
        spider.addPipeline(duanZiPipeline);
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
        spider.stop();
    }
}

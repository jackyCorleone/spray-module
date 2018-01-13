package com.shang.spray.job;

import com.shang.spray.pipeline.NewsPipeline;
import com.shang.spray.processor.JianShuProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

/**
 * Created by jacky on 2018/1/11.
 */
@Component
public class NewsScheduled {
    @Autowired
    private NewsPipeline newsPipeline;

    /**
     * 简书
     */
    //@Scheduled(cron = "0 0/5 *  * * ? ")//从0点开始,每2个小时执行一次
    public void jianShuScheduled() {
        System.out.println("----开始执行简书定时任务");
        Spider spider = Spider.create(new JianShuProcessor());
        spider.addUrl("http://www.jianshu.com");
        spider.addPipeline(newsPipeline);
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
        spider.stop();
    }

}

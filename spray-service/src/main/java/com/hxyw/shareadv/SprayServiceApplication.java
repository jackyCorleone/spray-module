package com.hxyw.shareadv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * info:spring boot打包必须要有一个main类，这里添加一个
 * Created by shang on 2017/8/17.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SprayServiceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SprayServiceApplication.class, args);
    }

   /* @Bean
    public EmbeddedServletContainerFactory servletContainer() {

        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        return factory;

    }*/
}

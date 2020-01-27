package com.corn.springcloud.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: TemplateApplication
 * @Package com.corn.springcloud.study
 * @Description: TODO
 * @date 2020/1/27 23:39
 */

@EnableScheduling
//jpa自动注入列值
@EnableJpaAuditing
@EnableEurekaClient
@SpringBootApplication
public class TemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class,args);
    }
}

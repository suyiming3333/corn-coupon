package com.corn.springcloud.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: SettlementApplication
 * @Package com.corn.springcloud.study
 * @Description: TODO
 * @date 2020/1/31 16:27
 */

@EnableEurekaClient
@SpringBootApplication
public class SettlementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SettlementApplication.class,args);
    }
}

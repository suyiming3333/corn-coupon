package com.corn.springcloud.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: EurekaApplication
 * @Package com.corn.springcloud.study
 * @Description: TODO
 * @date 2020/1/26 16:20
 */

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class,args);
    }
}

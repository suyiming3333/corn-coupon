package com.corn.springcloud.study;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: ZuulGatewayApplication
 * @Package com.corn.springcloud.study
 * @Description: @SpringCloudApplication 组合了springbootapplication/服务发现、熔断等三个注解
 * @date 2020/1/26 17:21
 */

@EnableZuulProxy
@SpringCloudApplication
public class ZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class,args);
    }
}

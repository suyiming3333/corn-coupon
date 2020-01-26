package com.corn.springcloud.study.filter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: RateLimiterFilter
 * @Package com.corn.springcloud.study.filter
 * @Description: 限流过滤器
 * @date 2020/1/26 18:10
 */

@Slf4j
@Component
public class RateLimiterFilter extends AbstractPreZuulFilter {

    /** 每秒可以获取到两个令牌 */
    //基于令牌桶算法(取到令牌才能访问)
    RateLimiter rateLimiter = RateLimiter.create(2.0);


    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();

        if (rateLimiter.tryAcquire()) {
            log.info("get rate token success");
            return success();
        } else {
            log.error("rate limit: {}", request.getRequestURI());
            return fail(402, "error: rate limit");
        }
    }

    @Override
    public int filterOrder() {
        //tokenFilter执行完之后执行
        return 2;
    }
}

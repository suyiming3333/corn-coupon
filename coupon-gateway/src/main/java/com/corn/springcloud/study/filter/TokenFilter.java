package com.corn.springcloud.study.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: TokenFilter
 * @Package com.corn.springcloud.study.filter
 * @Description: token过滤器
 * @date 2020/1/26 18:05
 */

@Slf4j
@Component
public class TokenFilter extends AbstractPreZuulFilter{

    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        //记录请求来源
        log.info(String.format("%s request to %s",
                request.getMethod(), request.getRequestURL().toString()));
        //获取token
        Object token = request.getParameter("token");
        //token为空，返回fail方法
        if (null == token) {
            log.error("error: token is empty");
            return fail(401, "error: token is empty");
        }
        //继续往下走
        return success();
    }

    @Override
    public int filterOrder() {
        //数字越小，优先级越高，约先执行
        return 1;
    }
}

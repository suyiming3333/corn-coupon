package com.corn.springcloud.study.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: AbstractPreZuulFilter
 * @Package com.corn.springcloud.study.filter
 * @Description: pre类型抽象过滤器
 * @date 2020/1/26 18:01
 */
public abstract class AbstractPreZuulFilter extends AbstactZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
}

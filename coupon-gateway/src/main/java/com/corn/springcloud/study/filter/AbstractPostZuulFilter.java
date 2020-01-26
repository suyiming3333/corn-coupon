package com.corn.springcloud.study.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: AbstractPostZuulFilter
 * @Package com.corn.springcloud.study.filter
 * @Description: post 类型抽象过滤器
 * @date 2020/1/26 18:03
 */
public abstract class AbstractPostZuulFilter extends AbstactZuulFilter{
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }
}

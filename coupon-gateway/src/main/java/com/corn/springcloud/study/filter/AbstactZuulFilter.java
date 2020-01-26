package com.corn.springcloud.study.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: AbstactZuulFilter
 * @Package com.corn.springcloud.study.filter
 * @Description: 通用的抽闲过滤器类
 * @date 2020/1/26 17:39
 */
public abstract class AbstactZuulFilter extends ZuulFilter {

    //用于过滤器之间传递信息，数据保存在每个请求的threadlocal中
    //是ConcurrentHashMap 线程安全的
    RequestContext context;

    //标识 继续向下执行过滤器
    private final static String NEXT = "next";

    @Override
    public boolean shouldFilter() {
        //获取当前线程的上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        //获取下一个过滤器(sc本身不含NEXT,所以默认是true值)
        return (boolean) ctx.getOrDefault(NEXT, true);
    }

    @Override
    public Object run() throws ZuulException {
        context = RequestContext.getCurrentContext();
        return cRun();
    }

    //用于给子类实现具体的逻辑实现(自定义实现)
    protected abstract Object cRun();

    Object fail(int code, String msg) {
        //run执行失败，停止往下面过滤器走
        context.set(NEXT, false);
        //设置不响应zuul
        context.setSendZuulResponse(false);
        //直接获取response设置返回的信息
        context.getResponse().setContentType("text/html;charset=UTF-8");
        context.setResponseStatusCode(code);
        context.setResponseBody(String.format("{\"result\": \"%s!\"}", msg));

        return null;
    }

    Object success() {
        //run执行成功，继续往下一个过滤器走逻辑
        context.set(NEXT, true);
        return null;
    }
}

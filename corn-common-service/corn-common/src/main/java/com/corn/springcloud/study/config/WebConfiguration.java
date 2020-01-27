package com.corn.springcloud.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: WebCongiguration
 * @Package com.corn.springcloud.study.config
 * @Description: TODO
 * @date 2020/1/27 21:20
 */

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /***
     * 消息转换器
     * java实体与http之前数据的转换
     * json->实体，实体->json
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        converters.add(new MappingJackson2HttpMessageConverter());

    }
}

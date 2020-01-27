package com.corn.springcloud.study.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: JacksonConfig
 * @Package com.corn.springcloud.study.config
 * @Description: 配置jackson转换
 * @date 2020/1/27 21:28
 */
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper getObjectMapper() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss"
        ));
        return mapper;
    }
}

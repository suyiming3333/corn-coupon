package com.corn.springcloud.study.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: IgnoreResponseAdvice
 * @Package com.corn.springcloud.study.annotation
 * @Description: 忽略统一返回格式的注解
 * @date 2020/1/27 21:35
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {
}

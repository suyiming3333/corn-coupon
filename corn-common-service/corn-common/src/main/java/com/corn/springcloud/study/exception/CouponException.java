package com.corn.springcloud.study.exception;

/**
 * <h1>优惠券项目通用异常定义</h1>
 * Created by Qinyi.
 */
public class CouponException extends Exception {

    public CouponException(String message) {
        super(message);
    }
}

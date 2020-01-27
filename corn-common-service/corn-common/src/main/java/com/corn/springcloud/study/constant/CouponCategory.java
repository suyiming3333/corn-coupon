package com.corn.springcloud.study.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: CouponCategory
 * @Package com.corn.springcloud.study.constant
 * @Description: 优惠券分类枚举类
 * @date 2020/1/27 23:51
 */

@Getter
@AllArgsConstructor
public enum CouponCategory {

    MANJIAN("满减券", "001"),
    ZHEKOU("折扣券", "002"),
    LIJIAN("立减券", "003");

    /** 优惠券描述(分类) */
    private String description;

    /** 优惠券分类编码 */
    private String code;

    public static CouponCategory of(String code) {

        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " not exists!"));
    }


}

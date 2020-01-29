package com.corn.springcloud.study.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: GoodsType
 * @Package com.corn.springcloud.study.constant
 * @Description: TODO
 * @date 2020/1/28 23:12
 */

@Getter
@AllArgsConstructor
public enum GoodsType {

    WENYU("文娱",1),
    SHENGXIAN("生鲜",2),
    JIAJU("家具",3),
    OTHERS("其他",4),
    ALL("全品类",5);


    private String description;

    private Integer code;

    public static GoodsType of(Integer code) {

        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(code + " not exists!"));
    }

}

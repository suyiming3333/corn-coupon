package com.corn.springcloud.study.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: CommonResponse
 * @Package com.corn.springcloud.study.vo
 * @Description: 接口请求统一返回对象
 * @date 2020/1/27 21:30
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

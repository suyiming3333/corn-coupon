package com.corn.springcloud.study.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: GoodsInfo
 * @Package com.corn.springcloud.study.vo
 * @Description: TODO
 * @date 2020/1/28 23:19
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfo {

    /**商品类型**/
    private Integer type;

    /**价格**/
    private Double price;

    /**商品数量**/
    private Integer count;

    private String goodsName;
}

package com.corn.springcloud.study.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: SettlementInfo
 * @Package com.corn.springcloud.study.vo
 * @Description: TODO
 *
 * <h1>结算信息对象定义</h1>
 * 1、userId
 * 2、商品信息(列表)
 * 3、优惠券列表
 * 4、结算结果金额
 * @date 2020/1/28 23:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementInfo {

    private Long userId;


    /**商品信息**/
    private List<GoodsInfo> goodsInfos;

    /**优惠券列表**/
    private List<CouponAndTemplateInfo> couponAndTemplateInfos;

    /**是否对结算生效 true核销 false 核销**/
    private Boolean employ;

    /**结果结算金额**/
    private Double cost;


    /***
     * 优惠券和模板信息实体类
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CouponAndTemplateInfo{

        /**coupon的主键**/
        private Integer id;

        /**优惠券的模板的信息**/
        private CouponTemplateSDK template;
    }
}

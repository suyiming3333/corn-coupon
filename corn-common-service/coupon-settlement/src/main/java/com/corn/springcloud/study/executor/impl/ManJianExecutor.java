package com.corn.springcloud.study.executor.impl;

import com.corn.springcloud.study.constant.RuleFlag;
import com.corn.springcloud.study.executor.AbstractExecutor;
import com.corn.springcloud.study.executor.RuleExecutor;
import com.corn.springcloud.study.vo.CouponTemplateSDK;
import com.corn.springcloud.study.vo.SettlementInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * <h1>满减优惠券结算规则执行器</h1>
 * Created by Qinyi.
 */
@Slf4j
@Component
public class ManJianExecutor extends AbstractExecutor implements RuleExecutor {

    /**
     * <h2>规则类型标记</h2>
     * @return {@link RuleFlag}
     */
    @Override
    public RuleFlag ruleConfig() {
        return RuleFlag.MANJIAN;
    }

    /**
     * <h2>优惠券规则的计算</h2>
     * @param settlement {@link SettlementInfo} 包含了选择的优惠券
     * @return {@link SettlementInfo} 修正过的结算信息
     */
    @Override
    public SettlementInfo computeRule(SettlementInfo settlement) {
        //计算商品总价
        double goodsSum = retain2Decimals(
                goodsCostSum(settlement.getGoodsInfos())
        );

        //可能的结算信息
        SettlementInfo probability = processGoodsTypeNotSatisfy(
                settlement, goodsSum
        );

        //不等于null 按照原来总价计算
        if (null != probability) {
            log.debug("ManJian Template Is Not Match To GoodsType!");
            return probability;
        }

        // 判断满减是否符合折扣标准
        CouponTemplateSDK templateSDK = settlement.getCouponAndTemplateInfos()
                .get(0).getTemplate();

        //获取模板的满减金额
        double base = (double) templateSDK.getRule().getDiscount().getBase();
        //获取模板满减的折扣
        double quota = (double) templateSDK.getRule().getDiscount().getQuota();

        // 如果不符合标准, 则直接返回商品总价
        if (goodsSum < base) {
            log.debug("Current Goods Cost Sum < ManJian Coupon Base!");
            settlement.setCost(goodsSum);
            settlement.setCouponAndTemplateInfos(Collections.emptyList());
            return settlement;
        }

        //符合满减优惠
        // 计算使用优惠券之后的价格 - 结算
        settlement.setCost(retain2Decimals(
                (goodsSum - quota) > minCost() ? (goodsSum - quota) : minCost()
        ));
        log.debug("Use ManJian Coupon Make Goods Cost From {} To {}",
                goodsSum, settlement.getCost());

        return settlement;
    }
}

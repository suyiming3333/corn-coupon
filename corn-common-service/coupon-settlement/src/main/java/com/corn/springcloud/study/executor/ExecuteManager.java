package com.corn.springcloud.study.executor;


import com.corn.springcloud.study.constant.CouponCategory;
import com.corn.springcloud.study.constant.RuleFlag;
import com.corn.springcloud.study.exception.CouponException;
import com.corn.springcloud.study.vo.SettlementInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>优惠券结算规则执行管理器</h1>
 * 即根据用户的请求(SettlementInfo)找到对应的 Executor, 去做结算
 * BeanPostProcessor: Bean 后置处理器
 * Created by Qinyi.
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class ExecuteManager implements BeanPostProcessor {

    /** 规则执行器映射 */
    private static Map<RuleFlag, RuleExecutor> executorIndex =
            new HashMap<>(RuleFlag.values().length);

    /**
     * <h2>优惠券结算规则计算入口</h2>
     * 注意: 一定要保证传递进来的优惠券个数 >= 1
     * */
    public SettlementInfo computeRule(SettlementInfo settlement)
            throws CouponException {

        SettlementInfo result = null;

        // 单类优惠券
        if (settlement.getCouponAndTemplateInfos().size() == 1) {

            // 获取优惠券的类别
            CouponCategory category = CouponCategory.of(
                    settlement.getCouponAndTemplateInfos().get(0)
                            .getTemplate().getCategory()
            );
            //根据优惠券，选择对应的结算处理器
            switch (category) {
                case MANJIAN:
                    result = executorIndex.get(RuleFlag.MANJIAN)
                            .computeRule(settlement);
                    break;
                case ZHEKOU:
                    result = executorIndex.get(RuleFlag.ZHEKOU)
                            .computeRule(settlement);
                    break;
                case LIJIAN:
                    result = executorIndex.get(RuleFlag.LIJIAN)
                            .computeRule(settlement);
                    break;
            }
        } else {

            // 多类优惠券
            List<CouponCategory> categories = new ArrayList<>(
                    settlement.getCouponAndTemplateInfos().size()
            );

            settlement.getCouponAndTemplateInfos().forEach(ct ->
                    categories.add(CouponCategory.of(
                            ct.getTemplate().getCategory()
                    )));
            if (categories.size() != 2) {
                throw new CouponException("Not Support For More " +
                        "Template Category");
            } else {
                if (categories.contains(CouponCategory.MANJIAN)
                        && categories.contains(CouponCategory.ZHEKOU)) {
                    result = executorIndex.get(RuleFlag.MANJIAN_ZHEKOU)
                            .computeRule(settlement);
                } else {
                    throw new CouponException("Not Support For Other " +
                            "Template Category");
                }
            }
        }

        return result;
    }

    /**
     * <h2>在 bean 初始化之前去执行(before)</h2>
     * */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {

        //如果不是RuleExecutor的实现，则直接返回
        if (!(bean instanceof RuleExecutor)) {
            return bean;
        }

        //如果是，强转类型
        RuleExecutor executor = (RuleExecutor) bean;
        //获取Ruleflag类型
        RuleFlag ruleFlag = executor.ruleConfig();

        //如果map已经包含了，代表已经设置过值，系统异常
        if (executorIndex.containsKey(ruleFlag)) {
            throw new IllegalStateException("There is already an executor" +
                    "for rule flag: " + ruleFlag);
        }

        //放入map
        log.info("Load executor {} for rule flag {}.",
                executor.getClass(), ruleFlag);
        executorIndex.put(ruleFlag, executor);

        return null;
    }

    /**
     * <h2>在 bean 初始化之后去执行(after)</h2>
     * */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }
}

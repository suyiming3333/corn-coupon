package com.corn.springcloud.study.dao;

import com.corn.springcloud.study.entity.CouponTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: CouponTemplateDao
 * @Package com.corn.springcloud.study.dao
 * @Description: TODO
 * @date 2020/1/28 13:08
 */
public interface CouponTemplateDao extends JpaRepository<CouponTemplate,Integer> {

    /**
     * <h2>根据模板名称查询模板</h2>
     * where name = ...
     * */
    CouponTemplate findByName(String name);

    /**
     * <h2>根据 available 和 expired 标记查找模板记录</h2>
     * where available = ... and expired = ...
     * */
    List<CouponTemplate> findAllByAvailableAndExpired(
            Boolean available, Boolean expired
    );

    /**
     * <h2>根据 expired 标记查找模板记录</h2>
     * where expired = ...
     * */
    List<CouponTemplate> findAllByExpired(Boolean expired);


    /**
     * 根据用户id获取可用的优惠券列表
     * @param userId
     * @return
     */
    List<CouponTemplate> findAllByAvailableAndExpiredAndUserId(Boolean available, Boolean expired,Long userId);


}

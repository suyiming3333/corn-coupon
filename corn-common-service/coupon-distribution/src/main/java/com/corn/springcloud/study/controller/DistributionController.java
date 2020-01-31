package com.corn.springcloud.study.controller;

import com.corn.springcloud.study.entity.Coupon;
import com.corn.springcloud.study.exception.CouponException;
import com.corn.springcloud.study.service.IUserService;
import com.corn.springcloud.study.vo.AcquireTemplateRequest;
import com.corn.springcloud.study.vo.CouponTemplateSDK;
import com.corn.springcloud.study.vo.SettlementInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: DistributionController
 * @Package com.corn.springcloud.study.controller
 * @Description: TODO
 * @date 2020/1/31 21:26
 */

@Slf4j
@RestController
public class DistributionController {


    @Autowired
    private IUserService userService;

    /**
     * 根据用户id获取可领取的优惠券(模板)
     * @param userId
     * @return
     */
    @GetMapping("/distribution/findAvailableTemplate/{userId}")
    public List<CouponTemplateSDK> findAvailableTemplateByUserId(@PathVariable("userId") Long userId){
        List<CouponTemplateSDK> result = new ArrayList<>();
        try {
            result =  userService.findAvailableTemplate(userId);
        } catch (CouponException e) {
            e.printStackTrace();
        }finally {
            return result;
        }
    }

    /****
     * 根据用户id 更状态 获取用户已经领取的优惠券(先从缓存取值)
     * @param userId
     * @param status
     * @return
     * @throws CouponException
     */
    @PostMapping("/distribution/findCouponsByStatusAndUserId")
    public List<Coupon> findCouponsByStatusAndUserId(@RequestParam("userId") Long userId,@RequestParam("status") Integer status) throws CouponException {
        List<Coupon> result = new ArrayList<>();
        result = userService.findCouponsByStatus(userId,status);
        return result;
    }

    /***
     * 用户领取优惠券
     * @param request
     * @return
     */
    @PostMapping("/distribution/acquireTemplate")
    public Coupon acquireTemplate(@RequestBody AcquireTemplateRequest request){
        Coupon coupon = new Coupon();
        try {
            coupon = userService.acquireTemplate(request);
        } catch (CouponException e) {
            e.printStackTrace();
        }finally {
            return coupon;
        }
    }

    /****
     * 结算、核销优惠券
     * @param info
     * @return
     */
    @PostMapping("/distribution/settlement")
    public SettlementInfo settlement(@RequestBody SettlementInfo info){
        SettlementInfo settlementInfo = new SettlementInfo();
        try {
            settlementInfo = userService.settlement(info);
        } catch (CouponException e) {
            e.printStackTrace();
        }finally {
            return settlementInfo;
        }
    }
}

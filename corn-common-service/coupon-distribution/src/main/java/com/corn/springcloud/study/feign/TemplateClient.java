package com.corn.springcloud.study.feign;


import com.corn.springcloud.study.feign.hystrix.TemplateClientHystrix;
import com.corn.springcloud.study.vo.CommonResponse;
import com.corn.springcloud.study.vo.CouponTemplateSDK;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <h1>优惠券模板微服务 Feign 接口定义</h1>
 * Created by Qinyi.
 *
 * value 调用的服务名称(服务id)
 * fallback 熔断响应的回调
 */
@FeignClient(value = "eureka-client-coupon-template",
        fallback = TemplateClientHystrix.class)
public interface TemplateClient {

    /**
     * <h2>查找所有可用的优惠券模板</h2>
     * */
    @RequestMapping(value = "/coupon-template/template/sdk/{userId}",
            method = RequestMethod.GET)
    CommonResponse<List<CouponTemplateSDK>> findAllUsableTemplate(@PathVariable("userId")Long userId);

    /**
     * <h2>获取模板 ids 到 CouponTemplateSDK 的映射</h2>
     * */
    @RequestMapping(value = "/coupon-template/template/sdk/infos",
            method = RequestMethod.GET)
    CommonResponse<Map<Integer, CouponTemplateSDK>> findIds2TemplateSDK(
            @RequestParam("ids") Collection<Integer> ids
    );
}

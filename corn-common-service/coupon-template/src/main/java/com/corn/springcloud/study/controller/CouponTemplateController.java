package com.corn.springcloud.study.controller;

import com.alibaba.fastjson.JSON;
import com.corn.springcloud.study.entity.CouponTemplate;
import com.corn.springcloud.study.exception.CouponException;
import com.corn.springcloud.study.service.IBuildTemplateService;
import com.corn.springcloud.study.service.ITemplateBaseService;
import com.corn.springcloud.study.vo.CouponTemplateSDK;
import com.corn.springcloud.study.vo.TemplateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <h1>优惠券模板相关的功能控制器</h1>
 * Created by Qinyi.
 */
@Slf4j
@RestController
public class CouponTemplateController {

    /** 构建优惠券模板服务 */
    private final IBuildTemplateService buildTemplateService;

    /** 优惠券模板基础服务 */
    private final ITemplateBaseService templateBaseService;

    @Autowired
    public CouponTemplateController(IBuildTemplateService buildTemplateService,
                                    ITemplateBaseService templateBaseService) {
        this.buildTemplateService = buildTemplateService;
        this.templateBaseService = templateBaseService;
    }

    /**
     * <h2>构建优惠券模板</h2>
     * 127.0.0.1:7001/coupon-template/template/build
     * 127.0.0.1:9000/corn/coupon-template/template/build
     * */
    @PostMapping("/template/build")
    public CouponTemplate buildTemplate(@RequestBody TemplateRequest request)
            throws CouponException {
        log.info("Build Template: {}", JSON.toJSONString(request));
        return buildTemplateService.buildTemplate(request);
    }

    /**
     * <h2>构造优惠券模板详情</h2>
     * 127.0.0.1:7001/coupon-template/template/info?id=1
     * */
    @GetMapping("/template/info")
    public CouponTemplate buildTemplateInfo(@RequestParam("id") Integer id)
            throws CouponException {
        log.info("Build Template Info For: {}", id);
        return templateBaseService.buildTemplateInfo(id);
    }

    /**
     * <h2>查找所有可用的优惠券模板</h2>
     * 127.0.0.1:7001/coupon-template/template/sdk/all
     * */
    @GetMapping("/template/sdk/all")
    public List<CouponTemplateSDK> findAllUsableTemplate() {
        List<CouponTemplateSDK> result = new ArrayList<>();
        log.info("Find All Usable Template.");
        result =  templateBaseService.findAllUsableTemplate();
        return result;
    }

    /***
     * 根据用户id获取可用的优惠券模板列表
     * @param userId
     * @return
     */
    @GetMapping("/template/sdk/{userId}")
    public List<CouponTemplateSDK> findAllUsableTemplateByUserId(@PathVariable("userId") Long userId) {
        List<CouponTemplateSDK> result = new ArrayList<>();
        log.info("Find Usable Template bY userId{}.",userId);
        result =  templateBaseService.findAllUsableTemplateByUserId(userId);
        return result;
    }


    /**
     * <h2>获取模板 ids 到 CouponTemplateSDK 的映射</h2>
     * 127.0.0.1:7001/coupon-template/template/sdk/infos
     * */
    @GetMapping("/template/sdk/infos")
    public Map<Integer, CouponTemplateSDK> findIds2TemplateSDK(
            @RequestParam("ids") Collection<Integer> ids
    ) {
        log.info("FindIds2TemplateSDK: {}", JSON.toJSONString(ids));
        return templateBaseService.findIds2TemplateSDK(ids);
    }
}

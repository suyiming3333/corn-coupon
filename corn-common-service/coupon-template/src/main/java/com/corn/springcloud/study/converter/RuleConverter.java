package com.corn.springcloud.study.converter;

import com.alibaba.fastjson.JSON;
import com.corn.springcloud.study.vo.TemplateRule;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * <h1>优惠券规则属性转换器</h1>
 * Created by Qinyi.
 */
@Converter
public class RuleConverter
        implements AttributeConverter<TemplateRule, String> {

    @Override
    public String convertToDatabaseColumn(TemplateRule rule) {
        return JSON.toJSONString(rule);
    }

    @Override
    public TemplateRule convertToEntityAttribute(String rule) {
        return JSON.parseObject(rule, TemplateRule.class);
    }
}

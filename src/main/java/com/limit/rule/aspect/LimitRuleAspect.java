package com.limit.rule.aspect;


import com.limit.rule.annotation.LimitRule;
import com.limit.rule.core.LimitService;
import com.limit.rule.enums.LimitRuleEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author shilei
 */
@Aspect
@Slf4j
@Data
public class LimitRuleAspect {

    @Autowired
    private Map<String, LimitService> limitServiceMap;

    @Autowired
    private Map<String, DefaultRedisScript<Long>> scriptMap;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Before("@annotation(limitRule)")
    public void limitRule(JoinPoint joinPoint, LimitRule limitRule) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        log.info("methodName:{}触发限流, 限流规则：{}", method.getName(), limitRule.rule().name());
        LimitRuleEnum rule = limitRule.rule();
        LimitService limitService = limitServiceMap.get(rule.getLimitBean());
        DefaultRedisScript<Long> script = scriptMap.get(rule.getScriptName());
        limitService.handle(limitRule, redisTemplate, script);
    }
}

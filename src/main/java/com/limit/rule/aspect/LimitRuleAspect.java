package com.limit.rule.aspect;


import com.limit.rule.annotation.LimitRule;
import com.limit.rule.core.LimitService;
import com.limit.rule.enums.LimitRuleEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author shilei
 */
@Component
@Aspect
@AllArgsConstructor
@Slf4j
public class LimitRuleAspect {
    private final Map<String, LimitService> limitServiceMap;
    private final RedisTemplate<String, String> redisTemplate;

    @Before("@annotation(limitRule)")
    public void aroundLock(JoinPoint joinPoint, LimitRule limitRule) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        log.info("methodName:{}触发限流, 限流规则：{}", method.getName(), limitRule.rule().name());
        LimitRuleEnum rule = limitRule.rule();
        LimitService limitService = limitServiceMap.get(rule.getLimitBean());
        limitService.handle(limitRule, redisTemplate);
    }
}

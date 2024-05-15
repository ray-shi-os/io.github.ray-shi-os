package com.limit.rule.core;


import com.limit.rule.annotation.LimitRule;
import com.limit.rule.exception.LimitException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

/**
 * @author shilei
 */
public class CounterLimitServiceImpl implements LimitService {
    @Override
    public void handle(LimitRule limitRule, RedisTemplate<String, String> redisTemplate) {
        Long increment = redisTemplate.opsForValue().increment(limitRule.resource(), 1);
        if (Objects.isNull(increment)) {
            return;
        }
        if (increment.intValue() <= 1) {
            redisTemplate.expire(limitRule.resource(), limitRule.time(), limitRule.timeUnit());
        }
        if (increment.intValue() > limitRule.limitCount()) {
            throw new LimitException();
        }
    }
}

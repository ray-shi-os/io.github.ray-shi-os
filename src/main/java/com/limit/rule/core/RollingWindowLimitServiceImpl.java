package com.limit.rule.core;

import com.limit.rule.annotation.LimitRule;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.Objects;

/**
 * @author shilei
 */
public class RollingWindowLimitServiceImpl implements LimitService{
    @Override
    public void handle(LimitRule limitRule, RedisTemplate<String, Object> redisTemplate, DefaultRedisScript<Long> script) {

        Long count = redisTemplate.execute(script, redisSerializer, redisSerializer, Collections.singletonList(limitRule.resource()),
                limitRule.time(), String.valueOf(System.currentTimeMillis()), limitRule.limitCount());
        if (Objects.nonNull(count) && count.intValue() > Integer.parseInt(limitRule.limitCount())) {
            throw new LimitException(limitRule.message());
        }
    }
}

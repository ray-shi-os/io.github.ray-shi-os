package com.limit.rule.core;

import com.limit.rule.annotation.LimitRule;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.Objects;

/**
 * @author shilei
 */
public class TokenBucketLimitServiceImpl implements LimitService {
    @Override
    public void handle(LimitRule limitRule, RedisTemplate<String, Object> redisTemplate, DefaultRedisScript<Long> script) {
        Long count = redisTemplate.execute(script, redisSerializer, redisSerializer, Collections.singletonList(limitRule.resource()),
                limitRule.rate(), limitRule.capacity(), String.valueOf(System.currentTimeMillis()));
        if (Objects.nonNull(count) && count.intValue() == 0) {
            throw new LimitException(limitRule.message());
        }
    }
}

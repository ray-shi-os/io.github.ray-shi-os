package com.limit.rule.core;

import com.limit.rule.annotation.LimitRule;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author shilei
 */
public class RollingWindowLimitServiceImpl implements LimitService{
    @Override
    public void handle(LimitRule limitRule, RedisTemplate<String, String> redisTemplate) {

    }
}

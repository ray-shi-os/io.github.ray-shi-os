package com.limit.rule.core;


import com.limit.rule.annotation.LimitRule;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author shilei
 */
public interface LimitService {


    /**
     * 限流处理
     *
     * @param limitRule     @see LimitRule
     * @param redisTemplate redisTemplate
     */
    void handle(LimitRule limitRule, RedisTemplate<String, String> redisTemplate);
}

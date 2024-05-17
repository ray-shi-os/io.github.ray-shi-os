package com.limit.rule.core;


import com.limit.rule.annotation.LimitRule;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author shilei
 */
public interface LimitService {

    RedisSerializer redisSerializer = new StringRedisSerializer();


    /**
     * 限流处理
     *
     * @param limitRule     @see LimitRule
     * @param redisTemplate redisTemplate
     * @param script        lua脚本
     */
    void handle(LimitRule limitRule, RedisTemplate<String, Object> redisTemplate , DefaultRedisScript<Long> script);
}

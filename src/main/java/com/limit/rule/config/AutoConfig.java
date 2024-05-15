package com.limit.rule.config;

import com.limit.rule.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author shilei
 */
@Configuration
public class AutoConfig {
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        template.setConnectionFactory(factory);
        //key序列化方式
        template.setKeySerializer(redisSerializer);
        //value序列化
        template.setValueSerializer(redisSerializer);
        //value hashmap序列化
        template.setHashValueSerializer(redisSerializer);
        //key hashmap序列化
        template.setHashKeySerializer(redisSerializer);
        return template;
    }

    @Bean
    public LimitService counterLimitServiceImpl() {
        return new CounterLimitServiceImpl();
    }

    @Bean
    public LimitService leakyBucketLimitServiceImpl() {
        return new LeakyBucketLimitServiceImpl();
    }

    @Bean
    public LimitService rollingWindowLimitServiceImpl() {
        return new RollingWindowLimitServiceImpl();
    }

    @Bean
    public LimitService tokenBucketLimitServiceImpl() {
        return new TokenBucketLimitServiceImpl();
    }
}

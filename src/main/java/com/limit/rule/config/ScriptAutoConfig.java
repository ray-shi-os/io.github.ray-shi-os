package com.limit.rule.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;

/**
 * @author shilei
 */
@Configuration
@AutoConfigureBefore(RedisConfig.class)
public class ScriptAutoConfig {
    @Bean
    public DefaultRedisScript<Long> rollingWindowLimitScript() {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        ClassPathResource classPathResource = new ClassPathResource("lua/rollingWindowLimit.lua");
        script.setLocation(classPathResource);
        return script;
    }

    @Bean
    public DefaultRedisScript<Long> counterLimitScript() {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        ClassPathResource classPathResource = new ClassPathResource("lua/counterLimit.lua");
        script.setLocation(classPathResource);
        return script;
    }

    @Bean
    public DefaultRedisScript<Long> leakyBucketLimitScript() {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        ClassPathResource classPathResource = new ClassPathResource("lua/leakyBucketLimit.lua");
        script.setLocation(classPathResource);
        return script;
    }

    @Bean
    public DefaultRedisScript<Long> tokenBucketLimitScript() {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        ClassPathResource classPathResource = new ClassPathResource("lua/tokenBucketLimit.lua");
        script.setLocation(classPathResource);
        return script;
    }
}

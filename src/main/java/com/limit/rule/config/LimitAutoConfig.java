package com.limit.rule.config;

import com.limit.rule.aspect.LimitRuleAspect;
import com.limit.rule.core.*;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shilei
 */
@Configuration
@AutoConfigureBefore(ScriptAutoConfig.class)
public class LimitAutoConfig {

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

    @Bean
    public LimitRuleAspect limitRuleAspect() {
        return new LimitRuleAspect();
    }
}

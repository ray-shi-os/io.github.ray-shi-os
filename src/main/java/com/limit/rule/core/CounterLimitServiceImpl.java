package com.limit.rule.core;


import com.limit.rule.annotation.LimitRule;
import com.limit.rule.exception.LimitException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author shilei
 */
@Slf4j
public class CounterLimitServiceImpl implements LimitService {
    @Override
    public void handle(LimitRule limitRule, RedisTemplate<String, Object> redisTemplate, DefaultRedisScript<Long> script) {
        List<String> keys = Collections.singletonList(limitRule.resource());
        Long increment = redisTemplate.execute(script, redisSerializer, redisSerializer, keys, limitRule.time());

        if (Objects.nonNull(increment) && increment.intValue() > Integer.parseInt(limitRule.limitCount())) {
            throw new LimitException(limitRule.message());
        }
    }
}

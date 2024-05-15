package com.limit.rule.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shilei
 */

@Getter
@AllArgsConstructor
public enum LimitRuleEnum {
    /**
     * 计数器算法
     */
    COUNTER("counterLimitServiceImpl"),

    /**
     * 滑动窗口算法
     */
    ROLLING_WINDOW("rollingWindowLimitServiceImpl"),

    /**
     * 漏桶算法
     */
    LEAKY_BUCKET("leakyBucketLimitServiceImpl"),

    /**
     * 令牌桶算法
     */
    TOKEN_BUCKET("tokenBucketLimitServiceImpl"),
    ;

    private final String limitBean;
}

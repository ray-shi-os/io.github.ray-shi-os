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
    COUNTER("counterLimitServiceImpl", "counterLimitScript"),

    /**
     * 滑动窗口算法
     */
    ROLLING_WINDOW("rollingWindowLimitServiceImpl", "rollingWindowLimitScript"),

    /**
     * 漏桶算法
     */
    LEAKY_BUCKET("leakyBucketLimitServiceImpl", "leakyBucketLimitScript"),

    /**
     * 令牌桶算法
     */
    TOKEN_BUCKET("tokenBucketLimitServiceImpl", "tokenBucketLimitScript"),
    ;

    private final String limitBean;
    private final String scriptName;
}

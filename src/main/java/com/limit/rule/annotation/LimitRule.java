package com.limit.rule.annotation;


import com.limit.rule.enums.LimitRuleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitRule {
    /**
     * 限流规则
     *
     * @return LimitRuleEnum
     */
    LimitRuleEnum rule();

    /**
     * 限流资源
     *
     * @return String
     */
    String resource();

    /**
     * 限流次数
     *
     * @return int
     */
    int limitCount() default 0;

    /**
     * 时间窗口
     *
     * @return long
     */
    long time() default 0;

    /**
     * 时间单位
     *
     * @return TimeUnit
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}

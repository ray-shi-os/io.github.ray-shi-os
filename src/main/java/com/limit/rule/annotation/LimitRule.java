package com.limit.rule.annotation;


import com.limit.rule.enums.LimitRuleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shilei
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitRule {
    LimitRuleEnum rule();

    String resource();

    String limitCount() default "0";

    String time() default "0";

    String rate() default "100";

    String capacity() default "9999999999";

    String message() default "接口请求频繁，请稍后再试";


}

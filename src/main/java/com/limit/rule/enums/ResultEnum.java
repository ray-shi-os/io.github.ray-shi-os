package com.limit.rule.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author shilei
 */

@Getter
@AllArgsConstructor
public enum ResultEnum {
    LIMIT_ERROR("9999", "接口请求频发,请稍后再试"),
    ;

    private final String code;

    private final String message;
}

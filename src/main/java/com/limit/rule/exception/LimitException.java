package com.limit.rule.exception;

import com.limit.rule.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author shilei
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LimitException extends RuntimeException {
    private String code;

    private String message;

    public LimitException() {
        this.message = ResultEnum.LIMIT_ERROR.getMessage();
        this.code = ResultEnum.LIMIT_ERROR.getCode();
    }
}

package com.limit.rule.exception;

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

    public LimitException(String message) {
        super();
        this.message =message;

    }
}

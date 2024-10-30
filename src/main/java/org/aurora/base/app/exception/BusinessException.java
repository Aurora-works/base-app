package org.aurora.base.app.exception;

import lombok.Getter;
import org.aurora.base.app.common.ResultStatus;

/**
 * 自定义业务异常
 */
@Getter
public class BusinessException extends RuntimeException {

    private final ResultStatus status;

    public BusinessException(String message) {
        super(message);
        this.status = ResultStatus.FAIL;
    }

    public BusinessException(ResultStatus status) {
        super(status.getDefaultMessage());
        this.status = status;
    }

    public BusinessException(ResultStatus status, String message) {
        super(message);
        this.status = status;
    }
}

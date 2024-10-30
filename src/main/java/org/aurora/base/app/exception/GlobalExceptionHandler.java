package org.aurora.base.app.exception;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authz.UnauthenticatedException;
import org.aurora.base.app.common.Result;
import org.aurora.base.app.common.ResultStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 未经身份验证
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public Result<?> unauthenticatedException(UnauthenticatedException e) {
        log.error(e.getMessage(), e);
        return Result.fail(ResultStatus.FAIL_UNAUTHENTICATED);
    }

    /**
     * 自定义业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<?> businessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<?> runtimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getClass().getSimpleName());
    }

    @ExceptionHandler(Exception.class)
    public Result<?> exception(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail();
    }
}

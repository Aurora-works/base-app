package org.aurora.base.app.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aurora.base.app.util.IPUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

@Log4j2
@Aspect
@Component
public class AopControllerLog {

    @Pointcut("execution(* org.aurora.base.app.controller..*.*(..))")
    private void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant startTime = Instant.now();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String requestUrl = String.valueOf(request.getRequestURL());
        String requestIp = IPUtils.getIpAddr(request);
        String requestMethod = request.getMethod();
        String requestController = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()";
        String requestParameters = RequestUtils.getParams(request);
        log.info("URL:{}", requestUrl);
        log.info("IP:{}", requestIp);
        log.info("请求方式:{}", requestMethod);
        log.info("请求方法:{}", requestController);
        log.info("参数列表:{}", requestParameters);
        Object result = joinPoint.proceed();
        Duration duration = Duration.between(startTime, Instant.now());
        log.info("方法结束:{}.{}() Time taken: {} ms", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), duration.toMillis());
        return result;
    }

    @AfterThrowing(value = "controllerAspect()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("异常名称:{}", e.getClass().getName());
        // log.error("堆栈跟踪:{}", e.getMessage(), e);
        log.error("异常方法:{}.{}()", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
    }
}

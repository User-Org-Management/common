package com.user_organization_management.aspect;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Order(1)
@Component
public class LoggingAspect {
    Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.user_organization_management.service..*(..))")
    public void forServiceLog() {}

    @Pointcut("execution(* com.user_organization_management.repository..*(..))")
    public void forRepositoryLog() {}

    @Pointcut("execution(* com.user_organization_management.controller..*(..))")
    public void forControllerLog() {}

    @Pointcut("forServiceLog() || forRepositoryLog() || forControllerLog()")
    public void forAllAppLogs() {}

    @Before("forAllAppLogs()")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        log.info("=================> Method Name is >> {} ", methodName);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args ){
            log.info("====> argument >> {}", arg);
        }
    }

}

package com.tamaraw.kamustahan.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger("Execution Time");

    @Around("@annotation(com.tamaraw.kamustahan.utils.TrackExecutionTime)")
    public Object executionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        LOGGER.info("Class: {} Method: {} Execution Time: {}", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName(), endTime - startTime);

        return object;
    }

}

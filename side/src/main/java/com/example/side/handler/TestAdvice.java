package com.example.side.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component // 예시에서 왜 bean으로 등록함?
@Aspect
public class TestAdvice {

    @Pointcut("@annotation(com.example.side.util.testAOP)")
    public void testAOP() {

    }

    @Around("testAOP()")
    public Object testAOPLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("AOP 실행됨");
        return proceedingJoinPoint.proceed();
    }

}

package cn.zjh.spring.serverauth.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class AcpectJ4TestAnnotation {
    @Pointcut("@annotation(cn.zjh.spring.serverauth.auth.config.TestAnnotation)")
    private void dealAnnotation(){
        log.info("dealAnnotation is starting.....");
    }

    @Around("dealAnnotation()")
    public void beforeLog(ProceedingJoinPoint joinPoint){

        System.out.println("注解权限判断......");
    }

}

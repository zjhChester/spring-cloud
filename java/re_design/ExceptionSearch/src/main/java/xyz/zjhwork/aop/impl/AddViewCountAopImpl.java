package xyz.zjhwork.aop.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class AddViewCountAopImpl {
//    @Pointcut("@annotation(xyz.zjhwork.aop.interfaces.AddViewsCount)")
    @Pointcut("execution(* xyz.zjhwork.controller.ExceptionController.getException(..) )")
    private void do_annotation(){
        System.out.println("do_annotation ....");
    }

    @Around("do_annotation()")
    public void addViewCount(JoinPoint joinPoint){
        System.out.println("do addViewCount.....");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
    }
}

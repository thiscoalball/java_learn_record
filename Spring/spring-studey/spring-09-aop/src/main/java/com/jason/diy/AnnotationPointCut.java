package com.jason.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect//标注这个类是一个切面
@Component
public class AnnotationPointCut {

    @Before("execution(* com.jason.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("注解实现 方法执行前");
    }

    @After("execution(* com.jason.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("注解实现 方法执行后");
    }

    //在环绕增强中我们可以给一个参数 代表我们要处理切入的点
    @Around("execution(* com.jason.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");
        Object proceed = jp.proceed();
        System.out.println("环绕后");

    }
}

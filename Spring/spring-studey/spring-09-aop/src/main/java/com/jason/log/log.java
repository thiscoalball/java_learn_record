package com.jason.log;


import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class log implements MethodBeforeAdvice, AfterReturningAdvice {
    //Method 要执行的方法
    //objects 参数
    //o 目标对象
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(method.getClass().getName()+"的"+method.getName()+"被执行了");
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行了"+method.getName()+"返回了"+returnValue);
    }

}

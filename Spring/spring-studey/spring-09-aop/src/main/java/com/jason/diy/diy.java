package com.jason.diy;

import org.springframework.stereotype.Component;


public class diy {
    public void before(){
        System.out.println("方法执行前");
    }
    public void after(){
        System.out.println("方法执行后");
    }
}

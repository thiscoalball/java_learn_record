package com.jason.pojo;

import org.springframework.stereotype.Component;

@Component
public class Cat {
    private String name;
    public void say(){
        System.out.println("meow~~~");
    }
}

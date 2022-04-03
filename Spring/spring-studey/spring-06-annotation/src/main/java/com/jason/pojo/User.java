package com.jason.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//这样就等价于在xml中装配了User @Component组件
@Component
@Scope("prototype")
public class User {
    //这两个地方都能用
    @Value("jack")
    private String name;
    @Value("jack")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

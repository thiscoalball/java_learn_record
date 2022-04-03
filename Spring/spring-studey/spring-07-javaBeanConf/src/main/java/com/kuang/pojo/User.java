package com.kuang.pojo;

import org.springframework.beans.factory.annotation.Value;

public class User {
    @Value("jason")
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}

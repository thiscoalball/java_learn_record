package com.example.springbootfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//springboot核心注解 主要用于开启spring自动配置的注解
//Springboot入口的启动类
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

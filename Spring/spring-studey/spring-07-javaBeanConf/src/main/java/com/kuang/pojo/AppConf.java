package com.kuang.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.kuang.pojo")
public class AppConf {
    @Bean
    public User getUser(){
        return new User();
    }
}

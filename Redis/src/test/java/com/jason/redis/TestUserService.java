package com.jason.redis;

import com.jason.RedisApplication;
import com.jason.pojo.User;
import com.jason.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = RedisApplication.class)
@RunWith(SpringRunner.class)
public class TestUserService {
    @Autowired
    public UserService userService;

    @Test
    public void test(){
        userService.findAll().forEach(user -> System.out.println(user));
        System.out.println("===========================================================");
        userService.deleteById(3);
        userService.findAll().forEach(user -> System.out.println(user));
        System.out.println("===========================================================");
        userService.findAll().forEach(user -> System.out.println(user));

    }
}

package com.jason.redis;


import com.jason.RedisApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = RedisApplication.class)
@RunWith(SpringRunner.class)
public class TestStringRedisTemplate {
    @Autowired
    private StringRedisTemplate  stringRedisTemplate;
    @Autowired
    private TestRedisUtil redisUtil;
    @Test
    public void testString(){
        //操作的是String类型
        redisUtil.set("name","jack");
        System.out.println(redisUtil.get("name"));
    }

    @Test
    public void testHash(){
        redisUtil.hset("user","name","jason");
        redisUtil.hset("user","age",12);
        redisUtil.hset("user","hobby","game");
        Object o = redisUtil.hget("user", "age");
        System.out.println(o);
    }
}

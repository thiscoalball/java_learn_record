package com.jason.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.pojo.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class JsonController {
    @RequestMapping("/json1")
    public String json() throws JsonProcessingException {
        User user = new User("陈", 1, "123123");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(user);
    }

    @RequestMapping("/json2")
    public String json2() throws JsonProcessingException {
        User user = new User("陈", 1, "123123");
        User user2 = new User("陈", 2, "123123");
        User user3 = new User("陈", 3, "123123");
        User user4 = new User("陈", 4, "123123");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(userList);
    }

    @RequestMapping("/json3")
    public String json3() throws JsonProcessingException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String format = (String) simpleDateFormat
        ObjectMapper mapper = new ObjectMapper();
        //方式2
        mapper.setDateFormat(simpleDateFormat);
        return mapper.writeValueAsString(date);
    }

    @RequestMapping("/json4")
    public String json4(){
        User user = new User("陈", 1, "123123");
        User user2 = new User("陈", 2, "123123");
        User user3 = new User("陈", 3, "123123");
        User user4 = new User("陈", 4, "123123");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        return JSONArray.toJSONString(userList);
    }
}

package com.jason.controller;


import com.jason.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/hello")
    public String hello(Model model) {
        //封装数据
        User user = new User("陈", 1, "123123");
        model.addAttribute("msg", user.toString());
        //视图的名字
        return "hello";//会被视图解析器解析
    }
}

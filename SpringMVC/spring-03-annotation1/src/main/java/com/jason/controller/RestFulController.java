package com.jason.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestFulController {
    //这是原来的风格
    //@RequestMapping("/add")
    //http://localhost:8080/spring_03_annotation1_war_exploded/add?a=1&b=4

    //RestFul风格

    @RequestMapping("/add/{a}/{b}")
    //由于我们是get方法提交 所以也可以用这个注解
    @GetMapping("/add/{a}/{b}")
    public String test1(@PathVariable int a,@PathVariable int b, Model model){
        int res = a + b;
        model.addAttribute("msg","Get结果为"+res);
        return "test";
    }

    //所以还可以再来一个不同提交方式的
    @PostMapping("/add/{a}/{b}")
    public String test2(@RequestParam String a, @RequestParam String b, Model model){
        int res = Integer.parseInt(a)+Integer.parseInt(b);
        model.addAttribute("msg","Post结果为"+res);
        return "test";
    }

}

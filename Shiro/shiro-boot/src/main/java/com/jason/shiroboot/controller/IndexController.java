package com.jason.shiroboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/toIndex")
    public String toIndex(){
        return "index";
    }
}

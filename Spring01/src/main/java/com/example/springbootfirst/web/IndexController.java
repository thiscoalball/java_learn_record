package com.example.springbootfirst.web;

import jdk.internal.util.xml.impl.Input;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Scanner;

@Controller
public class IndexController {
    @RequestMapping(value = "/web/say")
    public @ResponseBody String say(){
       return "hello SpringBoot";

    }
}

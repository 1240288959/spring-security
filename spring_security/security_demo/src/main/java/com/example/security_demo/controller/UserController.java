package com.example.security_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@Controller
public class UserController {
    @GetMapping
    @ResponseBody
    public String getUsers(){
        return "Hello Spring Security";
    }

    @RequestMapping("/login")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/index")
    public  String toIndex(){ return "index";}
}

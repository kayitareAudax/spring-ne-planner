package com.example.planner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class TestController {
    @GetMapping("/test")
    public String testAdmin(){
        return "Admin route reached successfully";
    }
}

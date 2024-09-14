package com.gentech.springDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringFirstDemoClass {
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome To SpringBoot";
    }
}

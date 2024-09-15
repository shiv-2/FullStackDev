package com.gentech.basicPrograms.p1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrintHello {
    @GetMapping("/hello")
    public String printHello(){
        return "Hello World";
    }
}

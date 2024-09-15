package com.gentech.basicPrograms.productController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ProductControllers {
@Value("${application.version}")
private String versionApp;
@GetMapping("/idk")
public String returnVersion(){
    return versionApp+"App Version";
}
}

package com.TaskMaster.TaskTrackingSystem.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {

    @GetMapping ("Hello")
    public String HelloWorld(){
        return "HelloWorld!";
    }
}

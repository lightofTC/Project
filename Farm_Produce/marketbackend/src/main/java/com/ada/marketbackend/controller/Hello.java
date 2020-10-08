package com.ada.marketbackend.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @RequestMapping(value="/say")
    public String sayHello(){
        return "Hello Spring Boot!";
    }

}

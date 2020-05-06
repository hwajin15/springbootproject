package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    //injection
        //1. annotation ->@Autowired
        //2. constructor
        //3. setter method

    @Autowired
    private MessageSource messageSource;


    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(){
        return "GOOD Morning";

    }

    @GetMapping(path = "/hello-world")
    public String helloWorld(
            @RequestHeader(name = "Accept-Language", required = false)
            Locale locale){
        return messageSource.getMessage("greeting.message",null,locale);
    }


}

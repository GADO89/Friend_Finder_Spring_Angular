package com.spring.studentsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.studentsystem.model.AuthenticationBean;

//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/")
public class BasicAuthenticationController {

    @GetMapping("/basicAuth")
    public AuthenticationBean BasicAuthentication(){
        return new AuthenticationBean("You are Authenticated");
    }

    @GetMapping("/main")
    public String Main(){
        return "Yes";
    }
}

package com.spring.studentsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.studentsystem.model.AuthenticationBean;
import com.spring.studentsystem.model.JwtLogin;
import com.spring.studentsystem.service.TokenService;

//@CrossOrigin("http://localhost:4200")

@RestController
@RequestMapping("/")
public class Login {
    private TokenService tokenService;

    @Autowired
    public Login(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/signin")
    public AuthenticationBean login(@RequestBody JwtLogin jwtLogin){
        return new AuthenticationBean(tokenService.logString(jwtLogin));
    }
}

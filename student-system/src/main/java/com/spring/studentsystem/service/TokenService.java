package com.spring.studentsystem.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.spring.studentsystem.config.jwt.JwtProperties;
import com.spring.studentsystem.model.JwtLogin;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;


@Service
public class TokenService {

    private AuthenticationManager authenticationManager;

    @Autowired
    public TokenService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String logString(JwtLogin jwtLogin){
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtLogin.getUsername(),jwtLogin.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=createToken(authentication);
        return token;
    }

    private String createToken(Authentication authentication) {
        User user=(User)authentication.getPrincipal();

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(HMAC512(JwtProperties.SECRET.getBytes()));

        return token;
    }
}

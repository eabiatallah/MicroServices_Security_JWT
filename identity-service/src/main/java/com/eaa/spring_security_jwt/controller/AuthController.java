package com.eaa.spring_security_jwt.controller;

import com.eaa.spring_security_jwt.dto.AuthRequest;
import com.eaa.spring_security_jwt.entity.UserInfo;
import com.eaa.spring_security_jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;


    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public String addNewUser(@RequestBody UserInfo userInfo) {

        return service.addUser(userInfo);
    }

    @PostMapping("/token")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            return service.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        System.out.println(token);
        service.validateToken(token);
        return "Token is valid";
    }
}

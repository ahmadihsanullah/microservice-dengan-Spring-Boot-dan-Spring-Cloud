package com.kelaskoding.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kelaskoding.dto.AuthRequest;
import com.kelaskoding.entity.UserInfo;
import com.kelaskoding.service.JwtService;
import com.kelaskoding.service.UserInfoService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
   private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addUser(@RequestBody UserInfo user) {
        logger.info("Received registration request for user: {}", user.getName()); // Log the username
        return userInfoService.addUser(user);
    }

    @PostMapping("/generateToken")
    public String login(@RequestBody AuthRequest user) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
        if(auth.isAuthenticated()){
            return jwtService.generateToken(user.getName());
        }else{
            throw new RuntimeException("Invalid username or password");
        }
    }

    @GetMapping("/validateToken")
    public String validateToken(@RequestParam String token) {
        return jwtService.validateToken(token);
    }
}
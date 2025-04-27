package com.aryan.splitify.controller;

import com.aryan.splitify.Entity.User;
import com.aryan.splitify.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class PublicController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/health")
    public String Health(){

        return "All working fine";
    }

    @PostMapping("/signup")
    public void  CreateUser(@RequestBody User user){

        user.setFriends(new ArrayList<>());

        // 👉 Before saving, encode the password
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.saveNewUser(user);


    }
}
